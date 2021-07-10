create sequence hibernate_sequence start 1 increment 1
create table address_book (id int8 not null, create_timestamp timestamp, name varchar(100) not null, user_id int8 not null, primary key (id))
create table contact (id int8 not null, name varchar(20) not null, phone int4 not null, address_book_id int8 not null, primary key (id))
create table user (id int8 not null, create_timestamp timestamp, name varchar(20) not null, primary key (id))
alter table if exists address_book add constraint FKtonvl3iscjgwmgrgehbyhhaok foreign key (user_id) references user
alter table if exists contact add constraint FK2d66c8jl8etfnc7rlcak1qqww foreign key (address_book_id) references address_book
