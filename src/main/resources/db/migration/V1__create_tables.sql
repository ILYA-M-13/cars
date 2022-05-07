drop table if exists car;
create table car(
 id integer not null auto_increment,
 car_brand varchar(255) not null,
 year_of_manufacture TINYTEXT not null,
 color TINYTEXT not null,
 number TINYTEXT not null,
 reg_time datetime not null,
 type enum('truck','passenger')default 'passenger' not null,
  primary key (id));