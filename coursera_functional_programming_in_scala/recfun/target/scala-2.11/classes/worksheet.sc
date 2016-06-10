val l = List(1,2,3,4,5)

val l1 = l.map(i => (i,0))

def countChange(money: Int, coins: List[Int]): Int = {
  if (money == 0) 1 else {
    coins.map(i => (i, money - i))
        .filter(t => t._2 >= 0)
      .map(t => countChange(t._2, coins.filter(c => c >= t._1)))
      .sum
  }

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
}

//countChange(3, List(1))
//countChange(4, List(1,2))

var a = 1
a += 2

