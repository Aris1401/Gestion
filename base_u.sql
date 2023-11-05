CREATE  TABLE avantagenature ( 
	id                   serial  NOT NULL  ,
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
	id                   serial  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_critere PRIMARY KEY ( id )
 );

CREATE TABLE motif (
	id                   serial  NOT NULL ,
    nom                  varchar(255),
	CONSTRAINT pk_motif PRIMARY KEY ( id )
);

CREATE  TABLE demandeconge ( 
	id                   serial  NOT NULL  ,
	motif                integer REFERENCES motif(id)    ,
	datedebut            timestamp DEFAULT CURRENT_TIMESTAMP + INTERVAL '15 days'   ,
	datefin              timestamp DEFAULT CURRENT_TIMESTAMP   ,
	personne 			 int REFERENCES personne(id) ,
	description          text    ,
	status               integer    ,
	CONSTRAINT pk_demandeconge PRIMARY KEY ( id )
 );

CREATE  TABLE ficheevaluation ( 
	id                   serial  NOT NULL  ,
	status               integer    ,
	CONSTRAINT pk_ficheevaluation PRIMARY KEY ( id )
 );

CREATE  TABLE profil ( 
	id                   serial  NOT NULL  ,
	nom                  varchar(255)    ,
	rang                 integer DEFAULT 0   ,
	CONSTRAINT pk_profil PRIMARY KEY ( id )
 );

CREATE  TABLE service ( 
	id                   serial  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_service PRIMARY KEY ( id )
 );

CREATE  TABLE souscritere ( 
	id                   serial  NOT NULL  ,
	critere              integer    ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_souscritere PRIMARY KEY ( id ),
	CONSTRAINT fk_souscritere_critere FOREIGN KEY ( critere ) REFERENCES critere( id )   
 );

CREATE  TABLE typecontrat ( 
	id                   serial  NOT NULL  ,
	nom                  varchar(255)    ,
	CONSTRAINT pk_typecontrat PRIMARY KEY ( id )
 );

CREATE  TABLE congee ( 
	id                   serial  NOT NULL  ,
	datedebut            timestamp    ,
	datefin              timestamp    ,
	demande               integer    ,
	CONSTRAINT pk_congee PRIMARY KEY ( id ),
	CONSTRAINT fk_congee_demandeconge FOREIGN KEY ( demande ) REFERENCES demandeconge( id )   
 );

CREATE  TABLE personne ( 
	id                   serial  NOT NULL  ,
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
	id                   serial  NOT NULL  ,
	service              integer    ,
	titreposte           varchar(255)    ,
	CONSTRAINT pk_posteservice PRIMARY KEY ( id ),
	CONSTRAINT fk_posteservice_service FOREIGN KEY ( service ) REFERENCES service( id )   
 );

CREATE  TABLE questionficheevaluation ( 
	id                   serial  NOT NULL  ,
	question             text    ,
	ficheevaluation      integer    ,
	service              integer    ,
	CONSTRAINT pk_questionficheevaluation PRIMARY KEY ( id ),
	CONSTRAINT fk_questionficheevaluation_ficheevaluation FOREIGN KEY ( ficheevaluation ) REFERENCES ficheevaluation( id )   ,
	CONSTRAINT fk_questionficheevaluation_service FOREIGN KEY ( service ) REFERENCES service( id )   
 );

CREATE  TABLE besoin ( 
	id                   serial  NOT NULL  ,
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
	id                   serial  NOT NULL  ,
	reponse              varchar(255)    ,
	question             integer    ,
	CONSTRAINT pk_reponseficheevaluation PRIMARY KEY ( id ),
	CONSTRAINT fk_reponseficheevaluation_questionficheevaluation FOREIGN KEY ( question ) REFERENCES questionficheevaluation( id )   
 );

CREATE  TABLE criterebesoin ( 
	id                   serial  NOT NULL  ,
	besoin               integer    ,
	critere              integer    ,
	coefficient          numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_criterebesoin PRIMARY KEY ( id ),
	CONSTRAINT fk_criterebesoin_besoin FOREIGN KEY ( besoin ) REFERENCES besoin( id )   
 );

CREATE  TABLE cv ( 
	id                   serial  NOT NULL  ,
	nom                  varchar(255)    ,
	prenom               varchar(255)    ,
	adresse              varchar(255)    ,
	email                varchar(255)    ,
	contact              varchar(255)    ,
	description          text    ,
	datenaissance        date DEFAULT CURRENT_DATE   ,
	preuvediplome        varchar(255)    ,
	preuvetravail        varchar(255)    ,
	besoin               serial  NOT NULL  ,
	personne             integer    ,
	status               integer    ,
	dateecriture         date DEFAULT CURRENT_DATE   ,
	CONSTRAINT pk_cv PRIMARY KEY ( id ),
	CONSTRAINT fk_cv_besoin FOREIGN KEY ( besoin ) REFERENCES besoin( id )   ,
	CONSTRAINT fk_cv_personne FOREIGN KEY ( personne ) REFERENCES personne( id )   
 );

