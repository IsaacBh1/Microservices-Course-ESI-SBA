#!/bin/bash

echo "================================================"
echo "Arrêt des Microservices"
echo "================================================"
echo ""

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

stop_service() {
    local service_name=$1
    local pid_file="logs/$service_name.pid"
    
    if [ -f "$pid_file" ]; then
        local pid=$(cat "$pid_file")
        if ps -p $pid > /dev/null 2>&1; then
            echo -e "Arrêt de $service_name (PID: $pid)..."
            kill $pid
            sleep 2
            if ps -p $pid > /dev/null 2>&1; then
                echo -e "${RED}Force kill de $service_name${NC}"
                kill -9 $pid
            fi
            echo -e "${GREEN}✓ $service_name arrêté${NC}"
        else
            echo -e "$service_name n'est pas en cours d'exécution"
        fi
        rm "$pid_file"
    else
        echo -e "$service_name: Aucun fichier PID trouvé"
    fi
}

stop_service "MS-Patient"
stop_service "MS-Ordonnance"
stop_service "MS-Remboursement"

echo ""
echo "Tous les services ont été arrêtés."
