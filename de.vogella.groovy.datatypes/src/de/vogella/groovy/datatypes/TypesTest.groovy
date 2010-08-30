
package de.vogella.groovy.datatypes

public class TypesTest{
	public static void main(args){
		int i = 1 // Short form for Integer i = new Integer(1)
		int j = i +3 
		int k = i.plus(3); // Same as above
		// Make sure this worked
		assert(k==4);
		println i.getClass().getName()
		println j.getClass().getName()
		println k.getClass().getName()
		
		// Automatic type assignement
		def value = 1.0F
		println value.getClass().getName()
		def value2 = 1;
		println value2.getClass().getName()
		value2 = value2 / 2;
		println value2
		println value2.getClass().getName()
	}	
	
}
