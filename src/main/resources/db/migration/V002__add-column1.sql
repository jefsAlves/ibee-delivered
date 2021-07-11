alter table tb_restaurant 
ADD active_status tinyint(1) NOT NULL;
update tb_restaurant set active_status = true;