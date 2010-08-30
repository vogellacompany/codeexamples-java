package de.vogella.groovy.shipmentrequest

import java.util.GregorianCalendar
import java.io.File
public class Main{
	static void main(args){
		println "Starting" ;
		def outputDir = "c:/temp/performance"
		
		def startTime = System.currentTimeMillis(); // How long does the creation take
		def createSR = new CreateSR();
		createSR.createFiles(outputDir, 1200);
		def stopTime = System.currentTimeMillis();
		def elapsedTime = stopTime - startTime;
		println elapsedTime ;
	}
}
