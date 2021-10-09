module com.yaeger.spacesimulator {
    requires javafx.controls;
	requires transitive hanyaeger;
	
    exports com.yaeger.spacesimulator.scenes;
    exports com.yaeger.spacesimulator.services;
    exports com.yaeger.spacesimulator.entities;
    exports com.yaeger.spacesimulator;
}
