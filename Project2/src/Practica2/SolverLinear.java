package Practica2;

public class SolverLinear implements Solver
{
    @Override
    public int get(int hash, int numIntentos) 
    {
        return (hash + numIntentos);
    }
}