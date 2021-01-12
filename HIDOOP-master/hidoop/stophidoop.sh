#!/bin/bash

#Import des paramètres de déploiement
source config.sh

ssh -o StrictHostKeyChecking=no "$USER"@"${RM}"  << HERE1
    cd ${PWD}
    ./kill.sh
    exit
HERE1

for Node in "${Nodes[@]}"
do
  ssh -o StrictHostKeyChecking=no "$USER"@"${Node}"  << HERE2
    cd ${PWD}
    ./kill.sh
    exit
HERE2
done

