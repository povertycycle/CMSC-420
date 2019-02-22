package cmsc420.meeshquest.part1;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cmsc420.xml.XmlUtility;

public class MeeshQuest {

    public static void main(String[] args) {
    	
    	Document results = null;
    	Dictionary library = new Dictionary();
    	
        try {
        	Document doc = XmlUtility.validateNoNamespace(System.in);
        	results = XmlUtility.getDocumentBuilder().newDocument();
        
        	Element commandNode = doc.getDocumentElement();
        	Element root = results.createElement("results");
        	results.appendChild(root);

        	final NodeList nl = commandNode.getChildNodes();
        	for (int i = 0; i < nl.getLength(); i++) {
        		if (nl.item(i).getNodeType() == Document.ELEMENT_NODE) {
        			commandNode = (Element) nl.item(i);
                
        			/* TODO: Process your commandNode here */
        			
        			if (commandNode.getNodeName().equals("createCity")) {
        				
        				String name = commandNode.getAttribute("name");
        				String color = commandNode.getAttribute("color");
        				String radius = commandNode.getAttribute("radius");
        				Float x = Float.parseFloat(commandNode.getAttribute("x"));
        				Float y = Float.parseFloat(commandNode.getAttribute("y"));
        				City newCity = new City(name, x, y, radius, color);        				
        				root.appendChild(XMLHandler.createCity(results, library, commandNode));	
        				library.createCity(newCity);
        				
        			} else if (commandNode.getNodeName().equals("listCities")) {
        				
        				root.appendChild(XMLHandler.listCities(results, library, commandNode));
        				
        			} else if (commandNode.getNodeName().equals("clearAll")) {
        				
        				library.clearAll();
        				root.appendChild(XMLHandler.clearAll(results));
        				
        			}
    
        		}
        	}
        } catch (SAXException | IOException | ParserConfigurationException e) {
        	
        	/* TODO: Process fatal error here */
        	
		} finally {
            try {
				XmlUtility.print(results);
			} catch (TransformerException e) {
				e.printStackTrace();
			}
        }
    }
}
