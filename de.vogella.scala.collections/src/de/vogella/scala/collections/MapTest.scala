package de.vogella.scala.collections

object MapTest {
    def main(args: Array[String]) {
	   val languages = Map("Scala"-> "JVM", "Java" -> "JVM", ".NET"-> "CLR");
	   println (languages("Scala"))
       
 }
}
