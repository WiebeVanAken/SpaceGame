package com.yaeger.spacesimulator.ui.entities.text;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;

/**
 * A {@code CompositeTitle} is a concrete implementation of
 * {@link CompositeEntity}, which is used to create a composite title that
 * contains of a title and subtitle.
 *
 */
public class CompositeTitle extends CompositeEntity {

	private String title;
	private String subtitle;

	/**
	 * Create a new instance of this {@link CompositeTitle} for the given initial
	 * location, title and subtitle.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param title           the title as a {@code String}.
	 * @param subtitle        the subtitle as a {@code String}.
	 */
	public CompositeTitle(Coordinate2D initialLocation, String title, String subtitle) {
		super(initialLocation);
		this.title = title;
		this.subtitle = subtitle;
	}

	@Override
	protected void setupEntities() {
		Value titleEntity = new Value(new Coordinate2D(), title);
		SubTextValue subtitleEntity = new SubTextValue(new Coordinate2D(title.length() * 11, 0), subtitle);

		titleEntity.setAnchorPoint(AnchorPoint.CENTER_LEFT);
		subtitleEntity.setAnchorPoint(AnchorPoint.CENTER_LEFT);

		addEntity(titleEntity);
		addEntity(subtitleEntity);
	}

}
