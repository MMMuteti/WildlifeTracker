CREATE DATABASE wildlife_tracker;

CREATE TABLE sightings(
id serial PRIMARY KEY,
wild_life_id int,zone varchar,
ranger_name varchar,time timestamp);

CREATE TABLE animals(
id serial PRIMARY KEY,
name varchar,type varchar,
age varchar,health varchar);
