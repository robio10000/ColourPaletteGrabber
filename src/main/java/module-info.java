module dev.goerissen.colourpalettegrabber {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires javafx.swing;

    opens dev.goerissen.colourpalettegrabber to javafx.fxml;
    exports dev.goerissen.colourpalettegrabber;
    exports dev.goerissen.colourpalettegrabber.controller;
    opens dev.goerissen.colourpalettegrabber.controller to javafx.fxml;

    exports dev.goerissen.colourpalettegrabber.util;
}