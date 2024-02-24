/*
CREATE TABLE IF NOT EXISTS customer (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        first_name VARCHAR(255) NOT NULL,
                                        last_name VARCHAR(255) NOT NULL,
                                        e_mail VARCHAR(255) UNIQUE NOT NULL,
                                        phone_number VARCHAR(20),
                                        address VARCHAR(255),
                                        city VARCHAR(255),
                                        subscribe_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS product (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       brand VARCHAR(255) NOT NULL,
                                       price DECIMAL(10, 2) NOT NULL,
                                       stock BIGINT NOT NULL,
                                       adding_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    total_price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);
CREATE TABLE IF NOT EXISTS ordertable (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          customer_id INT NOT NULL,
                                          cart_id INT NOT NULL,
                                          total_price DECIMAL(10, 2) NOT NULL,
                                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                          order_code VARCHAR(20) NOT NULL,
                                          FOREIGN KEY (customer_id) REFERENCES Customer(id),
                                          FOREIGN KEY (cart_id) REFERENCES Cart(id)
);

INSERT INTO Customer (first_name, last_name, e_mail, phone_number, address, city)
VALUES
    ('John', 'Smith', 'john.smith@example.com', '1234567890', '123 Main St', 'New York'),
    ('Emily', 'Johnson', 'emily.johnson@example.com', '0987654321', '456 Elm St', 'Los Angeles'),
    ('Michael', 'Williams', 'michael.williams@example.com', '9876543210', '789 Oak St', 'Chicago'),
    ('Sarah', 'Jones', 'sarah.jones@example.com', '0123456789', '101 Pine St', 'Houston');
INSERT INTO Cart (customer_id, created_at)
SELECT id, CURRENT_TIMESTAMP
FROM Customer;
INSERT INTO Product (name, brand, price, stock)
VALUES
    ('MSI GL65 Laptop', 'MSI', 1500.00, 100),
    ('Lenovo ThinkPad X1 Carbon', 'Lenovo', 1800.00, 150),
    ('ASUS ROG Zephyrus', 'ASUS', 2000.00, 80),
    ('HP Spectre x360', 'HP', 1700.00, 120),
    ('Apple MacBook Pro', 'Apple', 2200.00, 200),
    ('Samsung Galaxy S21 Ultra', 'Samsung', 1200.00, 180),
    ('Google Pixel 6 Pro', 'Google', 1000.00, 150),
    ('OnePlus 9 Pro', 'OnePlus', 1300.00, 100),
    ('LG OLED TV', 'LG', 2500.00, 80),
    ('Sony PlayStation 5', 'Sony', 500.00, 100),
    ('Bosch Washing Machine', 'Bosch', 700.00, 150),
    ('Canon EOS R5 Camera', 'Canon', 3500.00, 50),
    ('Samsung Galaxy A52', 'Samsung', 899.99, 50),
    ('Apple MacBook Air', 'Apple', 1299.99, 30),
    ('LG OLED TV', 'LG', 1999.99, 20),
    ('Siemens Washing Machine', 'Siemens', 799.99, 40),
    ('General Mobile Android Phone', 'General Mobile', 299.99, 100);
*/
