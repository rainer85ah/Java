package graphs.AL;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.*;

public class ALVertex<V,E> implements Vertex<V> 
{
    private V data;
    private double weight;
    private Set<Edge<E>> edges; 
    private final Graph graph;
    private boolean visit;

    public ALVertex(V data, Graph graph) 
    {
        this.data = data;
        this.weight= 0;
        this.edges = new HashSet<>();
        this.graph = graph;
        visit = false;
    }
    
    public ALVertex(V data, Set<Edge<E>> listEdges, double w, Graph g) 
    {
        this.data = data;
        this.edges = listEdges;
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
    
    public void setVisit(boolean v) 
    {
        this.visit = v;
    }

    public boolean getVisit() 
    {
        return this.visit;
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
        if (v instanceof ALVertex)
            return v;
        else
            return null;
    }

    public boolean isAdjacent(Vertex<V> v) 
    {
        return adjacentVertices().contains(v);
    }
    
    public Set<Edge<E>> incidentEdges() 
    {
        return this.edges;
    }   
    
    public List<Vertex<V>> adjacentVertices() 
    {
        List<Vertex<V>> adjList = new ArrayList<>();
        for(Edge<E> e : edges)
        {
            if(this.data == e.getSource().getValue())
                adjList.add((Vertex<V>) e.getDestination());
            if(this.data == e.getDestination().getValue())
                adjList.add((Vertex<V>) e.getSource());
        }
        return adjList;	
    }
    
    public Vertex<V> opposite(Edge e) 
    {
        if (e.getSource() == this)
            return e.getDestination();
        else
            return e.getSource();
    }
}