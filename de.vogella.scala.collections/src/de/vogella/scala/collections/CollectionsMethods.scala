package de.vogella.scala.collections

 object CollectionsMethods {
	def main(args: Array[String]) {
      val list= List(1, 2, 3, 4)
      // Prints number of all elements
       println (list.size )
      // Count only the elements which can be divided by 2
      println (list.count(i=> i %2==0))
      // Check if a elements in included in list
      println(list.exists(i=> i ==4));
      println(list.exists(i=> i ==5));
      // Using filter to get elements
      val newList = list.filter(i=> i>=3)
      println (newList)
      // Map does allow to transform a list
       val newList2 = list.map(i=> i*3)
       println (newList2)
    }
}
