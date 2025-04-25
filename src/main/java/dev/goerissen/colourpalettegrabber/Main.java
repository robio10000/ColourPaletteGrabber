package dev.goerissen.colourpalettegrabber;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

/**
 * Main class for the ColourPaletteGrabber application.
 * @version 1.0.0
 * @since 2025-04-24
 * @author robingorissen
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setTitle("Colour Palette Grabber");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}