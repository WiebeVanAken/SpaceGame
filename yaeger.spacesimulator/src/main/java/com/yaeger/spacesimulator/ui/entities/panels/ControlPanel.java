package com.yaeger.spacesimulator.ui.entities.panels;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.controls.DensityValueControl;
import com.yaeger.spacesimulator.ui.entities.controls.VolumeValueControl;

/**
 * A {@code ControlPanel} is a concrete implementation of {@link Panel}, which
 * is used to create a control panel that consists of a
 * {@link VolumeValueControl} and {@link DensityValueControl}.
 *
 */
public class ControlPanel extends Panel {

	private VolumeValueControl volumeValueControl;
	private DensityValueControl densityValueControl;

	/**
	 * Create a new instance of this {@link ControlPanel} for the given location and
	 * size.
	 *
	 * @param coordinate2d the location as a {@link Coordinate2D}.
	 * @param size         the size as a {@link Size}.
	 */
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

	/**
	 * Used to start observing the volume value control of this control panel.
	 *
	 * @param observer the observer as a {@link IObserver}.
	 */
	public void observeVolumeValueControl(IObserver<Double> observer) {
		volumeValueControl.observe(observer);
		observer.update(volumeValueControl, volumeValueControl.getValue());
	}

	/**
	 * Used to start observing the density value control of this control panel.
	 *
	 * @param observer the observer as a {@link IObserver}.
	 */
	public void observeDensityValueControl(IObserver<Double> observer) {
		densityValueControl.observe(observer);
		observer.update(densityValueControl, densityValueControl.getValue());
	}

	/**
	 * Used to check if the location of this {@link Coordinate2D} is inside the
	 * boundaries of this {@link ControlPanel}.
	 *
	 * @param pos the position as a {@link Coordinate2D}
	 * @return the result as a {@code boolean}.
	 */
	public boolean isInsideBoundary(Coordinate2D pos) {
		if (pos.getX() > getAnchorLocation().getX() && pos.getX() < getAnchorLocation().getX() + getWidth()
				&& pos.getY() > getAnchorLocation().getY() - getHeight() && pos.getY() < getAnchorLocation().getY())
			return true;
		return false;
	}
}
