import java.io.*;

public class DivideConquer
{
    public static void main(String args[]) throws Exception 
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));	  
        int n = Integer.parseInt(scan.readLine());
		int V[] = new int[n];
		
		String[] strNum = scan.readLine().split(" ");		
		for (int i=0; i < V.length; i++)
            V[i] = Integer.parseInt(strNum[i]);
		
        int index = divideConquer(V);
        System.out.println(index);  
    }
	
    //binary search.
    public static int divideConquer(int[] V) 
    {
		//Worst-case performance 		O(log n)
		//Best-case performance 		O(1)
		//Average performance 			O(log n)
		//Worst-case space complexity 	O(1)
        int low = 0;
        int high = V.length-1;
		
        while (low <= high) 
	{     
            int med = (low + high)/2;
            if (med == V[med])
                return med;
            else if (med > V[med])   
		low = med + 1;
            else
                high = med - 1;   
        }
        return -1;
    }
}
