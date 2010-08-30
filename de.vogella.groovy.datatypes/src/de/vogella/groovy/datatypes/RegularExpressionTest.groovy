
package de.vogella.groovy.datatypes

public class RegularExpressionTest{
	public static void main(String[] args) {
		// Defines a string with special signs
		def text = "John Jimbo jingeled happily ever after"
		
		// Every word must be followed by a nonword character
		// Match
		if (text==~/(\w*\W+)*/){
			println "Match was successful"
		} else {
			println "Match was not successful"
		}
		// Every word must be followed by a nonword character
		// Find
		if (text=~/(\w*\W+)*/){
			println "Find was successful"
		} else {
			println "Find was not successful"
		}
		
		if (text==~/^J.*/ ){
			println "There was a match"
		} else {
			println "No match found"
		}
		def newText = text.replaceAll(/\w+/, "hubba")
		println newText
	}
	
	
}
