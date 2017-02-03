/*
Ejercicio 1: Implementar dos clases derivadas de Graph que implementen la
estructura de grafos utilizando la idea de la lista de adyacencia y de la matrix de
adyacencia. Estas clases que tendrán por nombres ALGraph y AMGraph, deberán
cumplir los test que existen para la clase ELGraph.
*/
package graphs.AL;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.*;

public class ALGraph<V,E> implements Graph<V,E> 
{
    private Set<ALVertex<V,E>> vertexList;
    private Set<ALEdge<E,V>> edgeList;
    
    public ALGraph() 
    {
        vertexList = new HashSet<>();
        edgeList = new HashSet<>();
    }
    
    public ALGraph(Set<ALVertex<V,E>> vertexList, Set<ALEdge<E,V>> edgeList) 
    {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }
      
    /**
     * @return all vertices of the graph.
    */
    @Override
    public Collection<? extends Vertex<V>> vertices() 
    {
        return Collections.unmodifiableCollection(vertexList);
    }
    
    /**
     * @return all edges of the graph.
     */
    @Override
    public Collection<? extends Edge<E>> edges() 
    {
        return Collections.unmodifiableCollection(edgeList);
    }
    
    /**
     * @return an iterable collection of the edges incident upon vertex v.
    */
    @Override
    public Collection<? extends Edge<E>> incidentEdges(Vertex<V> v) 
    {
        ALVertex<V,E> vertex = (ALVertex<V,E>) checkVertex(v);
        Set<Edge<E>> listEdge = new HashSet<>();
        
        for (Edge<E> e : this.edgeList)
        {
            if (e.getSource() == vertex)
                listEdge.add(e);
            if (e.getDestination() == vertex)
                listEdge.add(e);            
        }
        return Collections.unmodifiableCollection(listEdge);
    }
    
    public Collection<? extends Edge<E>> adjacentVertices(Vertex<V> v) 
    {
        ALVertex<V,E> vertex = (ALVertex<V,E>) checkVertex(v);
        Set<Edge<E>> listEdge = new HashSet<>();
        
        for (Edge<E> e : this.edgeList)
        {
            if (e.getSource() == vertex)
                listEdge.add(e);
            if (e.getDestination() == vertex)
                listEdge.add(e);            
        }
        return Collections.unmodifiableCollection(listEdge);
    }
    
    /**
     * @return the end vertex of the edge e distinct of e.
     */
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
    {
        ALVertex<V,E> vertex = (ALVertex<V,E>) checkVertex(v);
        ALEdge<E,V> edge = (ALEdge<E,V>) checkEdge(e);
        
        if (vertex == edge.getSource())
            return edge.getDestination();
        else if (vertex == edge.getDestination())
            return edge.getSource();
        else
            throw new RuntimeException("The vertex is not in the edge");
    }
    
    /**
     * @return an array storing the end vertices of edge.
     */
    @Override
    public List<Vertex<V>> endVertices(Edge<E> e)
    {
        ALEdge<E,V> edge = (ALEdge<E,V>) checkEdge(e);
        List<Vertex<V>> list = new ArrayList<>();
        list.add(edge.getSource());
        list.add(edge.getDestination());
        return list;
    }
    
    /**
     * Test whether vertices v1 and v2 are adjacent.
     * @return true if are adjacent
     */
    @Override
    public boolean areAdjacent(Vertex<V> v1, Vertex<V> v2)
    {
        ALVertex<V,E> vertex1 = (ALVertex<V,E>) checkVertex(v1);
        ALVertex<V,E> vertex2 = (ALVertex<V,E>) checkVertex(v2);
        
        for(Edge<E> iE : this.edges())
        {
            if (iE.getSource() == vertex1 && iE.getDestination() == vertex2)
                return true;
            if (iE.getSource() == vertex2 && iE.getDestination() == vertex1)
                return true;
        }
        return false;
    }
    
    /**
     * Replace the element stored at vertex with vertexValue.
     * @return the old element stored at vertex.
     */
    @Override
    public V replace(Vertex<V> vertex, V vertexValue)
    {
        V aux = vertex.getValue();
        vertex.setValue(vertexValue);
        return aux;
    }
    
    /**
     * Replace the element stored at edge with edgeValue.
     * @return the old element stored at edge.
     */
    @Override
    public E replace(Edge<E> edge, E edgeValue)
    {
        ALEdge<E,V> e = checkEdge(edge);
        E aux = edge.getValue();
        e.setValue(edgeValue);
        return aux;    
    }
    
    /**
     * Insert and return a new vertex storing element value.
     */
    @Override
    public Vertex<V> insertVertex(V value)
    {
        ALVertex<V,E> v = new ALVertex<>(value,this);
        vertexList.add(v);
        return v;
    }
    
    /**
     * Insert and return a new undirected edge with end vertices 
     * v1 and v2 and storing element vertexValue.
     * If already exists an edge with this vertices the edge is replaced.
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeValue)
    {
        ALVertex<V,E> vertex1 = (ALVertex<V,E>) checkVertex(v1);
        ALVertex<V,E> vertex2 = (ALVertex<V,E>) checkVertex(v2);
        if(!vertexList.contains(vertex1))
            throw new RuntimeException("The vertex v1 doesn't belong to this graph");
        if(!vertexList.contains(vertex2))
            throw new RuntimeException("The vertex v2 doesn't belong to this graph");
        
        ALEdge<E,V> e = new ALEdge<>(vertex1,vertex2,edgeValue,this);
        if (edgeList.contains(e))
            edgeList.remove(e);
        edgeList.add(e);
        return e;
    }
        
    /**
     * Remove vertex v and all its incident edges.
     * @return the element stored at vertex
    */
    @Override
    public V removeVertex(Vertex<V> v) 
    {
        ALVertex<V,E> vertex = (ALVertex<V,E>) checkVertex(v);
        V aux = v.getValue();
        vertexList.remove(vertex);
        
        //You need an aux set, because you can't remove from a set while you iterate it
        List <ALEdge<E,V>> removeEdgeList = new ArrayList<>();
                
        for(Edge<E> iE : this.edgeList)
            if ((iE.getSource() == vertex) || (iE.getDestination() == vertex))
                removeEdgeList.add((ALEdge<E,V>) iE);
                    
        for (ALEdge edge : removeEdgeList) 
        {
            edgeList.remove(edge);
        }
        return aux;
    }
    
    /**
     * Remove edge
     * @return the element stored at e.
     */
    @Override
    public E removeEdge(Edge<E> edge)
    {
        ALEdge<E,V> e = checkEdge(edge);
        E aux = edge.getValue();
        edgeList.remove(e);
        return aux;
    }
        
    //Methods to check if the edge and vertex are from this class of object's
    private ALEdge<E,V> checkEdge(Edge<E> edge) 
    {
        if (edge instanceof ALEdge)
        {
            ALEdge<E,V> e = (ALEdge<E,V>) edge;
            return e;
        }
        throw new RuntimeException("The edge is not in the graph");
    }
    
    protected Vertex<V> checkVertex(Vertex<V> v)
    {
        if (v instanceof ALVertex)
            return v;
        else
            return null;
    }
}