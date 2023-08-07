-- auto-generated definition
create table sys_database_info
(
    db_num         bigserial
        primary key,
    db_name        varchar(16)                                                not null,
    db_username    varchar(16)                                                not null,
    db_password    varchar(128)                                               not null,
    db_url         varchar(128) default 'http://127.0.0.1'::character varying not null,
    db_driver_type char                                                       not null,
    db_port        varchar(8)                                                 not null
);


CREATE ROLE guest WITH LOGIN PASSWORD 'guest';
ALTER ROLE guest WITH LOGIN;
GRANT ALL PRIVILEGES ON DATABASE POSTGRES TO guest;

CREATE ROLE agent WITH LOGIN PASSWORD 'agent';
ALTER ROLE agent WITH LOGIN;
GRANT ALL PRIVILEGES ON DATABASE POSTGRES TO agent;

CREATE ROLE manager WITH LOGIN PASSWORD 'manager';
ALTER ROLE manager WITH LOGIN;
GRANT ALL PRIVILEGES ON DATABASE POSTGRES TO manager;

-- table
GRANT SELECT ON TABLE sys_database_info TO guest;
GRANT SELECT ON TABLE sys_database_info TO agent;
GRANT SELECT ON TABLE sys_database_info TO manager;
