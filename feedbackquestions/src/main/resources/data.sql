delete from feedbacktype;
delete from questioncategory;
delete from answers;
delete from questions;

INSERT INTO feedbacktype(feedbackid,feedback) VALUES(1,'Participated');
INSERT INTO feedbacktype(feedbackid,feedback) VALUES(2,'Not Participated');
INSERT INTO feedbacktype(feedbackid,feedback) VALUES(3,'Unregisterd');

INSERT INTO questioncategory(quescategoryid,quescategory) VALUES(1,'Allow Multiple Answer');
INSERT INTO questioncategory(quescategoryid,quescategory) VALUES(2,'Free Text Answer');
INSERT INTO questioncategory(quescategoryid,quescategory) VALUES(3,'Custom Question');

INSERT INTO questions(questionid,questioncategory,feedbacktype,status,eventid) VALUES(101,1,1,'ACTIVE',1);
INSERT INTO questions(questionid,questioncategory,feedbacktype,status,eventid) VALUES(102,1,1,'ACTIVE',1);
INSERT INTO questions(questionid,questioncategory,feedbacktype,status,eventid) VALUES(103,2,1,'INACTIVE',1);
INSERT INTO questions(questionid,questioncategory,feedbacktype,status,eventid) VALUES(104,3,1,'ACTIVE',1);
INSERT INTO questions(questionid,questioncategory,feedbacktype,status,eventid) VALUES(105,3,1,'INACTIVE',1);

INSERT INTO answers(answerid,questionid,answer,status) VALUES(201,101,'answer1011','ACTIVE');
INSERT INTO answers(answerid,questionid,answer,status) VALUES(202,101,'answer1012','ACTIVE');
INSERT INTO answers(answerid,questionid,answer,status) VALUES(203,101,'answer1013','ACTIVE');
INSERT INTO answers(answerid,questionid,answer,status) VALUES(301,105,'answer1051','ACTIVE');
INSERT INTO answers(answerid,questionid,answer,status) VALUES(302,105,'answer1052','ACTIVE');
INSERT INTO answers(answerid,questionid,answer,status) VALUES(303,105,'answer1053','ACTIVE');