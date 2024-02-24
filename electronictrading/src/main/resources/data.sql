CREATE TABLE IF NOT EXISTS Customer (
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

CREATE TABLE IF NOT EXISTS Product (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       brand VARCHAR(255) NOT NULL,
                                       price DECIMAL(10, 2) NOT NULL,
                                       stock BIGINT NOT NULL,
                                       adding_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Cart (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS OrderTable (
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

INSERT INTO Customer (first_name, last_name, e_mail, phone_number, address, city) VALUES
                                                                                     ('John', 'Smith', 'john.smith@example.com', '1234567890', '123 Main St', 'New York'),
                                                                                     ('Emily', 'Johnson', 'emily.johnson@example.com', '0987654321', '456 Elm St', 'Los Angeles'),
                                                                                     ('Michael', 'Williams', 'michael.williams@example.com', '9876543210', '789 Oak St', 'Chicago'),
                                                                                     ('Sarah', 'Jones', 'sarah.jones@example.com', '0123456789', '101 Pine St', 'Houston');

-- Product tablosuna göre INSERT işlemleri güncellendi.
INSERT INTO Product (name, brand, price, stock, adding_date, created_at, updated_at) VALUES
                                                                                         ('MSI GL65 Laptop', 'MSI', 1500.00, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                         ('Lenovo ThinkPad X1 Carbon', 'Lenovo', 1800.00, 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                         ('ASUS ROG Zephyrus', 'ASUS', 2000.00, 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                         ('HP Spectre x360', 'HP', 1700.00, 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                         ('Apple iPhone 13 Pro', 'Apple', 1200.00, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                         ('Samsung Galaxy S21', 'Samsung', 1000.00, 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                         ('Google Pixel 6', 'Google', 1100.00, 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                         ('OnePlus OnePlus 9', 'OnePlus', 1300.00, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO Cart (created_at, updated_at) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO OrderTable (customer_id, cart_id, total_price, order_code) VALUES
                                                                           (1, 1, 2500.00, 'ORDER001'),
                                                                           (2, 1, 3500.00, 'ORDER002');
