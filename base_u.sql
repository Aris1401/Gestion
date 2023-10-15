CREATE SCHEMA IF NOT EXISTS gestion;

CREATE  TABLE gestion.critere ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_critere PRIMARY KEY ( id )
 );

CREATE  TABLE gestion.profil ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	rang                 integer DEFAULT 0   ,
	CONSTRAINT pk_profil PRIMARY KEY ( id )
 );

CREATE  TABLE gestion.service ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_service PRIMARY KEY ( id )
 );

CREATE  TABLE gestion.souscritere ( 
	id                   integer  NOT NULL  ,
	critere              integer    ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_souscritere PRIMARY KEY ( id ),
	CONSTRAINT fk_souscritere_critere FOREIGN KEY ( critere ) REFERENCES gestion.critere( id )   
 );

CREATE  TABLE gestion.personne ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	prenom               varchar(255)    ,
	email                varchar(255)    ,
	motdepasse           varchar(255)    ,
	datenaissance        date    ,
	sexe                 integer    ,
	contact              varchar(255)    ,
	profil               integer    ,
	CONSTRAINT pk_personne PRIMARY KEY ( id ),
	CONSTRAINT fk_personne_profil FOREIGN KEY ( profil ) REFERENCES gestion.profil( id )   
 );

CREATE  TABLE gestion.posteservice ( 
	id                   integer  NOT NULL  ,
	service              integer    ,
	titreposte           varchar(255)    ,
	CONSTRAINT pk_posteservice PRIMARY KEY ( id ),
	CONSTRAINT fk_posteservice_service FOREIGN KEY ( service ) REFERENCES gestion.service( id )   
 );

CREATE  TABLE gestion.besoin ( 
	id                   integer  NOT NULL  ,
	service              integer    ,
	description          text    ,
	titre                text    ,
	posteservice         integer    ,
	volumetaches         numeric(18,5) DEFAULT 0   ,
	tauxjourhomme        numeric(18,5) DEFAULT 0   ,
	datebesoin           date DEFAULT CURRENT_DATE   ,
	datefin              date    ,
	status               integer    ,
	CONSTRAINT pk_besoin PRIMARY KEY ( id ),
	CONSTRAINT fk_besoin_service FOREIGN KEY ( service ) REFERENCES gestion.service( id )   ,
	CONSTRAINT fk_besoin_posteservice FOREIGN KEY ( posteservice ) REFERENCES gestion.posteservice( id )   
 );

CREATE  TABLE gestion.criterebesoin ( 
	id                   integer  NOT NULL  ,
	besoin               integer    ,
	critere              integer    ,
	coefficient          numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_criterebesoin PRIMARY KEY ( id ),
	CONSTRAINT fk_criterebesoin_besoin FOREIGN KEY ( besoin ) REFERENCES gestion.besoin( id )   
 );

CREATE  TABLE gestion.cv ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	prenom               varchar(255)    ,
	adresse              varchar(255)    ,
	email                varchar(255)    ,
	contact              varchar(255)    ,
	description          text    ,
	datenaissance        date DEFAULT CURRENT_DATE   ,
	preuvediplome        varchar(255)    ,
	preuvetravail        varchar(255)    ,
	besoin               integer  NOT NULL  ,
	personne             integer    ,
	status               integer    ,
	dateecriture         date DEFAULT CURRENT_DATE   ,
	CONSTRAINT pk_cv PRIMARY KEY ( id ),
	CONSTRAINT fk_cv_besoin FOREIGN KEY ( besoin ) REFERENCES gestion.besoin( id )   ,
	CONSTRAINT fk_cv_personne FOREIGN KEY ( personne ) REFERENCES gestion.personne( id )   
 );

