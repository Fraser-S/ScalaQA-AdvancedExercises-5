/**
  * Created by Fraser on 15/06/2017.
  */

class Game extends Controls {
  var player1Board: GameBoard = null
  var player2Board: GameBoard = null
  var running: Boolean = true

  def setup(): Unit ={

    //setup player 1s board
    println("Player 1 - Setup board")
    player1Board = new GameBoard(3)
    player1Board.setupBoard()
    player1Board.addShips()
    waitForKeyPress("Player 1 - End of Setup ")

    if(running){
      println("Player 2 - Setup board")
      player2Board = new GameBoard(3)
      player2Board.setupBoard()
      player2Board.addShips()
      waitForKeyPress("Player 2 - End of Setup ")
    }
  }

  def playerOneTurn(): Unit ={
    var hasHitShip: Boolean = false
    do {
      player2Board.displayBoard()
      hasHitShip = player2Board.takeTurn()

      if (player2Board.allShipsSunk()) {
        running = false
        println("Player 1 Wins")
      }
    }while(hasHitShip && running)
    player2Board.displayBoard()
  }

  def playerTwoTurn(): Unit ={
    var hasHitShip: Boolean = false
    do {
      player1Board.displayBoard()
      hasHitShip = player1Board.takeTurn()

      if (player1Board.allShipsSunk()) {
        running = false
        println("Player 2 Wins")
      }
    }while(hasHitShip && running)
    player1Board.displayBoard()
  }

  def update(): Unit ={
    //wait for key press
    waitForKeyPress("Player 1 Start Of Turn - Press Any Key To Continue")
    playerOneTurn()
    waitForKeyPress("Player 1 End of Turn - Press Any Key To Continue")

    //wait for key press
    waitForKeyPress("Player 2 Start Of Turn - Press Any Key To Continue")
    playerTwoTurn()
    waitForKeyPress("Player 2 End of Turn - Press Any Key To Continue")
  }

  def run(): Unit = {

    while(running){
      update()
    }
  }
}
