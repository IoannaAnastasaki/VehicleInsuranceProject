CREATE DATABASE VehicleInsuranceProject  CHARACTER SET utf8;
SHOW DATABASES;
USE VehicleInsuranceProject;
CREATE TABLE Owners (OwnerID int NOT NULL AUTO_INCREMENT,TaxNo INT(9) NOT NULL, PRIMARY KEY (OwnerID));
CREATE TABLE Vehicles (VehicleID int NOT NULL AUTO_INCREMENT, PlateNumber VARCHAR (8) NOT NULL,OwnerId int, ExpireDate  DATE NOT NULL,  PRIMARY KEY (VehicleID),FOREIGN KEY (OwnerID) REFERENCES Owners(OwnerID));
INSERT INTO Owners (TaxNo) VALUES (818386667); //id 1
INSERT INTO Owners (TaxNo) VALUES (173757962); //id 2
INSERT INTO Owners (TaxNo) VALUES (183457961); //id 3
INSERT INTO Owners (TaxNo) VALUES (114675923); //id 4
INSERT INTO Owners (TaxNo) VALUES (123467948); //id 5
Select * from Owners;
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("YTX-2356",'2017-6-23',1);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("XKT-2348",'2017-12-25',2);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("YTK-2896",'2017-5-20',3);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("YTX-1315",'2017-10-25',1);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("ATK-2346",'2017-11-15',5);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("KYX-2855",'2017-8-23',4);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("YKI-1678",'2017-9-26',5);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("ATI-5526",'2017-9-11',1);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("ITX-8865",'2017-8-15',5);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("XKT-5518",'2017-10-1',3);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("KIM-3356",'2018-3-21',4);
INSERT INTO Vehicles (PlateNumber,ExpireDate,OwnerID)   VALUES ("XKT-3855",'2017-6-22',5);
Select * from Vehicles;