package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.userinput.MouseButtonReleasedListener;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.ui.entities.EntityWrapper;
import com.yaeger.spacesimulator.ui.entities.text.Value;

public abstract class SimpleButton extends CompositeEntity implements MouseButtonReleasedListener {

	protected EntityWrapper wrapper;
	protected Value<String> text;

	protected SimpleButton(Coordinate2D initialLocation, Size size, String text) {
		super(initialLocation);
		wrapper = new EntityWrapper(new Coordinate2D(), size);
		this.text = new Value<String>(new Coordinate2D(Double.parseDouble(ConfigService.getValue("padding-m")),
				Double.parseDouble(ConfigService.getValue("padding-xs"))), text);
	}

	@Override
	protected void setupEntities() {
		addEntity(wrapper);
		addEntity(text);
	}

}
