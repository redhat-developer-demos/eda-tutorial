CREATE DATABASE transaction;
GRANT ALL PRIVILEGES ON transaction.* to transaction@'%' identified by 'transaction';

CREATE TABLE account (
id bigint not null primary key auto_increment, 
account_number int not null, 
active int not null
);

CREATE TABLE transaction (
id bigint not null primary key auto_increment, 
account_number int NOT NULL,
amount decimal(19,2) NOT NULL,
operation char(1) NOT NULL,
time datetime NOT NULL
);

insert into account values (NULL,123321,1);
insert into account values (NULL,123123,0);
