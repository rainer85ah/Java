/*
Ejercicio 1: Implementar dos clases derivadas de Graph que implementen la
estructura de grafos utilizando la idea de la lista de adyacencia y de la matrix de
adyacencia. Estas clases que tendrán por nombres AMGraph y AMGraph, deberán
cumplir los test que existen para la clase ELGraph.
*/
package graphs.AM;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.*;

public class AMGraph<V,E> implements Graph<V,E> 
{
    private List<List<AMEdge<E,V>>> adjMatrix;
    private Set<AMVertex<V,E>> vertexList;
    private int numVertex;
    private int numEdge;
    
    public AMGraph() 
    {
        numVertex = 0;
        numEdge = 0;
        vertexList = new HashSet<>();
        adjMatrix = new ArrayList<>();
        for (int i = 1; i < 50; i++ ) 
        {
            for (int j = 1; j < 50; j++ ) 
            {
                adjMatrix.get(i).get(j).setConect(false);
            }
        }
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
        List<Edge<E>> edgeList = new LinkedList<>();
        Edge<E> edge = new AMEdge<>(null,this);
        
        for (int i = 0; i < numVertex; i++ ) 
        {
            for (int j = 0; j < numVertex; j++ ) 
            {
                if(adjMatrix.get(i).get(j).getConect() == true)
                {
                    edgeList.add(edge);
                }
            }
        }        
        return Collections.unmodifiableCollection(edgeList);
    }
    
    /**
     * @return an iterable collection of the edges incident upon vertex v.
    */
    @Override
    public Collection<? extends Edge<E>> incidentEdges(Vertex<V> v) 
    {
        AMVertex<V,E> vertex = (AMVertex<V,E>) checkVertex(v);
        List<Edge<E>> edgeList = new LinkedList<>();         
        
        for (int i = 1; i < 50; i++ ) 
        {
            for (int j = 1; j < 50; j++ ) 
            {
                if(adjMatrix.get(i).get(j).getSource() == vertex ||
                   adjMatrix.get(i).get(j).getDestination() == vertex)
                    edgeList.add(adjMatrix.get(i).get(j));
            }
        }        
        return Collections.unmodifiableCollection(edgeList);
    }
    
    /**
     * @return the end vertex of the edge e distinct of e.
     */
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
    {
        AMVertex<V,E> vertex = (AMVertex<V,E>) checkVertex(v);
        AMEdge<E,V> edge = (AMEdge<E,V>) checkEdge(e);
        
        if (vertex == edge.getSource())
            return (Vertex<V>) edge.getDestination();
        else if (vertex == edge.getDestination())
            return (Vertex<V>) edge.getSource();
        else
            throw new RuntimeException("The vertex is not in the edge");
    }
    
    /**
     * @return an array storing the end vertices of edge.
     */
    @Override
    public List<Vertex<V>> endVertices(Edge<E> e)
    {
        AMEdge<E,V> edge = (AMEdge<E,V>) checkEdge(e);
        List<Vertex<V>> list = new ArrayList<>();
        list.add((Vertex<V>) edge.getSource());
        list.add((Vertex<V>) edge.getDestination());
        return list;
    }
    
