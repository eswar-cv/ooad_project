-------------------------------------------ACCOUNT 
 
 --Admin
INSERT INTO ACCOUNT VALUES("Dhanushya","dhanu","dhanu","admin");

--Student 

INSERT INTO ACCOUNT VALUES("Lavanya","lavu","lavu","student");
INSERT INTO ACCOUNT VALUES("Eswar","eshu","eshu","student");
INSERT INTO ACCOUNT VALUES("Ashritha","ashu","ashu","student");
INSERT INTO ACCOUNT VALUES("Arun","aru","aru","student");
INSERT INTO ACCOUNT VALUES("Routray","rou","rou","student");
INSERT INTO ACCOUNT VALUES("Tosh","toshu","toshu","student");
INSERT INTO ACCOUNT VALUES("Swathi","swu","swu","student");
INSERT INTO ACCOUNT VALUES("Gowri","gowru","gowru","student");
INSERT INTO ACCOUNT VALUES("Sanjeev","sanju","sanju","student");
INSERT INTO ACCOUNT VALUES("Aadhi","aadhu","aadhu","admin");

--Teacher 

INSERT INTO ACCOUNT VALUES("Nagu","nagu","nagu","teacher");
INSERT INTO ACCOUNT VALUES("Shanthanu","shanthu","shanthu","teacher");
INSERT INTO ACCOUNT VALUES("Pallavi","pallu","pallu","teacher");
INSERT INTO ACCOUNT VALUES("Rohith","rohu","rohu","teacher");
INSERT INTO ACCOUNT VALUES("Rishab","rishu","rishu","teacher");

--TA 

INSERT INTO ACCOUNT VALUES("Saanu","saanu","saanu","TA");
INSERT INTO ACCOUNT VALUES("Kamlesh","kamlu","kamlu","TA");
INSERT INTO ACCOUNT VALUES("Divyesh","divyu","divyu","TA");
INSERT INTO ACCOUNT VALUES("Sushmita","su","su","TA");
INSERT INTO ACCOUNT VALUES("Vandana","vanu","vanu","TA");


--------------------------------------------COURSE 

INSERT INTO COURSE VALUES("1","Compiler Design","It is a course describing all the phases of building a compiler.");
INSERT INTO COURSE VALUES("2","Cloud Computing","It is a course consisting of all the tech stack involving Cloud.");
INSERT INTO COURSE VALUES("3","Object Oriented Programming","It is a course involving Object oriented concepts.");
INSERT INTO COURSE VALUES("4","Natural Language Processing","Course decribing text processing tools and techniques.");
INSERT INTO COURSE VALUES("5","Network Analysis and Mining","Course to help learn analysing networks and making inferences.");

------------------------------------------ACC_COURSE 

--STUDENT 

INSERT INTO ACC_COURSE VALUES("lavu","1","student");
INSERT INTO ACC_COURSE VALUES("lavu","2","student");
INSERT INTO ACC_COURSE VALUES("eshu","3","student");
INSERT INTO ACC_COURSE VALUES("eshu","4","student");
INSERT INTO ACC_COURSE VALUES("ashu","5","student");
INSERT INTO ACC_COURSE VALUES("ashu","1","student");
INSERT INTO ACC_COURSE VALUES("aru","2","student");
INSERT INTO ACC_COURSE VALUES("aru","3","student");
INSERT INTO ACC_COURSE VALUES("rou","4","student");
INSERT INTO ACC_COURSE VALUES("rou","5","student");
INSERT INTO ACC_COURSE VALUES("toshu","1","student");
INSERT INTO ACC_COURSE VALUES("toshu","2","student");
INSERT INTO ACC_COURSE VALUES("swu","3","student");
INSERT INTO ACC_COURSE VALUES("swu","4","student");
INSERT INTO ACC_COURSE VALUES("gowru","5","student");
INSERT INTO ACC_COURSE VALUES("gowru","1","student");
INSERT INTO ACC_COURSE VALUES("sanju","2","student");
INSERT INTO ACC_COURSE VALUES("sanju","3","student");
INSERT INTO ACC_COURSE VALUES("aadhu","4","student");
INSERT INTO ACC_COURSE VALUES("aadhu","5","student");

--TEACHER 

INSERT INTO ACC_COURSE VALUES("nagu","1","teacher");
INSERT INTO ACC_COURSE VALUES("shanthu","2","teacher");
INSERT INTO ACC_COURSE VALUES("pallu","3","teacher");
INSERT INTO ACC_COURSE VALUES("rohu","4","teacher");
INSERT INTO ACC_COURSE VALUES("rishu","5","teacher");

--TA 

INSERT INTO ACC_COURSE VALUES("saanu","1","TA");
INSERT INTO ACC_COURSE VALUES("kamlu","2","TA");
INSERT INTO ACC_COURSE VALUES("divyu","3","TA");
INSERT INTO ACC_COURSE VALUES("su","4","TA");
INSERT INTO ACC_COURSE VALUES("vanu","5","TA");


--------------------------STUD_TEACHER

INSERT INTO STUD_TEACHER VALUES("lavu","nagu","1");
INSERT INTO STUD_TEACHER VALUES("lavu","shanthu","2");
INSERT INTO STUD_TEACHER VALUES("eshu","pallu","3");
INSERT INTO STUD_TEACHER VALUES("eshu","rohu","4");
INSERT INTO STUD_TEACHER VALUES("ashu","rishu","5");
INSERT INTO STUD_TEACHER VALUES("ashu","nagu","1");
INSERT INTO STUD_TEACHER VALUES("aru","shanthu","2");
INSERT INTO STUD_TEACHER VALUES("aru","pallu","3");
INSERT INTO STUD_TEACHER VALUES("rou","rohu","4");
INSERT INTO STUD_TEACHER VALUES("rou","rishu","5");
INSERT INTO STUD_TEACHER VALUES("toshu","nagu","1");
INSERT INTO STUD_TEACHER VALUES("toshu","shanthu","2");
INSERT INTO STUD_TEACHER VALUES("swu","pallu","3");
INSERT INTO STUD_TEACHER VALUES("swu","rohu","4");
INSERT INTO STUD_TEACHER VALUES("gowru","rishu","5");
INSERT INTO STUD_TEACHER VALUES("gowru","nagu","1");
INSERT INTO STUD_TEACHER VALUES("sanju","shanthu","2");
INSERT INTO STUD_TEACHER VALUES("sanju","pallu","3");
INSERT INTO STUD_TEACHER VALUES("aadhu","rohu","4");
INSERT INTO STUD_TEACHER VALUES("aadhu","rishu","5");