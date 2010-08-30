package de.vogella.scala.quicksort

/* Quicksort in Scala */
class Quicksort {
	def sort(a:Array[Int]): Array[Int] =	
		if (a.length < 2) a
		else {
			val pivot = a(a.length / 2)
			sort (a filter (pivot>)) ++ (a filter (pivot == )) ++
				sort (a filter(pivot <))
		}
}
