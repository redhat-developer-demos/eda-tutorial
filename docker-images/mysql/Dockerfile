FROM mysql:5.7

MAINTAINER Red Hat Developer Program

COPY mysql.cnf /etc/mysql/conf.d/
COPY *.sql /docker-entrypoint-initdb.d/
