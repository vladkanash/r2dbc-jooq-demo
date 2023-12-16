CREATE TABLE products (
    id text not null primary key,
    name text not null,
    quantity int not null
);

CREATE TABLE orders (
    id text not null primary key
);

CREATE TABLE order_items (
    order_id text not null references orders(id),
    product_id text not null references products(id)
);
