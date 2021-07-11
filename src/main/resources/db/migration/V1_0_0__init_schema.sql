create sequence hibernate_sequence start 1 increment 1;
create table address_book (id int8 not null, name varchar(100) not null, user_name varchar(40) not null, primary key (id));
create table contacts (id int8 not null, name varchar(40) not null, phone varchar(10) not null, address_book_id int8 not null, primary key (id));
alter table if exists contacts add constraint FKfp0kj24fakuw35jy0t5blc16r foreign key (address_book_id) references address_book;
