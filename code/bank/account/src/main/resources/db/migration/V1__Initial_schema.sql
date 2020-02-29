CREATE TABLE account
(
  id             bigint not null primary key auto_increment,
  account_number int    not null unique,
  active         int    not null
);

CREATE TABLE balance
(
  id             bigint         not null primary key auto_increment,
  account_number int            NOT NULL unique,
  amount         decimal(19, 2) NOT NULL
);

insert into account
values (NULL, 123321, 1);
insert into account
values (NULL, 123123, 0);