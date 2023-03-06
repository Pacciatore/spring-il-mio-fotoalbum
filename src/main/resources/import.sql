INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Titolo', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'La festa di Pedro', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Una cena', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Mistomare', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Bari', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Viaggio', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Titolo', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Titolo', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Titolo', 'https://picsum.photos/200', true);
INSERT INTO spring_fotoalbum.photos(description, title, url, visible) VALUES('Una descrizione casuale', 'Titolo', 'https://picsum.photos/200', true);

INSERT INTO spring_fotoalbum.categories(name) VALUES('Mare');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Montagna');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Panorama');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Selfie');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Vacanza');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Campagna');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Lavoro');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Sport');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Cucina');
INSERT INTO spring_fotoalbum.categories(name) VALUES('Informatica');

INSERT INTO spring_fotoalbum.photos_categories(photos_id, categories_id) VALUES(1, 5);
INSERT INTO spring_fotoalbum.photos_categories(photos_id, categories_id) VALUES(7, 5);
INSERT INTO spring_fotoalbum.photos_categories(photos_id, categories_id) VALUES(7, 2);
INSERT INTO spring_fotoalbum.photos_categories(photos_id, categories_id) VALUES(4, 1);
INSERT INTO spring_fotoalbum.photos_categories(photos_id, categories_id) VALUES(9, 8);

INSERT INTO spring_fotoalbum.role(id, name) VALUES(1, 'USER');
INSERT INTO spring_fotoalbum.role(id, name) VALUES(2, 'ADMIN');

INSERT INTO spring_fotoalbum.user(id, username, password) VALUES(1, 'Francesco', '{bcrypt}$2a$10$W8Zy76910kNtRRRDSKdg.e4OLzt8m.7LAWdMt7Mvhi4B/1QLryokm');
INSERT INTO spring_fotoalbum.user(id, username, password) VALUES(2, 'Pedro', '{bcrypt}$2a$10$W8Zy76910kNtRRRDSKdg.e4OLzt8m.7LAWdMt7Mvhi4B/1QLryokm');


INSERT INTO spring_fotoalbum.user_roles(user_id, roles_id) VALUES(1, 1);
INSERT INTO spring_fotoalbum.user_roles(user_id, roles_id) VALUES(2, 2);

INSERT INTO spring_fotoalbum.comments(content, photo_id) VALUES('Bella foto!', 1);
INSERT INTO spring_fotoalbum.comments(content, photo_id) VALUES('Mi ricordo di questo giorno', 1);
INSERT INTO spring_fotoalbum.comments(content, photo_id) VALUES('Bella foto!', 5);
INSERT INTO spring_fotoalbum.comments(content, photo_id) VALUES('Bella foto!', 6);
INSERT INTO spring_fotoalbum.comments(content, photo_id) VALUES('Bella foto!', 4);
INSERT INTO spring_fotoalbum.comments(content, photo_id) VALUES('Bella foto!', 7);
INSERT INTO spring_fotoalbum.comments(content, photo_id) VALUES('Quanto tempo!', 7);
INSERT INTO spring_fotoalbum.comments(content, photo_id) VALUES('Non ti ricordavo così!', 7);

INSERT INTO spring_fotoalbum.tags(name) VALUES('Amicizia');
INSERT INTO spring_fotoalbum.tags(name) VALUES('Divertimento');
INSERT INTO spring_fotoalbum.tags(name) VALUES('Passione');
INSERT INTO spring_fotoalbum.tags(name) VALUES('Tristezza');
INSERT INTO spring_fotoalbum.tags(name) VALUES('Rabbia');

INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(1,1);
INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(1,2);
INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(3,4);
INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(8,3);
INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(8,5);
INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(4,1);
INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(2,1);
INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(2,2);
INSERT INTO spring_fotoalbum.photos_tags(photos_id, tags_id) VALUES(7,1);