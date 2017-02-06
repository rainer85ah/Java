package graphs.AM;
 
import graphs.DirectedEdge;
import graphs.Graph;
import graphs.Vertex;

public class AMDirectedEdge<E,V> extends AMEdge implements DirectedEdge
{   
    public AMDirectedEdge(Object e, Graph g)
    {
        super(e, g);
    }
        
    @Override
    public void reverseDirection() 
    {
        Vertex temp = getSource();
        setSource(getDestination());
        setDestination(temp);
    }
    
    @Override
    public void setDirectionFrom(Vertex s, Vertex d) 
    {
        setSource(d);
        setDestination(s);
    }
    
    @Override
    public void setDirectionTo(Vertex s, Vertex d) 
    {
        setSource(s);
        setDestination(d);
    }
    
    @Override
    public String toString() 
    {
        String s = getSource().getValue() + " ------> " + getDestination().getValue();
        return s;
    }

    @Override
    public Vertex getSource() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vertex getDestination() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSource(Vertex v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDestination(Vertex v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}