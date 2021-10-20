package com.yaeger.spacesimulator.ui.entities.controls;

import java.text.DecimalFormat;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;

/**
 * A {@code VolumeValueControl} is a {@link ValueControl}, which is used to
 * create a control specific for configuration of the volume.
 *
 */
public class VolumeValueControl extends ValueControl {

	/**
	 * Create a new instance of {@code VolumeValueControl} for the given initial
	 * location and width.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @throws Exception the exception that get thrown when the max value of this
	 *                   object's slider is not greater then the min value.
	 */
	public VolumeValueControl(Coordinate2D initialLocation, double width) throws Exception {
		super(initialLocation, width, "Volume", Double.parseDouble(ConfigService.getValue("volume-min")),
				Double.parseDouble(ConfigService.getValue("volume-min")),
				Double.parseDouble(ConfigService.getValue("volume-max")), new DecimalFormat("###,###,###.###"));
		setSubtitle("(x 10^12 km^3)");
	}

}
