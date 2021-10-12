package com.yaeger.spacesimulator.scenes;

import java.util.ArrayList;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.MouseButtonReleasedListener;
import com.github.hanyaeger.api.userinput.MouseMovedWhileDraggingListener;
import com.yaeger.spacesimulator.dto.ObjectPlacementDTO;
import com.yaeger.spacesimulator.entities.Planet;
import com.yaeger.spacesimulator.entities.SimulationObject;
import com.yaeger.spacesimulator.services.ObjectCreationService;
import com.yaeger.spacesimulator.services.SimulationPauseService;
import com.yaeger.spacesimulator.services.SimulationUpdateService;
import com.yaeger.spacesimulator.ui.entities.controls.PauseButton;
import com.yaeger.spacesimulator.ui.entities.panels.ControlPanel;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class SimulationScene extends DynamicScene
		implements UpdateExposer, MouseButtonReleasedListener, MouseMovedWhileDraggingListener {
	private SimulationUpdateService simulationUpdater = SimulationUpdateService.getInstance();

	private ArrayList<SimulationObject> simulationObjects;
	private ObjectPlacementDTO objectPlacementDto;
	
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
			if(this.simulationObjects.size() == 0) 
				ObjectCreationService.getInstance().addCentrePlanet(this.objectPlacementDto);
			else
				ObjectCreationService.getInstance().addPlanet(this.objectPlacementDto);
			this.objectPlacementDto.setPlacing(false);
		}
	}

	@Override
	public void onMouseMovedWhileDragging(Coordinate2D mousePos) {
		if (objectPlacementDto.getPlacing()) {
			objectPlacementDto.setStopPosition(mousePos);
		} else {
			objectPlacementDto.setStartPosition(mousePos);
			objectPlacementDto.setPlacing(true);
		}
	}
}
