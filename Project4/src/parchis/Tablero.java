package parchis;

import java.util.List;

import parchis.Casilla.TipoCasilla;

/**
 *  Representa un Tablero de Parchís.
 * 
*  La clase Tablero representa un tablero de Parchís formado por las
*  diferentes Casillas por las cuales van a pasar las Fichas de los
*  Jugadores hasta llegar a la Casilla de meta.
 */

public class Tablero {
    private Casilla carril[];
    /**
     * < Atributo representa el carril principal de Casillas consecutivas hasta
     * la meta
     */
    private Casilla pasilloAmarillo[];
    private Casilla pasilloAzul[];
    private Casilla pasilloRojo[];
    private Casilla pasilloVerde[];

    private Casilla salidaAmarilla;
    private Casilla salidaAzul;
    private Casilla salidaRoja;
    private Casilla salidaVerde;

    private Casilla metaAmarilla;
    private Casilla metaAzul;
    private Casilla metaRoja;
    private Casilla metaVerde;

    private List<Ficha> fichasAmarillas;
    private List<Ficha> fichasAzules;
    private List<Ficha> fichasRojas;
    private List<Ficha> fichasVerdes;

    static final Color AMARILLO = new Color("amarillo");
    static final Color VERDE = new Color("verde");
    static final Color AZUL = new Color("azul");
    static final Color ROJO = new Color("rojo");

    /**
     * Calcula la Casilla destino de la Ficha.
     * 
     *        Este método calcula la posible Casilla de destino de la Ficha,
     *        Según la tirada del Dado. Si la Ficha esta en un Pasillo y la
     *        tirada es mayor de las posiciones que le quedan hasta llegar a
     *        meta, devuelve null. Redirecciona la siguiente Casilla al Pasillo
     *        correspondiente si le posible destino de la Ficha pasara por una
     *        Casilla ENTRADA_META.
     * 
     * @param ficha
     *            Ficha de la que queremos saber su posible destino.
     * @param avance
     *            Es el numero de posiciones que queremos calcular por delante
     *            de la Ficha.
     * @return La posible Casilla destino si la hay, si no devuelve null.
     */
    public Casilla calculaDestino(Ficha ficha, int avance) {
        Casilla posActual = ficha.getPosicion();

        int i = 0;
        while (i < avance && posActual != null) {
            if (posActual.getTipoCasilla() != TipoCasilla.ENTRADA_META) {
                    posActual = posActual.getCasillaSiguiente();
            } else {
                Color color = ficha.getColorFicha();
                if (color.equals(AMARILLO)) {
                    posActual = this.pasilloAmarillo[0];
                } else if (color.equals(AZUL)) {
                    posActual = this.pasilloAzul[0];
                } else if (color.equals(ROJO)) {
                    posActual = this.pasilloRojo[0];
                } else if (color.equals(VERDE)) {
                    posActual = this.pasilloVerde[0];
                }
            }
            i++;
        }
        return posActual;
    }

    /**
     * Calcula la siguiente Casilla de la Ficha.
     * 
     * Si la Ficha esta situada en una Casilla ENTRADA_META redirecciona
     * la siguiente Casilla al Pasillo correspondiente Según el color de
     * la Ficha.
     * 
     * @param ficha
     *            De la que queremos saber la siguiente casilla.
     * @return la Ficha siguiente a la actual.
     */
    public Casilla getSiguienteCasilla(Ficha ficha) {
        Casilla posicion = ficha.getPosicion();
        Casilla siguienteCasilla = posicion.getCasillaSiguiente();

        if (posicion.getTipoCasilla() == TipoCasilla.ENTRADA_META) {

            Color color = ficha.getColorFicha();
            if (color.equals(AMARILLO)) {
                siguienteCasilla = this.pasilloAmarillo[0];

            } else if (color.equals(AZUL)) {
                siguienteCasilla = this.pasilloAzul[0];

            } else if (color.equals(ROJO)) {
                siguienteCasilla = this.pasilloRojo[0];

            } else if (color.equals(VERDE)) {
                siguienteCasilla = this.pasilloVerde[0];
            }
        }
        return siguienteCasilla;
    }

    public Casilla getMetaAmarilla() {
        return metaAmarilla;
    }

    public void setMetaAmarilla(Casilla metaAmarilla) {
        this.metaAmarilla = metaAmarilla;
    }

    public Casilla getMetaAzul() {
        return metaAzul;
    }

