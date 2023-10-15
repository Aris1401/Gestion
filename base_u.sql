CREATE SCHEMA IF NOT EXISTS gestion;

CREATE  TABLE avantagenature ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_avantagenature PRIMARY KEY ( id )
 );

CREATE  TABLE configconge ( 
	idconfig             varchar(255)  NOT NULL  ,
	nomconfig            varchar(255)    ,
	configvalue          varchar(255)    ,
	CONSTRAINT pk_configconge PRIMARY KEY ( idconfig )
 );

CREATE  TABLE critere ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_critere PRIMARY KEY ( id )
 );

CREATE  TABLE demandeconge ( 
	id                   integer  NOT NULL  ,
	motif                integer    ,
	datedebut            timestamp DEFAULT CURRENT_TIMESTAMP + INTERVAL '15 days'   ,
	datefin              timestamp DEFAULT CURRENT_TIMESTAMP   ,
	description          text    ,
	status               integer    ,
	CONSTRAINT pk_demandeconge PRIMARY KEY ( id )
 );

CREATE  TABLE ficheevaluation ( 
	id                   integer  NOT NULL  ,
	status               integer    ,
	CONSTRAINT pk_ficheevaluation PRIMARY KEY ( id )
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

CREATE  TABLE typecontrat ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_typecontrat PRIMARY KEY ( id )
 );

