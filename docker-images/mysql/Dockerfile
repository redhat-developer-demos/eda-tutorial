FROM mysql:8.0

MAINTAINER Red Hat Developer Program

COPY mysql.cnf /etc/mysql/conf.d/
COPY *.sql /docker-entrypoint-initdb.d/
