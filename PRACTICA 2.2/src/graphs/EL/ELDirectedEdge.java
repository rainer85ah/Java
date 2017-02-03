package graphs.EL;
 
import graphs.DirectedEdge;
import graphs.Graph;
import graphs.Vertex;

public class ELDirectedEdge<E,V> extends ELEdge implements DirectedEdge
{
    public ELDirectedEdge(E e, Vertex s, Vertex d, Graph g) 
    {
        super(e,s,d,g);
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
}