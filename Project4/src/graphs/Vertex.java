package graphs;


public interface Vertex<V> extends Position<V>
{
    //Position: public V getValue();
    public void setValue(V vertexValue);
    public Vertex<V> checkVertex(Vertex<V> v);
    public V replaceElement(V o);
    public Graph getGraph();
    
    public boolean getVisit();
    public void setVisit(boolean v);
    /*
    public void insertEdge(Vertex<V> v);
    public void insertEdge(Edge e);
    public void removeEdge(Edge e);
    
    public int getDegree();
    public int getInDegree();
    public int getOutDegree();
    
    
    public Collection<? extends Edge> inIncidentEdges();
    public Collection<? extends Edge> outIncidentEdges();
    
       
    public boolean isAdjacent(Vertex<V> v);
    public Collection<? extends Vertex<V>> adjacentVertices();
    public Collection<? extends Vertex<V>> inAdjacentVertices();
    public Collection<? extends Vertex<V>> outAdjacentVertices();
    */
}