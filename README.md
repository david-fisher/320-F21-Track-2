Board Game Creator
Github Link

Board Game Creator is a java application that allows you to create, design, and play board games. It is possible to create classic board games with custom rules, adding a twist to many home classics.

*Any form of extra documentation goes here*

Table of Contents


Board Game Creator Information
How to Compile Board Game Creator into a JAR file
How to run Board Game Creator
Introduction
How to create a board game
Gameboard Editor
Object Editor
Rule Editor
How to edit an existing board game
How to start playing a new board game
How to resume playing an existing board game
FAQ
How to Compile Board Game Creator into a JAR File


Assuming you are using an IDE, the following steps will help you to compile the project into an executable JAR file. 

Open “file”
Select “Project Structure” from the drop down menu
Select “Artifacts” from the side menu, then the + button on top
Select “JAR”, then “from modules with dependencies…”

Alternatively, you may utilize the executable JAR file provided and it should run all the same.
How to Run Board Game Creator


Preamble

Board Game Creator comes as a stand-alone executable JAR file, able to be run without any additional dependencies. It is recommended that BoardGameCreator.jar be put within a folder, as it will save any created board games within a folder in the same directory as the executable. 

In order to access the main features of this program, you must first download and run BoardGameCreator.jar. Assuming you have done so, you will be prompted with the Main Menu.

How to Create a Board Game

Once at the Main Menu, in order to create a board game from scratch do the following:

Select “Create Game”
Enter a name for your game
Select which aspect of the game you wish to edit
Upon selecting “Return to Main”, any changes you have made will automatically be saved.

Gameboard Editor

Upon editing the Game Board for the first time, you will be prompted to adjust the number of Rows and Columns using the boxes on the right hand side of the screen. If left blank, the board will initialize to an 8x8 grid, otherwise it will be MxN with M rows and N columns.

The user is able to create tiles and specify their connections, color, shape, etc. These functionalities are further outlined in the “Help” section of the editor, attained by selecting the “Help” button on the right hand side of the screen.

Object Editor

Entering the Object Editor will present you with a menu consisting of various tabs at the top, each representing different objects that can exist in a Board Game. Each tab contains different editable fields given the context of the object, for example a card can contain Names, Icons, Colors, and Events, whereas a deck consists solely of cards.

In order to properly utilize the Object Editor, filling out the fields and selecting “Done” at the bottom will create the object or game piece, which can then be assigned to a player via the “Player” tab.

Rule Editor

Selecting “Rule Editor” will show a list of currently existing rules. Opting to add an event will cause the program to request a name for it, after which you will be brought to a visual programming interface that will allow users to create and program new rules for pieces and AIs to follow. As the user creates more rule nodes, they are able to specify the order by which they are computed.

For better understanding of how the rules are implemented and calculated by the Rule Engine, you may refer to the following document:

Rule Engine Documentation

How to Edit an existing Board Game

The editor allows for editing game boards that have been previously created by a user. In order to do this, simply do the following:

From the Main Menu, select “Edit Game” 
Select the game you wish to edit
Select “Edit Game”
Refer to the above instructions for further information regarding the editors

How to start playing a new Board Game

In order to begin playing a new board game, do the following:

From the Main Menu, select “Play Game”
Select the game you wish to play from the top row labeled “New Games”
Select “Start New Game”
Set the number of players, colors, and names,
(Optional) Enable tutorial mode and/or shuffle the turn order via the buttons below
Select “Start Game”

How to Edit an existing Board Game

Upon exiting a game in progress, assuming you have saved you can return to the game by doing the following:
	
From the Main Menu, select “Play Game”
Select the game you wish to resume from the bottom row labeled “Saved Games”
Select “Resume Game”


Frequently Asked Questions:

*Questions and Answers*