    public void setMetaAzul(Casilla metaAzul) {
        this.metaAzul = metaAzul;
    }

    public Casilla getMetaRoja() {
        return metaRoja;
    }

    public void setMetaRoja(Casilla metaRoja) {
        this.metaRoja = metaRoja;
    }

    public Casilla getMetaVerde() {
        return metaVerde;
    }

    public void setMetaVerde(Casilla metaVerde) {
        this.metaVerde = metaVerde;
    }

    public List<Ficha> getFichasAmarillas() {
        return fichasAmarillas;
    }

    public void setFichasAmarillas(List<Ficha> fichasAmarillas) {
        this.fichasAmarillas = fichasAmarillas;
    }

    public List<Ficha> getFichasAzules() {
        return fichasAzules;
    }

    public void setFichasAzules(List<Ficha> fichasAzules) {
        this.fichasAzules = fichasAzules;
    }

    public List<Ficha> getFichasRojas() {
        return fichasRojas;
    }

    public void setFichasRojas(List<Ficha> fichasRojas) {
        this.fichasRojas = fichasRojas;
    }

    public List<Ficha> getFichasVerdes() {
        return fichasVerdes;
    }

    public void setFichasVerdes(List<Ficha> fichasVerdes) {
        this.fichasVerdes = fichasVerdes;
    }

    public Casilla getSalidaAmarilla() {
        return salidaAmarilla;
    }

    public void setSalidaAmarilla(Casilla salidaAmarilla) {
        this.salidaAmarilla = salidaAmarilla;
    }

    public Casilla getSalidaAzul() {
        return salidaAzul;
    }

    public void setSalidaAzul(Casilla salidaAzul) {
        this.salidaAzul = salidaAzul;
    }

    public Casilla getSalidaRoja() {
        return salidaRoja;
    }

    public void setSalidaRoja(Casilla salidaRoja) {
        this.salidaRoja = salidaRoja;
    }

    public Casilla getSalidaVerde() {
        return salidaVerde;
    }

    public void setSalidaVerde(Casilla salidaVerde) {
        this.salidaVerde = salidaVerde;
    }

    public void setPasilloAmarillo(Casilla[] pasilloAmarillo) {
        this.pasilloAmarillo = pasilloAmarillo;
    }

    public void setPasilloAzul(Casilla[] pasilloAzul) {
        this.pasilloAzul = pasilloAzul;
    }

    public void setPasilloRojo(Casilla[] pasilloRojo) {
        this.pasilloRojo = pasilloRojo;
    }

    public void setPasilloVerde(Casilla[] pasilloVerde) {
        this.pasilloVerde = pasilloVerde;
    }

    public void setCarril(Casilla[] carril) {
        this.carril = carril;
    }

    public void ponerfichaEnPosicion(Ficha ficha, int posicion) {
        Color color = ficha.getColorFicha();

        if (color.equals(AMARILLO)) {
                carril[posicion - 1].ponFicha(ficha);
                getSalidaAmarilla().sacarFicha(ficha);
        } else if (color.equals(AZUL)) {
                carril[posicion - 1].ponFicha(ficha);
                getSalidaAzul().sacarFicha(ficha);
        } else if (color.equals(ROJO)) {
                carril[posicion - 1].ponFicha(ficha);
                getSalidaRoja().sacarFicha(ficha);
        } else if (color.equals(VERDE)) {
                carril[posicion - 1].ponFicha(ficha);
                getSalidaVerde().sacarFicha(ficha);
        }
    }

    public void colocarDosFichasEnPuntoPartida(Jugador jugador) {
        this.colocarUnaFichaEnPuntoPartida(jugador);
        this.colocarUnaFichaEnPuntoPartida(jugador);
    }

    public void ponerfichaEnMeta(Ficha ficha) {
        Color color = ficha.getColorFicha();
        if (color.equals(AMARILLO)) {
                getMetaAmarilla().ponFicha(ficha);
                getSalidaAmarilla().sacarFicha(ficha);
        } else if (color.equals(AZUL)) {
                getMetaAzul().ponFicha(ficha);
                getSalidaAzul().sacarFicha(ficha);
        } else if (color.equals(ROJO)) {
                getMetaRoja().ponFicha(ficha);
                getSalidaRoja().sacarFicha(ficha);
        } else if (color.equals(VERDE)) {
                getMetaVerde().ponFicha(ficha);
                getSalidaVerde().sacarFicha(ficha);
        }
    }

