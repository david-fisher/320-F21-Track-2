# 320-F21-Track-2
The 'Boardgame Engineering & Recreation Kit' (BERK). The BERK is a versatile system that allows users to express their creativity and share it with others to play. The BERK was writen in Java by five student teams for CS320 Fall 2021. The BERK is currently hosted [here](https://github.com/david-fisher/320-F21-Track-2).

## Teams 
- [Team GKNEW](https://github.com/david-fisher/320-F21-Track-2/wiki/Team-GKNEW)
  -   Rule Language & Engine 
- [Team Minjex](https://github.com/david-fisher/320-F21-Track-2/wiki/Team-Minjex)
  -   Gameplay UI
- [Team Odyssey](https://github.com/david-fisher/320-F21-Track-2/wiki/Team-Odyssey)
  -   Rule and Game Object Editor UI  
- [Team UMass Dining](https://github.com/david-fisher/320-F21-Track-2/wiki/Team-UMass-Dining)
  -   Object Modeling and Persistence Team
- [Team Wave](https://github.com/david-fisher/320-F21-Track-2/wiki/Team-Wave)
  -   Board Game Editor UI

# Branches used during Demo-or-Die
- Team Minjex used the [Minjex](https://github.com/david-fisher/320-F21-Track-2/tree/Minjex) branch to demonstrate the most integrated version of our software.
- Team Odyssey used the [Odyssey](https://github.com/david-fisher/320-F21-Track-2/tree/Odyssey) branch to demonstrate features in the most updated version of their editor UIs that were not pushed to the main.
- Team GKNEW used the [gknew](https://github.com/david-fisher/320-F21-Track-2/tree/gknew) branch to demonstrate their AI features not yet pushed to the main branch.

# Setup
### Requirements
- [Java 9](https://www.oracle.com/java/technologies/javase/javase9-archive-downloads.html)
- [JFoenix](https://github.com/sshahine/JFoenix)
- [SnakeYaml](https://github.com/david-fisher/320-F21-Track-2/tree/readme-update/lib)

### How to compile into a JAR file
Assuming you are using an IDE, the following steps will help you to compile the project into an executable JAR file. 
- Open “file”
- Select “Project Structure” from the drop down menu
- Select “Artifacts” from the side menu, then the + button on top
- Select “JAR”, then “from modules with dependencies…”

Alternatively, you may utilize the executable JAR file provided and it should run all the same.

### How to Create a Board Game

Once at the Main Menu, in order to create a board game from scratch do the following:
- Select “Create Game”
- Enter a name for your game
- Select which aspect of the game you wish to edit
- Upon selecting “Return to Main”, any changes you have made will automatically be saved.

### Gameboard Editor
Upon editing the Game Board for the first time, you will be prompted to adjust the number of Rows and Columns using the boxes on the right hand side of the screen. If left blank, the board will initialize to an 8x8 grid, otherwise it will be MxN with M rows and N columns.

The user is able to create tiles and specify their connections, color, shape, etc. These functionalities are further outlined in the “Help” section of the editor, attained by selecting the “Help” button on the right hand side of the screen.

### Object Editor
Entering the Object Editor will present you with a menu consisting of various tabs at the top, each representing different objects that can exist in a Board Game. Each tab contains different editable fields given the context of the object, for example a card can contain Names, Icons, Colors, and Events, whereas a deck consists solely of cards.

In order to properly utilize the Object Editor, filling out the fields and selecting “Done” at the bottom will create the object or game piece, which can then be assigned to a player via the “Player” tab.

### Rule Editor
Selecting “Rule Editor” will show a list of currently existing rules. Opting to add an event will cause the program to request a name for it, after which you will be brought to a visual programming interface that will allow users to create and program new rules for pieces and AIs to follow. As the user creates more rule nodes, they are able to specify the order by which they are computed.

For better understanding of how the rules are implemented and calculated by the Rule Engine, you may refer to the following document:
- [Rule Engine Documentation](https://github.com/david-fisher/320-F21-Track-2/wiki/Team-GKNEW)

### How to Edit an existing Board Game
The editor allows for editing game boards that have been previously created by a user. In order to do this, simply do the following:
- From the Main Menu, select “Edit Game” 
- Select the game you wish to edit
- Select “Edit Game”
- Refer to the above instructions for further information regarding the editors

### How to start playing a new Board Game
In order to begin playing a new board game, do the following:

- From the Main Menu, select “Play Game”
- Select the game you wish to play from the top row labeled “New Games”
- Select “Start New Game”
- Set the number of players, colors, and names,
- (Optional) Enable tutorial mode and/or shuffle the turn order via the buttons below
- Select “Start Game”

### How to Edit an existing Board Game
Upon exiting a game in progress, assuming you have saved you can return to the game by doing the following:
- From the Main Menu, select “Play Game”
- Select the game you wish to resume from the bottom row labeled “Saved Games”
- Select “Resume Game”
