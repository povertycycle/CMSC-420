package cmsc420.meeshquest.part1;

import java.util.TreeMap;
import java.util.TreeSet;

public class Dictionary {
	
	// Comparator sort reverse asciibetically.
	TreeMap<String, City> cityList = new TreeMap<String, City>(Comparators.sortNames);

	// Comparator sort based on instruction on pdf.
	TreeSet<City> coorList = new TreeSet<City>(Comparators.sortCoordinates);

	public void createCity(City newCity) {
		cityList.put(newCity.name, newCity);
		coorList.add(newCity);
	}
	
	public boolean checkDuplicateName(City newCity) {
		return cityList.containsKey(newCity.name);
	}
	
	public boolean checkDuplicateCoordinates(City newCity) {
		return coorList.contains(newCity);
	}
	
	public boolean isEmpty() {
		if (cityList.isEmpty() && coorList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clearAll() {
		cityList.clear();
		coorList.clear();
	}
}	
