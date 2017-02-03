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
package graphs.AM;

import graphs.Edge;
import graphs.Vertex;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Owner
 */
public class AMDigraph<V,E> extends AMGraph<V,E> 
{
    private List<List<AMDirectedEdge<E,V>>> adjMatrix;
    private Set<AMVertex<V,E>> vertexList;
    private int numVertex;
    private int numEdge;
    
    public AMDigraph() 
    {
        numVertex = 0;
        numEdge = 0;
        vertexList = new HashSet<>();
        adjMatrix = new ArrayList<>();
        for (int i = 0; i < 50; i++ ) 
        {
            for (int j = 0; j < 50; j++ ) 
            {
                adjMatrix.get(i).get(j).setConect(false);
            }
        }
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
        AMVertex<V,E> vertex1 = (AMVertex<V,E>) checkVertex(v1);
        AMVertex<V,E> vertex2 = (AMVertex<V,E>) checkVertex(v2);
        int i = 0, j=0;
        if(vertexList.contains(vertex1) && vertexList.contains(vertex2))
        {
            while(adjMatrix.get(i) != vertex1 && i < numVertex) 
            {
                i++;
            }
            while(adjMatrix.get(j) != vertex2 && j < numVertex) 
            {
                j++;
            }
            if(adjMatrix.get(i).get(j).getConect() == true)
                return true;
        }
        return false;
    }
    
    
    public Edge<E> insertVertex(Vertex<V> v1, Vertex<V> v2)
    {
        AMVertex<V,E> vertex1 = (AMVertex<V,E>) checkVertex(v1);
        AMVertex<V,E> vertex2 = (AMVertex<V,E>) checkVertex(v2);
        if(!vertexList.contains(vertex1))
            throw new RuntimeException("The vertex v1 doesn't belong to this graph");
        if(!vertexList.contains(vertex2))
            throw new RuntimeException("The vertex v2 doesn't belong to this graph");
        vertexList.add(vertex2);
        vertexList.add(vertex1);
        numVertex = numVertex + 2;
        AMEdge<E,V> edge = new AMEdge<>(null,this);
        edge.setSource((Vertex<E>) vertex1);
        edge.setSource((Vertex<E>) vertex2);
        edge.setConect(true);
        for(int i=0;i<numVertex;i++)
            for(int j=0;j<numVertex;j++)
                if(adjMatrix.get(i).get(j) == null)
                {                    
                    adjMatrix.get(i).add(j, (AMDirectedEdge<E, V>) edge);
                    break;
                }
        return edge;
    }
    
    public Collection<? extends Edge>  outputEdges(Vertex<V> v)
    {    
        AMVertex<V,E> vertex = (AMVertex<V,E>) checkVertex(v);
        Set<AMDirectedEdge> list = new HashSet<>();
        
        for(int i=0;i<numVertex;i++)
        {
            for(Edge<E> e : adjMatrix.get(i))
            {
                if(e.getSource() == vertex || e.getDestination() == vertex)
                    list.add((AMDirectedEdge) e);
            }
        }
        return list;
    }
}