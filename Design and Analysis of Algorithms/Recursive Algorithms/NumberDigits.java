import java.io.*;

public class numberDigits 
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
        if (N < 10)
            return 1;
        else        
            return 1 + recursiveFunction(N/10);
    }
}
