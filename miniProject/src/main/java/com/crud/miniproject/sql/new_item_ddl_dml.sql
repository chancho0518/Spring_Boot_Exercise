USE mini_project;

CREATE TABLE store_sales (
	id INT AUTO_INCREMENT PRIMARY KEY,
    store_name VARCHAR(30),
    amount INT NOT NULL DEFAULT 0 CHECK(amount >= 0)
);

CREATE TABLE new_items (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    type VARCHAR(20) NOT NULL,
    price INT,
    store_id INT,
    stock INT NOT NULL DEFAULT 0 CHECK(stock >= 0),
    cpu VARCHAR(30),
    capacity VARCHAR(30)
);

ALTER table new_items ADD FOREIGN KEY(store_id) REFERENCES store_sales(id);  

INSERT INTO store_sales(store_name, amount)
VALUES
    ('Store A', 10000),
    ('Store B', 15000),
    ('Store C', 20000),
    ('Store D', 25000),
    ('Store E', 30000);

INSERT INTO new_items(name, type, price, store_id, stock, cpu, capacity)
VALUES
    ('Apple iPhone 12 Pro Max', 'Smartphone', 1490000, 1, 100, 'A14 Bionic', '512GB'),
    ('Samsung Galaxy S21 Ultra', 'Smartphone', 1690000, 2, 80, 'Exynos 2100', '256GB'),
    ('Google Pixel 6 Pro', 'Smartphone', 1290000, 3, 120, 'Google Tensor', '128GB'),
    ('Dell XPS 15', 'Laptop', 2290000, 4, 50, 'Intel Core i9', '1TB SSD'),
    ('Sony Alpha 7 III', 'Mirrorless Camera', 2590000, 5, 60, 'BIONZ X', 'No internal storage'),
    ('Microsoft Xbox Series X', 'Gaming Console', 499000, 1, 30, 'Custom AMD Zen 2', '1TB SSD'),
    ('iPad Air', 'Tablet', 849000, 2, 70, 'A14 Boinic', '512GB'),
    ('MacBook Pro', 'Laptop', 1790000, 3, 40, 'Apple M1', '1TB SSD');