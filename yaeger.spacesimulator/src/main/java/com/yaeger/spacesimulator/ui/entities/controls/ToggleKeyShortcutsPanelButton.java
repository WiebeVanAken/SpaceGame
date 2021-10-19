package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.yaeger.spacesimulator.ui.entities.panels.KeyControlsPanel;

import javafx.scene.input.MouseButton;

/**
 * A {@code ToggleKeyShortcutsPanelButton} is a concrete implementation of
 * {@link SimpleButton}, which is used to create a button to toggle the
 * visibility of {@link KeyControlsPanel}.
 *
 */
public class ToggleKeyShortcutsPanelButton extends SimpleButton {

	private KeyControlsPanel panel;

	/**
	 * Create a new instance of this {@link ToggleKeyShortcutsPanelButton} for the
	 * given initial location, size and panel.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param size            the size as a {@link Size}.
	 * @param panel           the panel as a {@link KeyControlsPanel}.
	 */
	public ToggleKeyShortcutsPanelButton(Coordinate2D initialLocation, Size size, KeyControlsPanel panel) {
		super(initialLocation, size, "Key Controls");
		this.setAnchorPoint(AnchorPoint.BOTTOM_RIGHT);
		this.panel = panel;
	}

	/**
	 * Called when the mouse button is released and used to toggle the visibility of
	 * the {@link KeyControlsPanel}.
	 */
	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D coordinate2d) {
		panel.setVisible(!panel.isVisible());
	}

}
