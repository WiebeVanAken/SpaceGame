module com.yaeger.spacesimulator {
	requires javafx.controls;

	exports com.yaeger.spacesimulator.scenes;
	exports com.yaeger.spacesimulator.services;
	exports com.yaeger.spacesimulator.entities;

	requires hanyaeger;
	requires java.desktop;
	requires javafx.graphics;

	exports com.yaeger.spacesimulator;
}
