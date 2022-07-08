CREATE DATABASE employee
create table employeed_worked_hours (id integer not null auto_increment, worked_date datetime, worked_hours integer, emmployee_id integer, primary key (id)) engine=MyISAM
create table employees (id integer not null auto_increment, birthdate datetime, last_name varchar(255), name varchar(255), gender_id integer, job_id integer, primary key (id)) engine=MyISAM
create table genders (id integer not null auto_increment, name varchar(255), primary key (id)) engine=MyISAM
create table jobs (id integer not null auto_increment, name varchar(255), salary float, primary key (id)) engine=MyISAM
alter table employeed_worked_hours add constraint FK9pntoxr0fyhvpv01ubm2e7raa foreign key (emmployee_id) references employees (id)
alter table employees add constraint FK2ml2wdxfngkqqur3k4bgo0gdj foreign key (gender_id) references genders (id)
alter table employees add constraint FKnpqyu6u0fdh2rmqtoue23gxb4 foreign key (job_id) references jobs (id)