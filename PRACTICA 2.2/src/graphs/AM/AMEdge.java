package graphs.AM;

import graphs.Edge; 
import graphs.Graph;
import graphs.Vertex;

public class AMEdge<E,V> implements Edge<E>
{        
    private E value;
    private final AMVertex<V,E> source;
    private final AMVertex<V,E> destination;
    private boolean conect;
    private final Graph graph;

    public AMEdge(AMVertex<V,E> s, AMVertex<V,E> d, E e, Graph g) 
    {
        this.source = s;
        this.destination = d;
        this.value = e;
        this.graph = g;
        if (s!=null && d != null)
            this.conect=true;
        else
            this.conect=false;
    }
    
    public AMEdge(E e, Graph g) 
    {
        this.value = e;
        this.conect = false;
        this.graph = g;
        this.source = null;
        this.destination = null;
    }

    public boolean getConect() {
        return conect;
    }

    public void setConect(boolean conect) {
        this.conect = conect;
    }   
    
    @Override
    public E getValue() 
    {
        return value;
    }

    public void setValue(E value) 
    {
        this.value = value;
    }

    private Graph getGraph() 
    {
        return this.graph;
    }

    @Override
    public Vertex<E> getSource() 
    {
        return this.getSource();
    }

    @Override
    public Vertex<E> getDestination() 
    {
        return this.getDestination();
    }

    @Override
    public void setSource(Vertex<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDestination(Vertex<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}