package graphs;

public interface Edge<E> extends Position<E> 
{
    //Position: public E getValue();
    public Vertex<E> getSource();
    public Vertex<E> getDestination();
    public void setSource(Vertex<E> v);
    public void setDestination(Vertex<E> v);
}