    /**
     * Test whether vertices v1 and v2 are adjacent.
     * @return true if are adjacent
     */
    @Override
    public boolean areAdjacent(Vertex<V> v1, Vertex<V> v2)
    {
        AMVertex<V,E> vertex1 = (AMVertex<V,E>) checkVertex(v1);
        AMVertex<V,E> vertex2 = (AMVertex<V,E>) checkVertex(v2);
        
        for (int i = 1; i < numVertex; i++ ) 
        {
            for (int j = 1; j < numVertex; j++ ) 
            {
                if(vertex1 == adjMatrix.get(i).get(j).getSource() && 
                   vertex2 == adjMatrix.get(i).get(j).getDestination() ||
                   vertex2 == adjMatrix.get(i).get(j).getSource() && 
                   vertex1 == adjMatrix.get(i).get(j).getDestination())
                    return true;
            }
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
        AMVertex<V,E> v = (AMVertex<V,E>) checkVertex(vertex);
        V aux = vertex.getValue();
        v.setValue(vertexValue);
        vertexList.add((AMVertex<V, E>) v);
        return aux;
    }
    
    /**
     * Replace the element stored at edge with edgeValue.
     * @return the old element stored at edge.
     */
    @Override
    public E replace(Edge<E> edge, E edgeValue)
    {
        AMEdge<E,V> e = checkEdge(edge);
        E aux = null;
        for (int i = 1; i < numVertex; i++ ) 
        {
            for (int j = 1; j < numVertex; j++ ) 
            {
                if(e.getSource() == adjMatrix.get(i).get(j).getSource() &&
                   e.getDestination() == adjMatrix.get(i).get(j).getDestination())
                {
                    aux = adjMatrix.get(i).get(j).getValue();
                    adjMatrix.get(i).get(j).setValue(edgeValue);
                }
            }
        }          
        return aux;    
    }
    
    /**
     * Insert and return a new vertex storing element value.
     */
    @Override
    public Vertex<V> insertVertex(V value)
    {
        AMVertex<V,E> v = new AMVertex<>(value,this);
        vertexList.add(v);
        numVertex++;
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
        AMVertex<V,E> vertex1 = (AMVertex<V,E>) checkVertex(v1);
        AMVertex<V,E> vertex2 = (AMVertex<V,E>) checkVertex(v2);
        AMEdge<E,V> edge = new AMEdge<>(edgeValue,this);
        
        for (int i = 1; i < numVertex; i++ ) 
        {
            for (int j = 1; j < numVertex; j++ ) 
            {
                if(adjMatrix.get(i).get(j).getSource() == vertex1 &&
                   adjMatrix.get(i).get(j).getSource() == vertex2)
                {
                    edge.setSource((Vertex<E>) vertex1);
                    edge.setDestination((Vertex<E>) vertex2);
                    edge.setConect(true);
                    adjMatrix.get(i).add(j, edge);   
                    adjMatrix.get(j).add(i, edge);
                }
            }
        }
        numEdge=numEdge+2;
        return edge;
    }
        
    /**
     * Remove vertex v and all its incident edges.
     * @return the element stored at vertex
    */
    @Override
    public V removeVertex(Vertex<V> v) 
    {
        AMVertex<V,E> vertex = (AMVertex<V,E>) checkVertex(v);
        boolean findit = false;
        
        for (int i = 1; i < numVertex; i++ ) 
        {
            for (int j = 1; j < numVertex; j++ ) 
            {
                if (adjMatrix.get(i).get(j).getSource() == vertex || 
                    adjMatrix.get(i).get(j).getDestination() == vertex)
                {
                    adjMatrix.get(i).get(j).setConect(false);
                    adjMatrix.get(j).get(i).setConect(false);
                    findit = true;
                }
            }
        }
            
        if (findit)
        {
            vertexList.remove(vertex);
            numVertex--;
            return v.getValue();
        }
        else
            return null;
    }
    
    /**
     * Remove edge
     * @return the element stored at e.
     */
    @Override
    public E removeEdge(Edge<E> edge)
    {
        AMEdge<E,V> e = checkEdge(edge);
        boolean findit = false;
        
        for (int i = 0; i < numVertex; i++ ) 
        {
            for (int j = 0; j < numVertex; j++ ) 
            {
                if(adjMatrix.get(i).get(j) == e.getSource() && 
                   adjMatrix.get(i).get(j) == e.getDestination() ||
                   adjMatrix.get(j).get(i) == e.getSource() && 
                   adjMatrix.get(j).get(i) == e.getDestination())
                {
                    numEdge=numEdge-2;
                    Edge<E> eAux = new AMEdge<>(null,this);
                    adjMatrix.get(i).add(j, (AMEdge<E, V>) eAux);
                    adjMatrix.get(j).add(i, (AMEdge<E, V>) eAux);
                    break;
                }
            }
        }
        if (findit)           
            return edge.getValue();
        else
            return null;
    }
        
    //Methods to check if the edge and vertex are from this class of object's
    private AMEdge<E,V> checkEdge(Edge<E> edge) 
    {
        if (edge instanceof AMEdge)
        {
            AMEdge<E,V> e = (AMEdge<E,V>) edge;
            return e;
        }
        throw new RuntimeException("The edge is not in the graph");
    }
    
    protected Vertex<V> checkVertex(Vertex<V> v)
    {
        if (v instanceof AMVertex)
            return v;
        else
            return null;
    } 
}