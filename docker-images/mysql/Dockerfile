FROM quay.io/rhdevelopers/eda-tutorial-database:latest

MAINTAINER Red Hat Developer Program

COPY mysql.cnf /etc/mysql/conf.d/
COPY *.sql /docker-entrypoint-initdb.d/