CREATE  TABLE gestion.notesouscritere ( 
	id                   integer  NOT NULL  ,
	souscritere          integer    ,
	besoin               integer    ,
	note                 decimal(18,5)    ,
	CONSTRAINT pk_notesouscritere PRIMARY KEY ( id ),
	CONSTRAINT fk_notesouscritere_besoin FOREIGN KEY ( besoin ) REFERENCES gestion.besoin( id )   ,
	CONSTRAINT fk_notesouscritere_souscritere FOREIGN KEY ( souscritere ) REFERENCES gestion.souscritere( id )   
 );

CREATE  TABLE gestion.reponsecv ( 
	id                   integer  NOT NULL  ,
	cv                   integer    ,
	critere              integer    ,
	souscritere          integer    ,
	CONSTRAINT pk_reponsecv PRIMARY KEY ( id ),
	CONSTRAINT fk_reponsecv_cv FOREIGN KEY ( cv ) REFERENCES gestion.cv( id )   ,
	CONSTRAINT fk_reponsecv_critere FOREIGN KEY ( critere ) REFERENCES gestion.critere( id )   ,
	CONSTRAINT fk_reponsecv_souscritere FOREIGN KEY ( souscritere ) REFERENCES gestion.souscritere( id )   
 );

CREATE  TABLE gestion.testquestionnaire ( 
	id                   integer  NOT NULL  ,
	besoin               integer    ,
	question             text    ,
	estchoixmultiple     boolean    ,
	note                 numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_testquestionnaire PRIMARY KEY ( id ),
	CONSTRAINT fk_testquestionnaire_besoin FOREIGN KEY ( besoin ) REFERENCES gestion.besoin( id )   
 );

CREATE  TABLE gestion.choixreponse ( 
	id                   integer  NOT NULL  ,
	questionnaire        integer    ,
	reponse              text    ,
	note                 numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_choixreponse PRIMARY KEY ( id ),
	CONSTRAINT fk_choixreponse FOREIGN KEY ( questionnaire ) REFERENCES gestion.testquestionnaire( id )   
);

CREATE  TABLE gestion.reponsequestionnaire ( 
	id                   integer  NOT NULL  ,
	questionnaire        integer    ,
	reponse              integer    ,
	cv                   integer    ,
	CONSTRAINT pk_reponsequestionnaire PRIMARY KEY ( id ),
	CONSTRAINT fk_reponsequestionnaire FOREIGN KEY ( questionnaire ) REFERENCES gestion.testquestionnaire( id )   ,
	CONSTRAINT fk_reponse_choix_questionnaire FOREIGN KEY ( reponse ) REFERENCES gestion.choixreponse( id )   ,
	CONSTRAINT fk_reponsequestionnaire_cv FOREIGN KEY ( cv ) REFERENCES gestion.cv( id )   
 );

 /*-------------------------------------------------------------------------------------------------------------*/

CREATE  TABLE gestion.embauche (
	id                   integer  NOT NULL  ,
	dateEmbauche		 timestamp	,
	cv			 		 integer	,
	CONSTRAINT pk_embauche PRIMARY KEY ( id ),
	CONSTRAINT fk_embauche_cv FOREIGN KEY ( cv ) REFERENCES gestion.cv( id )
);

CREATE TABLE gestion.typecontrat(
	id                   integer  NOT NULL  ,
	nom					 varchar(255),
	CONSTRAINT pk_typecontrat PRIMARY KEY ( id )
);

CREATE	TABLE gestion.contrat(
	id                   integer  NOT NULL  ,
	typecontrat			 integer	,
	salairebrut			 decimal(18,5),
	debutcontrat		 date		,
	datefincontrat		 date		,
	datedebutessai		 date	 	,
	datefinessai		 date		,
	embauche			 integer	,
	status		 		 integer	,
	matricule			 varchar(255),
	CONSTRAINT pk_contrat PRIMARY KEY ( id ),
	CONSTRAINT fk_contrat_typecontrat FOREIGN KEY ( typecontrat ) REFERENCES gestion.typecontrat( id ),
	CONSTRAINT fk_contrat_embauche FOREIGN KEY ( embauche ) REFERENCES gestion.cv( embauche )
);

