
package de.vogella.groovy.datatypes

public class MapTest{
	public static void main(args){
		Map map = [:]
		def map2 = ["Jim":"Knopf", "Thomas":"Edison"]
		println map2["Jim"]
		map2["Test"] = "Tester"
		println map2["Test"]
	}
}
