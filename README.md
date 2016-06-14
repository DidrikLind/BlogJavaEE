# BlogJavaEE
A blog made with java EE technologies such as: JSF, CDI, EJB and JPA(eclipselink impl.).

___
###Overview of the Blog project: 
####ER_DIAGRAM
![alt text](https://raw.githubusercontent.com/DidrikLind/BlogJavaEE/master/erdiagram.png "Blog ER diagram")

####Technologies
I have been using java EE technologies such as
 1. JSF
 2. CDI
 3. EJB
 4. JPA - EclipseLink implementation

####The idea of my blog and what you can do
#####My idea of the blog project
Is to explore the functionality of JSF, CDI, EJB and JPA.
#####What you can do
___
###HOW TO RUN MY PROJECT
**Configuration Steps for the database and the persistence-unit**

1. Create a database(preferable in MySQL, otherwise change in the persistence.xml file the driver and download the driver you want for your specific RDBMS) with your name of choice, in the persistence.xml file I have named it "blogdatabase". JPA/EclipseLink will generate all the tables for you later on  
  * You can for example use [XAMPP](https://www.apachefriends.org/index.html) or [TOAD](https://www.toadworld.com/m/freeware) for managing the MySQL database.
  * Dont forget to change to your wanted username and password in the persistence.xml!
2. Since I use Glassfish 4.1 as an application server I will link you to the configuration part that I followed (if you want to use some other application server, then find out yourself how to configure this part)
  * How to configure Glassfish in the GUI(textual-guide, recommended): [CLick me!](http://dev.mysql.com/doc/connectors/en/connector-j-usagenotes-glassfish-config.html)
  * How to configure Glassfish in the GUI(youtube-guide):
  [CLick me!](https://www.youtube.com/watch?v=f1z-3zlkXj8)
  * You can download [JDBC Mysql driver](https://dev.mysql.com/downloads/connector/j/5.1.html) , if you dont have it already.
  * In the persistence.xml file I have named the jta-data-source "jdbc/MySQLDataSourceBlog", so dont forget to change that if you have named it "jdbc/MySQLDataSource" as in the recommended link above. This is your connection pool, so all the configurations are crucial here!
3. Now when you are going to run the project on the server (when everything hopefully works), you can just swap the values of the      		property tag with name="javax.persistence.schema-generation.database.action" and value="create" to none, drop and drop-and-create for given purpose. This is the key for generating your database as I mentioned in the first step above that JPA/EclipseLink will do :)

___


