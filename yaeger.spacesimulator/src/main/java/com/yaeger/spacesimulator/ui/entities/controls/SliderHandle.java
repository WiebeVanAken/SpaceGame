package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.userinput.MouseDraggedListener;
import com.yaeger.spacesimulator.ui.entities.Rectangle;

import javafx.scene.paint.Color;

public class SliderHandle extends Rectangle implements MouseDraggedListener {

	private Slider slider;

	public SliderHandle(Coordinate2D initialLocation, Size size, Color fill, Slider slider) {
		super(initialLocation, size, fill);
		this.slider = slider;
		this.setAnchorPoint(AnchorPoint.CENTER_CENTER);
	}

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
