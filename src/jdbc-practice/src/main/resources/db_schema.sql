DROP table if exists USERS;

create table USERS(
    userid  varchar(12) NOT NULL,
    password varchar(12) NOT NULL,
    name varchar(20) NOT NULL,
    email varchar(50),

    primary key (userid)
)