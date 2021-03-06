package tsp.main;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainGroup extends BorderPane implements MainGroupInterface {

    public MainGroupController controller;
    PannableCanvas pannableCanvas;
    ButtonBox buttonBox;
    private Label tourLengthLabel;


    public MainGroup() {
        controller = new MainGroupController();
        controller.setView(this);

        pannableCanvas = new PannableCanvas();
        buttonBox = new ButtonBox();

        controller.setPannableCanvasController(pannableCanvas.getController());
        controller.setButtonBoxController(buttonBox.getController());


        tourLengthLabel = new Label("start");


        setTop(buttonBox);
        setCenter(pannableCanvas);
        setBottom(tourLengthLabel);


    }


    public MainGroupControllerInterface getController() {
        return controller;
    }

    public void updateTourLength() {
        try {

            tourLengthLabel.setText(tourLengthLabel.getText() + "\n" + String.valueOf(controller.mainController.getInstance().getTourLength()));
        } catch (NullPointerException e) {
            tourLengthLabel.setText("Noch keine Tour berechnet");
        }
    }

    @Override
    public void resetTourLengthLabel() {
        tourLengthLabel.setText("start");
    }


}
