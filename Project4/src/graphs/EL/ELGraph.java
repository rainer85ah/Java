package graphs.EL;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.*;
/**
 * Graph implemented as a edge list
 * @author jvelez
 * @param <V>
 * @param <E>
 */
public class ELGraph<V,E> implements Graph<V,E> 
{
    private Set<ELVertex<V,E>> vertexList = new HashSet<>();
    private Set<ELEdge<E,V>> edgeList = new HashSet<>();
    
    @Override
    public Collection <? extends Vertex<V> > vertices() {
        return Collections.unmodifiableCollection(vertexList);
    }

    @Override
    public Collection <? extends Edge<E> > edges() {
        return Collections.unmodifiableCollection(edgeList);
    }

    @Override
    public Collection<? extends Edge<E>> incidentEdges(Vertex<V> v) {
        Set<Edge <E>> incidentEdges = new HashSet<>();
        
        for (ELEdge <E,V> e : edgeList)
        {
            if (e.getStartVertex() == v)
                incidentEdges.add(e);
            if (e.getEndVertex() == v)
                incidentEdges.add(e);            
        }
        return incidentEdges;
    }
    
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {

        ELEdge<E,V> elv = checkEdge(e);
        
        if (elv.getStartVertex() == v)
            return elv.getEndVertex();
        else if (elv.getEndVertex() == v)
            return elv.getStartVertex();
        else
            throw new RuntimeException("The vertex is not in the edge");
    }


    @Override
    public List<Vertex<V>> endVertices(Edge<E> edge) {
        ELEdge<E,V> elv = checkEdge(edge);
        ArrayList <Vertex<V> > output = new ArrayList<>();
        output.add(elv.getStartVertex());
        output.add(elv.getEndVertex());
        return output;
    }

    @Override
    public boolean areAdjacent(Vertex<V> v1, Vertex<V> v2) {
        for (ELEdge edge : edgeList)
        {
            if ((edge.getStartVertex() == v1) && (edge.getEndVertex() == v2))
                return true;
            else if ((edge.getStartVertex() == v2) && (edge.getEndVertex() == v1))
                return true;
        }
        return false;
    }

    @Override
    public V replace(Vertex<V> vertex, V vertexValue) {
        Vertex<V> v = checkVertex(vertex);
        V aux = v.getValue();
        v.setValue(vertexValue);
        return aux;
    }

    @Override
    public E replace(Edge<E> edge, E edgeValue) {
        ELEdge<E,V> e = checkEdge(edge);
        E aux = edge.getValue();
        e.setValue(edgeValue);
        return aux;
    }

    @Override
    public Vertex<V> insertVertex(V value) 
    {
        Vertex<V> v = new ELVertex(value,this);
        vertexList.add((ELVertex<V,E>) v);
        return v;
    }

