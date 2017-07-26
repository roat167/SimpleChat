# SimpleChat

This is a simple chat application that allows user to:

	- [x] register
	- [X] login implements JWT
	- [x] view profile
	- [x] edit profile 
	- [x] create a chat
	- [x] edit a chat
	- [x] edit profile/chat implements security(Only owner can edit)
	- [x] list all chats
	- [x] view all messages in a chat
	- [x] create a message in a chat
	- [x] logout

# Technologies
	- Java
	- Spring Boot, Spring: Data JPA, Rest, Validator, MVC, ...
	- JUnit, Mockito
	- Maven
	- MySQL
	- Log4j2
	- JSON Web Token
	- Redis

# Requirements 
What you will need:
	- JDK 1.8 or later version installed 
	- Maven 3.5+
	- MySQL 5.7+
	- Redis 

#### Note: For Windows user: To install Redis on follow https://redislabs.com/ebook/appendix-a/a-3-installing-on-windows/a-3-2-installing-redis-on-window/)
	
### Database configuration
- You can change the database's connection and name in application.properties, you can find it inside resources directory
- Here I'm using root as my username and password, you can change according to your current configuration
 <img src="https://github.com/roat167/SimpleChat/blob/master/screenshot/databaseConfig.jpg" width="800"/>
	
# Build
From the root of project directory (you will find pom.xml file there) run the following command
	
		mvn clean install

		java -jar target/simplechat-0.0.1.jar
	
You also use the following command		

		mvn spring-boot:run
	
# Test
The application will initialize some data from sample data set in import.sql in resource directory.
- Note: register and login request won't be filter with jwt. Other request required a valid token from successful login
## Using POSTMAN

### Register
- url: http://localhost:8080/register	(POST)	
		
		{		
		"username": "reg",
		"password": "1234",
		"firstName": "reg",
		"lastName": "user",
		"email": "test@gmail.com"
		}

### Login
- url: http://localhost:8080/login	(POST)	sample data

		{		
		"username": "reg",
		"password": "1234"
		}

- Upon successful login it will return  jwt token

### Logout
- url: http://localhost:8080/logout	(GET)
- It should return successfully logout message

### User list
- url: http://localhost:8080/api/users	(GET)	return list of users

- url: http://localhost:8080/api/users?page=0	(GET)	return paginated list of users, with page number 0 as requested, you can change the number to see next page if available 

Note: (username is unique)
### create user
url: http://localhost:8080/api/users (POST), sample data (id is not required, see register )
 
		 {
		"id":"7",
		"username": "uzer7",
		"password": "1234",
		"firstName": "Python",
		"lastName": "Piper",
		"email": "test@gmail.com"
		}		 
### view user
url: http://localhost:8080/api/users/1 (GET), 1 is user id

### update user
url: http://localhost:8080/api/users (PATCH), sample data
 
		 {
		"id":"7",
		"username": "uzer7",
		"password": "1234",
		"firstName": "Python Update",
		"lastName": "Piper",
		"email": "test@gmail.com"
		}
		
### Chat list
- url: http://localhost:8080/api/chats	(GET)	return paginated list of chat, with page number 0 as default

- url: http://localhost:8080/api/chats?page=0	(GET)	(DESC sort by posted date)
return paginated list of chats, with page number 0 as requested, you can change the number to see next page if available 

### create chat
url: http://localhost:8080/api/chats (POST) , sample data
 
		 {
		"id":"5",
		"owner": "1",
		"message": "Chat message number 5 by user 1",
		"postedDate": "2017-10-04T22:44:30.652"
		}


### view chat
url: http://localhost:8080/api/chats/5 (GET), 5 is chat id

### update chat
 url: http://localhost:8080/api/chats (PATCH), Sample data
 
  		{
		"id":"1",
		"owner": "1",
		"message": "Update chat id 1 chat date",
		"postedDate": "2016-10-04T22:44:30.652"
		}

### Message list
- url: http://localhost:8080/api/messages	(GET)	return paginated list of messages, with page number 0 as default

- url: http://localhost:8080/api/messages?page=0	(GET)	(DESC sort by posted date)
return paginated list of messages, with page number 0 as requested, you can change the number to see next page if available 

### create message
url: http://localhost:8080/api/messages (POST) , sample data
 
		 {
		"id":"5",
		"user": "1",
		"chat": "1",
		"message": "message reply to chat id 1",
		"postedDate": "2014-05-04T22:44:30.652"
		}

### view message
url: http://localhost:8080/api/messages/1 (GET), 1 is chat message id

### update message
 url: http://localhost:8080/api/messages (PATCH), Sample data
 
  		{
		"id":"1",
		"owner": "1",
		"chat": "1",
		"message": "Update message id 1 chat date",
		"postedDate": "2016-11-04T22:44:30.652"
		}


### Versioning
The api path '/api/' can be change in application.properties file. You can find the file under resources directory

		api.path: api

# Contributor

- Name : Khemroat Loem
- Email : khemroat@gmail.com
