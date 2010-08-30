package de.vogella.scala.uniformaccess

object Main {
	def main(args: Array[String]) {
	  var s = new Square();
	  println(s.length);
      s.length = 2;
      println(s.length);
    }

}
