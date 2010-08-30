
package de.vogella.groovy.training

public class TestingAround{
	public getMyMethods(){
		//GPath Example
		def list = this.class.methods.name.grep(~/get.*/).sort();
		list.each{println it}
		
		
	}
	public static void main (args){
		// Define a few closures
		def closure = {println it}
		def adder = {a,b -> a+b}
		println adder(1,20)
		
		(1..10).each{closure}
		sleep 500 // Wait 500 Millisecs
		(1..10).each{println it}
		def list = 1 .. 10
		list = 1..10 // defines list with elements 1 to 10
		list -= 5 // removes elements
		list += 11 // adds element to list
		list.each{println it}
		def map = [first:'Moin', second:'Tach']
		println map.first
		//GPath Example
		def test = new TestingAround();
		test.getMyMethods();
		
	}
}
