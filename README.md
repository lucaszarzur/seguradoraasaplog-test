## O sistema
O sistema "seguradoraasaplog-test" consiste em uma API REST, realizada na linguagem Java.

O sistema contém duas telas, uma chamada "ADMINISTRAR CLIENTES" e outra chamada "ADMINISTRAR APÓLICES". Cada uma tem a função de cadastrar (create), listar (read), atualizar (update), e deletar (delete) clientes, e apólices, respectivamente.

O sistema possui os seguintes links:

API
Clientes:
- /api/customers/create             - Criar cliente
- /api/customers/get                - Listar cliente pelo nome
- /api/customers/getAllCustomers    - Listar todos os clientes
- /api/customers/update             - Atualizar um cliente
- /api/customers/update             - Deletar um cliente pelo id
- /api/customers/deleteAllCustomers - Deletar todos os clientes

Apólices:
- /api/insurance-policies/create                     - Criar apólice
- /api/insurance-policies/get                        - Listar apólice pelo número
- /api/insurance-policies/getAllInsurancePolicies    - Listar todas as apólices
- /api/insurance-policies/update                     - Atualizar uma apólice
- /api/insurance-policies/delete                     - Deletar uma apólice pelo id
- /api/insurance-policies/deleteAllInsurancePolicies - Deletar todos as apólices



SISTEMA ADMIN
Clientes:
- /admin/customers          - Página para administrar clientes
- /admin/insurance-policies - Página para administrar apólices


## Tecnologias utilizadas
- Java
- Spring (Spring Boot, Spring MVC)
- MongoDB
- Spring Security (to-do)
- Thymeleaf
- Gradle


## Começando

# Pré-requisitos
- Garanta que você possui o JDK 11 instalado
- Alguma IDE instalada, pode ser o Eclipse, Spring Tools Suite ou IntelliJ (preferencial)
- Banco de dados MongoDB instalado

# Passo a passo
- Clone este repositório;
- Vá até o arquivo "application.properties" no caminho "seguradoraasaplog-test/src/main/resources" e garanta que as configurações do seu banco de dados estejam corretas;
- Crie a collection "counters" dentro da database "insurance" para servir o contador para o ID da apólice;
- Insira os datas: _id:"customers", seq:0
- Execute o build do projeto na IDE através do Gradle.


## Links úteis

- Instalação MondoDB Linux (https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/):
wget -qO - https://www.mongodb.org/static/pgp/server-4.2.asc | sudo apt-key add -
wget -qO - https://www.mongodb.org/static/pgp/server-4.2.asc | sudo apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu bionic/mongodb-org/4.2 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.2.list
sudo apt-get install -y mongodb-org
sudo systemctl start mongod
sudo systemctl status mongod

- Instalação do Compass (interface gráfica do MongoDB) - https://www.mongodb.com/try/download/compass
