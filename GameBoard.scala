import scala.collection.mutable.ListBuffer

/**
  * Created by Fraser on 15/06/2017.
  */
class GameBoard(dim: Int) extends Controls{
  private var board: Array[Array[Char]] = Array.ofDim[Char](dim, dim)
  //used for displaying what state the board is in
  private val hit = 'X'
  private val miss = 'M'
  private val empty = '+'
  private val shipHere = 'S'
  private var ships: ListBuffer[Ship] = ListBuffer(new PatrolBoat, new PatrolBoat)

  def setupBoard() = {
    //set up all the coords to be empty
    for(y <- 0 until dim)
      for(x <- 0 until dim)
        board(x)(y) = empty
  }

  def addShips(): Unit ={
    this.displayBoard()

    for(i<-0 until ships.size)
      ships(i) = placeShip(ships(i))

    //reset the board to be blank
    this.setupBoard()
  }

  def placeShip(ship: Ship): Ship ={
    var validPlacement: Boolean = false
    println("Place " + ship.getName())
    do{
      var x: Int = getInteger("Enter x value: ")
      var y: Int = getInteger("Enter y value: ")
      var horizontal: Boolean = getBoolean("Horizontal(true), Vertical(false): ")
      if(checkShipPlacement(x, y, horizontal, ship.getSize())){
        ship.setPos(x,y,horizontal)
        for(i<-0 until ship.getSize()){
          if(horizontal) board(x+i)(y) = shipHere else board(x)(y+i) = shipHere
        }
        validPlacement = true
      }
    }while(!validPlacement)
    this.displayBoard()
    println("Ship Placed")
    ship
  }

  def checkShipPlacement(x: Int, y: Int, horizontal: Boolean, shipSize: Int): Boolean ={
    def iter(index: Int): Boolean = index match{
      case a if index == shipSize => true
      case a if horizontal && !checkCoord(x+index, y) => false
      case a if !horizontal && !checkCoord(x, y+index) => false
      case _ => iter(index+1)
    }
    iter(0)
  }

  def displayBoard(): Unit ={
    //display row by row
    for(y <- 0 until dim){
      for(x <- 0 until dim){
        print(board(x)(y) + " ")
      }
      println()
    }
  }

  def checkCoord(x: Int, y: Int): Boolean = x match{
    case a if (x < 0 || x >= dim) => println("Invalid x co-ord"); false
    case a if (y < 0 || y >= dim) => println("Invalid y co-ord"); false
    case a if board(x)(y) == empty => true
    case _ => println("co-ord already used"); false
  }

  def checkShips(x: Int, y: Int): Boolean ={
    var i : Int = 0
    var shipHit: Boolean = false
    do{
      if(ships(i).checkForHit(x,y))
        shipHit = true
      else
        shipHit = false
      i+=1
    }while(i != ships.size && !shipHit)
    shipHit
  }

  def takeTurn(): Boolean ={
    var validTurn: Boolean = false
    var x: Int = 0
    var y: Int = 0

    do{
      x = getInteger("Enter X Co-ordinate: ")
      y = getInteger("Enter Y Co-ordinate: ")
      validTurn = checkCoord(x, y)
    }while(!validTurn)

    var hitShip: Boolean = false

    if(checkShips(x,y)) {
      board(x)(y) = hit
      hitShip = true
    }else {
      board(x)(y) = miss
    }
    hitShip
  }

  def allShipsSunk(): Boolean ={
    var shipsSunk = true
    var i: Int = 0
    do{
      shipsSunk = ships(i).isSunk()
      i+=1
    }while(i != ships.size && shipsSunk)
    shipsSunk
  }
}
