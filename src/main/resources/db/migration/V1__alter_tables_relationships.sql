/* ALTER TABLE users ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES roles(id);

ALTER TABLE category ADD CONSTRAINT fk_category_parentcategory FOREIGN KEY (parentid) REFERENCES category(id);

ALTER TABLE film ADD CONSTRAINT fk_film_category FOREIGN KEY (categoryid) REFERENCES category(id);

ALTER TABLE seat ADD CONSTRAINT fk_seat_cinema FOREIGN KEY (cinemaid) REFERENCES cinema(id);

ALTER TABLE show_time ADD CONSTRAINT fk_show_time_cinema FOREIGN KEY (cinemaid) REFERENCES cinema(id);
ALTER TABLE show_time ADD CONSTRAINT fk_show_time_film FOREIGN KEY (filmid) REFERENCES film(id);

ALTER TABLE ticket_type ADD CONSTRAINT fk_ticket_type_film FOREIGN KEY (filmid) REFERENCES film(id);

ALTER TABLE order_ticket_type ADD CONSTRAINT fk_ticket_order_ticket_type FOREIGN KEY (ticket_type_id) REFERENCES ticket_type(id);

ALTER TABLE order_combo ADD CONSTRAINT fk_combo_order_combo FOREIGN KEY (comboid) REFERENCES combo(id);

ALTER TABLE order_detail ADD CONSTRAINT fk_order_detail_seat FOREIGN KEY (seatid) REFERENCES seat(id);
ALTER TABLE order_detail ADD CONSTRAINT fk_order_detail_order_ticket FOREIGN KEY (order_ticket_id) REFERENCES order_ticket_type(id);
ALTER TABLE order_detail ADD CONSTRAINT fk_order_detail_order_combo FOREIGN KEY (order_combo_id) REFERENCES order_combo(id);
ALTER TABLE order_detail ADD CONSTRAINT fk_order_detail_showtime FOREIGN KEY (showtime_id) REFERENCES show_time(id);

ALTER TABLE orders ADD CONSTRAINT fk_orders_order_detail FOREIGN KEY (order_detail_id) REFERENCES order_detail(id);
ALTER TABLE orders ADD CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id); */