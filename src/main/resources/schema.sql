drop table ORDERS if exists;
create table ORDERS(OID varchar NOT NULL,
SYMBOL varchar NOT NULL, SHARES int, SIDE varchar NOT NULL, ORDERTIMESTAMP varchar NOT NULL, TRADERID varchar NOT NULL, ORDERTYPE varchar NOT NULL, LIMITPRICE double);

drop table PRICE_QUOTES if exists;
create table PRICE_QUOTES(SYMBOL varchar NOT NULL, 
PRICE double, SHARES int, QUOTETIMESTAMP varchar NOT NULL);