package de.vogella.scala.loops

object Loops {
def main(args: Array[String]) {
    val list = List("Java",  "Scala" , "Groovy" , "NET");
    
    // Use foreach and print out every element
    list.foreach(n=> println(n))
    
    // Use standard for
    for (n<-list){
     println(n)
    }
    // Use method to
    println
    for (i<- 1.to(9)){
      print (i)
    }
    // Use the shorter form
    println
    for (i<- 1 to 9){
      print (i)
    }
    

    }

}
