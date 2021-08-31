package triang;

import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.MaskSubgraph;
import tsp.main.Instance;
import tsp.main.ModifiedWeightedEdge;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TriangulationBuilder {
    DefaultUndirectedWeightedGraph<Point2D, ModifiedWeightedEdge> graph;
    MaskSubgraph<Point2D, ModifiedWeightedEdge> tour;
    MaskSubgraph<Point2D, ModifiedWeightedEdge> triangulation;
    private Instance instance;

    public TriangulationBuilder(DefaultUndirectedWeightedGraph<Point2D, ModifiedWeightedEdge> graph) {
        this.graph = graph;

        tour = new MaskSubgraph<>(graph, (Point2D p) -> false, (ModifiedWeightedEdge edge) -> !edge.isInTour());
        triangulation = new MaskSubgraph<>(graph, (Point2D p) -> false, (ModifiedWeightedEdge edge) -> !edge.isInTriangulation());

    }

    public TriangulationBuilder(Instance instance) {
        this.instance = instance;
    }




    public static boolean isLineIntersectAnyOtherLine(Line2D line, Collection<Line2D> lines) {
        for (Line2D possibleIntersectingLine : lines) {

            if (areLinesIntersectingWithoutEndpoints(line, possibleIntersectingLine)) {
                return true;
            }
        }
        return false;
    }

   public void deleteAllEdgesOfTriangulationWitchAreCrossingAnEdgeOfTour() {

        ArrayList<ModifiedWeightedEdge> traingulationEdgesToDelete = new ArrayList<>();

        for (ModifiedWeightedEdge triangulationEdge : triangulation.edgeSet()
        ) {
            for (ModifiedWeightedEdge tourEdge : tour.edgeSet()
            ) {
                if (!triangulationEdge.equals(tourEdge)) {

                    if (areLinesIntersectingWithoutEndpoints(triangulationEdge.getLine2D(), tourEdge.getLine2D())) {
                        traingulationEdgesToDelete.add(triangulationEdge);
                    }
                }
            }
        }

        traingulationEdgesToDelete.forEach(modifiedWeightedEdge -> modifiedWeightedEdge.setInTriangulation(false));

        completeTriangulationWithValidEdges();

    }





    public static boolean areLinesIntersectingWithoutEndpoints(Line2D line1, Line2D line2) {


        boolean result;
        boolean sameAtP1 = false;
        boolean sameAtP2 = false;

        if (line1.getP1().equals(line2.getP1()) || line1.getP1().equals(line2.getP2())) {
            sameAtP1 = true;

        }

        if (line1.getP2().equals(line2.getP1()) || line1.getP2().equals(line2.getP2())) {
            sameAtP2 = true;

        }

        if (sameAtP1 && sameAtP2) {
            result = false;

        } else {
            if (sameAtP1 || sameAtP2) {
                result = false;
            } else {
                result = line1.intersectsLine(line2);
            }
        }


        return result;
    }

    private void completeTriangulationWithValidEdges() {

        List<Line2D> triangulationLines = triangulation.edgeSet().stream().map(ModifiedWeightedEdge::getLine2D).collect(Collectors.toList());
        List<Line2D> tourLines = tour.edgeSet().stream().map(ModifiedWeightedEdge::getLine2D).collect(Collectors.toList());
        List<Line2D> doNotCrossingLines = new ArrayList<>();
        doNotCrossingLines.addAll(triangulationLines);
        doNotCrossingLines.addAll(tourLines);
        for (ModifiedWeightedEdge edge : graph.edgeSet()) {
            Line2D line = edge.getLine2D();

            if (!isLineIntersectAnyOtherLine(line, doNotCrossingLines)) {


                edge.setInTriangulation(true);
                doNotCrossingLines.add(line);

            }

        }
    }


}