CREATE TABLE gestion.avantagenature(
	id                   integer  NOT NULL  ,
	nom					 varchar(255),
	CONSTRAINT pk_avantagenature PRIMARY KEY ( id )
);

CREATE TABLE gestion.avantagenaturecontrat(
	id                   integer  NOT NULL  ,
	contrat				 integer	,
	avantagenature		 integer	,
	CONSTRAINT pk_avantagenaturecontrat PRIMARY KEY ( id ),
	CONSTRAINT fk_avantagenaturecontrat_contrat FOREIGN KEY ( contrat ) REFERENCES gestion.contrat( id ),
	CONSTRAINT fk_avantagenaturecontrat_avantagenature FOREIGN KEY ( avantagenature ) REFERENCES gestion.avantagenature( id )
);

CREATE TABLE gestion.ficheposte(
	id                   integer  NOT NULL  ,
	cv		 			 integer	,
	contrat				 integer	,
	CONSTRAINT pk_ficheposte PRIMARY KEY ( id ),
	CONSTRAINT fk_ficheposte_contrat FOREIGN KEY ( contrat ) REFERENCES gestion.contrat( id ),
	CONSTRAINT fk_avantagenaturecontrat_cv FOREIGN KEY ( cv ) REFERENCES gestion.cv( id )
);

CREATE TABLE gestion.superieurposte(
	id                   integer  NOT NULL  ,
	personne 			 integer	,
	ficheposte			 integer	,
	CONSTRAINT pk_superieurposte PRIMARY KEY ( id ),
	CONSTRAINT fk_superieurposte_personne FOREIGN KEY ( personne ) REFERENCES gestion.personne( id ),
	CONSTRAINT fk_superieurposte_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES gestion.ficheposte( id )
);

CREATE TABLE gestion.responsabiliteposte(
	id                   integer  NOT NULL  ,
	personne 			 integer	,
	ficheposte			 integer	,
	CONSTRAINT pk_responsabiliteposte PRIMARY KEY ( id ),
	CONSTRAINT fk_responsabiliteposte_personne FOREIGN KEY ( personne ) REFERENCES gestion.personne( id ),
	CONSTRAINT fk_responsabiliteposte_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES gestion.ficheposte( id )
);

CREATE TABLE gestion.ficheevaluation(
	id                   integer  NOT NULL  ,
	status				 integer 	,
	CONSTRAINT pk_ficheevaluation PRIMARY KEY ( id )
);

CREATE TABLE gestion.questionficheevaluation(
	id                   integer  NOT NULL  ,
	question 			 text		,
	ficheevaluation		 integer	,
	service				 integer	,
	CONSTRAINT pk_questionficheevaluation PRIMARY KEY ( id ),
	CONSTRAINT fk_questionficheevaluation_ficheevaluation FOREIGN KEY ( ficheevaluation ) REFERENCES gestion.ficheevaluation( id ),
	CONSTRAINT fk_questionficheevaluation_service FOREIGN KEY ( service ) REFERENCES gestion.service ( id )
);

CREATE TABLE gestion.choixreponseficheevaluation(
	id                   integer  NOT NULL  ,
	reponse				 integer	,
	question 			 integer	,
	CONSTRAINT pk_choixreponseficheevaluation PRIMARY KEY ( id ),
	CONSTRAINT fk_choixreponseficheevaluation_question FOREIGN KEY ( question ) REFERENCES gestion.questionficheevaluation( id )
);

CREATE TABLE gestion.reponseficheevaluation(
	id                   integer  NOT NULL  ,
	reponse				 integer	,
	textereponse		 varchar(255),
	ficheposte			 integer	,
	datereponse			 timestamp	,
	CONSTRAINT pk_reponseficheevaluation PRIMARY KEY ( id ),
	CONSTRAINT fk_reponseficheevaluation_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES gestion.ficheposte ( id ),
	CONSTRAINT fk_reponseficheevaluation_reponse FOREIGN KEY ( reponse ) REFERENCES gestion.choixreponseficheevaluation ( id )
);