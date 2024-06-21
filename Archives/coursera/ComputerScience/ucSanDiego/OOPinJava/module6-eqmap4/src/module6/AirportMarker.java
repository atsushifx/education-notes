package module6;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * A class to represent AirportMarkers on a world map.
 * 
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 *         MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;

	public AirportMarker(Feature city) {
		super(((PointFeature) city).getLocation(), city.getProperties());

	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(200, 10, 10);
		pg.ellipse(x, y, 5, 5);

		if (this.isSelected()) {
			this.showTitle(pg, x, y);
		}

	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		// show rectangle with titles
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x + 10, y - 20, 100, 40);
		pg.fill(14);
		pg.text(this.toString(), x + 10, y - 8);
		// show routes

	}

	// getter
	public String getCity() {
		return this.getStringProperty("city");
	}

	public String getCountry() {
		return this.getStringProperty("country");
	}

	public String toString() {
		return this.getCountry() + "\n" + getCity();
	}
}
