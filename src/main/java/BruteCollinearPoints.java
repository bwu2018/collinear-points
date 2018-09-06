import java.util.ArrayList;
import java.util.Arrays;


/*************************************************************************
 *  Compilation:  javac-algs4 BruteCollinearPoints.java
 *  Execution:    none
 *  Dependencies: Point.java LineSegment.java
 *
 *   A program that examines 4 points at a time 
 *   and checks whether they all lie on the same line segment, 
 *   returning all such line segments. 
 *   To check whether the 4 points p, q, r, and s are collinear, 
 *   check whether the three slopes between p and q, 
 *   between p and r, and between p and s are all equal.
 *
 *************************************************************************/
public class BruteCollinearPoints {
    
    private ArrayList<LineSegment> segments;
    private int length = 0;
    

    /**
     * finds all line segments containing 4 points
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int x = 0; x < points.length; x++) {
            //System.out.println("x is " + points[x]);
            if (points[x] == null) throw new IllegalArgumentException();
            for (int y = x+1; y < points.length; y++) {
                //System.out.println("y is " + points[y]);
                if (points[y] == null) throw new IllegalArgumentException();
                if (points[x].compareTo(points[y]) == 0) {
                    throw new IllegalArgumentException();
                    //System.out.println("DUP" + points[x] + " , " + points[y]);
                }
            }
        }
        Point[] originalPoints = points.clone();
        Arrays.sort(originalPoints);
        segments = new ArrayList<LineSegment>();
        for (int p = 0; p < originalPoints.length - 3; p++) {
            for (int q = p + 1; q < originalPoints.length - 2; q++) {
                if (originalPoints[p] == originalPoints[q]) throw new IllegalArgumentException();
                for (int r = q + 1; r < originalPoints.length - 1; r++) {
                    for (int s = r + 1; s < originalPoints.length; s++) {
                        
                        if (originalPoints[p].slopeTo(originalPoints[q]) == originalPoints[q].slopeTo(originalPoints[r]) &&
                                originalPoints[p].slopeTo(originalPoints[r]) == originalPoints[p].slopeTo(originalPoints[s])) {
                            segments.add(new LineSegment(originalPoints[p], originalPoints[s]));
                            length++;
                        }
                    }
                }
            }
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
     * return the line segments
     * @return
     */
    public LineSegment[] segments() {
        LineSegment[] finalSegment = new LineSegment[length];
        return segments.toArray(finalSegment);
    }
}