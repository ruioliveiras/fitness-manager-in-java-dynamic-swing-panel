**FitenessUM**
==============

**Developers:**

Andre Santos andrerfcsantos AT gmail DOT com

Rui Camposinhos rui DOT camposinhos AT gmail DOT com

(Owner) Rui Oliveira rui96pedro AT hotmail DOT com

--------------

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





**Source do Projeto**
Recomendado o uso de BlueJ, download: https://www.eclipse.org/downloads/. Eventualmente pode ser usado eclipse.

Projeto segue a arquitetura de MVC Model-View-Controller


**TODO (de acordo com reuniao de dia 05/05/2014):**

- Ver TODOs especificos nos ficheiros .java --- DONE

-Classe de conjunto de Users (rede social) --- MANAGER???

-Classe Calendario (cada User deve ter um calendario de actividades - requisito!) --- DONE (actividadesUser + actividadesEntre)

-Classes especializadas de cada actividade em particular --- DONE (completar)

-Recordes (ref. facebook Oliveira) --- falta comparar recordes

-Package eventos: Classe Evento + Classe Simulacao

-Classe Evento: {Conjunto Users, Actividade, Categoria, Data evento, Data inscricao, Nome, pre-requisito inscricao (long tempo/distancia), numero maximo de participantes}

-Classe DataSet: {HashMap Users, HashMap Eventos, ...} --- DONE (80%)
