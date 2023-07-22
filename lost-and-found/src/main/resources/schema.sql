CREATE TABLE item
(
    item_id int not null auto_increment,
    item_name VARCHAR(20) NOT NULL,
    category VARCHAR(50) NOT NULL,
    found_place VARCHAR(20) NOT NULL,
    description VARCHAR(500) DEFAULT NULL,
    created_at datetime NOT NULL,
    updated_at datetime  DEFAULT NULL
);
