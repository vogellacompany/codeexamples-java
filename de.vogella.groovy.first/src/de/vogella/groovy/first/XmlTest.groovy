package de.vogella.groovy.first

public class XmlTest{
	static void main(args){
		def xmldocument = '''
		<persons> 
			<person> <firstname age="3">Jim</firstname>  <lastname>Knopf </lastname> 
			</person>
			<person> <firstname age="4">Ernie</firstname>  <lastname>Bernd</lastname> 
			</person>
		</persons>
		'''
		def persons = new XmlParser().parseText(xmldocument);
		def allRecords = persons.person.size()
		println("Number of person is: $allRecords")
		def person = persons.person[0]
		// name is the name of the XML tag
		println("Name of the person tag is:" + person.name())
		// text gets the text of the node firstname
		println(person.firstname.text())
		
		// Lets print out all important information 
		for (p in persons.person){
			println "${p.firstname.text()}  ${p.lastname.text()}"
		} 
				
	}
	
}
