package com.yaeger.spacesimulator.ui.entities.text;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;

public class TitleValuePair extends CompositeEntity {

	private String title;
	private String subtitle;
	private String value;
	private int width;

	public TitleValuePair(Coordinate2D initialLocation, String title, String initialValue, int width) {
		super(initialLocation);
		this.title = title;
		this.value = initialValue;
		this.width = width;
	}

	public TitleValuePair(Coordinate2D initialLocation, String title, String initialValue, String subtitle, int width) {
		this(initialLocation, title, initialValue, width);
		this.subtitle = subtitle;
	}

	@Override
	protected void setupEntities() {
		CompositeTitle compositeTitleEntity = new CompositeTitle(new Coordinate2D(), title, subtitle);
		TextValueField textValueFieldEntity = new TextValueField(new Coordinate2D(width, 0), value);
		addEntity(compositeTitleEntity);
		addEntity(textValueFieldEntity);
	}

}
