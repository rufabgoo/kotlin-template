--liquibase formatted sql logicalFilePath:V2_002_EXAMPLE.sql

--changeset rufabgoo:example-01
create table ref.example (
  example_id bigint not null,
  example_name varchar(255) not null,
  created_by varchar(50) not null,
  creation_date timestamp without time zone not null,
  last_updated_by varchar(50) not null,
  last_update_date timestamp without time zone not null,
  object_version_number bigint not null
);

alter table ref.example add constraint example_pk primary key (example_id);