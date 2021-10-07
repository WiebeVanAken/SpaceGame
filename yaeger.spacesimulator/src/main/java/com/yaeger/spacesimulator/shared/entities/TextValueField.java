package com.yaeger.spacesimulator.shared.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;

public class TextValueField extends CompositeEntity {

	private TextValue valueField;

	public TextValueField(Coordinate2D initialLocation, String initialValue) {
		super(initialLocation);
		valueField = new TextValue(new Coordinate2D(5, 5), initialValue);
	}

	@Override
	protected void setupEntities() {
		var valueFieldWrapper = new EntityWrapper(new Coordinate2D(0, 0), new Size(100, 30));
		addEntity(valueFieldWrapper);
		addEntity(valueField);
	}

}
