import Practica1.LinkedTree;
import Practica1.LinkedTree.TreeNode;
import Practica1.LinkedTreePlus;
import Practica1.Position;
import Practica1.T;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main 
{         
    public static void main(String[] args) 
    {
        // TODO code application logic here                     
        ejercicio4();
    }

    private static void ejercicio4() 
    {   
        //LinkedTree tree = new LinkedTree();
        LinkedTreePlus tree = new LinkedTreePlus();
        System.out.println("Introduzca el nombre del directorio a analizar: ");
        Scanner scan = new Scanner (System.in);
        String url = scan.next();
        File f = new File(url);    
        long  totalSpace = f.getTotalSpace()/(1024*1024); 
        
        //Anade al arbol, los subdirectorios del especificado.
        addDirectoryToTree(tree,f);        
        
        //Muestra la info por consola
        lookInTheTree(tree,totalSpace);  
    }

    private static void addDirectoryToTree(LinkedTreePlus tree, File f) 
    {
        T datos = new T(null,0);          
        makeData(f,datos); 
        Position<T> pRoot = tree.addRoot(datos);      
        
        bucleAddFilesDirectories(tree,f,pRoot);        
        /*
        PRUEBAS.....
        System.out.println("Directorios agregados al arbol! Ficheros: " + tree.size());
        System.out.println("Profundidad: " + tree.depth());
        System.out.println("Grado Maximo: " + tree.degree());
        System.out.println("Hojas: " + tree.front());
        */
                
    }
    
    private static void makeData(File f, T datos) 
    {
        datos.setName(f.getName());
        datos.setSize(f.getTotalSpace()/(1024*1024)); 
    }    
    
    private static void bucleAddFilesDirectories(LinkedTreePlus tree, File f,Position<T> pRoot) 
    {
        File[] tablaDirHijos = f.listFiles();  
        long contSpace = 0;
        Stack<File> stkDir = new Stack<>();
        Stack<Position> stkPos = new Stack<>();
        Position<T> pAux = null;
        
        do
        {
            for (File fi : tablaDirHijos)
            {
                T newdata = new T(" ",0);
                makeData(fi,newdata);
                if (fi.isDirectory())
                {
                    pAux = tree.add(newdata, pRoot);
                    stkPos.push(pAux);
                    stkDir.push(fi);
                }else if (fi.isFile())
                    tree.add(newdata, pRoot);
                
                contSpace = contSpace + fi.getTotalSpace()/(1024*1024);
            }   
            f = stkDir.pop();
            tablaDirHijos = f.listFiles();          
            pRoot = stkPos.pop();
        }while(!stkDir.isEmpty() && !stkPos.isEmpty());
    }
    
    private static void lookInTheTree(LinkedTree tree, long  totalSpace) 
    {
        Scanner scan = new Scanner(System.in);
        TreeNode nodeRoot = (TreeNode) tree.root();
        T datos = new T("",0);
        datos = (T) nodeRoot.getElement();
        List<TreeNode> hijos = new ArrayList<>();
        int j=0, cont=0;
      
        do
        {
            hijos = nodeRoot.getChildren();
            System.out.println(datos.getName() + " - " + datos.getSize()/1024 + " MB - " +  datos.getSize()/totalSpace + "%");
            
            for (TreeNode iNode : hijos)
            {
                datos = (T) iNode.getElement();
                System.out.println(cont++ + " - " + datos.getName() + " - " + datos.getSize()/1024 + " MB - " +  datos.getSize()/totalSpace + "%");
            }
            
            System.out.println("Introduzca el número del subdirectorio a explorar o 0 para salir: ");       
            j = scan.nextInt();
       
            if(j == -1)// Sube al padre sino es la raiz
            {
                if (nodeRoot != (TreeNode)tree.root())
                    nodeRoot = (TreeNode) nodeRoot.getParent();
            }
            if (j > 1)
                nodeRoot = hijos.get(j); 
            datos = (T) nodeRoot.getElement();
            cont=0;
        }while (j != 0); //(j==0) termina! 
    }
}
//larabida.etsii.urjc.es