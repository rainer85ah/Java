/*
Ejercicio 2: Derivando de Graph, implementar las clases ELDigrap, ALDigraph y
AMDigraph, que permitirán construir grafos dirigidos. Estas clases serán similares a
sus homologas no dirigidas con las siguientes excepciones:**
• El método endVertices devolverá primero el nodo inicial y luego el nodo final de
la arista consultada.
• El método areAdjacent devolverá true si existe un nodo que parta de v1 a v2.
• Deberá incorporar el método outputELEdges(v) que devolverá las aristas de
salida desde el vértice v.
• En el método insertELVertex(v1,v2), v1 es el nodo inicial y v2 el final.
*/
package graphs.EL;

import graphs.Edge;
import graphs.Vertex;
import java.util.*;

public class ELDigraph<V,E> extends ELGraph<V,E>
{
    private Set<ELVertex<V,E>> vertexList = new HashSet<>();
    private Set<ELDirectedEdge<E,V>> edgeList = new HashSet<>();

    @Override
    public List<Vertex<V>> endVertices(Edge<E> edge) 
    {
        ELEdge<E,V> e = checkEdge(edge);
        List<Vertex<V>> output = new LinkedList<>();
        output.add(e.getStartVertex());
        output.add(e.getEndVertex());
        return output;
    }
    
    @Override
    public boolean areAdjacent(Vertex<V> v1, Vertex<V> v2) 
    {
        ELVertex<V,E> vertex1 = (ELVertex<V,E>) checkVertex(v1);
        ELVertex<V,E> vertex2 = (ELVertex<V,E>) checkVertex(v2);
        if (vertexList.contains(vertex1) && vertexList.contains(vertex2))
        {
            for (ELDirectedEdge<E,V> e : edgeList)
            {
                if ((e.getStartVertex() == vertex1) && (e.getEndVertex() == vertex2))
                    return true;
            }
        }
        return false;
    }
    
    public Edge<E> insertVertex(Vertex<V> v1, Vertex<V> v2) 
    {
        ELVertex<V,E> vertex1 = (ELVertex<V,E>) checkVertex(v1);
        ELVertex<V,E> vertex2 = (ELVertex<V,E>) checkVertex(v2);
        
        if (!vertexList.contains(vertex1))
            vertexList.add((ELVertex<V,E>) vertex1);
        if (!vertexList.contains(vertex2))
            vertexList.add((ELVertex<V,E>) vertex2);
        
        ELDirectedEdge e = new ELDirectedEdge<>(null,v1,v2,this);        
        if (edgeList.contains(e))
            edgeList.remove(e);
        edgeList.add(e);
        return e;
    }
    
    public Collection<? extends Edge> outputEdges(Vertex<V> v)
    {     
        ELVertex<V,E> vertex = (ELVertex<V,E>) checkVertex(v);
        Set<Edge<E>> list = new HashSet<>();
        
        for(Edge<E> iE : incidentEdges(vertex))
        {
            if (iE.getSource() == vertex)
            {
                ELEdge<E,V> e = (ELEdge<E,V>) checkEdge(iE);
                list.add(e);
            }
        }        
        return list;
    } 
}