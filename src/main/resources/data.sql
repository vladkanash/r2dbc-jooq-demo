INSERT INTO products (id, name, quantity) VALUES
    ('1', 'PS 5', 2143),
    ('2', 'Iphone', 2143),
    ('3', 'Xbox Series X', 2143),
    ('4', 'Steam Deck', 245345),
    ('5', 'PS 5', 2143);

INSERT INTO orders (id) VALUES
    ('714ff144-612c-4cff-90d3-990fabb80fee'),
    ('54a94fe7-14f5-4075-bb21-761a63309a0e'),
    ('273ab9ec-eca5-4844-967b-47463db94da5'),
    ('f2528662-fbbd-48e9-a4cc-4c35e43ef0de'),
    ('67a4ac6e-2bee-4222-a5d4-89d0b141aea6');

INSERT INTO order_items (order_id, product_id) VALUES
    ('714ff144-612c-4cff-90d3-990fabb80fee', '1'),
    ('714ff144-612c-4cff-90d3-990fabb80fee', '3'),
    ('714ff144-612c-4cff-90d3-990fabb80fee', '5');
