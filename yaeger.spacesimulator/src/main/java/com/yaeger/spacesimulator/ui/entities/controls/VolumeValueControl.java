package com.yaeger.spacesimulator.ui.entities.controls;

import java.text.DecimalFormat;

import com.github.hanyaeger.api.Coordinate2D;

public class VolumeValueControl extends ValueControl {

	public VolumeValueControl(Coordinate2D initialLocation, double width) throws Exception {
		super(initialLocation, width, "Volume", 1, 1, 1500, new DecimalFormat("###,###,###.###"));
		setSubtitle("(x 10^12 km^3)");
	}

}
