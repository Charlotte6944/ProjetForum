DROP DATABASE IF EXISTS Forum;
CREATE DATABASE IF NOT EXISTS Forum;

USE Forum;

-- création de la table User
CREATE TABLE IF NOT EXISTS user
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nickname VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- création de la table topic
CREATE TABLE IF NOT EXISTS topic
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content LONGTEXT NOT NULL,
    date_created DATE NOT NULL,
    id_user INT,
	CONSTRAINT FK_UserTopicId FOREIGN KEY (id_user)
    REFERENCES user(id)
);

-- création de la table comment
CREATE TABLE IF NOT EXISTS comment
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    comment LONGTEXT NOT NULL,
    date_created DATE NOT NULL,
    id_user INT,
	CONSTRAINT FK_UserCommentId FOREIGN KEY (id_user)
    REFERENCES user(id),
    id_topic INT,
    CONSTRAINT FK_TopicId FOREIGN KEY (id_topic)
    REFERENCES topic(id)
);

DELETE FROM user;
INSERT INTO user (nickname, email, login, password)
 VALUES
 ('Toto', 'toto@m2ifomration.fr', 'toto', '123456'),
 ('Tata', 'tata@m2ifomration.fr', 'tata', '123456'),
 ('Titi', 'titi@m2ifomration.fr', 'titi', '123456');

SELECT * FROM user;