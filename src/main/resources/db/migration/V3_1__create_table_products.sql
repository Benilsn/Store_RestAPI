CREATE TABLE IF NOT EXISTS tb_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name varchar(50) NOT NULL,
    product_description varchar(255) NOT NULL,
    product_unity varchar(50) NOT NULL
);