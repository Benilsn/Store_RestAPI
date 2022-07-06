CREATE TABLE IF NOT EXISTS tb_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    email varchar(50) NOT NULL,
    user_password varchar(255) NOT NULL,
    full_name varchar(50) NOT NULL,
    role_id BIGINT, 
    FOREIGN KEY (role_id) REFERENCES tb_roles(id)
);