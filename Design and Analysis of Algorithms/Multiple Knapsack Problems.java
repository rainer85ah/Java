import java.io.*;
//Multiple Knapsack Problems.
public class Practica4
{    
    public static void main(String[] args) throws IOException
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));	  
        int n = Integer.parseInt(scan.readLine());      
        
        String[] strNum = scan.readLine().split(" ");        
        int[] P = new int[n];//pesos de los elementos.
        int[] X = new int[n];//Array que guarda el estado de cada objeto (0: ninguna mochila, 1: mochila 1, 2: mochila 2)
        for (int i = 0; i < n; i++)
        {
            P[i] = Integer.parseInt(strNum[i]);
            X[i] = -1;
        }
        
        strNum = scan.readLine().split(" ");
        int C1 = Integer.parseInt(strNum[0]);
        int C2 = Integer.parseInt(strNum[1]);
		
        sortAsc(P);//Ascendente Pesos..Estrategia Optima en este caso.       
        int bestValor = MKP(0, n, P, X, C1, C2); 
        System.out.println(bestValor);
    }

    public static int MKP(int i, int n, int[] P, int[] X, int C1, int C2)
    {         
        if (i >= n)
            return 0;
        if (C1-P[i] < 0 && C2-P[i] < 0)
            return MKP(i+1, n, P, X, C1, C2); 
           
        int bestValor = 0, aux0 = 0,aux1 = 0,aux2 = 0;
        for (int k = 0; k < 3 && i < n; k++)//0/1/2
        {
            X[i] = k;	
            if (C1-P[i] >= 0 && X[i] == 1)
                aux1 = MKP(i+1, n, P, X, C1-P[i], C2) + P[i];
            if (C2-P[i] >= 0 && X[i] == 2)
                aux2 = MKP(i+1, n, P, X, C1, C2-P[i]) + P[i];
            aux0 = MKP(i+1, n, P, X, C1, C2);
            bestValor = findMax(aux0, aux1, aux2);			
        }     
        return bestValor;
    }
    
    //(BUBBLE SORT) Ascendente
    public static void sortAsc(int[] array) 
    {   
        for (int i = 0; i < array.length-1; i++) 
        {
            for (int j = 0; j < array.length-i-1; j++) 
            {
                if(array[j] > array[j+1])
                {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
	    
    public static int findMax(int ... vals) 
    {
        int max = Integer.MIN_VALUE;
        for (int d : vals) 
            if (d > max) 
                max = d;
        return max;
    }
}