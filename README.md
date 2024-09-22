# My Personal Project

## Fantasy Football Team Application 

The **fantasy football team application** will work like any other football manager career:
- *The user can create their own team, and will be able to add and remove players, along with being able to access the statistics of the players. They will also be able to add goals and assists to their players. They can also search and filter the players based on certain criteria.*


- *The users would mainly consist of people who follow football (soccer), and would like an interactive experience of managing a football team.*

The idea of a fantasy football team application appealed to me because I love football and I enjoy playing **the FIFA career mode**, which is the main inspiration of this project. 



## User Stories
- *As a user, I want to be able to create and name my team.*
- *As a user, I want to be able to add a new player to my team.*
- *As a user, I want to be able to remove a player from my team.*
- *As a user, I want to be able to view the expectations for the season.*
- *As a user, I want to be able to learn my club's history.*
- *As a user, I want to be able to view the statistics of my players.*
- *As a user, I want to add goals and assists to my players.*
- *As a user, I want to search for a player in my club.*
- *As a user, I want to search for players playing a particular position in my club.*
- *As a user, I want to have the option to save my team to the file when I quit the application.* 
- *As a user, I want to have the option to load my team from the file when I start the application.*


## Instructions for Grader

- *You can generate the first event (adding Xs to Y) by clicking on the "Add Player" button on the main menu which adds a player to the team.*
- *You can generate the second related event by clicking on the "Display Player" button which will display all the players and their stats.*
- *You can generate the third related event by clicking on the "Search by Name" button which will display that player and the statistics.*
- *You can locate my visual component on the right side of the main menu.*
- *You can save the state by clicking on "YES" when prompted to save by the quit frame.*
- *You can load the existing state by clicking on "LOAD TEAM" in the opening frame.*

## Phase 4: Task 2
Wed Nov 30 21:22:18 PST 2022 \
New team was created

Wed Nov 30 21:22:39 PST 2022\
Player was added

Wed Nov 30 21:22:51 PST 2022\
Player has scored 20 goals

Wed Nov 30 21:22:57 PST 2022\
Player was removed


Process finished with exit code 0

## Phase 4: Task 3
After reviewing my UML class diagram, I'm satisfied with the overall structure for the complexity of my application. Although I believe I have appropriately created classes and divided responsibility, especially in the ui package, I do think I could've done better in two aspects:
- *Introducing bi-directionality for the player and team classes.*
- *Implementing more abstraction for similar methods in the player and team classes.*


## Citations

The code for phase-2 (persistence package and its tests) has been heavily influenced by the supplied Workroom example for CPSC 210.

The link for phase-2 code inspiration: *https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo*

The link for the visual component in GUI: *https://www.researchgate.net/figure/A-football-team-with-a-4-4-2-formation_fig1_337482722*

The Event and EventLog classes were taken from the Alarm System example for CPSC 210.