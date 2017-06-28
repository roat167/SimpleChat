--Sample data set

--Table Users
insert into orachat.users(id, username, password, first_name, last_name, email) values(1,'java', '1234', 'khemroat', 'loem', 'khemroat@gmail.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(2,'dora', '1234', 'dora', 'khiev', 'dkheiv@mum.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(3,'dara', '1234', 'chandara', 'leang', 'cleang@mum.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(4,'kloem', '1234', 'k', 'lim', 'kloem@mum.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(5,'uzer1', '1234', 'khemroat', 'l', 'khemroatloem@live.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(10,'uzer2', '1234', 'chanpiseth', 'chea', 'cchea@mum.edu');

--Table Chat
insert into orachat.chat(id, user_id, message, posted_date) values(1, 1, 'First Posted by user Java id 1', '2017-03-04 22:44:30');
insert into orachat.chat(id, user_id, message, posted_date) values(2, 2, 'First Posted by user dora id 2', '2017-06-07 20:44:30');

--Table ChatMessage
insert into orachat.chat_message(id, user_id, chat_id, message, posted_date) values(1, 1, 1, 'reply to chat id 1', '2017-06-04 22:45:00');
insert into orachat.chat_message(id, user_id, chat_id, message, posted_date) values(2, 2, 2, 'reply to chat id 2 user 2', '2017-07-07 10:45:00');
insert into orachat.chat_message(id, user_id, chat_id, message, posted_date) values(3, 1, 2, 'reply to chat id 1 user 1', '2017-06-06 10:45:00');

