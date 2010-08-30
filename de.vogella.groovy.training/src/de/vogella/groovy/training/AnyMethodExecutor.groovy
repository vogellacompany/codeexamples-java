package de.vogella.groovy.training



public class AnyMethodExecutor{
	def map
	
	Object getProperty (String property){
		println "Setting this propery"
		return 5;
	}
	
	void setProperty (String property, Object o ){
		println "Hallo"
	}
	
	def methodMissing (String name, args){
		def s = name.toUpperCase();
		if (s.startsWith("HELLO"))
		{
			println "This method stats with Hello. Full name $name" 
		} else {
			println "This method is missing"
		}
		
	}
	
	public static void main (args){
		def test = new AnyMethodExecutor ();
		test.hall();
		test.helloMethod();
		test.Hallo();
		test.test= 5;
		println test.test; 
		
	}
	
}
