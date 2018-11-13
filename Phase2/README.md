# Set up instruction
1. Get URL from Markus: [https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0490](https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0490)
2. Use Android studio to clone the address:
First, create a new Android project. Then input project from external model.
3. Add Phase1/GameCentre to the gradle project path.
4. Select explicit model groups. Then use default gradle wrapper.
5. You might get the message "Unregistered VS root detected", and you should choose "Add root".
6. Then download A2 file from Markus and drag it into the Phase1 file.


# How to run the program
Firstly, user enters into an interface, where he is asked to sign up/log in. Afterwards, he is redirected to another interface in which, he can choose what game to play/ to reload previous game/to check previous record. We only write Sliding Tile Game this time. Before the game starts, player is allowed to choose different complexity. He can save and log off, if he finishes playing.


# Functionalities
## Functionality of Game launch centre
1.sign in/ sign up/log out
relevant class: UserManager (stores information of all users and manages the user to play the games). 
relevant method in UserManager: logIn(), logOut(), signUp()

2. start a new game/ replay the game
relevant Interface and its subclass: GameInterface (the manager for the game currently played by the user); SlidingTileGameInterface (the manager for sliding tile game)
relevant method: startNewGame(), replay()

3.load previously saved game
relevant Interface and its subclass: GameInterface; SlidingTileGameInterface
relevant method: loadState()


## Functionality of scoreboard
1. calculate the score
relevant Interface and its subclass: Score (get the score of the games); SlidingTileScore (get the score of the sliding tile game)
relevant method: getScore()

2. store score per game
relevant Interface and its subclass: Scoreboard (manager for the scoreboard of the game); SlidingTileScoreBoard (manager for the scoreboard of sliding tile game)
relevant method: addScore()

3. store score per user
relevant class; UserScoreBoard (manager for the scoreboard of the user)
relevant method: addScore()

4. update the scoreboard per game
relevant Interface and its subclass: ScoreBoard; SlidingTileScoreBoard
relevant method: getTop10(); sort()

5.update the scoreboard per user
relevant class: UserScoreBoard
relevant method: getTop10(), sort()


## Functionality of autosave and standard save
1. save the game(Specifically for the sliding tile game)
relevant class: SlidingTileState ( For the sliding tile game, we use the SlidingTileState class to manage and save each board after each step  which is exactly each state of the game, so that we can indirectly save the whole game by saving the last state.)

2.autosave the game(Specifically for the sliding tile game)
relevant class: SlidingTileState ( We save each board after each step, which can be regarded as the process of autosave)


## Functionality of undo
1. undo
relevant Interface and subclass: GameInterface; SlidingTileGameInterface
relevant method: undo()


## Functionality of puzzle game complexity
1. user can choose the complexity of the game
relevant class: Board & BoardManager
relevant method: constructor of Board, which includes an input called boardSize to determine the size of the board ; constructor of BoardManager, which includes an input called boardSize to determine the size of the board.


# Design Pattern
1. SingleTon Pattern (there is only one instance of a class is created in the Java):
relevant classes: SlidingTileStateManager, UserManager. The reason why we use SingleTon for these two classes is that there can only be one user playing only one game after logging in.

2. Observer Pattern (A class extending Observable class is an object which notifies classes or interfaces implementing Observer):
relevant classes implementing Observer: GameActivity; SlidingTileGameActivity;
relevant classes extending Observable: 

3. Adaptor (a software design pattern that allows the interface of an existing class to be used as another interface.):
relevant classes: CustomAdapter.  The class CustomAdapter
is a class adapter as it uses inheritance extending the superclass BaseAdapter and can only wrap a class.