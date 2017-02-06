package Practica3;

import java.util.Scanner;
import tree.LinkedBinaryTree;
import tree.Position;

public class Adivino
{
    Scanner scan = new Scanner(System.in);
    LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
    String obj = null;
    
    public void jugar()
    { 
        Position<String> current = null;  
        String findAnswer = "no";
        String answer = begin();
        
        while(!answer.equalsIgnoreCase("salir"))
        {
            if (!tree.isEmpty())
            {
                System.out.println("Es un " + current.element() + "?");
                findAnswer = scan.nextLine();
            }
            System.out.println("¿Que "+obj+" has pensando?");
            String animal = scan.nextLine();
            if (tree.isEmpty())
                current = tree.addRoot(animal);
            else
            {                    
                while(!tree.isLeaf(current)) 
                {
                    if (isTrue(current.element()))
                        current = tree.right(current);
                    else
                        current = tree.left(current);

                    System.out.println("Es un " + current.element() + "?");
                    findAnswer = scan.nextLine();
                }                         
            }
            
            if (findAnswer.equalsIgnoreCase("si"))
            {
                answer = "salir";
                System.out.println("ENCONTRADO!! :) FIN.");
            }
            System.out.println("Piensa en un "+obj+" y pulsa una tecla o escribe salir.");
            answer = scan.nextLine();            
        }
    }

    public String begin()
    {
        System.out.println("¿Con qué tipo de objeto vamos a jugar?");
        obj = scan.nextLine();
        System.out.println("Piensa en un "+obj+" y pulsa una tecla o escribe salir.");
        String answer = scan.nextLine();
        return answer;
    }

    //True when it´s "si" the element.
    public boolean isTrue(String element)
    {
        return (element.equalsIgnoreCase("si"));
    }

    public Position<String> addObject(Position<String> current)
    {
        Position<String> pNewAnimal = null;
        String newAnimal = null;
        System.out.println("¿Que animal has pensando?");
        newAnimal = scan.nextLine();
        pNewAnimal = tree.insertRight(current, newAnimal);
        System.out.println("Escribe una pregunta con respuesta afirmativa para un "+ newAnimal +" y negativa para un " + current.element());
        String newQuestion = scan.nextLine();
        Position<String> pQuestion = tree.insertLeft(current, newQuestion);		
        tree.swapElements(current, pQuestion); 
        return pNewAnimal;
    }
}