insert into service values(default, 'Service technique'), (default, 'Service Achat'), (default, 'Service de Securite'); 

insert into Critere (nom) values ('Diplome'), ('Sexe'), ('Situation matrimoniale'), ('Experience');

insert into SousCritere(nom, critere) values ('Bacc', 1), ('Bepc', 1), ('Cepe', 1);

insert into posteservice(service, titreposte) values (1, 'Maintenance'), (2, 'Comptable'), (3, 'Garde');