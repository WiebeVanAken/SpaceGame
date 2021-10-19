package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;

/**
 * A {@code DensityValueControl} is a {@link ValueControl}, which is used to
 * create a control specific for configuration of the density.
 *
 */
public class DensityValueControl extends ValueControl {

	/**
	 * Create a new instance of {@code DensityValueControl} for the given initial
	 * location and width.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @throws Exception the exception that get thrown when the max value of this
	 *                   object's slider is not greater then the min value.
	 */
	public DensityValueControl(Coordinate2D initialLocation, double width) throws Exception {
		super(initialLocation, width, "Density", Double.parseDouble(ConfigService.getValue("density-min")),
				Double.parseDouble(ConfigService.getValue("density-min")),
				Double.parseDouble(ConfigService.getValue("density-max")), "%.3f");
		setSubtitle("(g/cm^3)");
	}

}
