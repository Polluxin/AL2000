#!/bin/bash

# Ouvre un tunnel ssh et redirige les E/S sur le port 1521 de localhost
ssh -N -L 1521:im2ag-oracle.univ-grenoble-alpes.fr:1521 greniera@im2ag-oracle.univ-grenoble-alpes.fr