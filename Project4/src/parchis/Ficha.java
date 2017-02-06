package parchis;

import parchis.Casilla.TipoCasilla;
/**
 *  Representa una ficha.
 *  Gestiona las fichas con las que nos movemos por las casillas.
 */

public class Ficha {

    private Color colorFicha;/**< Representa el color de la Ficha */
    private Casilla posicion;/**<  Casilla en la que esta situada la Ficha */


    /**
     * Observador
     * @return El valor de la casilla donde esta la Ficha
     * @see Casilla.getPosicion()
     */
    public int getNumeroPosicion() {
            return posicion.getPosicion();
    }
    /**
     * Observador
     * @return La casilla donde esta situada la Ficha
     */
    public Casilla getPosicion() {
            return posicion;
    }





    @Override
    public String toString() {
            return "Ficha [colorFicha=" + colorFicha + ", getNumeroPosicion()="
                            + getNumeroPosicion() + "]";
    }

    /**
     * Modificador
     * @param posicion es la Casilla donde queremos situar la Ficha
     */
    public void setPosicion(Casilla posicion) {
            this.posicion = posicion;
    }
    /**
     * Observador
     * @return el color de la Ficha
     */
    public Color getColorFicha() {
            return colorFicha;
    }

    /**    constructor de casilla
     * 
     * Crea una ficha de un color.
     * 
     * @param colorFicha Color con el que queremos crear la ficha. La posiion de la Ficha se crea en TableroFabrica
     * 
     * @see TableroFabrica.createFichas()
     */
    public Ficha(Color colorFicha) {
            super();
            this.colorFicha = colorFicha;
            this.posicion=null;
    }

    /**
     * 
     * @return La Casilla 
     */
    public Casilla siguienteCasilla(){
            return posicion.getCasillaSiguiente();

    }


    /**
     *     Calcula si la Ficha esta en en la casilla de salida.
     * 
     * @return devuelve true si la casilla donde esta la Ficha es 0. 
     */


    public boolean estaEnCasillaSalida() {

            return this.posicion.getTipoCasilla()==TipoCasilla.INICIO;

    }

    /**
     *     Calcula si la Ficha esta salvada.
     * 
     * Una Ficha esta salvada siempre que este en una Casilla diferente de la NORMAL.
     * 
     * @return un boolean especificando si la Ficha esta salvada o no.
     * @see Casilla.getTipoCasilla()
     */
    public boolean estaSalvada() {
            boolean estaSalvada= false;
            if(this.posicion.getTipoCasilla()!=TipoCasilla.NORMAL){
                    estaSalvada=true;
            }
            return estaSalvada;
    }

    /**
     *     Calcula si la Ficha esta en el tipo casilla META
     * 
     * Una Ficha esta en META si TipoCasilla de Casilla es igual a META.
     * @return un boolean especificando si esta en META o no.
     */
    public boolean estaEnCasillaMeta() {

            return this.posicion.getTipoCasilla()==TipoCasilla.META;
    }
    /**
     *     Calcula si la Ficha esta en una Casilla ENTRADA_META.
     * 
     * Pregunta a la Casilla donde esta la Ficha, si su TipoCasilla es ENTRADA_META.
     * @return boolean con la información de si la ficha esta en una Casilla ENTRADA_META.
     */
    public boolean estaEnCasillaEntradaMeta() {
            boolean estaEnEntradaMeta = false;
            if(this.getPosicion().getTipoCasilla()==TipoCasilla.ENTRADA_META){
                    estaEnEntradaMeta=true;
            }
            return estaEnEntradaMeta;
    }

    /**
     * \pre  come() debe llamarse justo después de que la ficha
     * se haya movido.
     * 
     *     Comprueba si la ficha come.
     * 
     * Recorre las Fichas de la Casilla donde esta situada la Ficha
     * y si hay alguna de otro color que la ficha que comprobamos si
     * come se sale y devuelve true.
     * 
     * @return true si come, false si no come.
     */
    public boolean come() {
            boolean come = false;
            Casilla casilla = getPosicion();

            for (Ficha fichaCasilla : casilla.getFichas()) {
                    if (fichaCasilla.getColorFicha() != getColorFicha()) {
                            come = true;
                            break;
                    }
            }
            return come;
    }
}