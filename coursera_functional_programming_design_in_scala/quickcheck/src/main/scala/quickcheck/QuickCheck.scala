package quickcheck

//import Gen.{const, choose, sized, frequency, oneOf, buildableOf, resize}
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalacheck.Prop._
import org.scalacheck._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    v <- arbitrary[Int]
    h <- oneOf(const(empty),genHeap)
  } yield insert(v, h)
  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("deleteMin1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    val newM = m - 1
    if (newM < m) {
      val h1 = insert(newM, h)
      deleteMin(h1) == h
    }
    else true
  }

  property("deleteMin2") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    val newM = m + 1
    if (newM > m) {
      val h1 = insert(newM, h)
      deleteMin(h1) != h
    }
    else true
  }
}
