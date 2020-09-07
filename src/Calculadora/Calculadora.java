/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;
import java.io.*;
import java.util.Scanner;


/**
 *
 * @author logra
 */
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Polinomio a = new Polinomio();
        Polinomio b = new Polinomio();
        Polinomio c = new Polinomio();
        
        a.insertar(6, 5);
        a.insertar(23, 4);
        a.insertar(-5,3);
        a.insertar(-14, 2);
        a.insertar(-35,1);
        a.insertar(20, 0);
        
        b.insertar(3, 3);
        b.insertar(1, 2);
        b.insertar(-5, 0);
        

        
        /*c = c.restar(b, a);
        c.recorrer();
        
        System.out.println("\n");
        
        c = c.sumar(b, a);
        c.recorrer();*/
        
        c = c.dividir(a, b);
        c.recorrer();
        
        
    }
    
}
