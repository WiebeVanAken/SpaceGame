package com.yaeger.spacesimulator.ui.entities.panels;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.ui.entities.EntityWrapper;

/**
 * A {@code Panel} is a {@link CompositeEntity}, which is used to create a panel
 * that contains a border.
 *
 */
public abstract class Panel extends CompositeEntity {

	protected EntityWrapper wrapper;

	/**
	 * Create a new instance of this {@link Panel} for the given initial location
	 * and size.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param size            the size as a {@link Size}.
	 */
	protected Panel(Coordinate2D initialLocation, Size size) {
		super(initialLocation);
		wrapper = new EntityWrapper(new Coordinate2D(), size);
		this.setViewOrder(Double.parseDouble(ConfigService.getValue("ui-vieworder")));
	}
}
