# SimpleChat

This is a simple chat application that allows user to:

	- [x] register
	- [ ] login implements JWT
	- [x] view their profile
	- [x] edit their profile , [ ] implements securirty
	- [x] create a chat
	- [x] edit a chat, [ ] implements securirty
	- [x] list all chats
	- [x] view all messages in a chat
	- [x] create a message in a chat	

# Technologies
	- Java
	- Spring Boot, Spring: Data JPA, Rest, Validator, MVC, ...
	- JUnit, Mockito
	- Maven
	- MySQL

# Requirements 
Your machine should have :
	- JDK 1.8 or later version installed 
	- Maven 3.5+
	- MySQL 5.7+
	
# Build
From the root of project directory (you will find pom.xml file there) run the following command
	
		mvn clean install
		Go to the target folder
		java -jar simplechat-0.0.1.jar
	
You also use the following command
		

		mvn spring-boot:run
	
# Test
The application will initialize some data from sample data set in import.sql in resource directory
## Using POSTMAN
### User list
- url: http://localhost:8080/user	(GET)	return paginated list of users, with page number 0 as default

- url: http://localhost:8080/users/0	(GET)	return paginated list of users, with page number 0 as requested, you can change the number to see next page if available 

Note: (username is unique)
### register/create user
url: http://localhost:8080/user (POST), sample data
 
		 {
		"id":"7",
		"username": "uzer7",
		"password": "1234",
		"firstName": "Python",
		"lastName": "Piper",
		"email": "test@gmail.com"
		}

### update user
url: http://localhost:8080/user (PATCH), sample data
 
		 {
		"id":"7",
		"username": "uzer7",
		"password": "1234",
		"firstName": "Python Update",
		"lastName": "Piper",
		"email": "test@gmail.com"
		}
		
### Chat list
- url: http://localhost:8080/chat	(GET)	return paginated list of chat, with page number 0 as default

- url: http://localhost:8080/chats/0	(GET)	(DESC sort by posted date)
return paginated list of chats, with page number 0 as requested, you can change the number to see next page if available 

### create chat
url: http://localhost:8080/chat (POST) , sample data
 
		 {
		"id":"5",
		"owner": "1",
		"message": "Chat message number 5 by user 1",
		"postedDate": "2017-10-04T22:44:30.652"
		}
		
### update chat
 url: http://localhost:8080/chat (PATCH), Sample data
 
  		{
		"id":"1",
		"owner": "1",
		"message": "Update chat id 1 chat date",
		"postedDate": "2016-10-04T22:44:30.652"
		}

### Message list
- url: http://localhost:8080/message	(GET)	return paginated list of messages, with page number 0 as default

- url: http://localhost:8080/messages/0	(GET)	(DESC sort by posted date)
return paginated list of messages, with page number 0 as requested, you can change the number to see next page if available 

### create message
url: http://localhost:8080/chat (POST) , sample data
 
		 {
		"id":"5",
		"user": "1",
		"chat": "1",
		"message": "message reply to chat id 1",
		"postedDate": "2014-05-04T22:44:30.652"
		}
		
### update message
 url: http://localhost:8080/chat (PATCH), Sample data
 
  		{
		"id":"1",
		"owner": "1",
		"message": "Update chat id 1 chat date",
		"postedDate": "2016-10-04T22:44:30.652"
		}

# Contributor

- Name : Khemroat Loem
- Email : khemroat@gmail.com
