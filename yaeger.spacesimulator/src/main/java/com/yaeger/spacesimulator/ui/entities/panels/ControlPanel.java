package com.yaeger.spacesimulator.ui.entities.panels;

import java.text.DecimalFormat;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.yaeger.spacesimulator.ui.entities.controls.ValueControl;

public class ControlPanel extends Panel {

	private ValueControl volumeValueControl;
	private ValueControl densityValueControl;

	public ControlPanel(Coordinate2D coordinate2d, Size size) {
		super(coordinate2d, size);
		DecimalFormat myFormatter = new DecimalFormat("###,###,###.###");
		try {
			volumeValueControl = new ValueControl(new Coordinate2D(15, size.height() - 15), size.width() - 30, "Volume",
					0.001, 0.001, 150000, myFormatter);
			densityValueControl = new ValueControl(new Coordinate2D(15, size.height() - 120), size.width() - 30,
					"Density", 0.5, 0.5, 6, "%.3f");
		} catch (Exception e) {
			// log
			e.printStackTrace();
		}
	}

	@Override
	protected void setupEntities() {
		volumeValueControl.setSubtitle("(x 10^12 km^3)");
		densityValueControl.setSubtitle("(g/cm^3)");

		volumeValueControl.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		densityValueControl.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);

		addEntity(wrapper);
		addEntity(volumeValueControl);
		addEntity(densityValueControl);

	}

}
