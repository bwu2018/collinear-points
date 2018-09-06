import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class FastCollinearPointsTest {
  
  FastCollinearPoints fcp;
  @Before
  public void setUp() throws Exception {
    fcp = generateFCP("input10.txt");
  }
  
  private FastCollinearPoints generateFCP(String filename) throws IOException {
    In in = new In("collinear-test-files/" + filename);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    } 
    return new FastCollinearPoints(points);
  }

  @Test
  public void testNumberOfSegments() {
    fail("Not yet implemented");
  }

  @Test
  public void testSegments() {
    fail("Not yet implemented");
  }

}
