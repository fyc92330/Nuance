-- auto-generated definition
create table sys_database_info
(
    db_num         bigserial
        primary key,
    db_name        varchar(16)                                                not null,
    db_username    varchar(16)                                                not null,
    db_password    varchar(128)                                               not null,
    db_url         varchar(128) default '127.0.0.1'::character varying not null,
    db_driver_type char                                                       not null,
    db_port        varchar(8)                                                 not null
);


CREATE ROLE guest WITH LOGIN PASSWORD 'guest';
ALTER ROLE guest WITH LOGIN;
GRANT ALL PRIVILEGES ON DATABASE POSTGRES TO guest;
GRANT CONNECT ON DATABASE postgres TO guest;
GRANT USAGE ON SCHEMA public TO guest;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO guest;

CREATE ROLE agent WITH LOGIN PASSWORD 'agent';
ALTER ROLE agent WITH LOGIN;
GRANT ALL PRIVILEGES ON DATABASE POSTGRES TO agent;
GRANT CONNECT ON DATABASE postgres TO agent;
GRANT USAGE ON SCHEMA public TO agent;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO agent;

CREATE ROLE manager WITH LOGIN PASSWORD 'manager';
ALTER ROLE manager WITH LOGIN;
GRANT ALL PRIVILEGES ON DATABASE POSTGRES TO manager;
GRANT CONNECT ON DATABASE postgres TO manager;
GRANT USAGE ON SCHEMA public TO manager;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO manager;

