package de.vogella.scala.classexample

object Test extends Application {
	  val  robot = new RobotWithConstructor (true, "steel")
	  robot.printStatus
	  // material can be accessed as it is defined via val in the primary constructor
	  println (robot.material)
      // Status on cannot be reached, the following line would result in syntax error
      // robot.on
}