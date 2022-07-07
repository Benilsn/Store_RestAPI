INSERT INTO tb_roles (role_name) VALUES
('ADMIN'),
('USER');

INSERT INTO tb_users (email, user_password, full_name, role_id) VALUES 
("admin@gft.com", "$2a$10$GcDctv0H9Q1uWKvBl5luteo2ehZ4AJX2rsAKjzvO.taR.TufmseAi", "Admin", (SELECT id FROM tb_roles WHERE role_name = "ADMIN")),
("user@gft.com", "$2a$10$N5v9rpvcUQP58wBv/122tuiweDKPyMP2iQZfH4RCeWDutELWs1way", "User", (SELECT id FROM tb_roles WHERE role_name = "USER"));

INSERT INTO tb_branches (branch_name, address_number, street_name, complement, zip_code, district, county, estate) VALUES
("Alphaville",500, 'Alameda Rio Negro','13° Andar','06454-000','Alphaville', 'Alphaville', 'Barueri'),
("Curitiba",2451, 'Av. Sete de Setembro','Torre Trinity Corporate 6º andar','80230-010','Curitiba ', 'Curitiba', 'Para'),
("São Paulo", 98, 'Av. São Francisco', '','18095-450','Sorocaba', 'Jardim Sta. Rosália', 'São Paulo');

INSERT INTO tb_products (product_name, product_description, product_unity) VALUES
("TV", "Smart TV", "Unity"),
("Notebook", "Notebook Dell i5 RAM 32 GB SSD", "Unity"),
("Bicycle", "KESPOR K7 Folding Bike for Adults, Women, Men, Rear Carry Rack, Front and Rear Fenders, Shimano 7 Speed Aluminum Easy Folding City Bicycle 20-inch Wheels, Disc Brake", "Unity");

INSERT INTO tb_providers (cnpj, provider_name, phone, email, street_name, address_number, complement, zip_code, district, county, estate) VALUES
("32.048.884/0001-40", "Amazon", "559551583801", "amazon@aws.com", 'Av. Fagundes Dias', 144, '3th Floor','49774','New York', '5th Avenue', 'NYC'),
("43.378.725/0001-89", "Hogwarts", "61485816993", "hogwarts@jkrolling.com", 'Rua dos Alfeneiros', 4, '3th Floor','45891','Little Whining', 'Surrey', 'London'),
("94.955.941/0001-22", "Xuei", "8618555032655", "xuei@chingling.com", 'China avenue', 82, '144th Floor','11701','China', '5th Avenue', 'Taiwan');

INSERT INTO tb_clients (cpf, client_name, phone, email, street_name, address_number, complement, zip_code, district, county, estate) VALUES
("166.602.950-54", "Carlos", "8618555032655", "carlos@hotmail.com", 'Rua Guapore', 455, '','64215-315','Parnaíba', 'Pindorama', 'PL'),
("884.991.350-80", "Aline", "413562-4252", "aline@hotmail.com", 'Avenida Itaquatiara', 132, '','68909-693','Lagoa Azul', 'Macapá', 'AP'),
("524.917.230-00", "Pedro", "832519-6764", "pedro@gmail.com", 'Rua Humberto de Almeida', 81, '','60731-415','Canindezinho', 'Fortaleza', 'CE');


