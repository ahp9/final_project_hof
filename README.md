# Lokaverkefni 2024 - Hugbúnaðarhönnun og forritun
Ástríður Haraldsdóttir Passauer og Sylvía Hanna Tyrfingsdóttir

## Yfirlit
Ákveðið var að taka gamalt verkefni úr Viðmótsforritun 2022 en ákveðið var að taka út JavaFX virknina.
Verkefnið er útfærsla á BlackJack og er leikinn .....

## Uppsetning

### Forkröfur
Athugið að eftirfarandi þarf að vera uppsett á þínu tölvukerfi:
- Java Development Kit (JDK)

### Klóna repo
Til þess að geta keyrt verkefni locally þarf að ná í repo og klóna repoið með eftirfarandi skipun
```sh
git clone https://github.com/ahp9/final_project_hof.git
```

### Maven
Ef einstaklingur er með Maven eru eftirfarandi skipanir keyrðar:

Þýða (e. compile) verkefnið
```sh
mvn compile
```
Til að þjappa (e. package) verkefninu inn í JAR skrá:
```sh
mvn package
```
Til að búa til vefskýrslu skaltu keyra:
```sh
mvn site
```
Til að keyra verkefnið skaltu nota eftirfarandi skipun:
```sh
mvn exec:java
```

### Keyra án Maven
Ef einstaklingur er ekki með Maven getur verkefnið verið keyrt með eftirfarandi skipun:
```sh
.\runjar.cmd
```