import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class BruteCollinearPointsTest {
  
  BruteCollinearPoints bcp;
  @Before
  public void setUp() throws Exception {
    bcp = generateBCP("input10.txt");
  }
  
  private BruteCollinearPoints generateBCP(String filename) {
    In in = new In("collinear-test-files/" + filename);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    } 
    return new BruteCollinearPoints(points);
  }

  @Test
  public void testNumberOfSegments() {
    //fail("Not yet implemented");
  }

  @Test
  public void testSegments() {
    //fail("Not yet implemented");
  }

}
