create table LesFilms
(
    idFilm int primary key,
    titre char,
    dateFilm date,
    realisateur char,
    duree char,
    genre char
)
/

create table LesMachines
(
    idMachine int primary key,
    pos char
)
/

create table LesBluRays
(
    idBluRay int primary key,
    idFilm int,
    idMachine int,

    constraint blurays_fk_idFilms
        foreign key (idFilm)
            references LesFilms(idFilm),

    constraint blurays_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
)
/

create table LesLocations
(
    idLocation int primary key,
    dateLocation date,
    etat char,
    carte int,
    formatLocation int,
    idMachine int,
    idFilm,

    constraint locations_fk_idFilms
        foreign key (idFilm)
            references LesFilms(idFilm),

    constraint locations_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
)
/

create table LesCartesBancaires
(
    idCB int primary key,
    informations char
)
/

create table LesCartesAbonnes
(
    idAbo int primary key,
    nom char,
    prenom char,
    mail char,
    adressePostale char,
    solde float,
    mdp char
)
/

create table LesInterdits
(
    idAbo int primary key,
    genre char
)
/

create table LesTechniciens
(
    id int primary key,
    nom char,
    prenom char
)
/

create table LesStatistiques
(
    idMachine int,
    dateStat date,
    donnees char,

    constraint statistiques_fk_idMachine
        foreign key (idMachine)
            references LesMachines(idMachine)
)
/