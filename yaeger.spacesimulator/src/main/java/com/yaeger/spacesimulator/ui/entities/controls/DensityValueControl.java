package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;

public class DensityValueControl extends ValueControl {

	public DensityValueControl(Coordinate2D initialLocation, double width) throws Exception {
		super(initialLocation, width, "Density", 1, 1, 4, "%.3f");
		setSubtitle("(g/cm^3)");
	}

}
