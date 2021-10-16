package com.yaeger.spacesimulator.ui.entities.panels;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.ui.entities.EntityWrapper;

public abstract class Panel extends CompositeEntity {

	protected EntityWrapper wrapper;

	protected Panel(Coordinate2D initialLocation, Size size) {
		super(initialLocation);
		wrapper = new EntityWrapper(new Coordinate2D(), size);
		this.setViewOrder(Double.parseDouble(ConfigService.getValue("ui-vieworder")));
	}
}
