
# Dice Chess
![App Screenshot](https://i.imgur.com/9WL7IEy.png)
This game is called "Dice Chess" and it is a stochastic version of the classic game of chess.
The rules are very similar to those of traditional chess with three main exceptions:  

1. There is no check or checkmate, it is allowed to move the king to a square attacked by opponent's piece. The goal is to capture opponent's king.
2. A die is rolled for every move. The die is displayed below the game board and the number determines which piece can be used to make the move.  
| 1 | Pawn   |
|---|--------|
| 2 | Knight |
| 3 | Bishop |
| 4 | Rook   |
| 5 | Queen  |
| 6 | King   |
  
3. If a pawn is to be promoted (would advance to the last row), the player can move it even if the die does not show 1. However, he can only promote it to the piece chosen by the die roll - for example, if 3 is rolled, the pawn can be promoted to a bishop only. If 1 is rolled, the pawn can be promoted to any piece.  
  
  
    







## Installation
This program is a gradle project created using the LibGDX framework and written in java. Therefore, to run it you have to have gradle installed.  
To run the code:  
``` bash
$ git clone https://github.com/YungSpecht/DiceChess.git
$ cd DiceChess # change directory to the cloned repository
$ gradle run # run the project via gradle
```
## Usage
- Once you have started the program you will find yourself in the main menu of the game.  
- Press start to start a new game. Instructions about the gameplay can be found to the right of the board at all times.
- There is an option to switch to a regular chess mode in the Settings Section. You can navigate back to the main menu by pressing the arrow in the top-left corner of the screen.
## Authors

- [Deyvla2](https://github.com/Deyvla2)
- [Jack Waterman](https://github.com/jackwaterman13)
- [Guilherme Sequeira](https://github.com/sequeiragui)
- [alexandra10zam](https://github.com/alexandra10zam)
- [Rango](https://github.com/YungSpecht)
- [leastander](https://github.com/leastander)

