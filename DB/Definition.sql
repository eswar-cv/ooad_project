create database PROJECT ; 
use PROJECT; 

CREATE TABLE ACCOUNT 
(
name VARCHAR(20),
USER_NAME VARCHAR(20) primary key, 
password varchar(20), 
user_type varchar(20) NOT NULL
);
CREATE TABLE COURSE
(
Course_id INT auto_increment PRIMARY KEY, 
c_name varchar(75), 
c_desc varchar(150)
);

CREATE TABLE ASSIGNMENT 
(
Assgn_id INT auto_increment primary key, 
c_id INT, 
deadline datetime, 
assgn_file longblob, 
instruc varchar(100), 
foreign key(c_id) references COURSE(Course_id)
);

CREATE TABLE ACC_COURSE
(
u_name varchar(20), 
c_id int, 
u_type varchar(20), 
primary key(S_name,c_id,u_type),
foreign key(S_name) references account(user_name), 
foreign key(c_id) references COURSE(Course_id));

CREATE TABLE Stud_Teacher
(
stud_name varchar(20), 
teach_name varchar(20),
c_id int, 
foreign key(stud_name) references account(user_name), 
foreign key(teach_name) references account(user_name), 
foreign key(c_id) references COURSE(Course_id)
);

CREATE TABLE Submission
(
stud_name varchar(20), 
s_id int auto_increment primary key, 
a_id int, 
c_id int, 
results int, 
comments varchar(50), 
remarks varchar(50), 
sub_file longblob, 
foreign key(stud_name) references account(user_name), 
foreign key(c_id) references COURSE(Course_id), 
foreign key(a_id) references ASSIGNMENT(assgn_id)
);

