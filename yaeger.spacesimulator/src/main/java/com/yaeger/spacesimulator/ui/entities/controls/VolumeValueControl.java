package com.yaeger.spacesimulator.ui.entities.controls;

import java.text.DecimalFormat;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;

public class VolumeValueControl extends ValueControl {

	public VolumeValueControl(Coordinate2D initialLocation, double width) throws Exception {
		super(initialLocation, width, "Volume", Double.parseDouble(ConfigService.getValue("volume-min")),
				Double.parseDouble(ConfigService.getValue("volume-min")),
				Double.parseDouble(ConfigService.getValue("volume-max")), new DecimalFormat("###,###,###.###"));
		setSubtitle("(x 10^12 km^3)");
	}

}
