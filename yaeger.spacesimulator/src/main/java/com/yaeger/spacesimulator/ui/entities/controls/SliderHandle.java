package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.userinput.MouseDraggedListener;
import com.yaeger.spacesimulator.ui.entities.Rectangle;

import javafx.scene.paint.Color;

/**
 * A {@code SliderHandle} is used to create a handle for a {@link Slider}, which
 * can be moved to update the position in the slider.
 *
 */
public class SliderHandle extends Rectangle implements MouseDraggedListener {

	private Slider slider;

	/**
	 * Create a new instance of this {@link SliderHandle} for the given initial
	 * locaction, size, fill color and slider.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param size            the size as a {@link Size}.
	 * @param fill            the fill color as a {@link Color}.
	 * @param slider          the slider as a {@link Slider}.
	 */
	public SliderHandle(Coordinate2D initialLocation, Size size, Color fill, Slider slider) {
		super(initialLocation, size, fill);
		this.slider = slider;
		this.setAnchorPoint(AnchorPoint.CENTER_CENTER);
	}

	/**
	 * this method is called when the {@link SliderHandle} is dragged and used to
	 * update its position.
	 */
	@Override
	public void onDragged(Coordinate2D coordinate2d) {
		if (coordinate2d.getX() > slider.getAnchorLocation().getX()
				&& coordinate2d.getX() < slider.getAnchorLocation().getX() + slider.getBaseWidth()) {
			this.setAnchorLocationX(coordinate2d.getX());
			slider.setPosition(coordinate2d.getX());
		}
	}

	@Override
	public void onDropped(Coordinate2D coordinate2d) {

	}

}
