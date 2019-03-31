package Navigating2;
import java.util.*;
import java.io.*;
//Login: ams79
//Name: Adriana Samareanu


public class JourneyPlanner
{
  static HashMap<Vertex, ArrayList<Vertex> > paths;
    public static void main(String args[])
    {

      //create a list of all the rectangles on the grid
      List<Rectangle> rectangles = new ArrayList<Rectangle>();
      rectangles.add(new Rectangle(new Vertex(13, 8), new Vertex(13, 16), new Vertex(18, 16), new Vertex(18, 8)));
      rectangles.add(new Rectangle(new Vertex(19, 5), new Vertex(19, 8), new Vertex(20, 8), new Vertex(20, 5)));
      rectangles.add(new Rectangle(new Vertex(1, 11), new Vertex(1, 15), new Vertex(7, 15), new Vertex(7, 11)));
      rectangles.add(new Rectangle(new Vertex(18, 2), new Vertex(18, 5), new Vertex(20, 5), new Vertex(20, 2)));
      rectangles.add(new Rectangle(new Vertex(19, 7), new Vertex(19, 8), new Vertex(22, 8), new Vertex(22, 7)));
      rectangles.add(new Rectangle(new Vertex(1, 0), new Vertex(1, 5), new Vertex(8, 5), new Vertex(8, 0)));
      rectangles.add(new Rectangle(new Vertex(7, 6), new Vertex(7, 11), new Vertex(12, 11), new Vertex(12, 6)));
      rectangles.add(new Rectangle(new Vertex(14, 13), new Vertex(14, 20), new Vertex(16, 20), new Vertex(16, 13)));
      rectangles.add(new Rectangle(new Vertex(22, 3), new Vertex(22, 4), new Vertex(23, 4), new Vertex(23, 3)));
      rectangles.add(new Rectangle(new Vertex(18, 13), new Vertex(18, 14), new Vertex(22, 14), new Vertex(22, 13)));
      rectangles.add(new Rectangle(new Vertex(10, 18), new Vertex(10, 21), new Vertex(15, 21), new Vertex(15, 18)));
      rectangles.add(new Rectangle(new Vertex(4, 16), new Vertex(4, 21), new Vertex(11, 21), new Vertex(11, 16)));
      rectangles.add(new Rectangle(new Vertex(21, 6), new Vertex(21, 8), new Vertex(23, 8), new Vertex(23, 6)));
      rectangles.add(new Rectangle(new Vertex(7, 17), new Vertex(7, 21), new Vertex(11, 21), new Vertex(11, 17)));
      rectangles.add(new Rectangle(new Vertex(5, 19), new Vertex(5, 20), new Vertex(12, 20), new Vertex(12, 19)));
      rectangles.add(new Rectangle(new Vertex(6, 8), new Vertex(6, 10), new Vertex(7, 10), new Vertex(7, 8)));

      //loop through that list && add to a hashmap called paths (only add if it isnt already there)
      paths = new HashMap<Vertex, ArrayList<Vertex>>();
        for(Rectangle r: rectangles) {
        for(Vertex v: r.get_vertices()) {
          if(!paths.containsKey(v)){
        paths.put(v, new ArrayList<Vertex>());
        }
      }
    }

    Set<Vertex> vertices = paths.keySet();
    System.out.println(vertices.size());
      for (Vertex start : vertices) {
          List<Vertex> newPaths = paths.get(start);
          for(Vertex end : vertices){
            if(!start.equals(end)){
              int i = 0;
              //iterates through all rectangles to make sure the edge intersects none
              while (i < 16 && !rectangles.get(i).lineIntersects(start, end)){
                i++;
              }
                if(i == 16){
                  newPaths.add(end);

                }
            }
          }
        }
        //System.out.println(paths);

        //making lists for start/end vertices
        List<Vertex> start = new ArrayList<Vertex>();
        start.add(new Vertex (22, 4));
        start.add(new Vertex (22, 3));
        start.add(new Vertex (21, 8));
        start.add(new Vertex (21, 6));
        start.add(new Vertex (20, 8));
        start.add(new Vertex (20, 2));
        start.add(new Vertex (18, 16));
        start.add(new Vertex (16, 20));
        start.add(new Vertex (15, 21));
        start.add(new Vertex (10, 21));
        start.add(new Vertex (7, 21));
        start.add(new Vertex (11, 21));

        List<Vertex> finish = new ArrayList<Vertex>();
        finish.add(new Vertex (16, 20));
        finish.add(new Vertex (15, 21));
        finish.add(new Vertex (6, 10));
        finish.add(new Vertex (13, 16));
        finish.add(new Vertex (6, 8));
        finish.add(new Vertex (4, 21));
        finish.add(new Vertex (4, 16));
        finish.add(new Vertex (12, 11));
        finish.add(new Vertex (20, 5));
        finish.add(new Vertex (21, 8));
        finish.add(new Vertex (19, 8));
        finish.add(new Vertex (19, 7));


        for (int i = 0; i <12; i++){

      	JourneyPlanner planner = new JourneyPlanner();
      	LinkedList<Vertex> route = planner.iterativeDeepening(start.get(i), finish.get(i));
        saveRoute(route, i);
      	System.out.println("route = " + route);
        }

    }

    // writes the found route to a text file named num.txt (where num is an integer between 0 and 12)
    public static void saveRoute(List<Vertex> route, int num)
    {
        String routeStr = "";
        for (Vertex v : route)
        {
            routeStr += v.toString() + " ";
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(num + ".txt"));
            writer.write(routeStr);

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

//enumerate through the hashmap previously configured and returns a list of edges
//that correspond to the given vertices
    public List<Vertex> nextConfigs(Vertex vertex)
    {
	       return paths.get(vertex);
    }

//following code(written by Mr. King) provides an iterative depeening algorithm
//that runs through the LinkedList, and increases the depth each time until the
//goal has been reached
//I have adapted the code to fit to the rest of my code, and also tried to improve
//it's efficiency
    public LinkedList<Vertex> iterativeDeepening(Vertex start, Vertex end)
    {
        for (int depth = 1; true; depth++) //doubtful termination
        {
            Set<Vertex> visited = new HashSet<Vertex>();
            visited.add(start);
             LinkedList<Vertex> route = depthFirst(start, end, depth, visited);
             if (route != null) return route; //fast exit
             System.out.println(depth);
        }

    }

//
    private LinkedList<Vertex> depthFirst(Vertex start, Vertex end, int depth, Set<Vertex> visited)
    {
	if (depth == 0)
	{
	    return null;
	}
	else if (start.equals(end))
	{
	    LinkedList<Vertex> route = new LinkedList<Vertex>();
	    route.add(start);
	    return route;
        }
	else
        {
	    List<Vertex> nexts = nextConfigs(start);
	    for (Vertex next:nexts)
	    {
	        LinkedList<Vertex> route = depthFirst(next, end, depth - 1, visited);
		if (route != null)
		{
		    route.addFirst(start);
		    return route;
		}
            }
	    return null;
	}
    }
}
