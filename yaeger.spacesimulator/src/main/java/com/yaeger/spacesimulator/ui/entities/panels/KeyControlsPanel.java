package com.yaeger.spacesimulator.ui.entities.panels;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.ui.entities.text.SimpleValuePair;

public class KeyControlsPanel extends Panel {

	private SimpleValuePair pauseControlPair;
	private SimpleValuePair resetControlPair;

	public KeyControlsPanel(Coordinate2D initialLocation, Size size) {
		super(initialLocation, size);
		pauseControlPair = new SimpleValuePair(
				new Coordinate2D(Integer.parseInt(ConfigService.getValue("padding-m")), size.height()),
				size.width() - Integer.parseInt(ConfigService.getValue("padding-m")) * 2, "Pause", "P");

		resetControlPair = new SimpleValuePair(
				new Coordinate2D(Integer.parseInt(ConfigService.getValue("padding-m")), size.height() - 20),
				size.width() - Integer.parseInt(ConfigService.getValue("padding-m")) * 2, "Reset", "R");

	}

	@Override
	protected void setupEntities() {
		addEntity(wrapper);
		addEntity(pauseControlPair);
		pauseControlPair.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		resetControlPair.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		addEntity(resetControlPair);
	}
}