    public void colocarUnaFichaEnPuntoPartida(Jugador jugador) {
        Ficha ficha = jugador.getFichaEnSalida();

        Casilla casillaSiguiente = getSiguienteCasilla(ficha);
        casillaSiguiente.ponFicha(ficha);

        Color color = ficha.getColorFicha();
        if (color.equals(AMARILLO)) {
                getSalidaAmarilla().getFichas().remove(ficha);
        } else if (color.equals(AZUL)) {
                getSalidaAzul().getFichas().remove(ficha);
        } else if (color.equals(ROJO)) {
                getSalidaRoja().getFichas().remove(ficha);
        } else if (color.equals(VERDE)) {
                getSalidaVerde().getFichas().remove(ficha);
        }
    }

    public void moverFichaACasa(Ficha ficha) {
        ficha.getPosicion().sacarFicha(ficha);

        Color color = ficha.getColorFicha();
        if (color.equals(AMARILLO)) {
                getSalidaAmarilla().ponFicha(ficha);
        } else if (color.equals(AZUL)) {
                getSalidaAzul().ponFicha(ficha);
        } else if (color.equals(ROJO)) {
                getSalidaRoja().ponFicha(ficha);
        } else if (color.equals(VERDE)) {
                getSalidaVerde().ponFicha(ficha);
        }
    }

    public boolean tienePuentesEnMedio(Ficha ficha, int tirada) {
        boolean tienePuentes = false;

        //Casilla posicion = getSiguienteCasilla(ficha);
        Casilla posicion;
        int i = 0;
        while (!tienePuentes && i < tirada) {
                posicion = calculaDestino(ficha, i + 1);
                if (posicion.esPuente()) {
                        tienePuentes = true;
                }
                i++;
        }
        return tienePuentes;
    }

    private static final int MAXIMO_FICHA_EN_CASILLA_META =4;

    public boolean cabeEnDestino(Ficha ficha, int tirada) {
        boolean b = false;
        Casilla casillaDestino = calculaDestino(ficha, tirada);

        if (casillaDestino.getTipoCasilla() == TipoCasilla.META) {
            b = casillaDestino.getFichas().size() < MAXIMO_FICHA_EN_CASILLA_META;
        } else {
            b = casillaDestino.getFichas().size() < 2;
        }
        return b;
    }

    public Ficha elegirFichaDesplazable(Jugador jugador, int tirada) {
        Ficha f = null;
        List<Ficha> fichas = jugador.getFichas();

        if (jugador.todasEnSalida())
        {
            f = null;
        } else {
            for (Ficha ficha : fichas) {
                if (!ficha.estaEnCasillaSalida() && !ficha.estaEnCasillaMeta()) {
                    Casilla casillaDestino = calculaDestino(ficha, tirada);
                    if (casillaDestino != null
                                    && !tienePuentesEnMedio(ficha, tirada)
                                    && cabeEnDestino(ficha, tirada)) {
                            System.out.println(ficha.toString());
                            f = ficha;
                    }
                }
            }
        }
        return f;
    }

    public void comer(Ficha ficha) {
        Casilla casilla = ficha.getPosicion();
        Ficha fichaCompanera = null;
        for (Ficha fichaCasilla : casilla.getFichas()) {
            if (fichaCasilla.getColorFicha() != ficha.getColorFicha()) {
                fichaCompanera = fichaCasilla;
            }
        }
        moverFichaACasa(fichaCompanera);
    }

    public void moverFicha(Ficha ficha, int tirada) {
        Casilla casillaActual = ficha.getPosicion();
        Casilla casillaDestino = calculaDestino(ficha, tirada);

        casillaActual.sacarFicha(ficha);
        casillaDestino.ponFicha(ficha);
    }

    public void eligeFichaYMueveACasa(Jugador jugador, int tirada) {
        Ficha ficha = jugador.elegirFichaNoSalvada();
        if (ficha != null) {
            moverFichaACasa(ficha);
        }
    }


    private static final int TIRADA_COMER =20;
    public void eligeFichaYMueve(Jugador jugador, int tirada) {
        Ficha ficha = elegirFichaDesplazable(jugador, tirada);
        if (ficha == null) {
                System.out.println("No puede desplazarse");
        } else {
            moverFicha(ficha, tirada);
            if (ficha.come()) 
            {
                comer(ficha);
                eligeFichaYMueve(jugador, TIRADA_COMER);
            }
        }
    }
}