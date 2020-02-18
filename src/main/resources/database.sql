CREATE DATABASE ebdb;
use ebdb;

create table weather (
   id bigint not null,
    temperature varchar(255) not null,
    city_name varchar(255) not null,
    state_name varchar(255) not null,
    latitude float(8,4) not null,
    longitude float(8,4) not null,
    date_recorded date not null,
    date_updated date not null,
    primary key (id)
)

create table hibernate_sequence (
   next_val bigint
)

insert into hibernate_sequence values ( 1 )
