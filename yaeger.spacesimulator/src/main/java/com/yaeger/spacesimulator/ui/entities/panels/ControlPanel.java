package com.yaeger.spacesimulator.ui.entities.panels;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.controls.DensityValueControl;
import com.yaeger.spacesimulator.ui.entities.controls.VolumeValueControl;

public class ControlPanel extends Panel {

	private VolumeValueControl volumeValueControl;
	private DensityValueControl densityValueControl;

	public ControlPanel(Coordinate2D coordinate2d, Size size) {
		super(coordinate2d, size);

		try {
			volumeValueControl = new VolumeValueControl(new Coordinate2D(15, size.height() - 15), size.width() - 30);
			densityValueControl = new DensityValueControl(new Coordinate2D(15, size.height() - 120), size.width() - 30);
		} catch (Exception e) {
			// log
			e.printStackTrace();
		}
	}

	@Override
	protected void setupEntities() {

		volumeValueControl.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		densityValueControl.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);

		addEntity(wrapper);
		addEntity(volumeValueControl);
		addEntity(densityValueControl);

	}

	public void observeVolumeValueControl(IObserver<Double> observer) {
		volumeValueControl.observe(observer);
		System.out.println("bserving volume (controlpanel)");
	}

	public void observeDensityValueControl(IObserver<Double> observer) {
		densityValueControl.observe(observer);
	}
}
