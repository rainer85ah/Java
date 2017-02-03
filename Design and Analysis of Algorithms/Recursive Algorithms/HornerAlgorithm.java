import java.io.*;

public class hornerAlgorithms
{
    public static void main(String args[]) throws Exception 
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        
        int grado = Integer.parseInt(scan.readLine());
        double coefi[] = new double[grado+1];
        
        String[] strNum = scan.readLine().split(" ");        
        for (int i=0; i < coefi.length; i++)
            coefi[i] = Double.parseDouble(strNum[grado-i]);
        
        double realX = Double.parseDouble(scan.readLine());
        //System.out.println(); // MooSHAK
        
        double cont = recursiveFunction(coefi, 0, realX);
        System.out.format("%.3f%n",cont);    	
    }

    public static double recursiveFunction(double[] coefi, int i, double realX)
    {
        if (i == coefi.length-1)
            return coefi[coefi.length-1];
        else
            return coefi[i] + realX*recursiveFunction(coefi, ++i, realX);
    }
}
