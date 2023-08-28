Answers for teacher's questions.


(Wasn't specified if your TA was foreign so i will do hand-in in english.)

The idea with, and reasons for why to use, a ORM-mapper

   Increases the speed of development since you have to write less code, thus also decreaasing the cost of development. 
   It makes it easier to interact with the database, but also risky so we have to check that the database values doesn't get wierd, since it has Hibernate has standard values.
   
The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected

   JPA is a specification that facilitiates object-relational mapping, it's used to access, manage and persist data between Java object and relational database.
   Hibernate, is a ORM tool and a Java framework that handles the implementation of JPA.
   Spring Data JPA is another layer of abstraction to reduce the amount of code we need, it is not a JPA provider it's just a framework to help on top of Hibernate.
   
How to create simple Java entities and map them to a database via the Spring Data API

   We create a java class for the object that we need created, we mark it with @Entity and then we make a repository interface for the class and extend it with JpaRepository.
   Inside a datasetup/handler we create the objects that we need to create for the database.
   
How to control the mapping between individual fields in an Entity class and their matching columns in the database

   We can control the mapping with @Column annotation, and chose our own preference like name, length etc.
   
How to auto generate IDs, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)

   You give it a @Id annotation and for autoincrement we use this after the @Id annotation, @GeneratedValue(strategy = GenerationType.IDENTITY).
   
How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern

   We define repositories by using @Repository and same for queries we use @Query.
   
How to write simple "integration" tests, using H2 as a mock-database instead of MySQL

   We use the @DataJpaTest and autowire the repository we need to test. Then we use some beforeEach or beforeAll data to test on, you can autogenerate the testclass with most IDE's.
   
How to add (dev) connection details for you local MySQL database
   You use the application properties to write it in. If you want secret connection details you can hide them and use them in your configuration under enviormental variables.
