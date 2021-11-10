INSERT INTO USER (id, first_name, last_name, email, password)
values (1002, 'employee', 'employee', 'employee@email', '$2y$12$JMV5vNSmO2yle39us25MXOOik60qaeH6qdCBfCkro7/LdW0.UTzQC');
INSERT INTO USER (id, first_name, last_name, email, password)
values (1003, 'manager', 'manager', 'manager@email', '$2y$12$B612A8/fjgFlbD1NNx/UKu9E5rT7.Y6hxoigopbEEpTUp1XstEPhq');

INSERT INTO ROLE (id, name)
values (2002, 'ROLE_EMPLOYEE');
INSERT INTO ROLE (id, name)
values (2003, 'ROLE_MANAGER');
INSERT INTO ROLE (id, name)
values (2004, 'ROLE_USER');

INSERT INTO USERS_ROLES (user_id, role_id)
values (1002, 2002);
INSERT INTO USERS_ROLES (user_id, role_id)
values (1002, 2004);
INSERT INTO USERS_ROLES (user_id, role_id)
values (1003, 2002);
INSERT INTO USERS_ROLES (user_id, role_id)
values (1003, 2003);
INSERT INTO USERS_ROLES (user_id, role_id)
values (1003, 2004);

INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40000, 'Red book', 'Book about red things.', '9.99', sysdate);
INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40001, 'Green book', 'Book about green things.', '19.99', sysdate);
INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40002, 'Yellow book', 'Book about yellow things.', '29.99', sysdate);
INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40003, 'Brown book', 'Book about brown things.', '39.99', sysdate);
INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40004, 'Grey book', 'Book about grey things.', '49.99', sysdate);
INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40005, 'Orange book', 'Book about orange things.', '59.99', sysdate);
INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40006, 'Violet book', 'Book about violet things.', '69.99', sysdate);
INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40007, 'Geographical atlas', 'A simple atlas for beginners.', '79.99', sysdate);
INSERT INTO BOOK (book_id, name, description, price, created_time)
values (40008, 'Fantasy book', 'Never Ending story.', '89.99', sysdate);