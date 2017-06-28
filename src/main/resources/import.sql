--Sample data set

--Table Users
insert into orachat.users(id, username, password, first_name, last_name, email) values(1,'java', '1234', 'khemroat', 'loem', 'khemroat@gmail.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(2,'dora', '1234', 'dora', 'khiev', 'dkheiv@mum.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(3,'dara', '1234', 'chandara', 'leang', 'cleang@mum.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(4,'kloem', '1234', 'k', 'lim', 'kloem@mum.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(5,'uzer1', '1234', 'khemroat', 'l', 'khemroatloem@live.edu');
insert into orachat.users(id, username, password, first_name, last_name, email) values(10,'uzer2', '1234', 'chanpiseth', 'chea', 'cchea@mum.edu');

--Table Chat
insert into orachat.chat(id, user_id, message, posted_date) values(1, 1, 'First Posted by user Java id 1', '2017-10-04 22:44:30');
insert into orachat.chat(id, user_id, message, posted_date) values(2, 2, 'First Posted by user dora id 2', '2017-05-04 22:44:30');
