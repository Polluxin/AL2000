create table LesFilms
(
    idFilm int primary key,
    titre varchar2(50),
    dateFilm date,
    realisateur varchar2(50),
    duree char(5),              -- char de 5 octets car de format XX:YY (XX heure(s) et YY minute(s))
    genre varchar2(25)
);

create table LesMachines
(
    idMachine int primary key,
    pos varchar2(50)               -- sous le format d'une adresse
);

create table LesBluRays
(
    idBluRay int primary key,
    idFilm int,

    constraint blurays_fk_idFilms
        foreign key (idFilm)
            references LesFilms(idFilm)
);

create table LesStocks
(
    idBluRay int primary key,
    idMachine int,

    constraint stocks_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
);

create table LesCartes
(
    idCarte int primary key,
    nom varchar2(20),
    prenom varchar2(50)
);

create table LesCB
(
    idCarte int primary key,
    infos varchar2(150),        -- sous le format "5341 2154 2225 4448-04 25-Paul Fort-888-"

    constraint cb_fk_idCarte
        foreign key (idCarte)
            references LesCartes(idCarte)
);

create table LesCA
(
    idCarte int primary key,
    mail varchar2(100),
    adressePostale varchar2(100),
    solde float check ( solde>=0 ),
    mdp varchar2(50),

    constraint ca_fk_idCarte
        foreign key (idCarte)
            references LesCartes(idCarte)

);

create table LesLocations
(
    idLocation int primary key,
    dateLocation date,
    etat varchar2(10),
    idCarte int,
    idMachine int,

    constraint locations_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine),

    constraint locations_fk_idCarte
        foreign key (idCarte)
            references LesCartes(idCarte)
);

create table LesLocationsQRCode
(
    idLocation int primary key,
    idFilm int,

    constraint locationqr_fk_idLocation
        foreign key (idLocation)
            references LesLocations(idLocation),

    constraint locationsqr_fk_idFilms
        foreign key (idFilm)
            references LesFilms(idFilm)
);

create table LesLocationsBluRay
(
    idLocation primary key,
    idBluRay int,

    constraint locationbr_fk_idLocation
        foreign key (idLocation)
            references LesLocations(idLocation),

    constraint locationsbr_fk_idBluRay
        foreign key (idBluRay)
            references LesBluRays(idBluRay)
);

create table LesInterdits
(
    idAbo int primary key,
    genres varchar2(200)
);

create table LesTechniciens
(
    id int primary key,
    nom varchar2(25),
    prenom varchar2(25)
);

create table LesStatistiques
(
    idMachine int,
    dateStat date,
    donnees varchar2(1000),

    constraint statistiques_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
);