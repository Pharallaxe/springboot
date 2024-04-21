DROP TABLE sorcier;

DROP TABLE maison;

CREATE TABLE maison (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(255),
    bonusAttaque INT,
    bonusSante INT,
    modifiable BOOLEAN
);

CREATE TABLE sorcier (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    id_maison INT,
    attaque INT,
    sante INT,
    modifiable BOOLEAN,
    FOREIGN KEY (id_maison) REFERENCES maison(id)
);

INSERT INTO maison (nom, bonusAttaque, bonusSante, modifiable) 
VALUES 
('Gryffondor', 5, 10, false),
('Serdaigle', 1, 30, false),
('Poufsouffle', 4, 20, false),
('Serpentard', 8, 0, false),
('Hippogriffe', 4, 25, true),
('Moldu', 3, 20, true);

INSERT INTO sorcier (nom, prenom, id_maison, attaque, sante, modifiable) 
VALUES 
('Potter', 'Harry', 1, 10, 100, false),
('Granger', 'Hermione', 1, 9, 120, false),
('Lovegood', 'Luna', 2, 3, 200, false),
('Patil', 'Padma', 2, 4, 170, false),
('Diggory', 'Cedric', 3, 7, 150, false),
('Chourave', 'Pomona', 3, 8, 140, false),
('Rogue', 'Severus', 4, 15, 80, false),
('Malfoy', 'Draco', 4, 11, 60, false),
('McGregor', 'Edward', 5, 8, 150, true),
('Sword', 'William', 6, 5, 250, true);