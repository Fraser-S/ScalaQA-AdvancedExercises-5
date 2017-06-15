/**
  * Created by Fraser on 15/06/2017.
  */
class GameBoard(dim: Int){
  private var board: Array[Array[Int]] = Array.ofDim[Int](dim, dim)

  private val hit = 2
  private val miss = 1
  private val empty = 0

  def setupBoard() = {
    for(x <- 0 until dim){
      for(y <- 0 until dim){
        board(x)(y) = empty
      }
    }
  }
}
