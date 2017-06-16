/**
  * Created by Fraser on 15/06/2017.
  */
abstract class Ship(s: Int, n: String){
  protected var name: String = n
  protected var size: Int = s
  protected var hitBox: Array[Boolean] = new Array[Boolean](size)
  protected var xPosition: Int = 0
  protected var yPosition: Int = 0
  protected var horizontal: Boolean = true

  def getSize(): Int = {size}

  def getName(): String = {name}

  /**
    * This function sets the ships position on the board, and sets all the hit values to false
    * @param xPos - xPosition of the first part of the ship, top-left is the start
    * @param yPos - yPosition of the first part of the ship, top-left is the start
    * @param isHorizontal - true means the ship is horizontal, false means the ship is vertical
    */
  def setPos(xPos: Int,yPos:Int, isHorizontal: Boolean): Unit ={
    horizontal = isHorizontal
    xPosition = xPos
    yPosition = yPos

    for(i<-0 until hitBox.size){
      hitBox(i) = false
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
      case a if horizontal && xPosition+index == xPos && yPosition == yPos => hitBox(index) = true; hitBox(index) = true; true
      case a if !horizontal && xPosition == xPos && yPosition+index == yPos => hitBox(index) = true; hitBox(index) = true; true
      case _ => iter(index+1)
    }

    var hit = iter(0)
      if(hit && isSunk){
        println("Sunk " + name)
      }
    hit
  }

  /**
    * checks to see if the ship has been hit in all sections
    * @return true if the ship has been hit in all sections therefore sunk
    */
  def isSunk(): Boolean = {
    var allSectionsHit: Boolean = true
    for(i<-0 until hitBox.size){
      if(!hitBox(i)) allSectionsHit = false
    }
    allSectionsHit
  }
}
