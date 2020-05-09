# feedbackmgmtsystem:
 Feedback Management System manages the Cognizant Outreach events. 

 The tech stack is
       maven
       Spring framework
       Spring reactive
       mysql DB
       Spring Batch
       
  It has the following modules
        userentitlement - Manages users and roles. 
        feedbackquestions - manages feedback questions
        eventprocessor - Event batch processor. Used to read events from .csv files and populate it in mySql DB
        eventsmanagement - Manages events
        emailnotification - It is used send email with or without attachement.

# userentitlement:

1. POST: /api/auth/signin
	Endpoint to signin a user

2. GET: /api/auth/user/{id}
	Enpoint to get user details

3. PUT: /api/auth/user/{id}/{rolename}
	Enpoint to add role to a user

4. DELETE: /api/auth/user/{id}/{rolename}
	Endpoint to delete role of a user

5. POST: /api/auth/signup
	Endpoint to register a user

# feedbackquestions:

1. GET: /api/v1/feedback/question/{id}
	Endpoint to get a question based on id

2. POST: /api/v1/feedback/question
	Endpoint to create a queston

3. PUT: /api/v1/feedback/questions/{questionId}
	Endpoint to update a question

4. PUT: /api/v1/feedback/questions
	Endpoint to update a question

5. DELETE: /api/v1/feedback/questions/{questionId}
	Endpoint to delete a question

# eventprocessor:

1. GET: /run-batch-job
	Endpoint to run a job

# eventsmanagement:


1. GET: /events/eventinfo/{eventId}
	Endpoint to get event info based on event id

2. POST: /events/eventinfo
	Endpoint to add new event info

3. GET: /events/eventinfo
	Endpoint to get all event info

4. DELETE: /events/eventinfo/{id}/{eventId}
	Endpoint to delete an event info based on event id and id

5. DELETE: /events/eventinfo/{eventId}
	Endpoint to delete an event info based on event id

6. PUT: /events/eventinfo
	Endpoint to update event info
  
7. GET: /events/eventsummary/user/{userId}

8. GET: /events/eventsummary/{eventId}

9. POST: /events/eventsummary

10. DELETE: /events/eventsummary/{eventId}

11. PUT: /events/eventsummary

# emailnotification:

1. POST: /api/notification/api/notification/sendEmail
	Endpoint to send email as part of notification

2. POST: /sendEmailWithAttachment
	Endpoint to send email with an attachement as part of notification

