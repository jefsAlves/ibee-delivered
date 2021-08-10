create table tb_city (id bigint not null auto_increment, name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table tb_group (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table tb_group_permission (group_id bigint not null, permission_id bigint not null, primary key (group_id, permission_id)) engine=InnoDB
create table tb_kitchen (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_order (id bigint not null auto_increment, address_cep varchar(255), address_complement varchar(255), address_neighborhood varchar(255), address_number varchar(255), address_street varchar(255), amount decimal(19,2) not null, cancelled_date datetime(6), confirmation_date datetime(6), create_date datetime(6) not null, delivered_date datetime(6), freigth_rate decimal(19,2) not null, order_status varchar(255), sub_total decimal(19,2) not null, payment_id bigint not null, restaurant_id bigint not null, user_id bigint not null, primary key (id)) engine=InnoDB
create table tb_order_item (id bigint not null auto_increment, observation varchar(255), quantity integer not null, total_price decimal(19,2) not null, order_id bigint, product_id bigint, primary key (id)) engine=InnoDB
create table tb_payments (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_permissions (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table tb_products (id bigint not null auto_increment, active bit not null, description varchar(255) not null, price decimal(19,2) not null, restaurant_id bigint, primary key (id)) engine=InnoDB
create table tb_restaurant (id bigint not null auto_increment, address_cep varchar(255), address_complement varchar(255), address_neighborhood varchar(255), address_number varchar(255), address_street varchar(255), create_date datetime not null, freigth_rate decimal(19,2) not null, name varchar(255) not null, open bit, active_status bit, update_date datetime not null, kitchen_id bigint, primary key (id)) engine=InnoDB
create table tb_restaurant_payment (restaurant_id bigint not null, payment_id bigint not null, primary key (restaurant_id, payment_id)) engine=InnoDB
create table tb_restaurant_user (restaurant_id bigint not null, user_id bigint not null, primary key (restaurant_id, user_id)) engine=InnoDB
create table tb_state (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_user_group (user_id bigint not null, group_id bigint not null, primary key (user_id, group_id)) engine=InnoDB
create table tb_users (id bigint not null auto_increment, create_date datetime not null, password varchar(255) not null, update_date datetime not null, user varchar(255) not null, primary key (id)) engine=InnoDB
alter table tb_city add constraint FK1rn7oty4mwqviyw8vk67crapo foreign key (state_id) references tb_state (id)
alter table tb_group_permission add constraint FK4nqqp30lex1seax5tct2l9lsk foreign key (permission_id) references tb_permissions (id)
alter table tb_group_permission add constraint FKfabw19737lr4x9u74m9bhs9cx foreign key (group_id) references tb_group (id)
alter table tb_order add constraint FKotx7pn375sp110y6j38f7fcei foreign key (payment_id) references tb_payments (id)
alter table tb_order add constraint FKcub1ihx891msieq8q6fnk6jcg foreign key (restaurant_id) references tb_restaurant (id)
alter table tb_order add constraint FK8q4ock0ph4s1qu5o9ghia1tbn foreign key (user_id) references tb_users (id)
alter table tb_order_item add constraint FKgeobgl2xu916he8vhljktwxnx foreign key (order_id) references tb_order (id)
alter table tb_order_item add constraint FK11kgiid478vipvuwfkxs47t86 foreign key (product_id) references tb_products (id)
alter table tb_products add constraint FK4va9exr83m5atkllrjhqu99xs foreign key (restaurant_id) references tb_restaurant (id)
alter table tb_restaurant add constraint FKo4yy82dlq4ig3ebquw7ojjjwp foreign key (kitchen_id) references tb_kitchen (id)
alter table tb_restaurant_payment add constraint FK2p43ey8g0ih4behd6k5kh6jad foreign key (payment_id) references tb_payments (id)
alter table tb_restaurant_payment add constraint FK2qjft3d4o2n9ecl46qg03x4ao foreign key (restaurant_id) references tb_restaurant (id)
alter table tb_restaurant_user add constraint FK5hhg79cn0egpd7icynw068inx foreign key (user_id) references tb_users (id)
alter table tb_restaurant_user add constraint FKr25du2q3chu4tue0qmdgdmx8t foreign key (restaurant_id) references tb_restaurant (id)
alter table tb_user_group add constraint FKid96lv86ri5sahn1i5vfutj7u foreign key (group_id) references tb_group (id)
alter table tb_user_group add constraint FKao4ym2t6pgv6fgxuwy6465jec foreign key (user_id) references tb_users (id)
