INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');

insert into users(id, email, password, username) values (1, 'admin@admin.com', '$2a$10$nWw1swhcmQ2yZe9oYCtdOehdCDLwN39UipQq5wf9DpETzfUE6ZlIy', 'admin');
insert into user_roles (user_id, role_id) values (1, 1);

insert into users(id, email, password, username) values (2, 'user@user.com', '$2a$10$br1JF36W6nml/hUr4szSmO55FKILu5gy6X8K9dr4rmgnXaeI0WXhW', 'user');
insert into user_roles (user_id, role_id) values (2, 2);

