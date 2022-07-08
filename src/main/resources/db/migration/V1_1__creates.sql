CREATE TABLE IF NOT EXISTS tb_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name varchar(10) NOT NULL
);

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

CREATE TABLE IF NOT EXISTS tb_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    email varchar(50) NOT NULL,
    user_password varchar(255) NOT NULL,
    full_name varchar(50) NOT NULL,
    branch_id BIGINT,
    role_id BIGINT, 
    FOREIGN KEY (role_id) REFERENCES tb_roles(id),
    FOREIGN KEY (branch_id) REFERENCES tb_branches(id)
);

CREATE TABLE IF NOT EXISTS tb_providers ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cnpj varchar(50) NOT NULL,
    provider_name varchar(50) NOT NULL,
    phone varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    street_name varchar(50) NOT NULL,
    address_number int NOT NULL,
    complement varchar(50),
    zip_code varchar(50) NOT NULL,
    district varchar(50) NOT NULL,
    county varchar(50) NOT NULL,
    estate varchar(50) NOT NULL
);

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

CREATE TABLE IF NOT EXISTS tb_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name varchar(50) NOT NULL,
    product_description varchar(255) NOT NULL,
    product_unity varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_stocks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_id BIGINT,
    FOREIGN KEY (branch_id) REFERENCES tb_branches(id)
);

CREATE TABLE IF NOT EXISTS tb_stock_products (
    stock_id BIGINT,
    product_id BIGINT,
    FOREIGN KEY (stock_id) REFERENCES tb_stocks(id),
    FOREIGN KEY (product_id) REFERENCES tb_products(id)
);

CREATE TABLE IF NOT EXISTS tb_purchases(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_id BIGINT,
    provider_id BIGINT,
    FOREIGN KEY (branch_id) REFERENCES tb_branches(id),
    FOREIGN KEY (provider_id) REFERENCES tb_providers(id)
);

CREATE TABLE IF NOT EXISTS tb_sales(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_id BIGINT,
    client_id BIGINT,
    FOREIGN KEY (branch_id) REFERENCES tb_branches(id),
    FOREIGN KEY (client_id) REFERENCES tb_clients(id)
);

CREATE TABLE IF NOT EXISTS  tb_purchased_itens(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT,
    product_id BIGINT NOT NULL,
    amount INT NOT NULL,
    trade_value DECIMAL NOT NULL,
    FOREIGN KEY (purchase_id) REFERENCES tb_purchases(id),
    FOREIGN KEY (product_id) REFERENCES tb_products(id)
);

CREATE TABLE IF NOT EXISTS  tb_sold_itens(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sale_id BIGINT,
    product_id BIGINT NOT NULL,
    amount INT NOT NULL,
    trade_value DECIMAL NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES tb_sales(id),
    FOREIGN KEY (product_id) REFERENCES tb_products(id)
);