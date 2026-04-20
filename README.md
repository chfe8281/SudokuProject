Patterns:
* Factory Pattern: Create cell objects to inject into groups and group objects to inject into the board.
* Builder Pattern: To build the board according to size and/or group-type specifications. In SudokuBoard class.
* Command Pattern: To make the player's move with ability to execute and undo.
* Strategy Pattern: To allow the game object to either look at a horizontal line group, vertical line group, or box group. To be implemented in the SudokuGame class.
* MVC Pattern: Connects the UI Viewer SudokuFrame with the SudokuController that tracks moves performed on the SudokuGame.

Core OO Principles:
* Polymorphism: This was done by overloading setCells method in the BoxGroup as for the LineGroup and IGroup interface it was simpler to pass in an array while in the BoxGroup we passed in a 2D matrix.
* Dependency Injection: See SudokuGame, we use constructor injection to pass the SudokuBoard into the game. Also see SudokuBoard getBuilder() we pass the GroupsFactory and CellFactory in.
