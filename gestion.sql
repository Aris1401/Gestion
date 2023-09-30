CREATE TABLE Personne (
	id serial PRIMARY KEY,
	nom varchar(255) NOT NULL,
	prenom varchar(255) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	motDePasse varchar(255) NOT NULL,
	dateNaissance DATE NOT NULL,
	sexe integer NOT NULL,
	contact integer NOT NULL,
	isAdmin integer,
);



CREATE TABLE Service (
	id serial PRIMARY KEY,
	nom varchar(255) NOT NULL
);



CREATE TABLE Besoin (
	id serial PRIMARY KEY,
	service integer NOT NULL,
	description TEXT NOT NULL,
	titre varchar(255) NOT NULL,
	volumeTaches DECIMAL(18,5) NOT NULL DEFAULT '0',
	tauxJourHomme DECIMAL(18,5) NOT NULL DEFAULT '0',
	dateBesoin DATE NOT NULL,
	dateFin DATE NOT NULL,
	status integer NOT NULL DEFAULT '0'
);



CREATE TABLE CritereBesoin (
	id serial PRIMARY KEY,
	besoin integer NOT NULL,
	critere integer NOT NULL,
	coefficient integer NOT NULL DEFAULT '0'
);



CREATE TABLE Critere (
	id serial PRIMARY KEY,
	nom varchar(255) NOT NULL
);



CREATE TABLE NoteSousCritere (
	id serial PRIMARY KEY,
	sousCritere integer NOT NULL,
	note DECIMAL(18,5) NOT NULL DEFAULT '0',
	critereBesoin integer NOT NULL
);



CREATE TABLE SousCritere (
	id serial PRIMARY KEY,
	nom varchar(255) NOT NULL,
	critere integer NOT NULL
);



CREATE TABLE CV (
	id serial PRIMARY KEY,
	nom varchar(255) NOT NULL,
	prenom varchar(255) NOT NULL,
	adresse varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	contact integer NOT NULL,
	description TEXT NOT NULL,
	dateNaissance DATE NOT NULL,
	diplomeFichier TEXT NOT NULL,
	preuveTravailFichier TEXT NOT NULL,
	besoin integer NOT NULL
);



CREATE TABLE CritereCV (
	id serial PRIMARY KEY,
	critere integer NOT NULL,
	note DECIMAL(18,5) NOT NULL DEFAULT '0',
	idCV integer NOT NULL
);


ALTER TABLE Besoin ADD CONSTRAINT "Besoin_fk0" FOREIGN KEY (service) REFERENCES Service(id);

ALTER TABLE CritereBesoin ADD CONSTRAINT "CritereBesoin_fk0" FOREIGN KEY (besoin) REFERENCES Besoin(id);
ALTER TABLE CritereBesoin ADD CONSTRAINT "CritereBesoin_fk1" FOREIGN KEY (critere) REFERENCES Critere(id);


ALTER TABLE NoteSousCritere ADD CONSTRAINT "NoteSousCritere_fk0" FOREIGN KEY (sousCritere) REFERENCES SousCritere(id);
ALTER TABLE NoteSousCritere ADD CONSTRAINT "NoteSousCritere_fk1" FOREIGN KEY (critereBesoin) REFERENCES CritereBesoin(id);

ALTER TABLE SousCritere ADD CONSTRAINT "SousCritere_fk0" FOREIGN KEY (critere) REFERENCES Critere(id);

ALTER TABLE CV ADD CONSTRAINT "CV_fk0" FOREIGN KEY (besoin) REFERENCES Besoin(id);

ALTER TABLE CritereCV ADD CONSTRAINT "CritereCV_fk0" FOREIGN KEY (critere) REFERENCES Critere(id);
ALTER TABLE CritereCV ADD CONSTRAINT "CritereCV_fk1" FOREIGN KEY (idCV) REFERENCES CV(id);










