CREATE TABLE IF NOT EXISTS tb_clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cpf varchar(50) NOT NULL,
    client_name varchar(50) NOT NULL,
    phone varchar(50) NOT NULL,
    email varchar (50) NOT NULL,
    street_name varchar(50) NOT NULL,
    address_number int NOT NULL,
    complement varchar(50),
    zip_code varchar(50) NOT NULL,
    district varchar(50) NOT NULL,
    county varchar(50) NOT NULL,
    estate varchar(50) NOT NULL
);