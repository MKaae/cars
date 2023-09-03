# Cars project

This is the cars project big readme file with added navigation for future weeks.

## Table of Contents

- [Week 1](#week-1)
- [Week 2](#week-2)
- [Week 3](#week-3)

## Introduction

This is a compiled readme file for easier navigation the later we get in the weeks.

### Week 1

Answers for teacher's questions.


(Wasn't specified if your TA was foreign so i will do hand-in in english.)

The idea with, and reasons for why to use, a ORM-mapper
<span style="color:green">
   Increases the speed of development since you have to write less code, thus also decreaasing the cost of development. 
   It makes it easier to interact with the database, but also risky so we have to check that the database values doesn't get wierd, since it has Hibernate     has standard values.
</span>   
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

### Week 2

What are the benefits of using a RESTful API Det er meget hurtigere at kode da man skal skrive mindre. Samtidig sender det filer som JSON eller lignende som der er en standard og super fleksibelt. Der er også nemme at integrere da man bare kan skrive en full API dokumentation som alle kan bruge.

What is JSON, and why does JSON fit so well with REST? JSON er den måde vi modtager og sender data på. De passer perfekt fordi vi kan læse JSON som mennesker da det ikke står for meget i kodesprog. Samtidig kan JSON filer læses af næste alle teknologier. De er også gode til at sende data med da de har ting som array indbygget. Da de også bliver brugt som en standard er der en enorm mængde værktøjer til at håndtere JSON.

How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data -> Focus on your use of DTO's Jeg har mine entity klasses. Ud fra den har jeg lavet mine DTO'er (Reponse&Request) der indeholder de felter jeg gerne vil sende. Det gør jeg kan seperare det jeg ikke vil sende med. Så lavede jeg min controller og valgte hvilke endpoints jeg gerne ville have. Ud fra den har jeg så skrevet classService.Metode() og importeret metoden i den tilhørende serviceklasse. Inde i min serviceklasse har jeg så al logikken der fortæller hvad den skal gøre.

What is the advantage of using using DTOs to separate api from data structure when designing rest endpoints Vi kan vælge hvilke data der skal sendes, hvis vi ikke bruger DTO's sender vi bare hele databasen når vi sender noget. Det kan blandt andet være et problem når man har at gøre med passwords, eller følsomme informationer for dem der er i databasen.

Explain shortly the concept mocking in relation to software testing Mocking er at man laver en dummy implementation af en klasse. Det betyder vi kan teste uden at skulle implementere selve klassen selvom vi har dependencies til den. Det kan være relevant i flere tilfælde, fx. hvis vi ikke har lavet den klasse vi skal bruge til at teste endnu. Eller hvis vi kun har lavet den ene del af klassen, eller fordi vi skal teste noget seperat.

How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code Jeg brugte begge dele. MemberService test er brugt med H2test, se linje 19 @MemberServiceH2Test. CarService er test brugt med Mockito se linje 24 @CarServiceMockitoTest.

Explain the concept Build Server and the role Github Actions play here Github actions er vores build server med vores workflow. Vores CI/CD kører gennem vores github så når vi uploader noget kører den både building og deployment for os.

Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s) Når vi kigger i vores yml fil kan vi se hvornår maven bliver brugt. Den kører Build with Maven når jeg har uploadet tingene til github, da mit script starter på -master branch push. Det står på linje 24 i min yml fil.

Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin Jeg har brugt PaaS, platform as a service hvor mit projekt kører inde på Azure, og jeg har brugt DBaaS, mysql database hvor mit MYSQL kører.

### Week 3

sssss
