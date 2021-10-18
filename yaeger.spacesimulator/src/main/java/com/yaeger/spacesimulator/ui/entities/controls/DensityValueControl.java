package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;

public class DensityValueControl extends ValueControl {

	public DensityValueControl(Coordinate2D initialLocation, double width) throws Exception {
		super(initialLocation, width, "Density", Double.parseDouble(ConfigService.getValue("density-min")),
				Double.parseDouble(ConfigService.getValue("density-min")),
				Double.parseDouble(ConfigService.getValue("density-max")), "%.3f");
		setSubtitle("(g/cm^3)");
	}

}
