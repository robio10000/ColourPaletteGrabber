package dev.goerissen.colourpalettegrabber.controller.helper;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

/**
 * MainFXHelper is a helper class for the JavaFX application.
 *
 * @version 1.0.0
 * @since 2025-04-25
 * @author robingorissen
 */
public class MainFXHelper {

    private final Label title;
    private final Label subTitle;
    private final Label description;
    private final Button getTitlesBackButton;
    private final SplitPane splitPane;

        private boolean isTitleTriggered;

    public MainFXHelper(Label title, Label subTitle, Label description, Button getTitlesBackButton, SplitPane splitPane) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.getTitlesBackButton = getTitlesBackButton;
        this.splitPane = splitPane;
        this.isTitleTriggered = true; // Initial state
    }

    /**
     * Handles the button click event to toggle the visibility of titles/notes.
     */
    public void handleGetTitlesBack() {
        title.setVisible(isTitleTriggered);
        title.setManaged(isTitleTriggered);
        subTitle.setVisible(isTitleTriggered);
        subTitle.setManaged(isTitleTriggered);
        description.setVisible(isTitleTriggered);
        description.setManaged(isTitleTriggered);
        isTitleTriggered = !isTitleTriggered;
    }

    /**
     * Initializes the visibility of the UI components.
     */
    public void initializeVisibilities() {
        splitPane.setVisible(true);
        splitPane.setManaged(true);
        getTitlesBackButton.setVisible(true);
        getTitlesBackButton.setManaged(true);
        title.setVisible(false);
        title.setManaged(false);
        subTitle.setVisible(false);
        subTitle.setManaged(false);
        description.setVisible(false);
        description.setManaged(false);
    }
}
