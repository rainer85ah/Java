package graphs.AM;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.*;

public class AMVertex<V,E> implements Vertex<V> 
{
    private V data;
    private double weight;
    private final Graph graph;
    private boolean visit;

    public AMVertex(V data, Graph graph) 
    {
        this.data = data;
        this.weight= 0;
        this.graph = graph;
        visit = false;
    }
    
    public AMVertex(V data, double w, Graph g) 
    {
        this.data = data;
        this.weight = w;
        this.graph = g;
        visit = false;
    }
    
    @Override
    public void setValue(V vertexValue) 
    {
        this.data = vertexValue;
    }

    @Override
    public V getValue() 
    {
        return this.data;
    }
    
    public void setWeight(double weight) 
    {
        this.weight = weight;
    }

    public double getWeight() 
    {
        return this.weight;
    }
    
    /**
     * @return the graph
     */
    @Override
    public Graph getGraph() 
    {
        return graph;
    }
    
    //Return the value replaced..
    @Override
    public V replaceElement(V o) 
    {
        V temp = data;
        data = o;
        return temp;
    }
    
    @Override
    public String toString() 
    {
        return data.toString();
    }   

    @Override
    public Vertex<V> checkVertex(Vertex<V> v)
    {
        if (v instanceof AMVertex)
            return v;
        else
            return null;
    }

    public boolean isAdjacent(Vertex<V> v) 
    {
        return adjacentVertices().contains(v);
    }
    
    public List<Vertex<V>> adjacentVertices() 
    {
        List<Vertex<V>> adjList = new ArrayList<>();
         
        return adjList;	
    }
    
    public Vertex<V> opposite(Edge e) 
    {
        if (e.getSource() == this)
            return e.getDestination();
        else
            return e.getSource();
    }

    @Override
    public boolean getVisit() {
        return this.visit;
    }

    @Override
    public void setVisit(boolean v) {
        this.visit = v;
    }
}