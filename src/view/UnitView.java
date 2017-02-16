package view;

import java.awt.image.BufferedImage;

import model.Location;

public class UnitView extends View {

	public UnitView(BufferedImage image, Location loc, int rotation) {
		super(image, loc, rotation);
	}

	public UnitView(BufferedImage image, Location loc) {
		super(image, loc);
	}

}
