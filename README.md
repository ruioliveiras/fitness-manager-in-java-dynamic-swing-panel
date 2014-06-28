** \*=== FitenessUM ===\***
==============

**Developers:**

Andre Santos  -> andrerfcsantos AT gmail DOT com

Rui Camposinhos -> rui DOT camposinhos AT gmail DOT com

(Owner) Rui Oliveira -> rui96pedro AT hotmail DOT com

--------------
0) Abstract
1) Requirements
2) Implementation highlights


0) Abstract
==============

This project is a personal Fitness manager, was created for a practical work in University of Minho.

In this application it's possible to create new users, insert new Activities (like swim, cycling...) and automatically create is Personal records board.
Also it's possible for an Admin create new Events and simulate than.


1) Requirements
==============

Basic Requirements: 

 - Access the application with an email and password;
 - User should be able to:
 	- Add, update, create Activities;
 	- See a list of his activities ordered by date;
 	- See statics about time, calories, distance within a determined range;
 - There exists many Activities, with different attributes;
 - There must be a way to calculate the calories spend in a determined Activity;

Advanced Requirements

 - User can have friends, and see is activities;
 - User have a Record list;
 - Admin user can create an Event where:
 	- normal users can only adhere if fulfil the one pre-requirement;
 - An Event should be able to be simulated by the admin.

2) Implementation highlights
==============

 - MVC architecture
 - Java Swing windows created dynamically using Enums with the show values for each class.
 - Full Calorie calculation using MET (activity constant) or hearth frequency.
 - Dynamically calculation of records - fixing one variable and finding the best value to another.
 	-Example: activity cycling record 10 km, fix distance on 10 km and find best time.
 - Events result are randomly calculated using user history on the event activity, user performance on other activities and  weather if it is outdoor. Also there is a certain probability of give up (linear with the age)
 -
