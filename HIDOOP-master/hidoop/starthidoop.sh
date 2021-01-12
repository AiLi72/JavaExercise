#!/bin/bash

#Import des paramètres de déploiement
source config.sh

javac -cp doc/jsoup-1.13.1.jar -d bin src/*/*.java src/*/*/*.java

ssh -o StrictHostKeyChecking=no "$USER"@"${RM}"  << HERE1
    cd ${PWD}
    ./deployment.sh RM
    exit
HERE1

for Node in "${Nodes[@]}"
do
  ssh -o StrictHostKeyChecking=no "$USER"@"${Node}"  << HERE2
    cd ${PWD}
    ./deployment.sh NM
    exit
HERE2
done


