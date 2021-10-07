package com.yaeger.spacesimulator.ui.entities.text;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.EntityWrapper;

public class TextValueField extends CompositeEntity {

	private TextValue valueField;

	public TextValueField(Coordinate2D initialLocation, String initialValue) {
		super(initialLocation);
		valueField = new TextValue(new Coordinate2D(), initialValue);
	}

	@Override
	protected void setupEntities() {
		var valueFieldWrapper = new EntityWrapper(new Coordinate2D(5, 0), new Size(100, 25));
		valueField.setAnchorPoint(AnchorPoint.CENTER_RIGHT);
		valueFieldWrapper.setAnchorPoint(AnchorPoint.CENTER_RIGHT);
		addEntity(valueFieldWrapper);
		addEntity(valueField);
	}

}
