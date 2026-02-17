#!/bin/bash

echo "================================================"
echo "Test des Microservices - Gestion Médicale"
echo "================================================"
echo ""

# Couleurs pour l'affichage
GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Fonction pour attendre qu'un service soit prêt
wait_for_service() {
    local url=$1
    local name=$2
    echo -n "Attente du démarrage de $name..."
    for i in {1..30}; do
        if curl -s "$url" > /dev/null 2>&1; then
            echo -e "${GREEN} OK${NC}"
            return 0
        fi
        echo -n "."
        sleep 2
    done
    echo -e "${RED} TIMEOUT${NC}"
    return 1
}

echo "1. Vérification des services..."
echo "================================"

wait_for_service "http://localhost:8080/patients" "MS-Patient (8080)"
wait_for_service "http://localhost:8082/ordonnances" "MS-Ordonnance (8082)"
wait_for_service "http://localhost:8083/remboursements" "MS-Remboursement (8083)"

echo ""
echo "2. Création des données de test..."
echo "===================================="

# MS-Patient - Créer des patients
echo -e "${BLUE}Création des patients...${NC}"
curl -s -X POST http://localhost:8080/patients \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Jean Dupont",
    "numeroSecuriteSocial": "1234567890123",
    "plafondRemboursement": 5000.0
  }' > /dev/null

curl -s -X POST http://localhost:8080/patients \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Marie Martin",
    "numeroSecuriteSocial": "9876543210987",
    "plafondRemboursement": 7000.0
  }' > /dev/null

echo -e "${GREEN}✓ Patients créés${NC}"

# Créer des antécédents
curl -s -X POST http://localhost:8080/antecedentMedicals \
  -H "Content-Type: application/json" \
  -d '{
    "maladie": "Diabète Type 2",
    "dateDiagnostic": "2020-05-15",
    "patient": "http://localhost:8080/patients/1"
  }' > /dev/null

echo -e "${GREEN}✓ Antécédents médicaux créés${NC}"

# MS-Ordonnance - Créer des ordonnances
echo -e "${BLUE}Création des ordonnances...${NC}"
curl -s -X POST http://localhost:8082/ordonnances \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-01-15",
    "patientId": 1
  }' > /dev/null

curl -s -X POST http://localhost:8082/ordonnances \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-02-10",
    "patientId": 2
  }' > /dev/null

echo -e "${GREEN}✓ Ordonnances créées${NC}"

# Créer des médicaments
echo -e "${BLUE}Création des médicaments...${NC}"
curl -s -X POST http://localhost:8082/medicaments \
  -H "Content-Type: application/json" \
  -d '{
    "nomMedicament": "Doliprane 1000mg",
    "duree": 7,
    "dosage": "1 comprimé 3x/jour",
    "cout": 15.50,
    "ordonnance": "http://localhost:8082/ordonnances/1"
  }' > /dev/null

curl -s -X POST http://localhost:8082/medicaments \
  -H "Content-Type: application/json" \
  -d '{
    "nomMedicament": "Amoxicilline 500mg",
    "duree": 10,
    "dosage": "1 gélule 2x/jour",
    "cout": 25.00,
    "ordonnance": "http://localhost:8082/ordonnances/1"
  }' > /dev/null

echo -e "${GREEN}✓ Médicaments créés${NC}"

# MS-Remboursement - Créer patients assurés
echo -e "${BLUE}Création des patients assurés...${NC}"
curl -s -X POST http://localhost:8083/patientAssures \
  -H "Content-Type: application/json" \
  -d '{
    "numeroSecuriteSocial": "1234567890123",
    "nom": "Jean Dupont",
    "patientId": 1
  }' > /dev/null

echo -e "${GREEN}✓ Patients assurés créés${NC}"

# Créer des remboursements
echo -e "${BLUE}Création des remboursements...${NC}"
curl -s -X POST http://localhost:8083/remboursements \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-01-20",
    "montant": 35.50,
    "ordonnanceId": 1,
    "patientAssure": "http://localhost:8083/patientAssures/1"
  }' > /dev/null

echo -e "${GREEN}✓ Remboursements créés${NC}"

echo ""
echo "3. Tests des invocations synchrones (Q3)..."
echo "=============================================="

# Test 1: MS-Ordonnance - Ordonnance avec patient
echo -e "${BLUE}Test 1: GET /ordonnances/1/with-patient${NC}"
response=$(curl -s http://localhost:8082/ordonnances/1/with-patient)
if echo "$response" | grep -q "Jean Dupont"; then
    echo -e "${GREEN}✓ PASS - Nom du patient récupéré${NC}"
else
    echo -e "${RED}✗ FAIL - Nom du patient non trouvé${NC}"
fi

# Test 2: MS-Ordonnance - Ordonnance avec remboursement
echo -e "${BLUE}Test 2: GET /ordonnances/1/with-remboursement${NC}"
response=$(curl -s http://localhost:8082/ordonnances/1/with-remboursement)
if echo "$response" | grep -q "1234567890123" && echo "$response" | grep -q "35.5"; then
    echo -e "${GREEN}✓ PASS - NSS et montant remboursement récupérés${NC}"
else
    echo -e "${RED}✗ FAIL - Données remboursement incomplètes${NC}"
fi

# Test 3: MS-Patient GraphQL - Patient avec ordonnances
echo -e "${BLUE}Test 3: GraphQL - Patient avec ordonnances et remboursements${NC}"
graphql_query='{
  "query": "{ patientWithOrdonnances(id: 1) { id nom numeroSecuriteSocial ordonnances { idOrdonnance medicaments { nom cout } remboursement { montant date } } } }"
}'

response=$(curl -s -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d "$graphql_query")

if echo "$response" | grep -q "Jean Dupont" && echo "$response" | grep -q "Doliprane"; then
    echo -e "${GREEN}✓ PASS - Patient avec ordonnances et médicaments récupérés${NC}"
else
    echo -e "${RED}✗ FAIL - Données GraphQL incomplètes${NC}"
fi
8080
echo ""
echo "================================================"
echo "Tests terminés!"
echo "================================================"
echo ""
echo "Pour tester manuellement:"
echo "1. REST API: curl http://localhost:8082/ordonnances/1/with-patient | jq"
echo "2. GraphQL: Ouvrir http://localhost:8080/graphiql"
echo ""
echo "Consoles H2:"
echo "- MS-Patient: http://localhost:8080/h2-console"
echo "- MS-Ordonnance: http://localhost:8082/h2-console"
echo "- MS-Remboursement: http://localhost:8083/h2-console"
