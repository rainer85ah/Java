import java.io.*;

public class MST
{
    public static void main(String args[]) throws Exception 
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));	  
        int numNodos = Integer.parseInt(scan.readLine());
        int matrizAdy[][] = new int[numNodos][numNodos]; 
		
        String[] strNum;
        for (int i=0; i < numNodos; i++)
        {      
            strNum = scan.readLine().split(" ");		
            for (int j=0; j < numNodos; j++)
                matrizAdy[i][j] = Integer.parseInt(strNum[j]);
        }
        int result = primMST(matrizAdy); 
        System.out.println(result);  
    }
 
    public static int primMST(int[][] matrizAdy) 
    { 
        int[] nodosVisitados = new int[matrizAdy.length];
        int[] nodosNoVisitados = new int[matrizAdy.length];
        int cont = 0;
		
        for(int i = 0; i < matrizAdy.length; i++)
        { // Inicializa los vectores de nodos visitados y los no visitados.
            nodosVisitados[i] = 0;     //0,0,0
            nodosNoVisitados[i] = i+1; //1,2,3
        } 			 
        if(matrizAdy.length != 0)
        {
            nodosVisitados[0] = 1;    //1,0,0
            nodosNoVisitados[0] = 0;  //0,2,3 
        }
        while(!todosNodosVisitados(nodosVisitados))
        { 
            int[] aux = new int[3];               
            aux[0] = -1;
            aux[1] = -1;
            aux[2] = Integer.MAX_VALUE;

            for (int i=0; i < nodosNoVisitados.length; i++)
            {            
                if (nodosVisitados[i] != 0)
                {
                    for (int j=0; j < nodosNoVisitados.length; j++)
                    {
                        if (nodosNoVisitados[j] != 0)
                        {
                            if(i != j)//buscar el minimo!!
                            {	
                                if(matrizAdy[i][j] > 0 && matrizAdy[i][j] < aux[2])
                                {
                                    aux[0] = i;
                                    aux[1] = j;
                                    aux[2] = matrizAdy[i][j];
                                }
                            }
                        }
                    }
                }
            }
            cont = cont + aux[2];                                // EJ: (1,3) = min = 2
            nodosVisitados[aux[1]] = nodosNoVisitados[aux[1]];   //1,0,3    Visitado    	           
            nodosNoVisitados[aux[1]] = 0;                        //0,2,0   NO-Visitado	            
        }// while-end
        return cont;
    }
    
    public static boolean todosNodosVisitados(int[] nodosVisitados)
    {
        for (int i=0; i < nodosVisitados.length; i++)
            if (nodosVisitados[i] == 0)
                return false;
        return true;
    }
}