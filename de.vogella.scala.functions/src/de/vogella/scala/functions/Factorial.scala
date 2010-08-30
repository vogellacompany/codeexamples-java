package de.vogella.scala.functions

object Factorial {
  
 def main(args: Array[String]) {
   val f = Factorial
   println( f.factorial(5) )
   println( f.factorial(6) )
   println( f.factorial(7) )
   println( f.factorial(8) )
 }
 
 def factorial (x:BigInt):BigInt= {
   if (x==0) 1
   else x* factorial (x-1)
 }
}
