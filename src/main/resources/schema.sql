CREATE TABLE IF NOT EXISTS PRICE (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     brand_id INT,
                                     start_date DATETIME,
                                     end_date DATETIME,
                                     price_list INT,
                                     product_id INT,
                                     priority INT,
                                     price DOUBLE,
                                     currency VARCHAR(3)
    );