CREATE  TABLE embauche ( 
	id                   serial  NOT NULL  ,
	dateembauche         timestamp DEFAULT CURRENT_TIMESTAMP   ,
	cv                   integer    ,
	CONSTRAINT pk_embauche PRIMARY KEY ( id ),
	CONSTRAINT fk_embauche_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   
 );

CREATE  TABLE entretient ( 
	id                   serial  NOT NULL  ,
	cv                   integer    ,
	dateentretient       timestamp DEFAULT CURRENT_TIMESTAMP   ,
	dureeentretient      decimal(18,5) DEFAULT 0   ,
	CONSTRAINT pk_entretrient PRIMARY KEY ( id ),
	CONSTRAINT fk_entretrient_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   
 );

CREATE  TABLE notesouscritere ( 
	id                   serial  NOT NULL  ,
	souscritere          integer    ,
	besoin               integer    ,
	note                 numeric(18,5)    ,
	CONSTRAINT pk_notesouscritere PRIMARY KEY ( id ),
	CONSTRAINT fk_notesouscritere_besoin FOREIGN KEY ( besoin ) REFERENCES besoin( id )   ,
	CONSTRAINT fk_notesouscritere_souscritere FOREIGN KEY ( souscritere ) REFERENCES souscritere( id )   
 );

CREATE  TABLE reponsecv ( 
	id                   serial  NOT NULL  ,
	cv                   integer    ,
	critere              integer    ,
	souscritere          integer    ,
	CONSTRAINT pk_reponsecv PRIMARY KEY ( id ),
	CONSTRAINT fk_reponsecv_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   ,
	CONSTRAINT fk_reponsecv_critere FOREIGN KEY ( critere ) REFERENCES critere( id )   ,
	CONSTRAINT fk_reponsecv_souscritere FOREIGN KEY ( souscritere ) REFERENCES souscritere( id )   
 );

CREATE  TABLE testquestionnaire ( 
	id                   serial  NOT NULL  ,
	besoin               integer    ,
	question             text    ,
	estchoixmultiple     boolean    ,
	note                 numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_testquestionnaire PRIMARY KEY ( id ),
	CONSTRAINT fk_testquestionnaire_besoin FOREIGN KEY ( besoin ) REFERENCES besoin( id )   
 );

CREATE  TABLE choixreponse ( 
	id                   serial  NOT NULL  ,
	questionnaire        integer    ,
	reponse              text    ,
	note                 numeric(18,5) DEFAULT 0   ,
	CONSTRAINT pk_choixreponse PRIMARY KEY ( id ),
	CONSTRAINT fk_choixreponse FOREIGN KEY ( questionnaire ) REFERENCES testquestionnaire( id )   
 );

CREATE  TABLE contrat ( 
	id                   serial  NOT NULL  ,
	cv                   integer    ,
	typecontrat          integer    ,
	salairebrut          decimal(18,5) DEFAULT 0   ,
	datedebutcontrat         date    ,
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
	id                   serial  NOT NULL  ,
	cv                   integer    ,
	contrat              integer    ,
	CONSTRAINT pk_ficheposte PRIMARY KEY ( id ),
	CONSTRAINT fk_ficheposte_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   ,
	CONSTRAINT fk_ficheposte_contrat FOREIGN KEY ( contrat ) REFERENCES contrat( id )   
 );

CREATE  TABLE reponsabiliteposte ( 
	id                   serial  NOT NULL  ,
	personne             integer    ,
	ficheposte           integer    ,
	CONSTRAINT pk_reponsabiliteposte PRIMARY KEY ( id ),
	CONSTRAINT fk_reponsabiliteposte_personne FOREIGN KEY ( personne ) REFERENCES personne( id )   ,
	CONSTRAINT fk_reponsabiliteposte_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES ficheposte( id )   
 );

CREATE  TABLE reponseficheevaluation ( 
	id                   serial  NOT NULL  ,
	reponse              integer    ,
	textereponse         varchar(250)    ,
	ficheposte           integer    ,
	datereponse          timestamp DEFAULT CURRENT_TIMESTAMP   ,
	CONSTRAINT pk_reponseficheevaluation_0 PRIMARY KEY ( id ),
	CONSTRAINT fk_reponseficheevaluation_choixreponseficheevaluation FOREIGN KEY ( reponse ) REFERENCES choixreponseficheevaluation( id )   ,
	CONSTRAINT fk_reponseficheevaluation_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES ficheposte( id )   
 );

