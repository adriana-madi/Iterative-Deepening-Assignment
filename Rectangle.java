package Navigating2;
import java.util.*;
//Login: ams79
//Name: Adriana Samareanu

//This is a class for the attributes of a rectangles
//A rectangle has 4 vertices, and this class also allows to check if the edge of
//a rectangle intersects with another edge
public class Rectangle
{
  private Vertex v1;
  private Vertex v2;
  private Vertex v3;
  private Vertex v4;



  public Rectangle (Vertex v1, Vertex v2, Vertex v3, Vertex v4){
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.v4 = v4;
  }

  public List<Vertex> get_vertices(){
    List<Vertex> vertices = new ArrayList<Vertex>();
    vertices.add(v1);
    vertices.add(v2);
    vertices.add(v3);
    vertices.add(v4);

    return vertices;
  }

  public boolean insideRectangle(Vertex v) {
    return v.get_x() > v1.get_x() && v.get_x() > v2.get_x() && v.get_x() < v3.get_x() && v.get_x() < v4.get_x() &&
            v.get_y() > v1.get_y() && v.get_y() < v2.get_y() && v.get_y() < v3.get_y() && v.get_y() > v4.get_y();
  }

//Checks if an edge of a rectangle intersects another edge
//the following code makes sure that all route paths are valid
  public boolean lineIntersects(Vertex start, Vertex end){
    return (Vertex.linesIntersect(start, end, v1, v2)||
    Vertex.linesIntersect(start, end, v2, v3) ||
    Vertex.linesIntersect(start, end, v3, v4) ||
    Vertex.linesIntersect(start, end, v4, v1) ||
    Vertex.linesIntersect(start, end, v1, v3) ||
    Vertex.linesIntersect(start, end, v2, v4) ||
    (insideRectangle(start)) ||
    (insideRectangle(end)));
    //if ( Vertex.linesIntersect(start, end, v1, v2)||
    //Vertex.linesIntersect(start, end, v2, v3) ||
  //  Vertex.linesIntersect(start, end, v3, v4) ||
    //Vertex.linesIntersect(start, end, v4, v1)){
    //  return false;
  //  }
    //else{
      //return true;
  //  }


  }




}
