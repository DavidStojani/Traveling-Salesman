package tsp.main;

import javafx.scene.Scene;

public class MainScene extends Scene implements MainSceneInterface {

    MainSceneController mainSceneController;
    MainGroup mainGroup;

    MainScene(MainGroup root, double width, double height) {
        super(root, width, height);

        mainGroup = root;

        mainSceneController = new MainSceneController();

        mainSceneController.setScene(this);

        mainSceneController.setMainGroupController(mainGroup.getController());

    }

}
