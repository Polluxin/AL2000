create table LesFilms
(
    idFilm int generated by default on null as identity primary key,
    titre char(100),
    dateFilm date,
    realisateur char(100),
    duree char(50),
    genre char(25)
)
/

create table LesMachines
(
    idMachine int primary key,
    pos char(100)
)
/

create table LesBluRays
(
    idBluRay int primary key,
    idFilm int,

    constraint blurays_fk_idFilms
        foreign key (idFilm)
            references LesFilms(idFilm)
)
/

create table LesStocks
(
    idBluRay int primary key,
    idMachine int,

    constraint stocks_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
)
/

create table LesLocationsQRCode
(
    idLocation int generated by default on null as identity primary key,
    dateLocation date,
    etat char(25),
    carte int,
    idMachine int,
    idFilm,

    constraint locationsqr_fk_idFilms
        foreign key (idFilm)
            references LesFilms(idFilm),

    constraint locationsqr_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
)
/

create table LesLocationsBluRay
(
    idLocation int generated by default on null as identity primary key,
    dateLocation date,
    etat char(25),
    carte int,
    idMachine int,
    idBluRay int,

    constraint locationsbr_fk_idBluRay
        foreign key (idBluRay)
            references LesBluRays(idBluRay),

    constraint locationsbr_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
)
/

create table LesCartesBancaires
(
    idCB int generated by default on null as identity primary key,
    informations char(200)
)
/

create table LesCartesAbonnes
(
    idAbo int generated by default on null as identity primary key,
    nom char(25),
    prenom char(25),
    mail char(25),
    adressePostale char(100),
    solde float,
    mdp char(50)
)
/

create table LesInterdits
(
    idAbo int primary key,
    genres varchar(200)
)
/

create table LesTechniciens
(
    id int primary key,
    nom char(25),
    prenom char(25)
)
/

create table LesStatistiques
(
    idMachine int,
    dateStat date,
    donnees varchar(1000),

    constraint statistiques_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
)
/