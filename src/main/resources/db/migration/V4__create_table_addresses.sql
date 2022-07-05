CREATE TABLE tb_addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address_number int NOT NULL,
    complement varchar(50),
    zip_code varchar(50) NOT NULL,
    district varchar(50) NOT NULL,
    county varchar(50) NOT NULL,
    estate varchar(50) NOT NULL
);