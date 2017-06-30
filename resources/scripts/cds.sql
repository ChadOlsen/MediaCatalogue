CREATE TABLE CD (
    id INT AUTO_INCREMENT,
    title VARCHAR NOT NULL(35),
    genre VARCHAR NOT NULL(35),
    duration VARCHAR NOT NULL(20),
    tracks INT,
    artists SET
);