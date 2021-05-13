START TRANSACTION;

USE site_of_games; 

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO `user` VALUES (1,'admin','861fd923a1d6f6316d41f83bb067cedd','2000-02-01','user@domain.com','admin','admin','1',1,0),
(2,'user','861fd923a1d6f6316d41f83bb067cedd','2000-01-31','yigafas522@tlhao86.com','user','test','0',2,0);

INSERT INTO `address` VALUES (1,'CountryONE','CityONE','StreetOne', '1a'),
(2,'CountryTwo','CityTwo','StreetTwo', '2b');

INSERT INTO `game` VALUES (1,'GTA 5',30,'2013-11-30','Grand Theft Auto V — мультиплатформенная компьютерная игра в жанре action-adventure с открытым миром, разработанная компанией Rockstar North и изданная компанией Rockstar Games','Rockstar North',1,2,null),
(2,'Saint Row',20,'2009-12-31','Серия мультиплатформенных компьютерных игр, разработанных американской компанией Volition и изданных компаниями THQ и Deep Silver.','Deep Silver Volition',3,4,null),
(3,'Mafia 2',10,'2009-12-31','Mafia II — компьютерная игра в жанре приключенческого боевика с открытым миром, сочетающего в себе автомобильный симулятор и шутер от третьего лица, разработанная чешской компанией 2K Czech; вторая игра серии Mafia.','Hangar 13 Czech',1,2,12),
(4,'Witcher',20,'2019-12-31','«Ведьмак 3: Дикая Охота» — компьютерная игра в жанре action/RPG, разработанная польской студией CD Projekt RED. Выпущенная 19 мая 2015 года на Windows, PlayStation 4 и Xbox One, затем 15 октября 2019 года на Nintendo Switch, она является повествованием, продолжающим игры «Ведьмак» и «Ведьмак 2: Убийцы королей».','CD Projekt, CD Projekt RED',3,4,null),
(5,'Metro',32,'2020-09-30','Metro Exodus — компьютерная игра в жанре шутера от первого лица, разработанная украинской компанией 4A Games и изданная Deep Silver. Выход игры состоялся 15 февраля 2019 года для игровых платформ ПК, PlayStation 4 и Xbox One.','4A Engine',1,2,null),
(6,'Resident Evil: Village',50,'2021-05-09','Resident Evil Village — компьютерная игра в жанре survival horror. Часть серии Resident Evil и продолжение Resident Evil 7: Biohazard. В качестве разработчика и издателя игры выступает компания Capcom','Capcom',3,4,null),
(7,'Tom Clancys Rainbow Six',30,'2009-12-31','Tom Clancy’s Rainbow Six Siege — тактический шутер от первого лица, разработанный Ubisoft для Microsoft Windows, Xbox One и PlayStation 4. Игра была анонсирована Ubisoft 9 июня 2014 на E3 и выпущена 1 декабря 2015 года. Проект стал преемником закрытого Patriots.','Ubisoft Montreal',1,2,null);

INSERT INTO `system_requirements` VALUES 
(1,'windows7','Intel core i3',2.3,8,'nvidia rtx 2060',6,40),
(2,'windows10','Intel core I7',3.3,16,'nvidia rtx 2080ti',11,40),
(3,'windows8','Intel core i3',2.4,4,'nvidia 940',2,30),
(4,'windows8','Intel core I5',2.8,8,'nvidia 980',2,30);

COMMIT;

SET FOREIGN_KEY_CHECKS=1;



