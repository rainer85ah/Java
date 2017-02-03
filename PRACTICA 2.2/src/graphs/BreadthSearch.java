/*
Ejercicio 3: Implementar una clase llamada BreadthSearch que permita realizar una
búsqueda en amplitud en un grafo. La clase tendrá un único método público, llamado
getPath, que recibirá un objeto derivado de Graph y dos nodos del grafo, y devolverá
una lista con las aristas que unen ambos nodos. La clase podrá tener otros métodos
privados. La clase deberá venir acompañada de tres test unitarios que demuestren su
funcionamiento en el caso de un grafo trivial, en el caso de un grafo como el de la
figura y en el caso de un grafo con bucles.
*/
package graphs;

import graphs.AL.ALGraph;
import graphs.AL.ALVertex;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BreadthSearch<V,E>
{       
    Graph<V,E> graph = new ALGraph<>();
    ALVertex<V,E> currentVertex = null;
    ALVertex<V,E> vOpuesto = null;
    List<? extends Vertex> c ;
    List<Vertex<V>> path;

    public BreadthSearch(ALVertex<V, E> currentVertex, List<? extends Vertex> c, List<Vertex<V>> path) {
        this.currentVertex = currentVertex;
        this.c = c;
        this.path = path;
    }
    
    public BreadthSearch()
    {
        this.currentVertex = null;
        this.c = new LinkedList<>();
        this.path = new LinkedList<>();
    }
    //Recorrido en amplitud del grafo
    public Set<? extends Vertex<V>> DFS(Graph graph, Vertex<V> start)
    {
        Set<ALVertex<V,E>> list = new HashSet<>();
        clearVisit();//todos los vertices se pone a falso el atributo visitado
        ALVertex<V,E> v = (ALVertex<V,E>)start;
        v.setVisit(true);
        Stack<ALVertex<V,E>> st = new Stack<>();
        currentVertex = v;
        list.add(currentVertex);
        for (Vertex<V> e : currentVertex.adjacentVertices())
        {
            System.out.println("pasa x aki");
            ALVertex<V, E> aux = (ALVertex<V, E>) e;
            st.add(aux);
        }
        while(!st.isEmpty())
        {
           ALVertex<V,E> w = st.pop();
           
           for(Vertex<V> u : w.adjacentVertices())
           {
               ALVertex<V,E> uu = (ALVertex<V,E>) u;
               if(uu.getVisit() == false)
               {
                   uu.setVisit(true);
                   st.push(uu);
                   list.add(uu);
               }
           }
        }
        return list;
    }
    
    public List<? extends Vertex> getPath(Graph graph, Vertex<V> start, Vertex<V> end)
    {
        if (graph.vertices().contains(start) && graph.vertices().contains(end))
        {    
            clearVisit();
            c = getPathDFS(graph,start,end);
            if (c.isEmpty())
                return null;
            else
                return c;
        }
        return null;
    }

    //DFS ITERATIVE!!!!!
    private List<? extends Vertex> getPathDFS(Graph graph, Vertex<V> start, Vertex<V> end)
    {        
        clearVisit();
        path.add(start);
        start.setVisit(true);
        if (start == end)
            return path;
        Stack<ALVertex<V,E>> st = new Stack<>();
        currentVertex = (ALVertex<V, E>) start;
        for (Vertex<V> e : currentVertex.adjacentVertices())
        {
            ALVertex<V, E> aux = (ALVertex<V, E>) e;
            st.add(aux);
        }
        while(!st.isEmpty())
        {
           ALVertex<V,E> w = st.pop();
           for(Vertex<V> u : w.adjacentVertices())
           {
               ALVertex<V,E> uu = (ALVertex<V,E>) u;
               if(uu.getVisit() == false)
               {
                   uu.setVisit(true);
                   path.add(uu);
                   if (uu == end)
                        return path;
                   st.push(uu);
               }
           }
        }
        return path;
    }
    
    private void clearVisit() 
    {
        for (Vertex v : graph.vertices()) 
        {
            ALVertex vv = (ALVertex) v;
            vv.setVisit(false);
        }
    }   
}