INSERT INTO tb_branches (branch_name, address_id) VALUES
(
    "Alphaville",
    (
        SELECT id
        FROM tb_addresses
        WHERE id = 1
    )
),
(
    "Curitiba",
    (
        SELECT id
        FROM tb_addresses
        WHERE id = 2
    )
),
(
    "Sorocaba",
    (
        SELECT id
        FROM tb_addresses
        WHERE id = 3
    )
);