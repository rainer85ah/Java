import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Rainer Arencibia Hernandez;
 * Practica 1.2 HashMap y TreeMap
 */
public class Main 
{
    public static void main(String[] args) throws IOException 
    {
        Map<String, Map<String, Integer>> infoNews = new HashMap<>();
        System.out.println("Analizando Noticias..");
        topicList(infoNews); 
        System.out.println("Analisis Terminado.");
        System.out.println("Mostrando Entidades y Titulos: ");
        comunNews(infoNews);
        System.out.println("Fin..");
    }
    
    private static void topicList(Map<String, Map<String, Integer>> infoNews) throws IOException 
    {
        File file = new File("C:\\Users\\noroot\\Documents\\URJC\\CURSO 3\\ESTRUCTURA DE DATOS AVANZADOS\\PRACTICAS\\PRACTICA 1.2\\data\\news");       
        try 
        {
            addInfoNewsxTitulo(file, infoNews);
        }catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void addInfoNewsxTitulo(File file, Map<String, Map<String, Integer>> infoNews) throws FileNotFoundException, IOException 
    {          
        Map<String, Integer> entidades = new HashMap<>(); 
        Set<String> stopList = setStopList();
                
        for (File iFile : file.listFiles()) 
        {
            if (iFile.isFile()) 
            {
                try 
                {
                    FileInputStream fis = new FileInputStream(iFile);
                    DataInputStream dis = new DataInputStream(fis);
                    BufferedReader buff = new BufferedReader(new InputStreamReader(dis));
                    String line = null, titulo = null;
                    int contLine = 0;
                    boolean esta = false;
                    
                    while((line = buff.readLine()) != null)
                    {
                        if (contLine == 0)
                            titulo = line;
                        
                        for (String word : line.split(" "))
                        {
                            if (Character.isUpperCase(word.charAt(0)))
                            {// Busca cada palabra en el fichero StopList
                                esta = wordsFilter(stopList,word);
                                if (!esta)
                                {
                                    if(entidades.containsKey(word))
                                        entidades.put(word, entidades.get(word) + 1);
                                    else 
                                        entidades.put(word, 1);
                                }
                            }
                        }
                        contLine++;
                    }
                    Map<String, Integer> sortedEntities = new HashMap<>();
                    sortedEntities = orderingEntitys(entidades);
                    infoNews.put(titulo, sortedEntities);
                    contLine=0;
                } 
                catch (FileNotFoundException e) {} 
                catch (IOException e) {} 
            }//End of 1File
        }//End of For; next File..
    }
	
    private static boolean wordsFilter(Set<String> filtrado, String palabra) throws IOException 
    {
        boolean find = false;
        
        if (filtrado.contains(palabra))
            find = true;
        else
            find = false;
        return find;
    }
	
    //Show Entities from more popular than less popular.
    private static void comunNews(Map<String, Map<String, Integer>> infoNews)
    {	
        for(String iTitulo : infoNews.keySet())
        {
            System.out.println(iTitulo);
            for(String iNombreEnt : infoNews.get(iTitulo).keySet())
            {
                if (infoNews.get(iTitulo).keySet().contains(iNombreEnt))
                    System.out.println(iNombreEnt);  
            }
        }
    }    
    
    /* Funcion que ordena las ENTIDADES del HashMap en un TreeMap */
    private static Map<String, Integer> orderingEntitys(Map<String, Integer> entidades)
    {
        List<String> list = new ArrayList(entidades.entrySet());
        Collections.sort(list, new Comparator() 
        {
            @Override
            public int compare(Object o1, Object o2) 
            {
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });	
        
        Map result = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext();) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private static Set<String> setStopList() throws IOException 
    {
        Set<String> filtrado = null;
        final File fileStopList = new File("C:\\Users\\Owner\\Documents\\MEGA\\Dropbox\\Dropbox\\URJC\\CURSO 3\\ESTRUCTURA DE DATOS AVANZADOS\\PRACTICAS\\PRACTICA 1.2\\data\\ES_stopList.txt");       
        final FileInputStream fisStopList = new FileInputStream(fileStopList);
        final DataInputStream disStopList = new DataInputStream(fisStopList);
        final BufferedReader buffStopList = new BufferedReader(new InputStreamReader(disStopList));
        String line;
        
        while((line = buffStopList.readLine()) != null)
        {
            for (String word : line.split(" "))
            {
                filtrado.add(word);
            }
        }     
        return filtrado;
    }
}