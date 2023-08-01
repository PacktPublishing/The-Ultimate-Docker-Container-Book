#!/bin/bash
kubectl delete svc/web 
kubectl delete deploy/web 
kubectl delete svc/db 
kubectl delete statefulset/db 
kubectl delete pvc/pets-data-db-0
