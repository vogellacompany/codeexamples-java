package de.vogella.scala.quicksort

object Test {
  def main(args: Array[String]) = {
    val quicksort = new Quicksort
	val a = Array(5, 3, 2, 2, 1, 1, 9, 39 ,219)
	quicksort.sort(a).foreach(n=> (print(n), print (" " )))

  }
}
