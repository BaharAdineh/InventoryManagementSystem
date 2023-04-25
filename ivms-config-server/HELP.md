# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/#build-image)
* [Config Server](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_spring_cloud_config_server)

### Guides
The following guides illustrate how to use some features concretely:

* [Centralized Configuration](https://spring.io/guides/gs/centralized-configuration/)


openssl genrsa -des3 -out rootCA.key 2048
openssl req -x509 -new -nodes -key rootCA.key -sha256 -days 1825 -out rootCA.pem
openssl genrsa -des3 -out server.key 2048
openssl req -new -sha256 -key server.key -out server.csr
openssl genrsa -des3 -out client.key 2048
openssl req -new -sha256 -key client.key -out client.csr
openssl x509 -req -in server.csr -CA rootCA.pem -CAkey rootCA.key -CAcreateserial -out server.pem -days 365 -sha256
openssl pkcs12 -export -in server.pem -out keystore.p12 -name server -nodes -inkey server.key

docker run -d --hostname rabbitmq -p 5672:5672 rabbitmq
