# Natural-Language-Research

This repository contains the work for the Principles Of Software Engineering project.

1. ##MAVEN
The entire project is managed with Maven. [link to Maven](http://maven.apache.org) 
When you have a working installation cd into the root directory of your project and use: 

    mvn clean package javadoc:javadoc 

This command tells maven to complete the "clean" phase and the "package" which means that it's going to clean temporary files and target directories and then rebuild everything in order to deploy an application. In our case it is going to deploy a war file, which is a web application resource, suitable for TomCat. 

**WHEN YOU CHANGE SOMETHING, ALWAYS CHECK IF THE ENTIRE PROJECT COMPILES (WITH THE ABOVE COMMAND), AND THEN UPLOAD IT ON GITHUB**

2. ##STRUCTURE - MVC PATTERN

* The main/**database** folder contains all the SQL code to generate the database (e.g. CREATE DATABASE, CREATE TABLE) and to insert data into it (e.g. INSERT INTO TABLE...).
We are going to discuss together the database design to understand how we are going to implement the real database behind our webapp. 

* main/java/uk/uoa/cs/princEngSw/**database** contains all the java classes which are going to perform a query into the database. The most important thing inside this java classes are PreparedStatements to avoid SQLInjection 

* main/java/uk/uoa/cs/princEngSw/**resource** contains all the resources of our webapp: basically we need to wrap every concept of the tables represented into the database in a java class. (e.g. Student class with StudentID, Name, Surname, BirthDate etc...). This is the **Model** in a MVC pattern.

* main/java/uk/uoa/cs/princEngSw/**rest** This folder is used for the RestManager. I don't know if we are going to implement it, so we'll see. 

* java/uk/uoa/cs/princEngSw/**servlet** this folder contains the brain of our webapp. Every class is a Servlet which responds to user input on the website. If a user clicks a link, a Servlet is going to be instantiated to generate dynamic content and send it back to the user. This is the **Controller** in a MVC pattern.

* main/webapp contains HTML resources, JavaScript, CascadeStyleSheet and JavaServer Pages (JSP are the **View** in a MVC Pattern). Servlets are going to take this files, process them and send it to the user as a result of an action. 

* pom.xml, context.xml, web.xml are configuration files for the server and the database