CREATE  TABLE congee ( 
	id                   integer  NOT NULL  ,
	datedebut            timestamp    ,
	datefin              timestamp    ,
	demade               integer    ,
	CONSTRAINT pk_congee PRIMARY KEY ( id ),
	CONSTRAINT fk_congee_demandeconge FOREIGN KEY ( demade ) REFERENCES demandeconge( id )   
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

CREATE  TABLE questionficheevaluation ( 
	id                   integer  NOT NULL  ,
	question             text    ,
	ficheevaluation      integer    ,
	service              integer    ,
	CONSTRAINT pk_questionficheevaluation PRIMARY KEY ( id ),
	CONSTRAINT fk_questionficheevaluation_ficheevaluation FOREIGN KEY ( ficheevaluation ) REFERENCES ficheevaluation( id )   ,
	CONSTRAINT fk_questionficheevaluation_service FOREIGN KEY ( service ) REFERENCES service( id )   
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

CREATE  TABLE choixreponseficheevaluation ( 
	id                   integer  NOT NULL  ,
	reponse              varchar(255)    ,
	question             integer    ,
	CONSTRAINT pk_reponseficheevaluation PRIMARY KEY ( id ),
	CONSTRAINT fk_reponseficheevaluation_questionficheevaluation FOREIGN KEY ( question ) REFERENCES questionficheevaluation( id )   
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

CREATE  TABLE embauche ( 
	id                   integer  NOT NULL  ,
	dateembauche         timestamp DEFAULT CURRENT_TIMESTAMP   ,
	cv                   integer    ,
	CONSTRAINT pk_embauche PRIMARY KEY ( id ),
	CONSTRAINT fk_embauche_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   
 );

CREATE  TABLE entretient ( 
	id                   integer  NOT NULL  ,
	cv                   integer    ,
	dateentretient       timestamp DEFAULT CURRENT_TIMESTAMP   ,
	dureeentretient      decimal(18,5) DEFAULT 0   ,
	CONSTRAINT pk_entretrient PRIMARY KEY ( id ),
	CONSTRAINT fk_entretrient_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   
 );

CREATE  TABLE notesouscritere ( 
	id                   integer  NOT NULL  ,
	souscritere          integer    ,
	besoin               integer    ,
	note                 numeric(18,5)    ,
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

CREATE  TABLE contrat ( 
	id                   integer  NOT NULL  ,
	cv                   integer    ,
	typecontrat          integer    ,
	salairebrut          decimal(18,5) DEFAULT 0   ,
	debutcontrat         date    ,
	datefincontrat       date    ,
	datedebutessai       date    ,
	datefinessai         date    ,
	embauche             integer    ,
	status               integer DEFAULT 0   ,
	matricule            varchar(255)    ,
	CONSTRAINT pk_contrat PRIMARY KEY ( id ),
	CONSTRAINT fk_contrat_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   ,
	CONSTRAINT fk_contrat_typecontrat FOREIGN KEY ( typecontrat ) REFERENCES typecontrat( id )   ,
	CONSTRAINT fk_contrat_embauche FOREIGN KEY ( embauche ) REFERENCES embauche( id )   
 );

CREATE  TABLE ficheposte ( 
	id                   integer  NOT NULL  ,
	cv                   integer    ,
	contrat              integer    ,
	CONSTRAINT pk_ficheposte PRIMARY KEY ( id ),
	CONSTRAINT fk_ficheposte_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   ,
	CONSTRAINT fk_ficheposte_contrat FOREIGN KEY ( contrat ) REFERENCES contrat( id )   
 );

CREATE  TABLE reponsabiliteposte ( 
	id                   integer  NOT NULL  ,
	personne             integer    ,
	ficheposte           integer    ,
	CONSTRAINT pk_reponsabiliteposte PRIMARY KEY ( id ),
	CONSTRAINT fk_reponsabiliteposte_personne FOREIGN KEY ( personne ) REFERENCES personne( id )   ,
	CONSTRAINT fk_reponsabiliteposte_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES ficheposte( id )   
 );

CREATE  TABLE reponseficheevaluation ( 
	id                   integer  NOT NULL  ,
	reponse              integer    ,
	textereponse         varchar(250)    ,
	ficheposte           integer    ,
	datereponse          timestamp DEFAULT CURRENT_TIMESTAMP   ,
	CONSTRAINT pk_reponseficheevaluation_0 PRIMARY KEY ( id ),
	CONSTRAINT fk_reponseficheevaluation_choixreponseficheevaluation FOREIGN KEY ( reponse ) REFERENCES choixreponseficheevaluation( id )   ,
	CONSTRAINT fk_reponseficheevaluation_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES ficheposte( id )   
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

CREATE  TABLE superieurposte ( 
	id                   integer  NOT NULL  ,
	personne             integer    ,
	ficheposte           integer    ,
	CONSTRAINT pk_superieurposte PRIMARY KEY ( id ),
	CONSTRAINT fk_superieurposte_personne FOREIGN KEY ( personne ) REFERENCES personne( id )   ,
	CONSTRAINT fk_superieurposte_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES ficheposte( id )   
 );

CREATE  TABLE avantagenaturecontrat ( 
	id                   integer  NOT NULL  ,
	contrat              integer    ,
	avantagenautre       integer    ,
	CONSTRAINT pk_avantagenaturecontrat PRIMARY KEY ( id ),
	CONSTRAINT fk_avantagenaturecontrat FOREIGN KEY ( avantagenautre ) REFERENCES avantagenature( id )   ,
	CONSTRAINT fk_avantagenaturecontratcontrat FOREIGN KEY ( contrat ) REFERENCES contrat( id )   
 );

CREATE OR REPLACE VIEW listepersonnel AS SELECT 
cv.nom,
cv.prenom,
cv.email,
cv.datenaissance,
cv.besoin,
cv.personne,
ct.matricule,
cv.id as idCV,
ct.id as idContrat
FROM gestion.ficheposte as fp
join gestion.cv on fp.cv = cv.id
join gestion.contrat as ct on ct.cv = cv.id;

CREATE OR REPLACE VIEW notescv AS (select 
cv.id, criterebesoin.coefficient, noteSOUsCRITERE.note 
from gestion.reponseCV 
JOIN gestion.cv ON cv.id= reponseCV.cv 
LEFT JOIN gestion.noteSOUsCRITERE ON NoteSouscritere.besoin=cv.besoin 
    and gestion.NoteSousCritere.sousCritere=reponseCV.SousCritere 
LEFT JOIN gestion.critereBesoin ON CritereBesoin.besoin =NoteSousCritere.besoin 
    AND gestion.critereBesoin.Critere=ReponseCV.Critere);

CREATE OR REPLACE VIEW notetest AS (select 
cv.id as idCV, choixreponse.note 
from gestion.reponseQuestionnaire 
join gestion.cv on reponseQuestionnaire.cv=cv.id 
join gestion.testQuestionnaire on cv.besoin =testQuestionnaire.besoin 
join gestion.choixReponse on reponseQuestionnaire.questionnaire=choixReponse.questionnaire);

COMMENT ON TABLE configconge IS 'Exemple:
Nom: "Duree maximum congee"
ID: "max_congee" 
Value: "90"';

COMMENT ON TABLE demandeconge IS 'Conge:
En attente - 0
Approuver RH - 10
Approuver superviseur - 20
Refuser - 40';

COMMENT ON TABLE cv IS 'CV:
En attente - 0
Embaucher - 10
Refuser - 100';

COMMENT ON TABLE contrat IS 'Contrat: En cours - 0
En attente - 10
Terminer - 20
Refuser - 50';

