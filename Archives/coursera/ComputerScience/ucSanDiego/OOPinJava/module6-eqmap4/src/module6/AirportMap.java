package module6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

/**
 * An applet that shows airports (and routes) on a world map.
 * 
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 *         MOOC team
 *
 */
public class AirportMap extends PApplet {
	// property
	UnfoldingMap map;
	HashMap<Integer, Location> airports;

	// Marker
	/**
	 * 空港マーカリスト
	 */
	List<Marker> airportList;

	/**
	 * 路線マーカーリスト
	 */
	List<Marker> routesList;

	AirportMarker lastSelected = null;
	AirportMarker lastClicked = null;

	/**
	 * setup
	 */
	public void setup() {
		// setting up PAppler
		size(800, 600, OPENGL);

		// setting up map and default events
		map = new UnfoldingMap(this, 50, 50, 750, 550, new OpenStreetMap.OpenStreetMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);

		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");

		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		airports = new HashMap<Integer, Location>();

		// create markers from features
		for (PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
			int id = Integer.parseInt(feature.getId());
			m.setId(feature.getId());
			m.setRadius(5);
			airportList.add(m);

			// put airport in hashmap with OpenFlights unique id for key
			airports.put(id, feature.getLocation());

		}

		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		this.routesList = getRouteMarkers(routes);

		// add marker
		map.addMarkers(airportList);
		map.addMarkers(this.routesList);
	}

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

	/**
	 * map表示
	 * 
	 */
	public void draw() {
		background(0);
		map.draw();

	}

	/**
	 * Event handler that gets called automatically when the mouse moves.
	 */
	@Override
	public void mouseMoved() {
		AirportMarker m = getSelectedMarker(airportList);
		if (m != null) {
			if (lastSelected != null) {
				lastSelected.setSelected(false);
			}
			lastSelected = m;
			lastSelected.setSelected(true);
		} else {
			if (lastSelected != null) {
				lastSelected.setSelected(false);
			}
			lastSelected = null;
		}
	}

	/**
	 * mouseClicked マウスボタンクリック時の動作 ／ クリックした空港に発着した路線を表示
	 */
	@Override
	public void mouseClicked() {
		AirportMarker airport = getSelectedMarker(airportList);
		if (airport != null) {
			if (airport != this.lastClicked) {
				dispRouteswithPort(airport);
			}
			this.lastClicked = airport;
		} else {
			hideAllRoutes();
			this.lastClicked = null;
			System.out.println("routes all clear");
		}
	}

	/**
	 * getSelectedMarker 選択したマーカを取得 lastSelected = m; lastSelected }
	 */
	private AirportMarker getSelectedMarker(List<Marker> markers) {
		for (Marker m : markers) {
			if (!m.isHidden() && m.isInside(map, mouseX, mouseY)) {
				return (AirportMarker) m;
			}
		}
		return null;
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

}
