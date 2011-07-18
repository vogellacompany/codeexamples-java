package de.vogella.jsf.first.model;

public class TemperatureConvertor {
	private double celsius; 
	private double fahrenheit;
	private boolean initial= true; 
	
	public double getCelsius() {
		return celsius;
	}
	public void setCelsius(double celsius) {
		this.celsius = celsius;
	}
	public double getFahrenheit() {
		return fahrenheit;
	}
	
	public boolean getInitial(){
		return initial;
	}
	
	public String reset (){
		initial = true;
		fahrenheit =0;
		celsius = 0; 
		return "reset";
	}
	public String celsiusToFahrenheit(){
		initial = false; 
		fahrenheit = (celsius *9 / 5) +32;
		return "calculated";
	}
	
}

