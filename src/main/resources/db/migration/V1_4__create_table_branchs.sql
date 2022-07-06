CREATE TABLE tb_branches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_name varchar(50) NOT NULL,
    street_name varchar(50) NOT NULL,
    address_number int NOT NULL,
    complement varchar(50),
    zip_code varchar(50) NOT NULL,
    district varchar(50) NOT NULL,
    county varchar(50) NOT NULL,
    estate varchar(50) NOT NULL
);