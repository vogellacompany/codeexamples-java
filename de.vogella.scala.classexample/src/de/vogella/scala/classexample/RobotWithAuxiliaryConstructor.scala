package de.vogella.scala.classexample

class RobotWithAuxiliaryConstructor  (on: Boolean, val material:String ) {
	 def printStatus() = {
	  println(on)
	  println(material)
    }
  def this() = this(true, "Steel");
}
