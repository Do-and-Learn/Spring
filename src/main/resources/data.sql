INSERT INTO users (username, password, enabled) VALUES ('admin', '{noop}password', TRUE)
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER')