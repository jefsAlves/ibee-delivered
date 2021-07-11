set foreign_key_checks = 0;

DELETE FROM tb_city;
DELETE FROM tb_group;
DELETE FROM tb_group_permission;
DELETE FROM tb_kitchen;
DELETE FROM tb_payments;
DELETE FROM tb_permissions;
DELETE FROM tb_products;
DELETE FROM tb_restaurant;
DELETE FROM tb_restaurant_payment;
DELETE FROM tb_state;
DELETE FROM tb_user_group;
DELETE FROM tb_users;

set foreign_key_checks = 1;

ALTER TABLE tb_city auto_increment = 1;
ALTER TABLE tb_group auto_increment = 1;
ALTER TABLE tb_kitchen auto_increment = 1;
ALTER TABLE tb_payments auto_increment = 1;
ALTER TABLE tb_permissions auto_increment = 1;
ALTER TABLE tb_products auto_increment = 1;
ALTER TABLE tb_restaurant auto_increment = 1;
ALTER TABLE tb_state auto_increment = 1;

INSERT INTO tb_kitchen (id, name) VALUES (1, 'American');
INSERT INTO tb_kitchen (id, name) VALUES (2, 'Portuguese');
INSERT INTO tb_kitchen (id, name) VALUES (3, 'Indian');
INSERT INTO tb_kitchen (id, name) VALUES (4, 'Mexican');
INSERT INTO tb_kitchen (id, name) VALUES (5, 'Japan');

INSERT INTO tb_restaurant (id, freigth_rate, name, address_cep, address_street, address_number, address_complement, address_neighborhood, create_date, update_date, kitchen_id, active_status) VALUES (1, 5.50, 'MustBig', '98438943', 'times square', '843', '', 'manhattan', utc_timestamp, utc_timestamp, 1, 1);
INSERT INTO tb_restaurant (id, freigth_rate, name, address_cep, address_street, address_number, address_complement, address_neighborhood, create_date, update_date, kitchen_id, active_status) VALUES (2, 3.12, 'Boizao', '43094309', 'marginal', '32', '', 'santana', utc_timestamp, utc_timestamp, 2, 1);
INSERT INTO tb_restaurant (id, freigth_rate, name, address_cep, address_street, address_number, address_complement, address_neighborhood, create_date, update_date, kitchen_id, active_status) VALUES (3, 6.33, 'All Quijash', '98438394', 'mounati raion', '43', '', 'mounrth oian', utc_timestamp, utc_timestamp, 3, 1);
INSERT INTO tb_restaurant (id, freigth_rate, name, address_cep, address_street, address_number, address_complement, address_neighborhood, create_date, update_date, kitchen_id, active_status) VALUES (4, 2.71, 'Del Tacho', '98329483', 'mimaote', '952', '', 'maiore', utc_timestamp, utc_timestamp, 4, 1);
INSERT INTO tb_restaurant (id, freigth_rate, name, address_cep, address_street, address_number, address_complement, address_neighborhood, create_date, update_date, kitchen_id, active_status) VALUES (5, 5.05, 'Xiu Jiaum', '98587394', 'jain xuam', '842', '', 'xaum saokm', utc_timestamp, utc_timestamp, 5, 1);

INSERT INTO tb_payments (id, description) VALUES (1, 'card credit');
INSERT INTO tb_payments (id, description) VALUES (2, 'card debit');
INSERT INTO tb_payments (id, description) VALUES (3, 'money');

INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (1, 1);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (1, 2);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (2, 1);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (2, 2);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (2, 3);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (3, 2);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (3, 3);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (4, 3);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (5, 1);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (5, 2);
INSERT INTO tb_restaurant_payment (restaurant_id, payment_id) VALUES (5, 3);

INSERT INTO tb_state (id, name) VALUES (1, 'New York');
INSERT INTO tb_state (id, name) VALUES (2, 'Sao Paulo');
INSERT INTO tb_state (id, name) VALUES (3, 'Querala');
INSERT INTO tb_state (id, name) VALUES (4, 'Quitana Roo');
INSERT INTO tb_state (id, name) VALUES (5, 'Toquio');

INSERT INTO tb_city (id, name, state_id) VALUES (1, 'New York', 1);
INSERT INTO tb_city (id, name, state_id) VALUES (2, 'Sao Paulo', 2);
INSERT INTO tb_city (id, name, state_id) VALUES (3, 'Cochin', 3);
INSERT INTO tb_city (id, name, state_id) VALUES (4, 'Cancun', 4);
INSERT INTO tb_city (id, name, state_id) VALUES (5, 'Chinjuku', 5);

INSERT INTO tb_products (id, description, price, active, restaurant_id) VALUES (1, 'incridible bacons', 12.3, true, 1);

INSERT INTO tb_permissions (id, description) VALUES (1, 'admin'); 
INSERT INTO tb_permissions (id, description) VALUES (2, 'user'); 

INSERT INTO tb_group (id, name) VALUES (1, 'clients');
INSERT INTO tb_group (id, name) VALUES (2, 'clients_golden');

INSERT INTO tb_group_permission (group_id, permission_id) VALUES (1, 1);
INSERT INTO tb_group_permission (group_id, permission_id) VALUES (1, 2);
INSERT INTO tb_group_permission (group_id, permission_id) VALUES (2, 2);

INSERT INTO tb_users (id, user, password, create_date, update_date) VALUES (1, 'jonh@gmail.com', '122345', utc_timestamp, utc_timestamp);
INSERT INTO tb_users (id, user, password, create_date, update_date) VALUES (2, 'bob@gmail.com', '122345', utc_timestamp, utc_timestamp);

INSERT INTO tb_user_group (user_id, group_id) VALUES (1, 1);
INSERT INTO tb_user_group (user_id, group_id) VALUES (2, 1);