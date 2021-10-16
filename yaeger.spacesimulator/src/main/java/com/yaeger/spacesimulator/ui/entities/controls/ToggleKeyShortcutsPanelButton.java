package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.yaeger.spacesimulator.ui.entities.panels.KeyControlsPanel;

import javafx.scene.input.MouseButton;

public class ToggleKeyShortcutsPanelButton extends SimpleButton {

	private KeyControlsPanel panel;

	public ToggleKeyShortcutsPanelButton(Coordinate2D initialLocation, Size size, KeyControlsPanel panel) {
		super(initialLocation, size, "Key Controls");
		this.setAnchorPoint(AnchorPoint.BOTTOM_RIGHT);
		this.panel = panel;
	}

	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D coordinate2d) {
		panel.setVisible(!panel.isVisible());
	}

}
