package de.vogella.scala.functions

object Function {
 def main(args: Array[String]) {
      println("Hello, world!")
      val f = Function;
      println(f.min(3,5))
      println(f.min2(3,5))
      // If you have no parameters you can leave the () away
      f.sayHello
      f.sayHello("Lars")
      // You can leave away the point and if you only have one paramter also the brackets
      f sayHello "Lars"
    }
 
  //Explicit return parameter
 def min(i:Int, j:Int):Int =  {
   if (i>j)
     return i
    else
     return j
 	}
 
 //Without explicit return parameter
 def min2(i:Int, j:Int) =  {
	  if (i>j)
		  i
	  else
		  j
 }
 
 // One one line a body and no parameters
 def sayHello = println("Hello")
 
 // One parameter
 def sayHello(s: String ) = println("Hello " + s)
}
