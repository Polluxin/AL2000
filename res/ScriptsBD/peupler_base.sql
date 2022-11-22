-- noinspection SqlInsertNullIntoNotNullForFile

-- Script de peuplement de la base

-- Ajout des films
truncate table LESFILMS;
--  Vrais films
insert into LESFILMS values(null, 'Hunger Games 1', to_date('2012', 'YYYY'), 'Francis Lawrence', '2:30', 'SF');
insert into LESFILMS values(null, 'Hunger Games 2', to_date('2013', 'YYYY'), 'Francis Lawrence', '2:26', 'SF');
--  Films inventés
insert into LESFILMS values(null, 'La faim', to_date('1999', 'YYYY'), 'Jacques Dubois', '1:00', 'ROMANCE');
insert into LESFILMS values(null, 'Maman ou papa ?', to_date('2010', 'YYYY'), 'Jeanne Jira', '1:02', 'COMEDIE');
insert into LESFILMS values(null, 'Fight pub', to_date('2002', 'YYYY'), 'Al Quolick', '1:45', 'WESTERN');
insert into LESFILMS values(null, 'Hangugsig Bokk-Eummyeon', to_date('2015', 'YYYY'), 'Park Chan-Wook', '0:45', 'ANIME');
insert into LESFILMS values(null, 'La guerre des boulons', to_date('2010', 'YYYY'), 'Richard Boulon', '1:45', 'HORREUR');
insert into LESFILMS values(null, 'Fifty shades of dark dark grey', to_date('2018', 'YYYY'), 'James Fouettais', '2:47', 'ROMANCE');
insert into LESFILMS values(null, 'Massage à la tronçonneuse', to_date('2005', 'YYYY'), 'Marc Rill', '1:24', 'HORREUR');
insert into LESFILMS values(null, 'Attente', to_date('2002', 'YYYY'), 'Pat Science', '3:13', 'SUSPENSE');
insert into LESFILMS values(null, 'La bataille de la fin', to_date('2002', 'YYYY'), 'Paul DesChamps', '1:58', 'ACTION');
insert into LESFILMS values(null, 'Xelcome to Mars', to_date('2020', 'YYYY'), 'John Do', '2:35', 'SF');
insert into LESFILMS values(null, 'Das Labyrinth von einst', to_date('2019', 'YYYY'), 'Heindrich Hanzler', '2:00', 'FANTAISIE');
insert into LESFILMS values(null, 'Comment annexer un pays slave ?', to_date('2022', 'YYYY'), 'Wladimir Routine', '2:59', 'ACTION');
commit;

-- Ajout des machines
truncate table LESMACHINES;
insert into LESMACHINES values(1, '2 rue de la Porte 38100 Grenoble');
insert into LESMACHINES values(2, '5 avenue Février 3100 Grenoble');
insert into LESMACHINES values(3, '14 bis rue Jean Moulin 38100 Grenoble');
insert into LESMACHINES values(4, '58 rue Alsace-Flandrin 38100 Grenoble');
commit;

-- Ajout des BluRays
truncate table LESBLURAYS;
insert into LESBLURAYS values(1, 2);
insert into LESBLURAYS values(2, 3);
insert into LESBLURAYS values(3, 12);
insert into LESBLURAYS values(4, 13);
insert into LESBLURAYS values(5, 14);

insert into LESBLURAYS values(6, 1);
insert into LESBLURAYS values(7, 2);
insert into LESBLURAYS values(8, 3);
insert into LESBLURAYS values(9, 4);
insert into LESBLURAYS values(10, 8);

insert into LESBLURAYS values(11, 6);
insert into LESBLURAYS values(12, 7);
insert into LESBLURAYS values(13, 12);
insert into LESBLURAYS values(14, 10);
insert into LESBLURAYS values(15, 14);

insert into LESBLURAYS values(16, 5);
insert into LESBLURAYS values(17, 8);
insert into LESBLURAYS values(18, 14);
insert into LESBLURAYS values(19, 11);
insert into LESBLURAYS values(20, 4);
commit;

-- Ajout des BluRays dans les machines
truncate table LESSTOCKS;
insert into LESSTOCKS values(1, 1);
insert into LESSTOCKS values(2, 1);
insert into LESSTOCKS values(3, 1);
insert into LESSTOCKS values(4, 1);
insert into LESSTOCKS values(5, 1);

insert into LESSTOCKS values(6, 2);
insert into LESSTOCKS values(7, 2);
insert into LESSTOCKS values(8, 2);
insert into LESSTOCKS values(9, 2);
insert into LESSTOCKS values(10, 2);

