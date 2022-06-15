package module6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

/**
 * EarthquakeCityMap An application with an interactive map displaying
 * earthquake data. Author: UC San Diego Intermediate Software Development MOOC
 * team
 * 
 * @author Your name here Date: July 17, 2015
 */
public class EarthquakeCityMap extends PApplet {

	// We will use member variables, instead of local variables, to store the data
	// that the setUp and draw methods will need to access (as well as other
	// methods)
	// You will use many of these variables, but the only one you should need to add
	// code to modify is countryQuakes, where you will store the number of
	// earthquakes
	// per country.

	// You can ignore this. It's to get rid of eclipse warnings
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFILINE, change the value of this variable to true
	private static final boolean offline = false;

	/**
	 * This is where to find the local tiles, for working without an Internet
	 * connection
	 */
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	// feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	// The files containing city names and info and country names and info
	private String cityFile = "city-data.json";
	private String countryFile = "countries.geo.json";

	// The map
	private UnfoldingMap map;

	// Markers for each city
	private List<Marker> cityMarkers;
	// Markers for each earthquake
	private List<Marker> quakeMarkers;

	// A List of country markers
	private List<Marker> countryMarkers;

	/**
	 * ai
	 */
	private HashMap<Integer, Location> airports;

	/**
	 * A List of airport markers
	 */
	private List<Marker> airportList;

	/**
	 * 路線マーカーリスト
	 */
	private List<Marker> routesList;

	// NEW IN MODULE 5
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;

	public void setup() {
		// (1) Initializing canvas and map tiles
		size(900, 700, OPENGL);
		if (offline) {
			map = new UnfoldingMap(this, 200, 50, 650, 600, new MBTilesMapProvider(mbTilesString));
			earthquakesURL = "2.5_week.atom"; // The same feed, but saved August 7, 2015
		} else {
			map = new UnfoldingMap(this, 200, 50, 650, 600, new OpenStreetMap.OpenStreetMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			earthquakesURL = "all_month.atom";
		}
		MapUtils.createDefaultEventDispatcher(this, map);

		// FOR TESTING: Set earthquakesURL to be one of the testing files by
		// uncommenting
		// one of the lines below. This will work whether you are online or offline
		// earthquakesURL = "test1.atom";
		// earthquakesURL = "test2.atom";

		// Uncomment this line to take the quiz
		// earthquakesURL = "quiz2.atom";

		// (2) Reading in earthquake data and geometric properties
		// STEP 1: load country features and markers
		List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);

		// STEP 2: read in city data
		List<Feature> cities = GeoJSONReader.loadData(this, cityFile);
		cityMarkers = new ArrayList<Marker>();
		for (Feature city : cities) {
			cityMarkers.add(new CityMarker(city));
		}

		// STEP 3: read in earthquake RSS feed
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		quakeMarkers = new ArrayList<Marker>();

		for (PointFeature feature : earthquakes) {
			// check if LandQuake
			if (isLand(feature)) {
				quakeMarkers.add(new LandQuakeMarker(feature));
			}
			// OceanQuakes
			else {
				quakeMarkers.add(new OceanQuakeMarker(feature));
			}
		}

		// create Airport marker

		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		airportList = new ArrayList<Marker>();
		this.airports = new HashMap<Integer, Location>();
		for (PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
			int id = Integer.parseInt(feature.getId());
			m.setId(feature.getId());
			m.setRadius(5);

			// airportList.add(m);
			// airports.put(id, feature.getLocation());
		}

		// create route markers
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		this.routesList = getRouteMarkers(routes);

		// could be used for debugging

		printQuakes();

		// (3) Add markers to map
		// NOTE: Country markers are not added to the map. They are used
		// for their geometric properties
		map.addMarkers(quakeMarkers);
		map.addMarkers(cityMarkers);
		map.addMarkers(airportList);
		map.addMarkers(routesList);

		// print earthquakes list
		sortAndPrint(20);

	} // End setup

	public void draw() {
		background(0);
		map.draw();
		addKey();

	}

	// setup Sub
	/**
	 * getRouteMarkers
	 */
	private ArrayList<Marker> getRouteMarkers(List<ShapeFeature> routes) {
		ArrayList<Marker> routeList = new ArrayList<Marker>();

		for (ShapeFeature rt : routes) {
			int src = Integer.parseInt(rt.getStringProperty("source"));
			int dst = Integer.parseInt(rt.getStringProperty("destination"));
			if (this.airports.containsKey(src) && this.airports.containsKey(dst)) {
				rt.addLocation(this.airports.get(src));
				rt.addLocation(this.airports.get(dst));
				SimpleLinesMarker marker = new SimpleLinesMarker(rt.getLocations(), rt.getProperties());
				marker.setHidden(true);
				routeList.add(marker);
			}
		}
		return routeList;
	}

