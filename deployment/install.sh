function deployMongoDB() {
    kubectl apply -f https://raw.githubusercontent.com/mongodb/mongodb-kubernetes-operator/master/config/crd/bases/mongodbcommunity.mongodb.com_mongodbcommunity.yaml
    kubectl apply -f https://raw.githubusercontent.com/mongodb/mongodb-kubernetes-operator/master/config/rbac/role.yaml
    kubectl apply -f https://raw.githubusercontent.com/mongodb/mongodb-kubernetes-operator/master/config/rbac/role_database.yaml
    kubectl apply -f https://raw.githubusercontent.com/mongodb/mongodb-kubernetes-operator/master/config/rbac/service_account.yaml
    kubectl apply -f https://raw.githubusercontent.com/mongodb/mongodb-kubernetes-operator/master/config/rbac/service_account_database.yaml
    kubectl apply -f https://raw.githubusercontent.com/mongodb/mongodb-kubernetes-operator/master/config/rbac/role_binding.yaml
    kubectl apply -f https://raw.githubusercontent.com/mongodb/mongodb-kubernetes-operator/master/config/rbac/role_binding_database.yaml

    kubectl create -f https://raw.githubusercontent.com/mongodb/mongodb-kubernetes-operator/master/config/manager/manager.yaml
    kubectl create secret generic inventory-user-password -n inventory-management --from-literal="password=TXs3ZsuIqT-pQFvwxOec"

    kubectl apply -f ./mongodb.yaml
}

function deployKafka(){
  kubectl apply -f ./kafka.yaml
}

function deployIMS(){
  kubectl apply -f ./ims.yaml
  kubectl apply -f ./ingess.yaml
}


kubectl apply -f ./namespace.yaml
kubectl config set-context $(kubectl config current-context) --namespace=inventory-management

deployMongoDB
deployKafka
sleep 60
deployIMS
