FROM quay.io/rhdevelopers/mysql:latest

MAINTAINER Red Hat Developer Program

COPY mysql.cnf /etc/mysql/conf.d/
COPY *.sql /docker-entrypoint-initdb.d/