	// TODO: Add the method:
	// private void sortAndPrint(int numToPrint)
	// and then call that method from setUp

	/**
	 * Event handler that gets called automatically when the mouse moves.
	 */
	@Override
	public void mouseMoved() {
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		}

		selectMarkerIfHover(quakeMarkers);
		selectMarkerIfHover(cityMarkers);
		selectMarkerIfHover(this.airportList);
	}

	// If there is a marker selected
	private void selectMarkerIfHover(List<Marker> markers) {
		// Abort if there's already a marker selected
		if (lastSelected != null) {
			return;
		}

		CommonMarker m = getSelectedMarker(markers);
		if (m != null) {
			lastSelected = m;
			m.setSelected(true);
		}
	}

	/**
	 * The event handler for mouse clicks It will display an earthquake and its
	 * threat circle of cities Or if a city is clicked, it will display all the
	 * earthquakes where the city is in the threat circle
	 */
	@Override
	public void mouseClicked() {
		AirportMarker airport = dispRouteifClicked();

		if (lastClicked != null) {
			unhideMarkers();
			lastClicked = airport;
		} else {
			CommonMarker m1 = getSelectedMarker(quakeMarkers);
			if (m1 != null) {
				this.lastClicked = m1;
				hideMarkers(quakeMarkers);
				hideCityMarkers((EarthquakeMarker) m1);
			}
			CommonMarker m2 = getSelectedMarker(cityMarkers);
			if (m2 != null) {
				this.lastClicked = m2;
				hideMarkers(cityMarkers);
				hideQuakeMarkers((CityMarker) m2);
			}
		}
	}

	private AirportMarker dispRouteifClicked() {
		AirportMarker airport = (AirportMarker) getSelectedMarker(this.airportList);

		if (airport != null) {
			if (airport != this.lastClicked) {
				dispRouteswithPort(airport);
			}
		} else {
			hideAllRoutes();
		}

		return airport;
	}

	/**
	 * getSelectedMarker 選択したマーカを取得
	 * 
	 */
	private CommonMarker getSelectedMarker(List<Marker> markers) {
		for (Marker m : markers) {
			if (!m.isHidden() && m.isInside(map, mouseX, mouseY)) {
				return (CommonMarker) m;
			}
		}
		return null;
	}

	/**
	 * hide markers if not selected
	 */
	private void hideMarkers(List<Marker> markers) {
		for (Marker m : markers) {
			if (m != this.lastClicked) {
				m.setHidden(true);
			}
		}
	}

	/**
	 * hideQuakeMarkers 都市に影響しない地震マーカーを消去
	 * 
	 */
	private void hideQuakeMarkers(CityMarker city) {
		for (Marker m : quakeMarkers) {
			EarthquakeMarker qm = (EarthquakeMarker) m;
			if (qm.getDistanceTo(city.getLocation()) > qm.threatCircle()) {
				qm.setHidden(true);
			}
		}

	}

	/**
	 * hideCityMarkers 地震の影響範囲外の都市マーカーを消去
	 */
	private void hideCityMarkers(EarthquakeMarker quake) {
		for (Marker mhide : cityMarkers) {
			if (mhide.getDistanceTo(quake.getLocation()) > quake.threatCircle()) {
				mhide.setHidden(true);
			}
		}
	}

	// loop over and unhide all markers
	private void unhideMarkers() {
		for (Marker marker : quakeMarkers) {
			marker.setHidden(false);
		}

		for (Marker marker : cityMarkers) {
			marker.setHidden(false);
		}
	}

	/**
	 * dispRoureswithPort 指定された空港に発着する路線のみ表示
	 */
	private void dispRouteswithPort(AirportMarker airport) {
		int airportID = Integer.parseInt(airport.getId());

		for (Marker rt : this.routesList) {
			int src = Integer.parseInt(rt.getStringProperty("source"));
			int dst = Integer.parseInt(rt.getStringProperty("destination"));
			if (src == airportID || dst == airportID) {
				rt.setHidden(false);
			} else {
				rt.setHidden(true);
			}

		}
	}

	/**
	 * hideAllRoutes 路線マーカーをすべて消去する
	 */
	private void hideAllRoutes() {
		for (Marker r : this.routesList) {
			r.setHidden(true);
		}
	}

	// helper method to draw key in GUI
	private void addKey() {
		// Remember you can use Processing's graphics methods here
		fill(255, 250, 240);

		int xbase = 25;
		int ybase = 50;

		rect(xbase, ybase, 150, 250);

		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);
		text("Earthquake Key", xbase + 25, ybase + 25);

		fill(150, 30, 30);
		int tri_xbase = xbase + 35;
		int tri_ybase = ybase + 50;
		triangle(tri_xbase, tri_ybase - CityMarker.TRI_SIZE, tri_xbase - CityMarker.TRI_SIZE,
				tri_ybase + CityMarker.TRI_SIZE, tri_xbase + CityMarker.TRI_SIZE, tri_ybase + CityMarker.TRI_SIZE);

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);
		text("City Marker", tri_xbase + 15, tri_ybase);

		text("Land Quake", xbase + 50, ybase + 70);
		text("Ocean Quake", xbase + 50, ybase + 90);
		text("Size ~ Magnitude", xbase + 25, ybase + 110);

		fill(255, 255, 255);
		ellipse(xbase + 35, ybase + 70, 10, 10);
		rect(xbase + 35 - 5, ybase + 90 - 5, 10, 10);

		fill(color(255, 255, 0));
		ellipse(xbase + 35, ybase + 140, 12, 12);
		fill(color(0, 0, 255));
		ellipse(xbase + 35, ybase + 160, 12, 12);
		fill(color(255, 0, 0));
		ellipse(xbase + 35, ybase + 180, 12, 12);

		textAlign(LEFT, CENTER);
		fill(0, 0, 0);
		text("Shallow", xbase + 50, ybase + 140);
		text("Intermediate", xbase + 50, ybase + 160);
		text("Deep", xbase + 50, ybase + 180);

		text("Past hour", xbase + 50, ybase + 200);

		fill(255, 255, 255);
		int centerx = xbase + 35;
		int centery = ybase + 200;
		ellipse(centerx, centery, 12, 12);

		strokeWeight(2);
		line(centerx - 8, centery - 8, centerx + 8, centery + 8);
		line(centerx - 8, centery + 8, centerx + 8, centery - 8);

	}

	// Checks whether this quake occurred on land. If it did, it sets the
	// "country" property of its PointFeature to the country where it occurred
	// and returns true. Notice that the helper method isInCountry will
	// set this "country" property already. Otherwise it returns false.
	private boolean isLand(PointFeature earthquake) {

		// IMPLEMENT THIS: loop over all countries to check if location is in any of
		// them
		// If it is, add 1 to the entry in countryQuakes corresponding to this country.
		for (Marker country : countryMarkers) {
			if (isInCountry(earthquake, country)) {
				return true;
			}
		}

		// not inside any country
		return false;
	}

	/**
	 * sortAndPrint 地震情報をマグニチュードで降順ソートし表示
	 * 
	 */
	private void sortAndPrint(int numToPrint) {
		EarthquakeMarker[] quakes = new EarthquakeMarker[quakeMarkers.size()];
		quakeMarkers.toArray(quakes);

		Arrays.sort(quakes, Comparator.reverseOrder());
		for (int i = 0; i < numToPrint; i++) {
			System.out.println("M " + quakes[i].getMagnitude() + " :  " + quakes[i].getTitle());
		}
	}

	// prints countries with number of earthquakes
	// You will want to loop through the country markers or country features
	// (either will work) and then for each country, loop through
	// the quakes to count how many occurred in that country.
	// Recall that the country markers have a "name" property,
	// And LandQuakeMarkers have a "country" property set.
	private void printQuakes() {
		int totalWaterQuakes = quakeMarkers.size();
		for (Marker country : countryMarkers) {
			String countryName = country.getStringProperty("name");
			int numQuakes = 0;
			for (Marker marker : quakeMarkers) {
				EarthquakeMarker eqMarker = (EarthquakeMarker) marker;
				if (eqMarker.isOnLand()) {
					if (countryName.equals(eqMarker.getStringProperty("country"))) {
						numQuakes++;
					}
				}
			}
			if (numQuakes > 0) {
				totalWaterQuakes -= numQuakes;
				System.out.println(countryName + ": " + numQuakes);
			}
		}
		System.out.println("OCEAN QUAKES: " + totalWaterQuakes);
	}

	// helper method to test whether a given earthquake is in a given country
	// This will also add the country property to the properties of the earthquake
	// feature if
	// it's in one of the countries.
	// You should not have to modify this code
	private boolean isInCountry(PointFeature earthquake, Marker country) {
		// getting location of feature
		Location checkLoc = earthquake.getLocation();

		// some countries represented it as MultiMarker
		// looping over SimplePolygonMarkers which make them up to use isInsideByLoc
		if (country.getClass() == MultiMarker.class) {

			// looping over markers making up MultiMarker
			for (Marker marker : ((MultiMarker) country).getMarkers()) {

				// checking if inside
				if (((AbstractShapeMarker) marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));

					// return if is inside one
					return true;
				}
			}
		}

		// check if inside country represented by SimplePolygonMarker
		else if (((AbstractShapeMarker) country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));

			return true;
		}
		return false;
	}

}
