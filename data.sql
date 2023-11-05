insert into service values(default, 'Service technique'), (default, 'Service Achat'), (default, 'Service de Securite'); 

insert into Critere (nom) values ('Diplome'), ('Sexe'), ('Situation matrimoniale'), ('Experience');

insert into SousCritere(nom, critere) values ('Bacc', 1), ('Bepc', 1), ('Cepe', 1);

insert into posteservice(service, titreposte) values (1, 'Maintenance'), (2, 'Comptable'), (3, 'Garde');

insert into profil (nom, rang) values ('Client', 0);
insert into profil (nom, rang) values ('Personnel', 1);


INSERT INTO personne (
    nom, 
    prenom, 
    email, 
    motdepasse, 
    datenaissance, 
    sexe, 
    contact, 
    profil
) 
VALUES (
    'Smith', 
    'John', 
    'johnsmith@example.com', 
    'password123', 
    '1990-01-01', 
    1, 
    '555-555-5555', 
    1  -- Assuming '1' exists in 'profil' table
);

INSERT INTO cv (
    nom, 
    prenom, 
    adresse, 
    email, 
    contact, 
    description, 
    datenaissance, 
    preuvediplome, 
    preuvetravail, 
    besoin, 
    personne, 
    status, 
    dateecriture
) 
VALUES (
    'Doe', 
    'John', 
    '123 Main St', 
    'johndoe@example.com', 
    '555-555-5555', 
    'Experienced software developer', 
    '1985-07-20', 
    'Bachelor of Science in Computer Science', 
    'Software Developer at XYZ Corp from 2010 to 2015', 
    1,  -- Assuming '1' exists in 'besoin' table
    1,  -- Assuming '1' exists in 'personne' table
    1, 
    '2023-10-08'
);