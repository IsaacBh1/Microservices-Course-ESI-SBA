#!/bin/bash

echo "================================================"
echo "Démarrage des Microservices"
echo "================================================"
echo ""

# Couleurs
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m'

# Fonction pour vérifier si un port est utilisé
check_port() {
    if lsof -Pi :$1 -sTCP:LISTEN -t >/dev/null 2>&1 ; then
        echo -e "${YELLOW}⚠ Port $1 est déjà utilisé${NC}"
        return 1
    fi
    return 0
}

# Vérifier les ports
echo "Vérification des ports..."
check_port 8080 || echo "  MS-Patient pourrait ne pas démarrer"
check_port 8082 || echo "  MS-Ordonnance pourrait ne pas démarrer"
check_port 8083 || echo "  MS-Remboursement pourrait ne pas démarrer"
echo ""

# Fonction pour démarrer un service
start_service() {
    local service_dir=$1
    local service_name=$2
    local port=$3
    
    echo -e "${BLUE}Démarrage de $service_name sur le port $port...${NC}"
    cd "$service_dir"
    ./mvnw spring-boot:run > "../logs/$service_name.log" 2>&1 &
    echo $! > "../logs/$service_name.pid"
    cd ..
    echo -e "${GREEN}✓ $service_name démarré (PID: $(cat logs/$service_name.pid))${NC}"
}

# Créer le dossier logs
mkdir -p logs

# Démarrer les services
echo "Démarrage des services..."
echo "========================="

start_service "ms-patient" "MS-Patient" "8080"
sleep 5

start_service "ms-ordonnance" "MS-Ordonnance" "8082"
sleep 5

start_service "ms-remboursement" "MS-Remboursement" "8083"
sleep 5

echo ""
echo "================================================"
echo "Services démarrés!"
echo "================================================"
echo ""
echo "URLs des services:"
echo "- MS-Patient (GraphQL): http://localhost:8080/graphiql"
echo "- MS-Ordonnance (REST): http://localhost:8082/ordonnances"
echo "- MS-Remboursement (REST): http://localhost:8083/remboursements"
echo ""
echo "Consoles H2:"
echo "- MS-Patient: http://localhost:8080/h2-console"
echo "- MS-Ordonnance: http://localhost:8082/h2-console"
echo "- MS-Remboursement: http://localhost:8083/h2-console"
echo ""
echo "Logs disponibles dans le dossier 'logs/'"
echo ""
echo "Pour arrêter tous les services, exécutez: ./stop-services.sh"
