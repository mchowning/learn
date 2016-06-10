package recfun

object Main {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {

    // 1
    val isEdge = c == 0 || r == 0 || c == r
    if (isEdge) 1 else pascal(c,r-1) + pascal(c-1,r-1)

    // 2
    //if (c == 0 || r == 0 || c == r) 1 else pascal(c, r-1) + pascal(c-1,r-1)

    // 3
    //(c,r) match {
    //  case (0, _) => 1
    //  case (_, 0) => 1
    //  case (c, r) if c == r => 1
    //  case (`r`, _) => 1
    //  case _ => pascal(c, r - 1) + pascal(c - 1, r - 1)
    //}

  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean =  {
    val balanceSum = chars.map(c => c match {
        case '(' => 1
        case ')' => -1
        case _ => 0
      }).scanLeft(0)((acc,i) => acc+i)

    balanceSum.min >= 0 && balanceSum.last == 0
  }

  
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    //1
    if (money == 0)
      1
    else
      coins.map(i => (i, money - i))
        .filter(t => t._2 >= 0)
        .map(t => countChange(t._2, coins.filter(c => c >= t._1)))
        .sum

    // 2
    //var result = 0
    //for (i <- coins) {
    //  val combos = money - i match {
    //    case x if x == 0 => 1
    //    case x if x > 0  => countChange(x, coins.filter(c => c >= i))
    //    case _           => 0
    //  }
    //  result += combos
    //}
    //result

    // 3
    //var result = 0
    //for (i <- coins) {
    //  money - i match {
    //    case x if x==0 => result += 1
    //    case x if x>0  =>
    //      result += countChange(x, coins.filter(c => c >= i))
    //    case _ =>
    //  }
    //}
    //result

    // 4
    //if (money == 0) 1 else {
    //  var result = 0
    //  for (i <- coins) {
    //    val combos = money - i match {
    //      case x if x >= 0 => countChange(x, coins.filter(c => c >= i))
    //      case _ => 0
    //    }
    //    result += combos
    //  }
    //  result
    //}
  }
}
