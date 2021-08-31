package tsp.main;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PannableCanvasController implements PannableCanvasControllerInterface {

    private MainController mainController;
    private PannableCanvasInterface view;



    public List<Line2D> getDelaunayEdgesWithOrder(int order) {
        List<Line2D> result = mainController.getInstance()
                .graph
                .edgeSet()
                .stream()
                .filter(modifiedWeightedEdge -> modifiedWeightedEdge.getUsefulDelaunayOrder() == order)
                .map(ModifiedWeightedEdge::getLine2D)
                .collect(Collectors.toList());

        return result;
    }

    public MainController getMainController() {
        return mainController;
    }

    public ArrayList<Line2D> getTourLines() {
        return mainController.getInstance().getTourLines();
    }

    public ArrayList<Line2D> getTriangulationLines() {
        return mainController.getInstance().getTriangulationLines();
    }

    public double getRadiusOfInstance() {
        return mainController.getVertex().getRadius() / 2;
    }



    public void setView(PannableCanvasInterface pannableCanvas) {
        view = pannableCanvas;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setPannableCanvasController(this);
    }


    @Override
    public void updateMST() {
        view.updateMST();
    }

    @Override
    public void updateTour() {
        view.updateTour();
    }

    @Override
    public void updateTriangulation() {
        view.updateTriangulation();
    }

    @Override
    public void updatePoints() {
        view.updatePoints();
    }



    @Override
    public void showMST() {
        view.showMST();
    }

    @Override
    public void showTour() {
        view.showTour();
    }

    @Override
    public void showTriangulation() {
        view.showTriangulation();
    }

    @Override
    public void showDelaunayEdgesWithSpecificOrder(int order) {
        view.showDelaunayEdgesWithSpecificOrder(order);
    }

    @Override
    public void hideDelaunayEdgesWithSpecificOrder() {
        view.hideDelaunayEdgesWithSpecificOrder();
    }

    @Override
    public void centerVisualisation() {
        view.centerVisualisation();
    }


}
