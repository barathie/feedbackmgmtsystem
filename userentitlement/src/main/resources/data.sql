delete from roles;
delete from users;
delete from user_roles;

INSERT INTO roles(id,name) VALUES (2001,'ROLE_ADMIN');
INSERT INTO roles(id,name) VALUES (2002,'ROLE_USER');
INSERT INTO roles(id,name) VALUES (2003,'ROLE_POC');

INSERT INTO users(id,username,password,email) VALUES (1001,'admin','admin','admin@gmail.com');
INSERT INTO users(id,username,password,email) VALUES (1002,'pmo','pmo','pmo@gmail.com');
INSERT INTO users(id,username,password,email) VALUES (1003,'poc','poc','poc@gmail.com');

INSERT INTO user_roles(user_id,role_id) VALUES (1001,2001);
INSERT INTO user_roles(user_id,role_id) VALUES (1002,2002);
INSERT INTO user_roles(user_id,role_id) VALUES (1003,2003);