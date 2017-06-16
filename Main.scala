/**
  * Created by Fraser on 15/06/2017.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val game: Game = new Game()

    game.setup()

    game.run()
  }
}
