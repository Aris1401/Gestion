
CREATE  TABLE critere ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_critere PRIMARY KEY ( id )
 );

CREATE  TABLE profil ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	rang                 integer DEFAULT 0   ,
	CONSTRAINT pk_profil PRIMARY KEY ( id )
 );

CREATE  TABLE service ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_service PRIMARY KEY ( id )
 );

CREATE  TABLE souscritere ( 
	id                   integer  NOT NULL  ,
	critere              integer    ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_souscritere PRIMARY KEY ( id ),
	CONSTRAINT fk_souscritere_critere FOREIGN KEY ( critere ) REFERENCES critere( id )   
 );

CREATE  TABLE personne ( 
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
	CONSTRAINT fk_personne_profil FOREIGN KEY ( profil ) REFERENCES profil( id )   
 );

CREATE  TABLE posteservice ( 
	id                   integer  NOT NULL  ,
	service              integer    ,
	titreposte           varchar(255)    ,
	CONSTRAINT pk_posteservice PRIMARY KEY ( id ),
	CONSTRAINT fk_posteservice_service FOREIGN KEY ( service ) REFERENCES service( id )   
 );

CREATE  TABLE besoin ( 
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
	CONSTRAINT fk_besoin_service FOREIGN KEY ( service ) REFERENCES service( id )   ,
	CONSTRAINT fk_besoin_posteservice FOREIGN KEY ( posteservice ) REFERENCES posteservice( id )   
 );

CREATE  TABLE criterebesoin ( 
	id                   integer  NOT NULL  ,
	besoin               integer    ,
	critere              integer    ,
	coefficient          numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_criterebesoin PRIMARY KEY ( id ),
	CONSTRAINT fk_criterebesoin_besoin FOREIGN KEY ( besoin ) REFERENCES besoin( id )   
 );

CREATE  TABLE cv ( 
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
	CONSTRAINT fk_cv_besoin FOREIGN KEY ( besoin ) REFERENCES besoin( id )   ,
	CONSTRAINT fk_cv_personne FOREIGN KEY ( personne ) REFERENCES personne( id )   
 );

CREATE  TABLE notesouscritere ( 
	id                   integer  NOT NULL  ,
	souscritere          integer    ,
	besoin               integer    ,
	note                 decimal(18,5)    ,
	CONSTRAINT pk_notesouscritere PRIMARY KEY ( id ),
	CONSTRAINT fk_notesouscritere_besoin FOREIGN KEY ( besoin ) REFERENCES besoin( id )   ,
	CONSTRAINT fk_notesouscritere_souscritere FOREIGN KEY ( souscritere ) REFERENCES souscritere( id )   
 );

CREATE  TABLE reponsecv ( 
	id                   integer  NOT NULL  ,
	cv                   integer    ,
	critere              integer    ,
	souscritere          integer    ,
	CONSTRAINT pk_reponsecv PRIMARY KEY ( id ),
	CONSTRAINT fk_reponsecv_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   ,
	CONSTRAINT fk_reponsecv_critere FOREIGN KEY ( critere ) REFERENCES critere( id )   ,
	CONSTRAINT fk_reponsecv_souscritere FOREIGN KEY ( souscritere ) REFERENCES souscritere( id )   
 );

CREATE  TABLE testquestionnaire ( 
	id                   integer  NOT NULL  ,
	besoin               integer    ,
	question             text    ,
	estchoixmultiple     boolean    ,
	note                 numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_testquestionnaire PRIMARY KEY ( id ),
	CONSTRAINT fk_testquestionnaire_besoin FOREIGN KEY ( besoin ) REFERENCES besoin( id )   
 );

CREATE  TABLE choixreponse ( 
	id                   integer  NOT NULL  ,
	questionnaire        integer    ,
	reponse              text    ,
	note                 numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_choixreponse PRIMARY KEY ( id ),
	CONSTRAINT fk_choixreponse FOREIGN KEY ( questionnaire ) REFERENCES testquestionnaire( id )   
 );

CREATE  TABLE reponsequestionnaire ( 
	id                   integer  NOT NULL  ,
	questionnaire        integer    ,
	reponse              integer    ,
	cv                   integer    ,
	CONSTRAINT pk_reponsequestionnaire PRIMARY KEY ( id ),
	CONSTRAINT fk_reponsequestionnaire FOREIGN KEY ( questionnaire ) REFERENCES testquestionnaire( id )   ,
	CONSTRAINT fk_reponse_choix_questionnaire FOREIGN KEY ( reponse ) REFERENCES choixreponse( id )   ,
	CONSTRAINT fk_reponsequestionnaire_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   
 );