insert into LESSTOCKS values(11, 3);
insert into LESSTOCKS values(12, 3);
insert into LESSTOCKS values(13, 3);
insert into LESSTOCKS values(14, 3);
insert into LESSTOCKS values(15, 3);

insert into LESSTOCKS values(16, 4);
insert into LESSTOCKS values(17, 4);
insert into LESSTOCKS values(18, 4);
insert into LESSTOCKS values(19, 4);
insert into LESSTOCKS values(20, 4);


-- Ajout des statistiques
truncate table LESSTATISTIQUES;
insert into LESSTATISTIQUES values(1, to_date('2022/05/15 8:30:25', 'YYYY/MM/DD HH:MI:SS'), '0-0-');
insert into LESSTATISTIQUES values(2, to_date('2022/05/15 8:30:25', 'YYYY/MM/DD HH:MI:SS'), '0-0-');
insert into LESSTATISTIQUES values(3, to_date('2022/05/15 8:30:25', 'YYYY/MM/DD HH:MI:SS'), '0-0-');
insert into LESSTATISTIQUES values(4, to_date('2022/05/15 8:30:25', 'YYYY/MM/DD HH:MI:SS'), '0-0-');
commit;

-- Ajout des techniciens
truncate table LESTECHNICIENS;
insert into LESTECHNICIENS values(1, 'Planeur', 'Coralie');
insert into LESTECHNICIENS values(2, 'Pavlova', 'Matthieu');
insert into LESTECHNICIENS values(3, 'Grenada', 'Armando');
insert into LESTECHNICIENS values(4, 'Havide', 'Godfroy');
commit;

-- Ajout des genres interdits
truncate table LESINTERDITS;
insert into LESINTERDITS values(1, 'ROMANCE-SF-FANTAISIE');
insert into LESINTERDITS values(2, 'SUSPENSE');

-- Ajout des cartes abonnés
truncate table LESCARTESABONNES;
insert into LESCARTESABONNES values(1,'DelaVie', 'Charles', 'charles.vie@gmail.com', '4 avenue de la Bouteille 38100 Grenoble',15, 'CHARLESbogoss2002');
insert into LESCARTESABONNES values(2,'Lavant', 'Alphonse', 'al-fonce@yahoo.fr', '8 avenue Jack Jacky 38100 Grenoble',0, 'DéfonceAL78!666');
insert into LESCARTESABONNES values(3,'Grégory', 'Yann', 'lepilotevroum@orange.fr', '7 rue des Fleurs 38100 Grenoble',12, 'formule1??007');
insert into LESCARTESABONNES values(4,'DelaMarne', 'Juliette', 'jaimelesfleurs@orange.fr', '10 rue des Lylas 38100 Grenoble',37, '!42paquerettes&cookies!');
insert into LESCARTESABONNES values(5,'Boulon', 'Marion', 'marion.boulon@gmail.com', '13 rue de la Commanderie 38100 Grenoble',0.7, 'petitSoleil4667');
commit;

-- Ajout des cartes bancaires
truncate table LESCARTESBANCAIRES;
insert into LESCARTESBANCAIRES values(1, '5341 2154 2225 4448-04 25-Paul Fort-888-');
insert into LESCARTESBANCAIRES values(2, '2125 5858 5247 2596-07 24-Jacques Al-257-');
insert into LESCARTESBANCAIRES values(3, '7787 8855 9387 7781-11 23-Dorian DuRien-135-');
commit;

-- Ajout des locations de BluRays
truncate table LESLOCATIONSBLURAY;
insert into LESLOCATIONSBLURAY values(null, to_date('2022/07/15 8:30:25', 'YYYY/MM/DD HH:MI:SS'), 'TERMINEE', 1, 1, 1);
insert into LESLOCATIONSBLURAY values(null, to_date('2022/08/21 9:44:11', 'YYYY/MM/DD HH:MI:SS'), 'TERMINEE', 2, 1, 3);
insert into LESLOCATIONSBLURAY values(null, to_date('2022/07/15 11:30:25', 'YYYY/MM/DD HH:MI:SS'), 'TERMINEE', 3, 1, 5);
commit;

-- Ajout des locations de QRCode
truncate table LESLOCATIONSQRCODE;
insert into LESLOCATIONSQRCODE values(null, to_date('2022/07/15 8:30:25', 'YYYY/MM/DD HH:MI:SS'), 'TERMINEE', 1, 1, 12);
insert into LESLOCATIONSQRCODE values(null, to_date('2022/08/21 9:44:11', 'YYYY/MM/DD HH:MI:SS'), 'TERMINEE', 2, 1, 13);
insert into LESLOCATIONSQRCODE values(null, to_date('2022/07/15 11:30:25', 'YYYY/MM/DD HH:MI:SS'), 'TERMINEE', 3, 1, 14);
commit;