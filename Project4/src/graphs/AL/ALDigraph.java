/*
Ejercicio 2: Derivando de Graph, implementar las clases ELDigrap, ALDigraph y
AMDigraph, que permitirán construir grafos dirigidos. Estas clases serán similares a
sus homologas no dirigidas con las siguientes excepciones:
• El método endVertices devolverá primero el nodo inicial y luego el nodo final de
la arista consultada.
• El método areAdjacent devolverá true si existe un nodo que parta de v1 a v2.
• Deberá incorporar el método outputEdges(v) que devolverá las aristas de
salida desde el vértice v.
• En el método insertVertex(v1,v2), v1 es el nodo inicial y v2 el final.
*/
package graphs.AL;

import graphs.Edge;
import graphs.Vertex;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ALDigraph<V,E> extends ALGraph<V,E> 
{
    private int value; 
    private Set<Vertex<V>> vertexList;
    private Set<Edge<E>> edgeList;

    public ALDigraph() 
    {
        super();
    }

    @Override
    public List<Vertex<V>> endVertices(Edge<E> edge) 
    {
        List<Vertex<V>> ends = new ArrayList<>(2);
        ends.add(0, (Vertex<V>) edge.getSource());
        ends.add(1, (Vertex<V>) edge.getDestination());
        return ends;
    }
    
    @Override
    public boolean areAdjacent(Vertex<V> v1, Vertex<V> v2) 
    {
        ALVertex<V,E> vertex1 = (ALVertex<V,E>) checkVertex(v1);
        ALVertex<V,E> vertex2 = (ALVertex<V,E>) checkVertex(v2);
        
        for(Edge<E> iE : this.edges())
        {
            if (iE.getSource() == vertex1 && iE.getDestination() == vertex2)
                return true;
        }
        return false;
    }
    
    
    public Edge<E> insertVertex(Vertex<V> v1, Vertex<V> v2)
    {
        ALVertex<V,E> vertex1 = (ALVertex<V,E>) checkVertex(v1);
        ALVertex<V,E> vertex2 = (ALVertex<V,E>) checkVertex(v2);
        if(!vertexList.contains(vertex1))
            throw new RuntimeException("The vertex v1 doesn't belong to this graph");
        if(!vertexList.contains(vertex2))
            throw new RuntimeException("The vertex v2 doesn't belong to this graph");
        
        ALDirectedEdge<E,V> e = new ALDirectedEdge<>(vertex1,vertex2,null,this);        
        if (edgeList.contains(e))
            edgeList.remove(e);
        edgeList.add(e);
        return e;
    }
    
    public Collection<? extends Edge>  outputEdges(Vertex<V> v)
    {    
        ALVertex<V,E> vertex = (ALVertex<V,E>) checkVertex(v);
        Set<Edge<E>> list = new HashSet<>();
        
        for(Edge<E> iE : vertex.incidentEdges())
        {
            if (iE.getSource() == vertex)
                list.add(iE);
        }        
        return list;
    }
}