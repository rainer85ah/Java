package graphs.AL;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.Objects;

public class ALEdge<E,V> implements Edge<E>
{        
    private E value;
    private final Graph graph;
    private final ALVertex<V,E> source;
    private final ALVertex<V,E> destination;

    public ALEdge(ALVertex<V,E> s, ALVertex<V,E> d, E e, Graph g) 
    {
        this.source = s;
        this.destination = d;
        this.value = e;
        this.graph = g;
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
    
    @Override
    public Vertex getSource() 
    {
        return source;
    }
    
    @Override
    public Vertex getDestination() 
    {
        return destination;
    }
    
    @Override
    public String toString() 
    {
        String s = source.getValue() + "------" + destination.getValue();
        return s;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        
        final int min = Math.min(Objects.hashCode(this.source), Objects.hashCode(this.destination));
        final int max = Math.max(Objects.hashCode(this.source), Objects.hashCode(this.destination));
        
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
        
        final ALEdge<E,V> other = (ALEdge<E,V>) obj;
        if (!Objects.equals(this.graph, other.graph)) {
            return false;
        }
        
        final int min1 = Math.min(Objects.hashCode(this.source), Objects.hashCode(this.destination));
        final int max1 = Math.max(Objects.hashCode(this.source), Objects.hashCode(this.destination));

        final int min2 = Math.min(Objects.hashCode(other.source), Objects.hashCode(other.destination));
        final int max2 = Math.max(Objects.hashCode(other.source), Objects.hashCode(other.destination));

        if (!Objects.equals(min1, min2)) {
            return false;
        }
        if (!Objects.equals(max1, max2)) {
            return false;
        }
        return true;
    }
    @Override
    public void setSource(Vertex<E> v) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDestination(Vertex<E> v) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Graph getGraph() 
    {
        return this.graph;
    }
}