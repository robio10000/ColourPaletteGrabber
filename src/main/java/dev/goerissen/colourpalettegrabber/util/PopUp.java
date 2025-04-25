package dev.goerissen.colourpalettegrabber.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.util.Duration;

/**
 * Utility class to show pop-up tooltips in the application.
 * @version 1.0.0
 * @since 2025-04-24
 * @author robingorissen
 */
public class PopUp {

    private PopUp() {
        // Private constructor to prevent instantiation
    }

    /**
     * Shows a pop-up tooltip with the given message.<br/>
     * The tooltip will be displayed for 2 seconds and will hide automatically.<br/>
     * The Popup position is calculated relative to the ownerNode's scene.<br/>
     * @param ownerNode To which the tooltip is attached
     * @param message The message to be displayed in the tooltip
     */
    public static void showPopUpTooltip(Node ownerNode, String message) {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);

        Label label = new Label(message);
        label.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        StackPane pane = new StackPane(label);
        pane.setStyle("-fx-background-color: transparent;");

        popup.getContent().add(pane);
        popup.setAutoHide(true);

        // Calculate the position relative to the ownerNode's scene
        double x = ownerNode.localToScene(0, 0).getX() + ownerNode.getScene().getWindow().getX() + ownerNode.getScene().getX();
        double y = ownerNode.localToScene(0, 0).getY() + ownerNode.getScene().getWindow().getY() + ownerNode.getScene().getY();
        popup.show(ownerNode, x, y);

        // If the user do not click outside the popup, it will hide after 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> popup.hide()));
        timeline.setCycleCount(1);
        timeline.play();
    }

    /**
     * Shows an alert dialog with the given title, message, and alert type.<br/>
     * The alert will be displayed as a modal dialog.<br/>
     * The alert will wait for the user to close it if waitForUser is true.<br/>
     * @param title The title of the alert dialog
     * @param message The message to be displayed in the alert dialog
     * @param alertType The type of alert to be displayed (e.g., Alert.AlertType.INFORMATION, Alert.AlertType.ERROR, etc.)
     * @param waitForUser If true, the alert will wait for the user to close it
     */
    public static void showAlert(String title, String message, Alert.AlertType alertType, boolean waitForUser) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        if(waitForUser)
            alert.showAndWait();
        else
            alert.show();
    }
}
