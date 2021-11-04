# Game object editor UI

This directory holds the source files for the game object editor UI. There is a single java package
`GameObjectUI` with a main class of the same name and a single controller class
`GameObjectUIController` in a subpackage `GameObjectUI.controllers`. This class contains a number
of event handlers for the done and save buttons in the UI, all of which will simply populate
the appropriate internal game objects with the contents of the text fields, color fields, etc.
Once integration begins, we will add more event handlers for the cancel buttons, and edit the ones
for the done and save buttons to transition to other views.

