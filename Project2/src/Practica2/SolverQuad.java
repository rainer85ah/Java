package Practica2;

public class SolverQuad implements Solver
{
    @Override
    public int get(int hash, int num) 
    {
        final int PRIMO1 = 499;
        final int PRIMO2 = 997;
        return (int) (hash + (PRIMO1*num) + (PRIMO2*Math.pow(num,2)));
    }
}