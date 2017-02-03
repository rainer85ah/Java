import java.io.*;

public class oddsSum 
{
    public static void main(String args[]) throws Exception 
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));	  
        int N = Integer.parseInt(scan.readLine());
        //System.out.println(); // MooSHAK
        
        int cont = recursiveFunction(N);
        System.out.print(cont); 
        System.out.println();
    }

    public static int recursiveFunction(int N)
    {
        if (N == 0)     
            return 0;
        else
            return (2*N - 1) + recursiveFunction(N-1);
    }
}
