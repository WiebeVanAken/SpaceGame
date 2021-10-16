package com.yaeger.spacesimulator.scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.userinput.MouseButtonReleasedListener;
import com.github.hanyaeger.api.userinput.MouseMovedWhileDraggingListener;
import com.yaeger.spacesimulator.dto.ObjectPlacementDTO;
import com.yaeger.spacesimulator.entities.SimulationObject;
import com.yaeger.spacesimulator.services.ObjectCreationService;
import com.yaeger.spacesimulator.services.SimulationPauseService;
import com.yaeger.spacesimulator.services.SimulationUpdateService;
import com.yaeger.spacesimulator.ui.entities.PreviewObject;
import com.yaeger.spacesimulator.ui.entities.controls.PauseButton;
import com.yaeger.spacesimulator.ui.entities.controls.ResetButton;
import com.yaeger.spacesimulator.ui.entities.panels.ControlPanel;
import com.yaeger.spacesimulator.ui.entities.panels.KeyControlsPanelWrapper;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class SimulationScene extends DynamicScene
		implements UpdateExposer, MouseButtonReleasedListener, MouseMovedWhileDraggingListener, KeyListener {
	private SimulationUpdateService simulationUpdater = SimulationUpdateService.getInstance();

	private ArrayList<SimulationObject> simulationObjects;
	private ObjectPlacementDTO objectPlacementDto;
	private PreviewObject previewPlanet;

	public SimulationScene() {
		this.simulationObjects = new ArrayList<SimulationObject>();
		this.objectPlacementDto = new ObjectPlacementDTO();
	}

	public ArrayList<SimulationObject> getSimulationObjects() {
		return this.simulationObjects;
	}

	public void addSimulationObject(SimulationObject object) {
		this.simulationObjects.add(object);
		this.addEntity(object);
	}

	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	@Override
	public void setupEntities() {
		ControlPanel controlPanel = new ControlPanel(new Coordinate2D(20, getHeight() - 20), new Size(220, 350));
		controlPanel.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		controlPanel.observeDensityValueControl(objectPlacementDto);
		controlPanel.observeVolumeValueControl(objectPlacementDto);
		addEntity(controlPanel);

		PauseButton pauseButton = new PauseButton(new Coordinate2D(20, 20), new Size(32, 32));
		addEntity(pauseButton);

		SimulationPauseService.initializeInstance(this, pauseButton);
		ObjectCreationService.initializeInstance(this);

		KeyControlsPanelWrapper keyControlsPanelWrapper = new KeyControlsPanelWrapper(
				new Coordinate2D(getWidth() - 20, getHeight() - 20));
		addEntity(keyControlsPanelWrapper);

		ResetButton resetButton = new ResetButton(new Coordinate2D(getWidth() - 210, getHeight() - 20),
				new Size(100, 30), this);
		resetButton.setAnchorPoint(AnchorPoint.BOTTOM_RIGHT);
		addEntity(resetButton);
	}

	@Override
	public void postActivate() {
		super.postActivate();
		SimulationPauseService.getInstance().setPaused(true);
	}

	@Override
	public void explicitUpdate(long timestamp) {
		simulationUpdater.updateSimulation(simulationObjects);
	}

	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D mousePos) {
		if (button.name() == MouseButton.PRIMARY.toString() && this.objectPlacementDto.getPlacing()) {
			if (this.simulationObjects.size() == 0)
				ObjectCreationService.getInstance().addCentrePlanet(this.objectPlacementDto);
			else
				ObjectCreationService.getInstance().addPlanet(this.objectPlacementDto);
			this.objectPlacementDto.setPlacing(false);
			previewPlanet.remove();
			objectPlacementDto.reset();
		}
	}

	@Override
	public void onMouseMovedWhileDragging(Coordinate2D mousePos) {
		if (objectPlacementDto.getPlacing()) {
			objectPlacementDto.setStopPosition(mousePos);
		} else {
			objectPlacementDto.setStartPosition(mousePos);
			objectPlacementDto.setPlacing(true);
			previewPlanet = new PreviewObject(new Coordinate2D(),
					objectPlacementDto.getVolume() / objectPlacementDto.getDensity(), objectPlacementDto.getColor());
			this.addEntity(previewPlanet);
			previewPlanet.setAnchorLocation(mousePos);
		}
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.P))
			SimulationPauseService.getInstance().togglePause();
		else if (pressedKeys.contains(KeyCode.R))
			resetSimulation();
	}

	public void resetSimulation() {
		for (Iterator<SimulationObject> iterator = simulationObjects.iterator(); iterator.hasNext();) {
			SimulationObject o = iterator.next();
			o.remove();
			iterator.remove();
		}
	}

}
