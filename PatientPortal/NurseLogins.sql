use patientportalinfo;

SELECT * FROM nurselogins;
--Set initial nursing staff
INSERT INTO nurselogins
(nurseID, username, password, firstname, lastname)
VALUES (null, "BestNurse", "NurseBest", "Tanishq", "Mor"),
	   (null, "AlmostBest", "jkBestNurse", "Mark", "Ashinhust"),
       (null, "WhatAboutMe", "IKnowIam", "JC", "Hizarsa"),
       (null, "ImTheBest", "YeahIam", "Sara", "Kane");
       

       
