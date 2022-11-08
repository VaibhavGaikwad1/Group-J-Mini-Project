use quiz_data;
create table Quiz_Data(
Que_No int primary key not null auto_increment,
Question varchar(250),
Option_1 varchar(50),
Option_2 varchar(50),
Option_3 varchar(50),
Option_4 varchar(50),
Correct_Answer varchar(50)
);
create table Student(
Student_ID int primary key not null auto_increment,
Student_Name varchar(100),
Marks int,
Grade varchar(50)
);
