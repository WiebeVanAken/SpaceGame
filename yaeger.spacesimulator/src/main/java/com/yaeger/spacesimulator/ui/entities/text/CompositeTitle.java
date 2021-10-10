package com.yaeger.spacesimulator.ui.entities.text;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;

public class CompositeTitle extends CompositeEntity {

	private String title;
	private String subtitle;

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
