import parchis.Parchis;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        Parchis parchis = new Parchis();
        parchis.addJugador();
        parchis.addJugador();
        parchis.addJugador();
        parchis.addJugador();
        parchis.jugar();
    }   
}