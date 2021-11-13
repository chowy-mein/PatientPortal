use patientportalinfo;

SELECT * FROM patientlogins;
--Set-up initial patient. Wow such healthy. Very healthy. Healthy boy Mark
INSERT INTO patientlogins
(patientID, username, password, firstname, lastname, phonenumber, medh, imm)
VALUES (null, "mashinhu", "mashinhust12", "Mark", "Ashinhust", "6235232880", "Healthy", "All");
