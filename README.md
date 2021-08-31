# Traveling-Salesman-Problem
This famous problem could be described with the simple question:

"Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once
and returns to the origin city?"

It is proven that there is no algorithm to solve this problem in polynomial time(NP-Hard), as for every added city in the list the calculation time grows exponentially.
Different Algorithms were developed to get a solution as near as possible to optimal one. In this project is implemented an improved version of K-OPT Algorithm using Delaunay Triangulation.
*The basic idea of K-OPT* :

*1.Given a tour, delete k mutually disjoint edges.

*2.Reassemble the remaining fragments into a tour, leaving no disjoint subtours

*3.Each fragment endpoint can be connected to 2k − 2 other possibilities: of 2k total fragment endpoints available, the two endpoints of the fragment under consideration are disallowed.

In this algorithm, first the crossing edges are eliminated by detouring in the region. This results always in a shorter tour. Then the resulting tour and the triangulation are synchronized. The last step is the K-Optimization
that deletes k mutually disjoint edges and reconnects the subtours using Delaunay edges (from the Delaunay triangulation). The solution is then compared to the result from two different algorithms: MST-Tour which is a 2-Approx Algorithm,
and to Christophides-Tour which is a 1,5-Approx algorithm. In every example, the solution is significantly better.

###Features

-Open examples

-Zoom in / Zoom out

-Scene dragging 

-Drag&Drop Points 

-Delaunay Triangulation

-Delaunay edges of different order (max 4)

-Minimal Spanning Tree

-Syncing Tour and Triangulation

-Save Tour/Instance

