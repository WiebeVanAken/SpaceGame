package com.yaeger.spacesimulator.ui.entities.panels;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.controls.ToggleKeyShortcutsPanelButton;

/**
 * A {@code KeyControlsPanelWrapper} is a concrete implementation of
 * {@link CompositeEntity}, which is used to create a wrapper that contains a
 * {@link KeyControlsPanel} and {@link ToggleKeyShortcutsPanelButton}.
 *
 */
public class KeyControlsPanelWrapper extends CompositeEntity {

	/**
	 * Create a new instance of this {@link KeyControlsPanelWrapper} for the given
	 * initial location.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 */
	public KeyControlsPanelWrapper(Coordinate2D initialLocation) {
		super(initialLocation);
	}

	@Override
	protected void setupEntities() {
		KeyControlsPanel keyControlsPanel = new KeyControlsPanel(new Coordinate2D(0, -30), new Size(170, 65));
		keyControlsPanel.setAnchorPoint(AnchorPoint.BOTTOM_RIGHT);
		keyControlsPanel.setVisible(false);
		addEntity(keyControlsPanel);
		ToggleKeyShortcutsPanelButton toggleKeyShortcutsPanelButton = new ToggleKeyShortcutsPanelButton(
				new Coordinate2D(), new Size(170, 30), keyControlsPanel);
		addEntity(toggleKeyShortcutsPanelButton);
	}

}
