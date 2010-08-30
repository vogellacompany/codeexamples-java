package de.vogella.groovy.shipmentrequest

import java.util.GregorianCalendar

public class CreateSR {
	void createFiles(String outputDir, int number ){
		println outputDir
		//		def inputFile = "c:/temp/groovy/P6F_SRQvrs2.xml"
		long startTime = System.currentTimeMillis(); // How long does the creation take
//		def text = new File(inputFile).getText();
		InputStream is = Main.class.getResourceAsStream( "SRExample.xml" );
		def reader = new BufferedReader(new InputStreamReader(is)); 
		def text = reader.getText();
		Calendar cal = new GregorianCalendar();
		def year = cal.get(Calendar.YEAR)
		def month = cal.get(Calendar.MONTH)+1 // month starts with 0
		def day = cal.get(Calendar.DATE) + 1 // we want tomorrow
		def newDate = "$year-$month-$day" 
		def newtext = text.replaceAll("2008-11-14", "$newDate")
		(1 .. number).each ({new File("$outputDir/shipmentrequest$it").write("$newtext")})
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
}
