FROM mysql:5.7

MAINTAINER Red Hat Developer Program

COPY mysql.cnf /etc/mysql/conf.d/
COPY customer.sql /docker-entrypoint-initdb.d/
COPY preference.sql /docker-entrypoint-initdb.d/
COPY recommendation.sql /docker-entrypoint-initdb.d/
