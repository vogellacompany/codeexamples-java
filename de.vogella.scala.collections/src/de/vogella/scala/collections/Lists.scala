package de.vogella.scala.collections;

  object Lists {
    def main(args: Array[String]) {
      val languages = List("Java", ".NET", "C++")
      println (languages)
      println (languages(2))
      println (languages.contains("Java"))
      // + should be replaced with ::: but the Eclipse does currenlty not support this
      val additionalLanguages = languages + ("COBOL") 
      println (additionalLanguages)
    }
   
  }
  
  

