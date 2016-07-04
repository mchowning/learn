
val s1 = List(1,2,3,4,5,6,7,8,9,10)
val s2 = Stream(11,12,13,14,15,16,17,18,19,20)
s1 ++ s2
s1.filter(_ % 2 == 0)

//def f(s: Stream[Int]): Stream[Int] = s match {
//  case Empty => Empty
//  case h #:: tail => (h * 2) #:: f(tail)
//}
//
//f(s1)
//f(s2)

def f(s: List[Int]): List[Int] = s match {
  case List() => List()
  case h :: tail => (h * 2) :: f(tail)
}

f(s1)