CREATE  TABLE reponsequestionnaire ( 
	id                   serial  NOT NULL  ,
	questionnaire        integer    ,
	reponse              integer    ,
	cv                   integer    ,
	CONSTRAINT pk_reponsequestionnaire PRIMARY KEY ( id ),
	CONSTRAINT fk_reponsequestionnaire FOREIGN KEY ( questionnaire ) REFERENCES testquestionnaire( id )   ,
	CONSTRAINT fk_reponse_choix_questionnaire FOREIGN KEY ( reponse ) REFERENCES choixreponse( id )   ,
	CONSTRAINT fk_reponsequestionnaire_cv FOREIGN KEY ( cv ) REFERENCES cv( id )   
 );


CREATE  TABLE superieurposte ( 
	id                   serial  NOT NULL  ,
	personne             integer    ,
	ficheposte           integer    ,
	CONSTRAINT pk_superieurposte PRIMARY KEY ( id ),
	CONSTRAINT fk_superieurposte_personne FOREIGN KEY ( personne ) REFERENCES personne( id )   ,
	CONSTRAINT fk_superieurposte_ficheposte FOREIGN KEY ( ficheposte ) REFERENCES ficheposte( id )   
 );

CREATE  TABLE avantagenaturecontrat ( 
	id                   serial  NOT NULL  ,
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
FROM ficheposte as fp
join cv on fp.cv = cv.id
join contrat as ct on ct.cv = cv.id;

CREATE OR REPLACE VIEW notescv AS SELECT 
    rc.cv AS cv_id,
    SUM(nb.note * cb.coefficient) AS note_totale,
    SUM(cb.coefficient) AS coefficient_total
FROM reponsecv rc
JOIN criterebesoin cb ON rc.critere = cb.critere
JOIN notesouscritere nb ON rc.souscritere = nb.souscritere
JOIN cv ON cv.id = rc.cv
where cv.besoin = cb.besoin
GROUP BY rc.cv;


-- CREATE OR REPLACE VIEW notescv AS (select
-- cv.id, sum(noteSOUsCRITERE.note * criterebesoin.coefficient) as total
-- from reponseCV 
-- JOIN cv ON cv.id= reponseCV.cv 
-- LEFT JOIN noteSOUsCRITERE ON NoteSouscritere.besoin=cv.besoin 
-- LEFT JOIN critereBesoin ON CritereBesoin.besoin =NoteSousCritere.besoin 
-- group by cv.id
-- );

CREATE OR REPLACE VIEW notetest AS
SELECT cv.id as idCV, sum(choixreponse.note) as note
FROM reponseQuestionnaire
JOIN cv ON reponseQuestionnaire.cv=cv.id
JOIN testQuestionnaire ON cv.besoin =testQuestionnaire.besoin
JOIN choixReponse ON reponseQuestionnaire.questionnaire=choixReponse.questionnaire
GROUP BY cv.id;

CREATE OR REPLACE VIEW vue_ficheposte AS
SELECT
    fp.id AS ficheposte_id,
    fp.cv AS cv_id,
    c.id AS contrat_id,
    c.typecontrat AS typecontrat_id,
    c.salairebrut,
    c.datedebutcontrat,
    c.datefincontrat,
    rc.id AS reponsecv_id,
    rc.critere AS critere_id,
    cr.nom AS critere_nom,
    rc.souscritere AS souscritere_id,
    sc.nom AS souscritere_nom,
    a.description AS annonce_description
FROM
    ficheposte AS fp
    INNER JOIN contrat AS c ON fp.contrat = c.id
    LEFT JOIN reponsecv AS rc ON fp.cv = rc.cv
    LEFT JOIN critere AS cr ON rc.critere = cr.id
    LEFT JOIN souscritere AS sc ON rc.souscritere = sc.id
    LEFT JOIN besoin AS b ON fp.cv = b.cv;

CREATE SEQUENCE matricule
    START 1
    INCREMENT 1
    MAXVALUE 999999999
    CYCLE;

CREATE OR REPLACE FUNCTION getMatricule()
RETURNS integer AS $$
DECLARE
    prochaine_valeur integer;
BEGIN
    SELECT nextval('matricule') INTO prochaine_valeur;
    RETURN prochaine_valeur;
END;
$$ LANGUAGE plpgsql;

create or replace view totalnote as (
	Select cv.besoin as besoin, nt.idcv as cv, sum((nc.note_totale * nc.coefficient_total) + nt.note) as total
	from notetest as nt
	join notescv as nc on nt.idcv = nc.cv_id
	join cv as cv on cv.id = nt.idcv
	group by nt.idcv, cv.besoin
);

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


