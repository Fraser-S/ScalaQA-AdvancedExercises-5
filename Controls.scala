/**
  * Created by Fraser on 16/06/2017.
  */

trait Controls {
  /**
    * used to get an integer from the user
    * @param prompt - string message that will be displayed with the input
    * @return - the integer that the user input
    */
  def getInteger(prompt: String): Int ={
    var num: Int = 0
    var validInput: Boolean = true
    do{
      try{
        num = scala.io.StdIn.readLine(prompt).toInt
        validInput = true
      } catch{
        case e: NumberFormatException => println("Invalid Input");validInput = false
      }
    }while(!validInput)
    num
  }

  /**
    * used to get an integer from the user
    * @param prompt - string message that will be displayed with the input
    * @return - the integer that the user input
    */
  def getBoolean(prompt: String): Boolean ={
    var bool: Boolean = false
    var validInput: Boolean = true
    do{
      try{
        bool = scala.io.StdIn.readLine(prompt).toBoolean
        validInput = true
      } catch{
        case e: IllegalArgumentException => println("Invalid Input");validInput = false
      }
    }while(!validInput)
    bool
  }

  def waitForKeyPress(prompt: String): Unit ={
    scala.io.StdIn.readLine(prompt)
  }

  def clearScreen(): Unit ={

  }
}
