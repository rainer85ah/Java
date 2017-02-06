package parchis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
*   La Clase Jugador es un ArrayList<Ficha> que contendrá las fichas de
*   las que dispone el Jugador
 * 
 */

public class Jugador {

    private List<Ficha> fichas;
    /** < ArrayList<Fichas> que contiene las fichas del jugador */

    /**
     *
     * @param fichas
     *  ArrayList de las Fichas con las que se crea el Jugador
     */
    public Jugador(List<Ficha> fichas) {
        super();
        this.fichas = new ArrayList<Ficha>();
        this.fichas.addAll(fichas);
    }

    @Override
    public String toString() {
        return "Jugador [fichas=" + fichas + ", getColor()=" + getColor() + "]";
    }

    /**
     *   Observador
     * @return Las Fichas del Jugador
     */
    public List<Ficha> getFichas() {
        return fichas;
    }

    /**
     *   Calcula el color con el que juega el jugador
     * 
     *        Calcula el color de la ficha con la que juega el Jugador a partir
     *        de una ficha de las que dispone.
     * @return el color de la ficha
     * @see Ficha.getColorFicha()
     */
    public Color getColor() {
        return this.fichas.get(0).getColorFicha();
    }

    /**
     *   Devuelve una Ficha de la Casilla de Salida.
     * 
     *        Este método busca si alguna Ficha del Jugador esta en la casilla
     *        de salida, si encuentra una te la devuelve, si no encuentra
     *        ninguna devuelve null
     * @return una Ficha de la casilla de salida. Null si no hay
     * @see Ficha.estaEnCasillaSalida()
     */
    public Ficha getFichaEnSalida() {
        Ficha result = null;
        boolean isFound = false;
        Iterator<Ficha> itf = fichas.iterator();
        while (itf.hasNext() && !isFound) {
                Ficha actual = itf.next();
                if (actual.estaEnCasillaSalida()) {
                        result = actual;
                        isFound = true;
                }
        }
        return result;
    }

    /**
     *   Calcula si todas las Fichas están en la Casilla de salida.
     * 
     *        Recorre todas las Fichas del Jugador, si alguna no esta en la
     *        Casilla de Salida devuelve null.
     * @return true si todas las fichas del Jugador están en la Casilla de
     *         salida.
     */
    public boolean todasEnSalida() {
        boolean allInExit = true;
        Iterator<Ficha> itf = fichas.iterator();
        while (itf.hasNext() & allInExit) {
                Ficha actual = itf.next();
                if (!actual.estaEnCasillaSalida()) {
                        allInExit = false;
                }
        }
        return allInExit;
    }

    /**
     *   Calcula si tiene dos fichas en la casilla de punto de partida.
     * 
     *        Este método pregunta el color de la ficha que tiene el Jugador y
     *        Según el color va a la posición de la casilla de punto de partida
     *        correspondiente.
     * @return true si tiene dos de sus fichas en su casilla de salida.
     */

    static final int CASILLA_INICIAL_AMARILLA = 5;
    static final int CASILLA_INICIAL_AZUL  = 22;
    static final int CASILLA_INICIAL_ROJO = 39;
    static final int CASILLA_INICIAL_VERDE = 56;

    public boolean tieneDosFichasEnPP() {
        boolean tieneDosFichasEnPP = false;
        int numeroDeFichas = 0;
        int idCasillaInicial = 0;

        Color color = this.getColor();
        if (color.equals(Tablero.AMARILLO)) {
                idCasillaInicial = CASILLA_INICIAL_AMARILLA;
        } else if (color.equals(Tablero.AZUL)) {
                idCasillaInicial =CASILLA_INICIAL_AZUL;
        } else if (color.equals(Tablero.ROJO)) {
                idCasillaInicial = CASILLA_INICIAL_ROJO;
        } else if (color.equals(Tablero.VERDE)) {
                idCasillaInicial = CASILLA_INICIAL_VERDE;
        }
        for (Ficha ficha : fichas) {
                if (ficha.getNumeroPosicion() == idCasillaInicial) {
                        numeroDeFichas++;
                }
                if (numeroDeFichas == 2) {
                        tieneDosFichasEnPP = true;
                }
        }
        return tieneDosFichasEnPP;
    }

    /**
     *   Calcula el numero de fichas que están en la Casilla de salida.
     * 
     *        Recorre las fichas del jugador preguntando si la posición de la
     *        casilla donde se encuentra la ficha es 0.
     * @return El numero de fichas que están en la Casilla de Salida.
     */
    public int getNumeroDeFichasEnSalida() {
        int i = 0;
        for (Ficha ficha : fichas) {
                if (ficha.getNumeroPosicion() == 0) {
                        i++;
                }
        }
        return i;
    }

    /**
     *   Calcula si el Jugador tiene fichas en casa.
     * 
     *        Recorre las Fichas del Jugador preguntado si tiene alguna Ficha en
     *        la Casilla de Salida, si encuentra una la devuelve true.
     * 
     * @return true si al menos una Ficha esta en Casilla de Salida.
     */

    public boolean tieneFichaEnCasillaSalida() {
        boolean oneInExit = false;
        Iterator<Ficha> itf = fichas.iterator();
        while (itf.hasNext() & !oneInExit) {
                Ficha actual = itf.next();
                if (!actual.estaEnCasillaSalida()){
                        oneInExit = true;				
                }
        }
        return oneInExit;
    }

    /**
     *   Elige una Ficha no salvada.
     * 
     *        Recorre las Fichas del Jugador comprobando si están salvadas si
     *        encuentra una, la devuelve.
     * 
     * @return La una Ficha que no está salvada, si no hay devuelve null.
     */
    public Ficha elegirFichaNoSalvada() {
        Ficha fichaNoSalvada = null;
        for (Ficha ficha : this.fichas) {
                if (!ficha.estaSalvada()) {
                        fichaNoSalvada = ficha;
                }
        }
        return fichaNoSalvada;
    }
}