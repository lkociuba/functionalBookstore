INSERT INTO USER (id, first_name, last_name, email, password)
values (1002, 'employee', 'employee', 'employee@email', '$2y$12$JMV5vNSmO2yle39us25MXOOik60qaeH6qdCBfCkro7/LdW0.UTzQC');
INSERT INTO USER (id, first_name, last_name, email, password)
values (1003, 'manager', 'manager', 'manager@email', '$2y$12$B612A8/fjgFlbD1NNx/UKu9E5rT7.Y6hxoigopbEEpTUp1XstEPhq');

INSERT INTO ROLE (id, name)
values (2002, 'ROLE_EMPLOYEE');
INSERT INTO ROLE (id, name)
values (2003, 'ROLE_MANAGER');

INSERT INTO USERS_ROLES (user_id, role_id)
values (1002, 2002);
INSERT INTO USERS_ROLES (user_id, role_id)
values (1003, 2002);
INSERT INTO USERS_ROLES (user_id, role_id)
values (1003, 2003);