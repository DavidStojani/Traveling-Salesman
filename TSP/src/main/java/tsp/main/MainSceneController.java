package tsp.main;

public class MainSceneController {

    MainGroupControllerInterface mainGroupController;
    MainController mainController;
    private MainSceneInterface mainScene;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainGroupController.setMainController(mainController);
    }

    public void setMainGroupController(MainGroupControllerInterface mainGroupController) {
        this.mainGroupController = mainGroupController;
    }

    public void setScene(MainSceneInterface mainScene) {
        this.mainScene = mainScene;
    }
}
