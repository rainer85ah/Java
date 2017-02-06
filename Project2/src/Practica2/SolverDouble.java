package Practica2;

public class SolverDouble implements Solver
{    
    @Override
    public int get(int hash, int num)
    {
        return (hash + solverPrivate(hash) * num);
    }   
    
    private int solverPrivate(int hash) 
    {
        final int PRIMO = 997;
        int index = PRIMO - (hash % PRIMO);
        return index;
    }      
} 