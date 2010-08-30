
package de.vogella.scala.switchcase

object SwitchExample {
    def matcher(s : String): String = {
      s match {
        case "test" => "Just testing, right?"
        case "lars" => "That is your name, right?"
        case s => "I don't know that"
      }
    }
	def main(args: Array[String]) {
		var s = "test";
		val test = SwitchExample
		println (test.matcher(s))
	  }
}
