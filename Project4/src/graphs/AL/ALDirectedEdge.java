package graphs.AL;
 
import graphs.DirectedEdge;
import graphs.Graph;
import graphs.Vertex;

public class ALDirectedEdge<E,V> extends ALEdge implements DirectedEdge
{
    public ALDirectedEdge(ALVertex s, ALVertex d, E e, Graph g) 
    {
        super(s,d,e,g);
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