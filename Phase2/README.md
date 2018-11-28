# Set up instruction
1. Get URL from Markus: [https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0621](https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0621)
2. Use Android studio to clone the address:
First, create a new Android project. Then input project from external model.
3. Add Phase2 to the gradle project path.
4. Select explicit model groups. Then use default gradle wrapper.
5. You might get the message "Unregistered VS root detected", and you should choose "Add root".
6. Then download Phase1 file from Markus and drag it into the Phase2 file.


# How to run the program
Firstly, user is directed to login interface, where he can either choose Create Account if he hasn't registered yet or login directly. Afterwards, he is directed to user interface, in which he can choose three different games to play, which are sliding tile, sudoku, subtract square. After user clicks a game, he enters into game start interface and can start playing the game! Different games have common functionalities. For example, save game, load game, user scoreboard, game scoreboard, etc. Meanwhile, they have their own functionalities as well. For instance, Sudoku has start over button which can regenerate a board to play, Subtract square has 'vs computer' mode and Sliding tile can choose different level of complexity. For more details about the functionality of different games, please check the Functionalities part.


# Functionalities
## Functionality of Game launch centre
1. log in/ sign up: user can choose to create a new account if he hasn't gotten one or log in directly.
relevant class: 
User: a class representing the users of the game
Session:
UserDataBase: a database restoring all users

## Functionality of Sliding Tile
1. Start new game: start a new sliding tile game.
relevant class:
Board: the sliding tiles board
BoardManager: manage a board, including swapping tiles, checking for a win, and managing taps.
Tile: A Tile in a sliding tiles puzzle.
MovementController:

2. load saved game: load the previous saved game
relevant class:
GameStateDataBase: a database restoring all game states
StartingActivity: The initial activity for the sliding puzzle tile game.

3. save game: save current game state.
relevant class:
GameStateDataBase: a database restoring all game states
StartingActivity: The initial activity for the sliding puzzle tile game.

4. select difficulty level: player can choose either 3*3 or 4*4 or 5*5 sliding board game, representing relatively easy, medium and difficult. 
relevant class: 
SlidingtileSelectSizeActivity: an activity relevant to selecting difficulty.

5. undo: user can undo the move he just made.
relevant class:
BoardManager: manage a board, including swapping tiles, checking for a win, and managing taps.
GameActivity: the sliding tile game activity

6. User scoreboard: when user enters into the game , he can check his score history of the game.
relevant class/interface:
Scoreboard: a database for scoreboard.
Score: an interface dealing with score

7. Game scoreboard: when user enters into the game, he can check the score history of this game.
relevant class/ interface:
Scoreboard: a database for scoreboard
Score: and interface dealing with score

8.Slicing image
9.import image


## Functionality of Sudoku
1. Start a new game: start a new sudoku game
relevant class:
SudokuGenerator: generate a new sudoku game
GameInterface: main activity for sudoku game


2. select difficulty level: player can choose three different difficulty level.
relevant class: 
SudokuGameActivity: activity for selecting different game difficulty level.
SudokuGenerator: generate a new sudoku game.

3. undo: user can undo the move he just made.
relevant class: 
GameInterface: main activity for sudoku game.

4. start over: user can press start over button to generate a new board
relevant class:
GameInterface: main activity for sudoku game

## Functionality of Subtract Square
1. start new game: start a new subtract square game
relevant class: 
SubtractSquareGame: subtract square game manager
SubtractSquareState: the state of the subtract square game

2. load game: load previously saved game
relevant class: 
GameStateDataBase: a database restoring all game states
SubtractSquareGameCentreActivity: activity for subtract square game centre.
SubtractSquareActivity: main activity of subtract square.

3. vs pc: user can choose to play with computer
relevant class:
MiniMax Node: minimax strategy of computer.

4. play with another player.
relevant class:
SubtractSquareSelectActivity: 
SubtractSquareStartActivity:

5. user scoreboard: when user enters into the game , he can check his score history of the game.
relevant class/interface:
Scoreboard: a database for scoreboard.
Score: an interface dealing with score

6. game scoreboard: when user enters into the game, he can check the score history of this game.
relevant class/ interface:
Scoreboard: a database for scoreboard
Score: and interface dealing with score

7. undo: user can undo the move he just made.
relevant class: 
SubtractSquareActivity: main activity for subtract square.

8. payment: undo exceed 3 times will pop a payment dialog
relevant class:
UndoPaymentDialogue: class manage undo payment dialogue.


# Design Pattern
1. SingleTon Pattern (there is only one instance of a class is created in the Java):
relevant classes: SlidingTileStateManager, UserManager. The reason why we use SingleTon for these two classes is that there can only be one user playing only one game after logging in.

2. Observer Pattern (A class extending Observable class is an object which notifies classes or interfaces implementing Observer):
relevant classes implementing Observer: GameActivity; SlidingTileGameActivity;
relevant classes extending Observable: 

3. Adaptor (a software design pattern that allows the interface of an existing class to be used as another interface.):
relevant classes: CustomAdapter.  The class CustomAdapter
is a class adapter as it uses inheritance extending the superclass BaseAdapter and can only wrap a class.