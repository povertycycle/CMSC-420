package cmsc420.meeshquest.part1;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLHandler {
	public static Element createCity(Document results, Dictionary list, Element commandNode) {
		String name = commandNode.getAttribute("name");
		String color = commandNode.getAttribute("color");
		String radius = commandNode.getAttribute("radius");
		Float x = Float.parseFloat(commandNode.getAttribute("x"));
		Float y = Float.parseFloat(commandNode.getAttribute("y"));
		City newCity = new City(name, x, y, radius, color);
		
		Element elt = null, output = null;
		
		if (list.checkDuplicateCoordinates(newCity)) {
			elt = results.createElement("error");
			elt.setAttribute("type", "duplicateCityCoordinates");
		}
		
		else if (list.checkDuplicateName(newCity)) {
			elt = results.createElement("error");
			elt.setAttribute("type", "duplicateCityName");
		} 
		
		if (elt == null) {
			elt = results.createElement("success");
			output = results.createElement("output");
		}

		Element command = results.createElement("command");
		Element parameter = results.createElement("parameters");

		elt.appendChild(command);
		elt.appendChild(parameter);
		command.setAttribute("name", "createCity");
		Element nameCity = results.createElement("name");
		Element xCity = results.createElement("x");
		Element yCity = results.createElement("y");
		Element radiusCity = results.createElement("radius");
		Element colorCity = results.createElement("color");
		parameter.appendChild(nameCity);
		parameter.appendChild(xCity);
		parameter.appendChild(yCity);
		parameter.appendChild(radiusCity);
		parameter.appendChild(colorCity);
		nameCity.setAttribute("value", commandNode.getAttribute("name"));
		xCity.setAttribute("value", commandNode.getAttribute("x"));
		yCity.setAttribute("value", commandNode.getAttribute("y"));
		radiusCity.setAttribute("value", commandNode.getAttribute("radius"));
		colorCity.setAttribute("value", commandNode.getAttribute("color"));
		
		if (output != null) {
			elt.appendChild(output);
		}
		return elt;
	}
	
	public static Element listCities(Document results, Dictionary list, Element commandNode) {
		String type = commandNode.getAttribute("sortBy");
		
		Element elt = null, output = null;
		
		if (list.isEmpty()) {
			elt = results.createElement("error");
			elt.setAttribute("type", "noCitiesToList");
		}
		
		if (elt == null) {
			elt = results.createElement("success");
			output = results.createElement("output");
		}
		
		Element command = results.createElement("command");
		Element parameter = results.createElement("parameters");
		
		elt.appendChild(command);
		elt.appendChild(parameter);
		command.setAttribute("name", "listCities");
		Element sortBy = results.createElement("sortBy");
		parameter.appendChild(sortBy);
		sortBy.setAttribute("value", type);
		Element cityList = results.createElement("cityList");
		if (cityList != null) {
			output.appendChild(cityList);
		}
		
		if (list.isEmpty() == false) {
			if (type.equals("name")) {
				SortedMap<String, City> sortedNames = list.cityList;
				Iterator<City> iterator = sortedNames.values().iterator();
				while (iterator.hasNext()) {
					City current = iterator.next();
					Element city = results.createElement("city");
					city.setAttribute("color", current.getColor());
					city.setAttribute("name", current.getName());
					city.setAttribute("radius", current.getRadius());
					city.setAttribute("x", Integer.toString(current.getIntX()));
					city.setAttribute("y", Integer.toString(current.getIntY()));			
					cityList.appendChild(city);
				}
				
			} else if (type.equals("coordinate")) {
				SortedSet<City> sortedCoor = list.coorList;
				Iterator<City> iterator = sortedCoor.iterator();
				while (iterator.hasNext()) {
					City current = iterator.next();
					Element city = results.createElement("city");
					city.setAttribute("color", current.getColor());
					city.setAttribute("name", current.getName());
					city.setAttribute("radius", current.getRadius());
					city.setAttribute("x", Integer.toString(current.getIntX()));
					city.setAttribute("y", Integer.toString(current.getIntY()));
					cityList.appendChild(city);
				}
			}
		}
		
		if (output != null) {
			elt.appendChild(output);
		}
		return elt;
		
	}
	
	public static Element clearAll(Document results) {
		
		Element	elt = results.createElement("success");
		Element command = results.createElement("command");
		Element parameters = results.createElement("parameters");
		Element output = results.createElement("output");
		elt.appendChild(command);
		elt.appendChild(parameters);
		elt.appendChild(output);
		command.setAttribute("name", "clearAll");
		return elt;
		
	}
}
