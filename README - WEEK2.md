Jeg er gået igang med at lave en compiled README fil inde i README.md skriv gerne hvis jeg kan fortsætte med det ellers ender vi med 100.000 README filer tænker jeg.

1. What are the benefits of using a RESTful API 

Det er meget hurtigere at kode da man skal skrive mindre. Samtidig sender det filer som JSON eller lignende som der er en standard og super fleksibelt. Der er også nemme at integrere da man bare kan skrive en full API dokumentation som alle kan bruge.

2. What is JSON, and why does JSON fit so well with REST? 

JSON er den måde vi modtager og sender data på. De passer perfekt fordi vi kan læse JSON som mennesker da det ikke står for meget i kodesprog. Samtidig kan JSON filer læses af næste alle teknologier. De er også gode til at sende data med da de har ting som array indbygget. Da de også bliver brugt som en standard er der en enorm mængde værktøjer til at håndtere JSON.

3. How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data -> Focus on your use of DTO's 

Jeg har mine entity klasses. Ud fra den har jeg lavet mine DTO'er (Reponse&Request) der indeholder de felter jeg gerne vil sende. Det gør jeg kan seperare det jeg ikke vil sende med. Så lavede jeg min controller og valgte hvilke endpoints jeg gerne ville have. Ud fra den har jeg så skrevet classService.Metode() og importeret metoden i den tilhørende serviceklasse. Inde i min serviceklasse har jeg så al logikken der fortæller hvad den skal gøre.

4. What is the advantage of using using DTOs to separate api from data structure when designing rest endpoints

Vi kan vælge hvilke data der skal sendes, hvis vi ikke bruger DTO's sender vi bare hele databasen når vi sender noget. Det kan blandt andet være et problem når man har at gøre med passwords, eller følsomme informationer for dem der er i databasen.

5. Explain shortly the concept mocking in relation to software testing 

Mocking er at man laver en dummy implementation af en klasse. Det betyder vi kan teste uden at skulle implementere selve klassen selvom vi har dependencies til den. Det kan være relevant i flere tilfælde, fx. hvis vi ikke har lavet den klasse vi skal bruge til at teste endnu. Eller hvis vi kun har lavet den ene del af klassen, eller fordi vi skal teste noget seperat.

6. How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code 

Jeg brugte begge dele. MemberService test er brugt med H2test, se linje 19 @MemberServiceH2Test. CarService er test brugt med Mockito se linje 24 @CarServiceMockitoTest.

7. Explain the concept Build Server and the role Github Actions play here 

Github actions er vores build server med vores workflow. Vores CI/CD kører gennem vores github så når vi uploader noget kører den både building og deployment for os.

8. Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s) 

Når vi kigger i vores yml fil kan vi se hvornår maven bliver brugt. Den kører Build with Maven når jeg har uploadet tingene til github, da mit script starter på -master branch push. Det står på linje 24 i min yml fil.

9. Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin 

Jeg har brugt PaaS, platform as a service hvor mit projekt kører inde på Azure, og jeg har brugt DBaaS, mysql database hvor mit MYSQL kører.
