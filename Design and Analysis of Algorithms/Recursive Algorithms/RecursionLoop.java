import java.io.*;

public class recursiveloop
{
    public static void main(String args[]) throws Exception 
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));	  
        int N = Integer.parseInt(scan.readLine());
        //System.out.println(); // MooSHAK

        int cont = recursiveFunction(N);
        System.out.println(cont);  
    }

    public static int recursiveFunction(int N)
    {
        int cont=0;
        
        if (N == 1 || N == 2)
            return cont+1;
        else
        {
            for (int i=1; i <= N-2; i++)
                cont = cont + recursiveFunction(i);
        }  
        return cont+1;
    }
}
