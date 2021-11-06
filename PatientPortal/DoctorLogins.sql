use patientportalinfo;

SELECT * FROM doctorlogins;

INSERT INTO doctorlogins
(doctorID, username, password, firstname, lastname)
VALUES (null, "DoctorM", "121668&^S", "Martin", "Cuebert"),
	   (null, "PhilP", "IamDOCP", "Phillip", "Peterson");
       
