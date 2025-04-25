package dev.goerissen.colourpalettegrabber.controller;

import dev.goerissen.colourpalettegrabber.controller.helper.MainFXHelper;
import dev.goerissen.colourpalettegrabber.util.ColorExtractor;
import dev.goerissen.colourpalettegrabber.util.PopUp;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * MainController is the controller class for the main view of the application.
 * It handles user interactions, image loading, and color extraction.
 * @version 1.0.0
 * @since 2025-04-24
 * @author robingorissen
 */
public class MainController {
    private static final System.Logger logger = System.getLogger(MainController.class.getName());

    @FXML private Button selectImageButton;
    @FXML private Button getTitlesBackButton;

    @FXML private Label title;
    @FXML private Label subTitle;
    @FXML private Label description;

    @FXML private VBox colorPane;
    @FXML private VBox imageViewParent;

    @FXML private ImageView imageView;

    @FXML private BorderPane mainPane;
    @FXML private ScrollPane colorScrollPane;
    @FXML private SplitPane splitPane;

    private boolean isFirstUse = true;
    private boolean isTitleTriggered = true;

    private int colorCount = 16;
    private int colorTolerance = 50;
    private int rectangleHeight = 100;
    private MainFXHelper fxHelper;

    /**
     * Initializes the controller and sets up event handlers.
     */
    @FXML
    private void initialize() {

        fxHelper = new MainFXHelper(title, subTitle, description, getTitlesBackButton, splitPane);


        // Drag and drop functionality
        mainPane.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != imageView &&
                    event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        mainPane.setOnDragDropped(this::handleImageDrop);

        // Window resizing listeners
        colorScrollPane.widthProperty().addListener((observable, oldValue, newValue) ->
                updateColorBoxes(newValue.doubleValue()-15)); // 15px padding

        imageViewParent.widthProperty().addListener((observable, oldValue, newValue) ->
            imageView.setFitWidth(newValue.doubleValue()-10));
    }

    /**
     * Handles the button click event to select an image file.
     * @param event The action event triggered by the button click.
     */
    @FXML
    private void handleSelectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        File file = fileChooser.showOpenDialog(selectImageButton.getScene().getWindow());
        if (file != null) {
            loadImageAndColors(file);
        }
    }

    /**
     * Handles the drag and drop event for image files.
     * @param event The drag event containing the dropped files.
     */
    private void handleImageDrop(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            File file = db.getFiles().get(0);
            loadImageAndColors(file);
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }

    /**
     * Loads the image and extracts colors from it.
     * @param file The image file to load.
     */
    private void loadImageAndColors(File file) {
        try {
            // Image
            BufferedImage bufferedImage = ImageIO.read(file);
            Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(fxImage);

            // Colors and hashes
            List<Color> colors = ColorExtractor.extractDistinctColors(ColorExtractor.extractAllColors(bufferedImage), colorCount, colorTolerance);
            colorPane.getChildren().clear();
            for (javafx.scene.paint.Color color : colors) {
                StackPane pane = ColorExtractor.createColorBoxWithColorButtons(color, colorScrollPane.getWidth()-15, rectangleHeight);
                colorPane.getChildren().add(pane);
            }

            // Hide titles initially and show color boxes
            if(isFirstUse) {
                fxHelper.initializeVisibilities();
            }
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Error loading image and extracting colors", e);

            PopUp.showAlert("Error", "An error occurred while loading the image. Please try again.",
                    Alert.AlertType.ERROR, true
            );
        }
    }

    /**
     * Updates the width of the color boxes based on the parent width.
     * @param parentWidth The width of the parent container.
     */
    private void updateColorBoxes(double parentWidth) {
        for (javafx.scene.Node node : colorPane.getChildren()) {
            if (node instanceof StackPane stackPane) {
                Rectangle rect = (Rectangle) stackPane.getChildren().get(0);
                rect.setWidth(parentWidth);
            }
        }
    }


    /**
     * Handles the button click event to toggle the visibility of titles/notes.
     */
    public void handleGetTitlesBack() {
        fxHelper.handleGetTitlesBack();
    }


    // Getter and Setter


    public Button getSelectImageButton() {
        return selectImageButton;
    }

    public void setSelectImageButton(Button selectImageButton) {
        this.selectImageButton = selectImageButton;
    }

    public Button getGetTitlesBackButton() {
        return getTitlesBackButton;
    }

    public void setGetTitlesBackButton(Button getTitlesBackButton) {
        this.getTitlesBackButton = getTitlesBackButton;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public Label getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(Label subTitle) {
        this.subTitle = subTitle;
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(Label description) {
        this.description = description;
    }

    public VBox getColorPane() {
        return colorPane;
    }

    public void setColorPane(VBox colorPane) {
        this.colorPane = colorPane;
    }

    public VBox getImageViewParent() {
        return imageViewParent;
    }

    public void setImageViewParent(VBox imageViewParent) {
        this.imageViewParent = imageViewParent;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    public ScrollPane getColorScrollPane() {
        return colorScrollPane;
    }

    public void setColorScrollPane(ScrollPane colorScrollPane) {
        this.colorScrollPane = colorScrollPane;
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public void setSplitPane(SplitPane splitPane) {
        this.splitPane = splitPane;
    }

    public boolean isFirstUse() {
        return isFirstUse;
    }

    public void setFirstUse(boolean firstUse) {
        isFirstUse = firstUse;
    }

    public boolean isTitleTriggered() {
        return isTitleTriggered;
    }

    public void setTitleTriggered(boolean titleTriggered) {
        isTitleTriggered = titleTriggered;
    }

    public int getColorCount() {
        return colorCount;
    }

    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }

    public int getColorTolerance() {
        return colorTolerance;
    }

    public void setColorTolerance(int colorTolerance) {
        this.colorTolerance = colorTolerance;
    }

    public int getRectangleHeight() {
        return rectangleHeight;
    }

    public void setRectangleHeight(int rectangleHeight) {
        this.rectangleHeight = rectangleHeight;
    }
}
