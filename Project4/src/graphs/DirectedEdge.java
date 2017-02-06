package graphs;

public interface DirectedEdge extends Edge 
{
    @Override
    public Vertex getSource();
    @Override
    public Vertex getDestination();
    
    public void reverseDirection();
    public void setDirectionFrom(Vertex e, Vertex v);
    public void setDirectionTo(Vertex e, Vertex v);
}