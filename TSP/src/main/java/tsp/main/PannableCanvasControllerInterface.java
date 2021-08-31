package tsp.main;

public interface PannableCanvasControllerInterface extends Controller {
    void showMST();

    void updateMST();

    void showTour();


    void updateTour();

    void updateTriangulation();

    void updatePoints();



    void showDelaunayEdgesWithSpecificOrder(int order);

    void hideDelaunayEdgesWithSpecificOrder();

    void showTriangulation();

    void centerVisualisation();
}
