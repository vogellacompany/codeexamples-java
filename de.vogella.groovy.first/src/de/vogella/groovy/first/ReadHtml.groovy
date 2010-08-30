package de.vogella.groovy.first

public class ReadHtml{

	static void main(def args) {
	def records = new XmlSlurper().parseText("http://www.vogella.de")
	def allNodes = records.depthFirst().collect{ it }
	def list = []
	allNodes.each {
	    it.text().tokenize().each {
	        list << it
	    }
	}
//	println done
	}
}
