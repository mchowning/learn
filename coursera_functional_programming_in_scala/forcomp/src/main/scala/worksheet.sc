import forcomp.Anagrams._


val s:Sentence = List("Yes", "man")
//val sOccurrences: Occurrences = sentenceOccurrences(s)
//val sCombinations: List[Occurrences] = combinations(sOccurrences)
//val validCombos: List[Occurrences] = sCombinations.filter(dictionaryByOccurrences.contains)
//val resultingWords0: List[List[Word]] = validCombos.map(dictionaryByOccurrences.getOrElse(_, Nil))
//val resultingWords1: List[Word] = validCombos.flatMap(dictionaryByOccurrences.getOrElse(_, Nil))

def f(s: Occurrences, acc: Sentence): List[Sentence] = {
  if (s.isEmpty) List(acc)
  else {
    val sCombinations: List[Occurrences] = combinations(s)
    val validCombos: List[Occurrences] = sCombinations.filter(dictionaryByOccurrences.contains)
    val resultingWords: List[Word] = validCombos.flatMap(dictionaryByOccurrences.getOrElse(_, Nil))
    if (resultingWords.isEmpty) Nil
    else {
      resultingWords.flatMap(w => {
        val s_ = subtract(s, wordOccurrences(w))
        f(s_, w :: acc)
      })
    }
  }
}

val r = f(sentenceOccurrences(s), Nil)

//lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]]
//type Word = String
//type Sentence = List[Word]
//type Occurrences = List[(Char, Int)]
//def sentenceAnagrams(sentence: Sentence): List[Sentence] = ???
