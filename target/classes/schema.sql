create table clients
(
    id       serial primary key,
    login    varchar(20),
    password varchar(100),
    surname  varchar(50),
    name     varchar(50),
    cash     decimal(9, 2)
);
create table stocks
(
    id     serial primary key,
    issuer varchar(30),
    ticker varchar(30),
    quote  decimal(9, 2)
);
create table portfolios
(
    id     serial primary key,
    client_id   integer,
    stock_id    integer,
    stock_count integer,
    foreign key (client_id) references clients (id),
    foreign key (stock_id) references stocks (id)
);

-- Заполняем таблицу clients
insert into clients(login, password, surname, name, cash)
values ('thShel', 'qwerty001', 'Shelby', 'Thomas', 10000);

insert into clients(login, password, surname, name, cash)
values ('artShel', 'qwerty002', 'Shelby', 'Arthur', 4500);

insert into clients(login, password, surname, name, cash)
values ('alfSol', 'qwerty003', 'Solomons', 'Alfie', 12000);

insert into clients(login, password, surname, name, cash)
values ('bilKimb', 'qwerty004', 'Kimber', 'Billi', 3770);

insert into clients(login, password, surname, name, cash)
values ('fredThor', 'qwerty005', 'Thorne', 'Freddie', 2000);

-- Заполняем таблицу stocks
insert into stocks(issuer, ticker, quote)
values ('meta', 'FB', 310);

insert into stocks(issuer, ticker, quote)
values ('apple', 'AAPL', 163);

insert into stocks(issuer, ticker, quote)
values ('amazon', 'AMZN', 3440);

insert into stocks(issuer, ticker, quote)
values ('netflix', 'NFLX', 619);

insert into stocks(issuer, ticker, quote)
values ('alphabet', 'GOOGL', 2863);

-- Заполняем таблицу portfolios
insert into portfolios(client_id, stock_id, count)
values (1, 2, 10);
insert into portfolios(client_id, stock_id, count)
values (1, 3, 5);
insert into portfolios(client_id, stock_id, count)
values (1, 5, 7);

insert into portfolios(client_id, stock_id, count)
values (2, 1, 7);
insert into portfolios(client_id, stock_id, count)
values (2, 2, 4);
insert into portfolios(client_id, stock_id, count)
values (2, 4, 5);

insert into portfolios(client_id, stock_id, count)
values (3, 3, 10);
insert into portfolios(client_id, stock_id, count)
values (3, 4, 5);
insert into portfolios(client_id, stock_id, count)
values (3, 1, 4);

insert into portfolios(client_id, stock_id, count)
values (4, 1, 15);
insert into portfolios(client_id, stock_id, count)
values (4, 2, 1);
insert into portfolios(client_id, stock_id, count)
values (4, 4, 3);

insert into portfolios(client_id, stock_id, count)
values (5, 3, 6);
insert into portfolios(client_id, stock_id, count)
values (5, 4, 9);
insert into portfolios(client_id, stock_id, count)
values (5, 5, 11);

--ALTER TABLE portfolios DROP COLUMN sum;
--ALTER TABLE portfolios DROP COLUMN id;
-- ALTER TABLE portfolios ADD COLUMN id serial primary key;

Select p.id, p.client_id, issuer, ticker, quote, count
from stocks s
inner join portfolios p on s.id = p.stock_id
and p.client_id = 1
order by p.client_id;

insert into portfolios(client_id, stock_id, count)
values (37, 2, 6);