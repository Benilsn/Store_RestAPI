CREATE TABLE tb_branches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_name varchar(50) NOT NULL,
    address_id BIGINT, 
    FOREIGN KEY (address_id ) REFERENCES tb_addresses(id)
);