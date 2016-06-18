//import patmat.Huffman._
//
//val simpleTree = Fork(Fork(Leaf('a', 1), Leaf('b', 2), List('a','b'), 3), Leaf('c',1), List('a','b','c'), 4)
//
////         *
////        / \
////       /   \
////      *    'c'
////     / \
////    /   \
////  'a'   'b'
//
//decode(simpleTree, List(1,0,0,0,1))
//decodedSecret
//
//encode(simpleTree)("cab".toList)
//quickEncode(simpleTree)("cab".toList)
//
//List(('b',2),('a',3), ('c',1)).sortBy(_._2)
//
//val l = List(Leaf('a', 4), Leaf('b', 5), Leaf('c', 6))
//combine(l)

val listOfChars: List[List[Char]] = List("hey".toList, "there".toList)
listOfChars.flatMap(identity)
listOfChars.flatten

