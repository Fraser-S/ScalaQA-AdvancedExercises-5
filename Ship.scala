/**
  * Created by Fraser on 15/06/2017.
  */
abstract class Ship(s: Int){
  protected var size: Int = s
  protected var hitBox: Array[Boolean] = new Array[Boolean](size)
  protected var xPosition: Int = 0
  protected var yPosition: Int = 0
  protected var horizontal: Boolean = true
  protected var sunk: Boolean = false

  /**
    * This function allows ship to be positioned on the board.
    * @param xPos - xPosition of the first part of the ship, top-left is the start
    * @param yPos - yPosition of the first part of the ship, top-left is the start
    * @param isHorizontal - true means the ship is horizontal, false means the ship is vertical
    * @param boardSize - the size of the game board
    * @return returns true if the ship is placed false if the ship is not placed
    */
  def setPos(xPos: Int,yPos:Int, isHorizontal: Boolean, boardSize: Int):Boolean ={
    xPos match {
      case a if horizontal && (xPos >= 0 && xPos+size <= boardSize) && (yPos >= 0 && yPos<=boardSize) => horizontal = isHorizontal; xPosition = xPos; yPosition = yPos; true
      case a if !horizontal && (xPos >= 0 && xPos<= boardSize) && (yPos >= 0 && yPos+size<=boardSize) => horizontal = isHorizontal; xPosition = xPos; yPosition = yPos; true
      case _ => false
    }
  }

  /**
    * This function checks a coordinate with the ships coordinate and
    * determines wither the ship is hit or not
    * @param xPos - x position of the coordinate
    * @param yPos - y position of the coordinate
    * @return - returns true if there's a hit false if there's not
    */
  def checkForHit(xPos: Int, yPos: Int): Boolean ={
    def iter(index: Int): Boolean = index match{
      case a if index == size => false
      case a if horizontal && xPosition+index == xPos && yPosition == yPos => hitBox(index) = true; true
      case a if horizontal && xPosition == xPos && yPosition+index == yPos => hitBox(index) = true; true
      case _ => iter(index+1)
    }

    if(xPosition == xPos || yPosition == yPos)
      iter(0)
    else
      false
  }

  def isSunk(): Boolean = {sunk}
}
