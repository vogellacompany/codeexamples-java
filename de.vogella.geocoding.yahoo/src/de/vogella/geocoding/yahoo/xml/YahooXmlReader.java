package de.vogella.geocoding.yahoo.xml;

import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import de.vogella.geocoding.model.Coordinates;

public class YahooXmlReader {
	private YahooXmlReader() {
		// no instantiation
	}

	public static Coordinates readConfig(InputStream in) {
		Coordinates coordinates = new Coordinates();
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			// InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					if (event.asStartElement().getName().getLocalPart() == ("Latitude")) {
						event = eventReader.nextEvent();
						coordinates.setLatitude(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == ("Longitude")) {
						event = eventReader.nextEvent();
						coordinates
								.setLongitude(event.asCharacters().getData());
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return coordinates;
	}
}
