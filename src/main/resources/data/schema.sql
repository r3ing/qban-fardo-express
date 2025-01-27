-- PostgreSQL Script
-- Modelo: qban-fardo-express

-- Deshabilitar temporalmente las verificaciones de claves únicas y externas
SET session_replication_role = 'replica';

-- Crear esquema
CREATE SCHEMA IF NOT EXISTS qban_fardo_express;

-- Cambiar al esquema
SET search_path TO qban_fardo_express;

-- Crear tabla "rol"
CREATE TABLE IF NOT EXISTS rol (
                                   id_rol SERIAL PRIMARY KEY,
                                   name VARCHAR(45) NOT NULL
);

-- Crear tabla "branch"
CREATE TABLE IF NOT EXISTS branch (
                                      id_branch SERIAL PRIMARY KEY,
                                      phone VARCHAR(45),
                                      address TEXT,
                                      state VARCHAR(45),
                                      email VARCHAR(45)
);

-- Crear tabla "user"
CREATE TABLE IF NOT EXISTS users (
                                     id_user SERIAL PRIMARY KEY,
                                     username VARCHAR(45) NOT NULL,
                                     password VARCHAR(45) NOT NULL,
                                     enabled BOOLEAN NOT NULL DEFAULT TRUE,
                                     phone BIGINT,
                                     id_branch INT NOT NULL,
                                     FOREIGN KEY (id_branch) REFERENCES branch (id_branch) ON DELETE CASCADE
);

-- Crear tabla "route"
CREATE TABLE IF NOT EXISTS route (
                                     id_route SERIAL PRIMARY KEY,
                                     route_url VARCHAR(45) NOT NULL
);

-- Crear tabla "route_has_rol"
CREATE TABLE IF NOT EXISTS route_has_rol (
                                             id_route_role SERIAL PRIMARY KEY,
                                             id_route INT NOT NULL,
                                             id_rol INT NOT NULL,
                                             FOREIGN KEY (id_route) REFERENCES route (id_route) ON DELETE CASCADE,
                                             FOREIGN KEY (id_rol) REFERENCES rol (id_rol) ON DELETE CASCADE
);

-- Crear tabla "province"
CREATE TABLE IF NOT EXISTS province (
                                        id_province SERIAL PRIMARY KEY,
                                        name VARCHAR(45) NOT NULL
);

-- Crear tabla "town"
CREATE TABLE IF NOT EXISTS town (
                                    id_town SERIAL PRIMARY KEY,
                                    name VARCHAR(45) NOT NULL,
                                    id_province INT NOT NULL,
                                    FOREIGN KEY (id_province) REFERENCES province (id_province) ON DELETE CASCADE
);

-- Crear tabla "shipping_status"
CREATE TABLE IF NOT EXISTS shipping_status (
                                               id_shipping_status SERIAL PRIMARY KEY,
                                               status VARCHAR(45) NOT NULL
);

-- Crear tabla "customer"
CREATE TABLE IF NOT EXISTS customer (
                                        id_customer SERIAL PRIMARY KEY,
                                        name VARCHAR(45) NOT NULL,
                                        last_name VARCHAR(45) NOT NULL,
                                        phone VARCHAR(45)
);

-- Crear tabla "address"
CREATE TABLE IF NOT EXISTS address (
                                       id_address SERIAL PRIMARY KEY,
                                       beneficiary VARCHAR(45) NOT NULL,
                                       ci BIGINT,
                                       phone BIGINT,
                                       street TEXT,
                                       number VARCHAR(45),
                                       between_street TEXT,
                                       locality VARCHAR(45),
                                       ref TEXT,
                                       id_town INT NOT NULL,
                                       id_customer INT NOT NULL,
                                       FOREIGN KEY (id_town) REFERENCES town (id_town) ON DELETE CASCADE,
                                       FOREIGN KEY (id_customer) REFERENCES customer (id_customer) ON DELETE CASCADE
);

-- Crear tabla "rol_has_user"
CREATE TABLE IF NOT EXISTS user_has_role (
                                            id_rol_user SERIAL PRIMARY KEY,
                                            id_rol INT NOT NULL,
                                            id_user INT NOT NULL,
                                            FOREIGN KEY (id_rol) REFERENCES rol (id_rol) ON DELETE CASCADE,
                                            FOREIGN KEY (id_user) REFERENCES users (id_user) ON DELETE CASCADE
);

-- Crear tabla "shipping"
CREATE TABLE IF NOT EXISTS shipping (
                                        id_shipping SERIAL PRIMARY KEY,
                                        tracking_number VARCHAR(45),
                                        weight DECIMAL(10, 2),
                                        amount DECIMAL(10, 2),
                                        create_date TIMESTAMP NOT NULL DEFAULT NOW(),
                                        details TEXT,
                                        articles TEXT,
                                        id_address INT NOT NULL,
                                        id_user INT NOT NULL,
                                        id_shipping_status INT NOT NULL,
                                        FOREIGN KEY (id_address) REFERENCES address (id_address) ON DELETE CASCADE,
                                        FOREIGN KEY (id_user) REFERENCES users (id_user) ON DELETE CASCADE,
                                        FOREIGN KEY (id_shipping_status) REFERENCES shipping_status (id_shipping_status) ON DELETE SET NULL
);

-- Restaurar las verificaciones de claves únicas y externas
SET session_replication_role = 'origin';


-- DATABASE SETUP
INSERT INTO qban_fardo_express.rol(id_rol, name) VALUES
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_EXECUTIVE'),
    (3, 'ROLE_DELIVERY');

INSERT INTO qban_fardo_express.route (id_route, route_url) VALUES
    (1, '/home.xhtml'),
    (2, '/login.xhtml'),
    (3, '/admin/**'),
    (4, '/executive/**'),
    (5, '/delivery/**');

INSERT INTO qban_fardo_express.route_has_rol (id_route_role, id_route, id_rol) VALUES
   (1, 1, 1),
   (2, 3, 1),
   (3, 4, 1),
   (4, 5, 1),
   (5, 4, 2),
   (6, 5, 3);

