//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import edu.princeton.cs.algs4.MergeX;



/*************************************************************************
 *  Compilation:  javac-algs4 FastCollinearPoints.java
 *  Execution:    none
 *  Dependencies: Point.java LineSegment.java
 *
 *   Given a point p, the following method determines 
 *   whether p participates in a set of 4 or more collinear points.
 *   Think of p as the origin.
 *   - For each other point q, determine the slope it makes with p.
 *   - Sort the points according to the slopes they makes with p.
 *   - Check if any 3 (or more) adjacent points in 
 *      the sorted order have equal slopes with respect to p. 
 *      If so, these points, together with p, are collinear.
 *
 *************************************************************************/
public class FastCollinearPoints {

    private LineSegment[] finalSegment;
    private ArrayList<LineSegment> segments;
    private ArrayList<SpecialLineSegment> specialSegments;
    private int length = 0;
    private ArrayList<Point> collinearPoints;
    private Point[] holder;
    
    //PrintWriter out;

    /**
     * finds all line segments containing 4 or more points
     * @param points
     * @throws IOException 
     */
    public FastCollinearPoints(Point[] points) {
        //out = new PrintWriter(new FileWriter("output.txt")); 

        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int x = 0; x < points.length; x++) {
            if (points[x] == null) throw new IllegalArgumentException();
            for (int y = x+1; y < points.length; y++) {
                if (points[y] == null) throw new IllegalArgumentException();
                if (points[x].compareTo(points[y]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        Point[] originalPoints = points.clone();
        segments = new ArrayList<LineSegment>();

        collinearPoints = new ArrayList<Point>();
        specialSegments = new ArrayList<SpecialLineSegment>();
        
        for (int i = 0; i < originalPoints.length; i++) {
            Point currentPoint = points[i]; 

            MergeX.sort(originalPoints, currentPoint.slopeOrder());

            collinearPoints.clear();
            for (int j = 1; j < originalPoints.length-1; j++) {
                if (currentPoint.slopeTo(originalPoints[j]) == currentPoint.slopeTo(originalPoints[j+1])) {
                    collinearPoints.add(originalPoints[j]);
                } else {
                    collinearPoints.add(currentPoint); // add original point 
                    collinearPoints.add(originalPoints[j]);
                    //out.println("I is " + i +  " J is " + j + " current is " + currentPoint + " Num of Collinear is " + collinearPoints.size());


                    if (collinearPoints.size() > 3) {
                        holder = new Point[collinearPoints.size()];
                        holder = collinearPoints.toArray(holder);
                        MergeX.sort(holder);
                        SpecialLineSegment testSegment = new SpecialLineSegment(holder[0], holder[holder.length-1]);

                        if (!specialSegments.contains(testSegment)) {
                            specialSegments.add(testSegment);
                            LineSegment toAdd = new LineSegment(holder[0], holder[holder.length-1]);
                            segments.add(toAdd);
                            //out.println("Add Line Segment " + toAdd.toString());
                            length++;
                        }
                        else {
                            //out.println("Already added" + testSegment.toString());
                        }

                    }
                    collinearPoints.clear();
                }
            }
            if (collinearPoints.size() >= 1) {
                //out.println("Adding end point");
                if (currentPoint.slopeTo(originalPoints[originalPoints.length-1]) == currentPoint.slopeTo(collinearPoints.get(collinearPoints.size()-1))) {
                    collinearPoints.add(originalPoints[originalPoints.length-1]);
                }
            }
            // check if last point is also collinear
            if (collinearPoints.size() >= 3) {
                //out.println("Special Add");
                //out.println(currentPoint);
                collinearPoints.add(currentPoint);
                holder = new Point[collinearPoints.size()];
                holder = collinearPoints.toArray(holder);
                MergeX.sort(holder);
                SpecialLineSegment testSegment = new SpecialLineSegment(holder[0], holder[holder.length-1]);

                if (!specialSegments.contains(testSegment)) {
                    specialSegments.add(testSegment);
                    LineSegment toAdd = new LineSegment(holder[0], holder[holder.length-1]);
                    segments.add(toAdd);
                    //out.println("Add Line Segment " + toAdd.toString());
                    length++;
                }
                else {
                    //out.println("Already added" + testSegment.toString());
                }
            }
        }
        //out.close();
    }

    private class SpecialLineSegment {
        private final Point p;   // one endpoint of this line segment
        private final Point q;   // the other endpoint of this line segment

        public SpecialLineSegment(Point p, Point q) {
            if (p == null || q == null) {
                throw new NullPointerException("argument is null");
            }
            this.p = p;
            this.q = q;
        }

        @Override
        public boolean equals(Object object) {
            SpecialLineSegment seg = (SpecialLineSegment) object;
            if (this.p.compareTo(seg.p) == 0 && this.q.compareTo(seg.q) == 0) return true;
            return false;

        }

        /**
         * Returns a string representation of this line segment
         * This method is provide for debugging;
         * your program should not rely on the format of the string representation.
         *
         * @return a string representation of this line segment
         */
        public String toString() {
            return p + " -> " + q;
        }

    }

    /**
     * the number of line segments
     * @return
     */
    public int numberOfSegments() {
        return length;
    }

    /**
     * the line segments
     * @return
     */
    public LineSegment[] segments() {
        finalSegment = new LineSegment[length];
        return segments.toArray(finalSegment);
    }
}