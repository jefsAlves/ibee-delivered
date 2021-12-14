ALTER TABLE tb_order ADD order_code VARCHAR(36) NOT NULL AFTER id;
UPDATE tb_order SET order_code = uuid();
ALTER TABLE tb_order ADD CONSTRAINT uk_order_order_code UNIQUE(order_code);