    @Override
    public Edge <E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeValue) 
    {
        if (!vertexList.contains(v1))
            throw new RuntimeException("The vertex v1 doesn't belong to this graph");
        if (!vertexList.contains(v2))
            throw new RuntimeException("The vertex v2 doesn't belong to this graph");

        ELEdge<E,V> e = new ELEdge(edgeValue,v1,v2,this);

        if (edgeList.contains(e))
            edgeList.remove(e);
        edgeList.add(e);
        return e;
    }

    @Override
    public V removeVertex(Vertex<V> vertex) {
        Vertex<V> v = checkVertex(vertex);
        V aux = vertex.getValue();
        vertexList.remove(v);

        //You need an aux set, because you can't remove from a set while you iterate it
        List <ELEdge<E,V>> removeEdgeList = new ArrayList<>();
        for (ELEdge edge : edgeList) {
            if ((edge.getStartVertex() == vertex) || (edge.getEndVertex() == vertex)) {
                removeEdgeList.add(edge);
            }
        }
        
        for (ELEdge edge : removeEdgeList) {
            edgeList.remove(edge);
        }
        
        return aux;        
    }

    @Override
    public E removeEdge(Edge<E> edge) {
        ELEdge<E, V> e = checkEdge(edge);
        E aux = edge.getValue();
        edgeList.remove(e);
        return aux;
    }    
    
    protected ELEdge<E,V> checkEdge(Edge<E> edge) {
        if (edge instanceof ELEdge){
            ELEdge<E, V> e = (ELEdge<E,V>)edge;
            if (e.getGraph() == this)
                return e;
        }
        throw new RuntimeException("The edge is not in the graph");
    }

    protected Vertex<V> checkVertex(Vertex<V> vertex) {
        if (vertex instanceof ELVertex){
            ELVertex<V,E> v = (ELVertex<V,E>)vertex;
            if (v.getGraph() == this)
                return v;
        }
        throw new RuntimeException("The vertex is not in the graph");        
    }

    public Edge<E> insertEdge(Vertex<V> V, Vertex<V> W) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class ELVertex<V,E> implements Vertex<V> 
{
    private V vertexValue;
    private final Graph graph;
    private boolean visit; 
    
    @Override
    public V getValue() {
        return vertexValue;
    }
    
    @Override
    public void setValue(V value) {
        vertexValue = value;
    }
    
    public ELVertex(V value, Graph graph) 
    {
        visit = false;
        this.vertexValue = value;
        this.graph = graph;
    }

    /**
     * @return the graph
     */
    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public Vertex<V> checkVertex(Vertex<V> vertex) 
    {
        if (vertex instanceof ELVertex)
        {
            ELVertex<V,E> v = (ELVertex<V,E>)vertex;
            if (v.getGraph() == this)
                return v;
        }
        throw new RuntimeException("The vertex is not in the graph");        
    }

    public V getElement() 
    {
        return this.getValue();
    }

    @Override
    public V replaceElement(V o) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected ELEdge<E,V> checkEdge(Edge<E> edge) {
        if (edge instanceof ELEdge){
            ELEdge<E, V> e = (ELEdge<E,V>)edge;
            if (e.getGraph() == this)
                return e;
        }
        throw new RuntimeException("The edge is not in the graph");
    }
    
    public Set<Edge<E>> incidentEdges()
    {
        Vertex<V> vertex = checkVertex(this);
        Set<Edge<E>> list = new HashSet<>();
        for (Object e : vertex.getGraph().edges())
        {
            ELEdge<E,V> e1 = checkEdge((Edge<E>) e);
            if (e1.getSource() == vertex || e1.getDestination() == vertex)
                list.add(e1);            
        }
        return list;
    }

    @Override
    public boolean getVisit() 
    {
        return this.visit;
    }

    @Override
    public void setVisit(boolean v) {
        this.visit = v;
    }
}

class ELEdge<E,V> implements Edge<E> 
{
    private E edgeValue;
    private final Graph graph;    
    private final Vertex<V> startVertex;
    private final Vertex<V> endVertex;

    public ELEdge(E edgeValue, Vertex<V> v1,Vertex<V> v2, Graph g)
    {
        this.edgeValue = edgeValue;
        this.startVertex = v1;
        this.endVertex = v2;
        this.graph = g;
    }
            
    @Override
    public int hashCode() {
        int hash = 3;
        
        final int min = Math.min(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));
        final int max = Math.max(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));
        
        hash = 67 * hash + Objects.hashCode(this.getGraph());
        hash = 67 * hash + min;
        hash = 67 * hash + max;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ELEdge<E, V> other = (ELEdge<E, V>) obj;
        if (!Objects.equals(this.graph, other.graph)) {
            return false;
        }
        
        final int min1 = Math.min(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));
        final int max1 = Math.max(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));

        final int min2 = Math.min(Objects.hashCode(other.startVertex), Objects.hashCode(other.endVertex));
        final int max2 = Math.max(Objects.hashCode(other.startVertex), Objects.hashCode(other.endVertex));

        if (!Objects.equals(min1, min2)) {
            return false;
        }
        if (!Objects.equals(max1, max2)) {
            return false;
        }
        return true;
    }
    
    public E getValue() 
    {
        return edgeValue;
    }

    public void setValue(E value) {
        edgeValue = value;
    }
    
    /**
     * @return the startVertex
     */
    public Vertex <V> getStartVertex() {
        return startVertex;
    }

    /**
     * @return the endVertex
     */
    public Vertex <V> getEndVertex() {
        return endVertex;
    }

    /**
     * @return the graph
     */
    public Graph getGraph() {
        return graph;
    }

    @Override
    public Vertex<E> getSource() 
    {
        return (Vertex<E>) startVertex;
    }
    
    @Override
    public Vertex<E> getDestination() 
    {
        return (Vertex<E>) endVertex;
    }

    @Override
    public void setSource(Vertex<E> v) 
    {
        
    }

    @Override
    public void setDestination(Vertex<E> v) 
    {
        
    }
}