drop table if exists user_roles;
drop table if exists roles;
drop table if exists users;

create table users(
  id INTEGER not null AUTO_INCREMENT,
  email varchar(255) not null,
  password varchar(255),
  username varchar(255),
  PRIMARY KEY ( id )
);

create table roles(
  id INTEGER not null AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY ( id )
);

create table user_roles (
   user_id INT not null,
   role_id INT not null,
   primary key (user_id, role_id)
 );
