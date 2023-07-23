# Study project: Auth server using Kotlin and SpringBoot.

The server contains authentication and users configured.
Users also have roles configured, therefore some actions are protected only for logged in users or even only for users with specfic roles such as admin.

The project simulates an article and reading lists application. Articles and lists have a many to many relationship.
Also, on this sutdy project I wanted to implement all CRUD actions, therefore they are all implemented for all models (users, articles and articles reading lists).

Unit testing for all service classes was implemented to cover most important methods.

## Git flow
The project follow the best practices on git flow. Bellow is a brief description of all branches used:
 - **main**: Release branch, represents all store releases;
 - **development**: Development branch, where all features branches starts from and usually the newest and most stable code;
 - **FEATURE/{featureName}**: The "FEATURE folder" wraps all feature branches. A feature branch will start from development, ex.: FEATURE/home. And all the feature work will be done at the feature branch until it is ready to be merged on development branch.
 - **DEV/{devName}/{taskName}**: The "DEV folder" wraps all the developer tasks branches, ex.: DEV/Bressam/articleService. All work is done on developer task branch and merged to destination branch, usually the FEATURE/{featureName} branch.
 - **hotfix**: Hotfix branch is used to fix major updates on release build (latest version on main branch). This is important to be able to fix a major issue on release branch without carrying all the new development branch that store unstable, and usually not validated yet, features.

The project also uses Git Tags to mark important release milestones.
### Project Git Flow image:
<img width=1200px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/gitflow.png">

## Architecture
Project uses sort of MVC architecture, more specifically a Model-Controller-Service-Repository architecture. Some important design patterns are used, mostly dependency injection by Spring beans.
 - Controller: handles all REST interface, parsing data to request objects and also maps result of service actions to response objects.
 - Service: Contains all business logic and communicate with repository classes to retrieve data. It may combine multiples repositories classes to achieve the result requested by the controller.
 - Repository: Knows how to communicate with its database table, and can apply column filters.
 - Model: Objetcs of database elements. Also contains methods to be parsed to a response or to be parsed from request objects.

 <img width=460px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/architecture.png">

## Authentication
For user authentication tokens are used by JWT (JSON web tokens).
To setup authentication some security classes and JWT classes needed to be created:

<img width=360px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/securityClasses.png">

Filterchain used also excluded some endpoints from authentication validation, since they need to operate without authentication (example: user creation):
<img width=800px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/filterChain.png">

Also all JWT logic:
<img width=800px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/JWT.png">

## Bootstrap
There are also bootstraps configured for all models, so the server always startsup with an admin account, default articles and default reading lists (favorites and read later).

Articles List:
<img width=800px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/articlesListBootstrap.png">

Users:
<img width=800px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/usersBootstrap.png">

## Validation
Projet also has a "postman" set up, so it is possible to test requests on intellij:
Articles:
<img width=800px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/articlespostman.png">

Users:
<img width=800px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/userpostman.png">

## Testing
Unit tests were implemented on server. Mockk library was used for that purpose, since it makes very easy to implement stub behaviors.
Examples:
 Users:
 <img width=800px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/usersUnitTesting.png">
 
 Articles:
 <img width=800px src="https://github.com/Bressam/authserver-study-springboot/blob/main/SampleResources/articlesUnitTesting.png">
