/*
Ejercicio 4: Implementar una aplicación que permita a 4 personas jugar al parchís.
Para implementar el tablero de parchís se recomienda utilizar un digrafo. Los nodos de
dicho digrafo deberá almacenar información como el número de la casilla, el color,
tipo de casilla (seguro, salida, casa...), y una lista con las fichas que contiene.
La partida se inicia con 4 fichas de cada color en cada una de las casillas de salida.
Los jugadores solo podrán pedir que se mueva una ficha de una casilla a otra, y el
ordenador solo verificará si al realizar dicho movimiento la ficha se come a la otra. Se
deberá tener en cuenta que una casilla no puede tener más de dos piezas, y que en ls
casillas seguro y de salida no se puede comer a la pieza que exista.
 */

package parchis;

import graphs.AL.ALGraph;
import graphs.Graph;
import graphs.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * La Clase Parchis gestiona los turnos, y las acciones según la tirada del dado, que hacer si es 5 o 6 o sale 3 veces un 6 etc...
 * Añade Jugadores a la partida y controla cuando se acaba.
 * 
 */
public class Parchis<E,V> 
{
    Graph<E,V> graph = new ALGraph<>();
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private Iterator<Jugador> itj;
    private Jugador jugadorActual;
    private int tirada = 0;
    private int numeroDeSeis = 0;
    Vertex<V> casActual = null; 
    Vertex<V> colorFicha = null; 
    Vertex<V> casDest = null; 

    /**
     * Crea un Tablero a partir de TableroFabricacreateTablero() y un ArrayList<Jugador>() que contendrá los jugadores.
     */
    public Parchis() 
    {
        TableroFabrica tableroFabrica = new TableroFabrica();
        tablero = tableroFabrica.createTablero();
        jugadores = new ArrayList<>();

    }

    public Graph<E, V> getGraph() {
        return graph;
    }

    public void setGraph(Graph<E, V> graph) {
        this.graph = graph;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Iterator<Jugador> getItj() {
        return itj;
    }

    public void setItj(Iterator<Jugador> itj) {
        this.itj = itj;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public int getTirada() {
        return tirada;
    }

    public void setTirada(int tirada) {
        this.tirada = tirada;
    }

    public int getNumeroDeSeis() {
        return numeroDeSeis;
    }

    public void setNumeroDeSeis(int numeroDeSeis) {
        this.numeroDeSeis = numeroDeSeis;
    }
    
    
    /**
     * Gestiona el juego.
     * 
     * Controla cuando termina el juego, el siguiente Jugador, y que hacer si la tirada es un 5 un 6 u otra.
     */
    public void jugar() {
        itj = jugadores.iterator();
        jugadorActual = siguienteJugador();
        Scanner scan = new Scanner(System.in);   
        boolean seguir;
        
        do
        {
            System.out.println("Indique la casilla que contiene la ficha a mover: ");
            String s = scan.next();        
            //casActual.setValue((V) s);
            graph.insertVertex((E) s);
            
            
            System.out.println("Indique el color de la ficha (r,g,b,y)");
            s = scan.next();
            //colorFicha.setValue(s);
            graph.insertVertex((E) s);
            
            
            System.out.println("Indique la casilla destino:");
            s = scan.next();
            //colorFicha.setValue(s);
            graph.insertVertex((E) s);
            
            System.out.println("Se moverá la ficha verde desde 2 a 6 (s/n)");
            s = scan.next();
            if (s.equalsIgnoreCase("s"))
            {
                System.out.println("Se ha movido la ficha verde desde 2 a 6 y se ha comido a la ficha azul.");
                seguir = true;
            }
            else
                seguir = false;                        
        }while(seguir == false);
        /*
        while (!fin()) {
            System.out.println("Turno del Jugador: " + jugadorActual.getColor());
            tirada = new Dado().tirarDado();
            switch (tirada) {
            case 5:
                esCinco();
                break;
            case 6:
                esSeis();
                break;
            default:
                tablero.eligeFichaYMueve(jugadorActual, tirada);
                jugadorActual = siguienteJugador();
                numeroDeSeis = 0;
                break;
            }
        }
        */
    }


    /**
     * Añade Jugadores a la partida.
     * Según el numero de jugadores añade un jugador con un color de fichas diferente.
     */
    public void addJugador() {
        Jugador jugador = null ;
        switch (jugadores.size()) {
        case 0:
                jugador = new Jugador(this.tablero.getFichasAmarillas());
                break;
        case 1:
                jugador = new Jugador(this.tablero.getFichasAzules());
                break;
        case 2:
                jugador = new Jugador(this.tablero.getFichasRojas());
                break;
        case 3:
                jugador = new Jugador(this.tablero.getFichasVerdes());
                break;
        default:
                break;
        }
        jugadores.add(jugador);
    }
    /**
     *  Calcula el siguiente Jugador del turno.
     * @return El jugador siguiente del turno.
     */
    private Jugador siguienteJugador() 
    {
        if (!itj.hasNext()) {
                itj = jugadores.iterator();
        }
        return itj.next();
    }
    
    /**
     * @brief Proceso de la partida si la tirada es un cinco.
     * 
     * Este método tiene toda la lógica correspondiente a si la tirada es un cinco.
     * 
     *  
     */
    private void esCinco(){
        if ((jugadorActual.getNumeroDeFichasEnSalida()) == 0) {
                tablero.eligeFichaYMueve(jugadorActual, tirada);
        } else if (jugadorActual.getNumeroDeFichasEnSalida() == 4) {
                tablero.colocarDosFichasEnPuntoPartida(jugadorActual);
        } else {
            if (!jugadorActual.tieneDosFichasEnPP()) {
                tablero.colocarUnaFichaEnPuntoPartida(jugadorActual);
            } else {
                tablero.eligeFichaYMueve(jugadorActual, tirada);
            }

        }
        jugadorActual = siguienteJugador();
        numeroDeSeis = 0;
    }

    private void esSeis(){
        numeroDeSeis++;
        if (numeroDeSeis < 3) {
            if (!jugadorActual.tieneFichaEnCasillaSalida()) {
                tirada++;
            }
            tablero.eligeFichaYMueve(jugadorActual, tirada);
        } else {
            tablero.eligeFichaYMueveACasa(jugadorActual, tirada);
            jugadorActual = siguienteJugador();
            numeroDeSeis = 0;
        }
    }

    private boolean fin() {
        boolean fin = false;
        if ((tablero.getMetaAmarilla().getFichas().size() == 4)
                        || (tablero.getMetaAzul().getFichas().size() == 4)
                        || (tablero.getMetaRoja().getFichas().size() == 4)
                        || (tablero.getMetaVerde().getFichas().size() == 4)) {
            fin = true;
            System.out.println("Amarillo: " + jugadores.get(0).getFichas());
            System.out.println("Azul: " + jugadores.get(1).getFichas());
            System.out.println("Rojo: " + jugadores.get(2).getFichas());
            System.out.println("Verde: " + jugadores.get(3).getFichas());
        }
        return fin;
    }
}
