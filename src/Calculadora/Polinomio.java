/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import static java.lang.Math.abs;

/**
 *
 * @author logra
 */
public class Polinomio {
    
    Monomio inicio = null;
      
     public void insertar(int coeficiente, int exponente){
        
        Monomio nuevo = crearMonomio(coeficiente, exponente);
        
        if(inicio == null){
            inicio = nuevo;
        }
        else if(inicio.exponente <= nuevo.exponente){
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        else{
            Monomio ant = null;
            Monomio aux = inicio;
            
            while((aux != null) && (aux.exponente > nuevo.exponente)){
                ant = aux;
                aux = aux.siguiente;
            }
            
            ant.siguiente = nuevo;
            nuevo.siguiente = aux;
        }
    }
    
    
    public void simplificar(){
        
        if(inicio.siguiente != null){
            
            Monomio aux = inicio;
            while(aux != null && aux.siguiente != null){
                if(aux.siguiente.exponente == aux.exponente){
                    aux.coeficiente += aux.siguiente.coeficiente;
                    aux.siguiente = aux.siguiente.siguiente;
                }else{
                    aux = aux.siguiente;
                }
            }
        }
    }
    
    public Polinomio sumar(Polinomio a, Polinomio b){
        
        a.simplificar();
        b.simplificar();
        
        Polinomio c = new Polinomio();
        
        Monomio aux_a = a.inicio;
        Monomio aux_b = b.inicio;
        
        while(aux_a != null && aux_b != null){
            if(aux_a.exponente > aux_b.exponente){
                c.insertar(aux_a.coeficiente, aux_a.exponente);
                aux_a = aux_a.siguiente;
            }
            else if(aux_b.exponente > aux_a.exponente){
                c.insertar(aux_b.coeficiente, aux_b.exponente);
                aux_b = aux_b.siguiente;
            }
            else{
                int coef;
                coef = aux_a.coeficiente + aux_b.coeficiente;
                c.insertar(coef, aux_a.exponente);
                aux_a = aux_a.siguiente;
                aux_b = aux_b.siguiente;
            }
        }
        
        if(aux_a == null){
            while(aux_b != null){
                c.insertar(aux_b.coeficiente, aux_b.exponente);
                aux_b = aux_b.siguiente;
            }
        }
        else if(aux_b == null){
            while(aux_a != null){
                c.insertar(aux_a.coeficiente, aux_a.exponente);
                aux_a = aux_a.siguiente;
            }
        }
        
        c.simplificar();
        
        return c;
    }
    
    
    public Polinomio restar(Polinomio a, Polinomio b){
        
        a.simplificar();
        b.simplificar();
        
        Polinomio c = new Polinomio();
        
        Monomio aux_a = a.inicio;
        Monomio aux_b = b.inicio;
        
        while(aux_a != null && aux_b != null){
            if(aux_a.exponente > aux_b.exponente){
                c.insertar(0 - aux_a.coeficiente, aux_a.exponente);
                aux_a = aux_a.siguiente;
            }
            else if(aux_b.exponente > aux_a.exponente){
                c.insertar(0 - aux_b.coeficiente, aux_b.exponente);
                aux_b = aux_b.siguiente;
            }
            else{
                int coef;
                coef = aux_a.coeficiente - aux_b.coeficiente;
                c.insertar(coef, aux_a.exponente);
                aux_a = aux_a.siguiente;
                aux_b = aux_b.siguiente;
            }
        }
        
        if(aux_a == null){
            while(aux_b != null){
                c.insertar(aux_b.coeficiente, aux_b.exponente);
                aux_b = aux_b.siguiente;
            }
        }
        else if(aux_b == null){
            while(aux_a != null){
                c.insertar(aux_a.coeficiente, aux_a.exponente);
                aux_a = aux_a.siguiente;
            }
        }
        
        c.simplificar();
        return c;
    }
    
    public Polinomio multiplicar(Polinomio a, Polinomio b){
        
        a.simplificar();
        b.simplificar();
        
        Polinomio c = new Polinomio();
        Monomio aux_a = a.inicio;
        Monomio aux_b = b.inicio;
        
        int coef, exp;
        
        while(aux_a != null){
            while(aux_b != null){
                coef = aux_a.coeficiente * aux_b.coeficiente;
                exp = aux_a.exponente + aux_b.exponente;
                c.insertar(coef, exp);
                
                aux_b = aux_b.siguiente;
            }
            aux_a = aux_a.siguiente;
            aux_b = b.inicio;
        }
         
        c.simplificar();
        return c;
    }
    
    public Polinomio multiplicarpormono(Monomio a, Polinomio b){
        
        b.simplificar();
        Polinomio c = new Polinomio();
        
        Monomio aux = b.inicio;
        int coef, exp;
        
        while(aux != null){
            coef = a.coeficiente * aux.coeficiente;
            exp = a.exponente + aux.exponente;
            c.insertar(coef, exp);
            
            aux = aux.siguiente;
        }
        
        c.simplificar();
        return c;
    }
    
    public Polinomio dividir(Polinomio a, Polinomio b){
        
        a.simplificar();
        b.simplificar();
        
        Polinomio tiene = a;
        Polinomio recipiente = new Polinomio();
        Polinomio quita = new Polinomio();
        
        
        while(b.inicio.exponente < tiene.inicio.exponente){
            Monomio divisor = new Monomio();
            divisor.coeficiente = tiene.inicio.coeficiente / b.inicio.coeficiente;
            divisor.exponente = tiene.inicio.exponente - b.inicio.exponente;
            recipiente.insertar(divisor.coeficiente, divisor.exponente);
            
            quita = quita.multiplicarpormono(divisor, b);
            quita.inicio = quita.inicio.siguiente;
            tiene.inicio = tiene.inicio.siguiente;

            Monomio aux = tiene.inicio;
            
            while(quita.inicio != null){
                if(quita.inicio.exponente == aux.exponente){
                    aux.coeficiente -= quita.inicio.coeficiente;
                    aux = aux.siguiente;
                    quita.inicio = quita.inicio.siguiente;
                }
                else{
                    aux = aux.siguiente;
                }
                
            } 
        }
        
        Monomio divisor = new Monomio();
        divisor.coeficiente = tiene.inicio.coeficiente / b.inicio.coeficiente;
        divisor.exponente = tiene.inicio.exponente - b.inicio.exponente;
        recipiente.insertar(divisor.coeficiente, divisor.exponente);

        return recipiente;
        
    }
    
    
    public void recorrer(){
        Monomio aux = inicio;
        
        if(inicio.exponente > 1){
            System.out.print(aux.coeficiente + "x^" + aux.exponente);
        }else if(inicio.exponente == 0){
            System.out.print(aux.coeficiente);
        }
        else if(inicio.exponente == 1){
            System.out.print(aux.coeficiente + "x");
        }
        aux = aux.siguiente;
        
        while(aux != null){

            if(aux.exponente > 1 && aux.coeficiente >= 0){
                System.out.print("+" + aux.coeficiente + "x^" + aux.exponente);
            }
            else if(aux.exponente > 1 && aux.coeficiente < 0){
                System.out.print(aux.coeficiente + "x^" + aux.exponente);
            }
            else if(aux.exponente == 0 && aux.coeficiente < 0){
                System.out.print(aux.coeficiente);
            }
            else if(aux.exponente == 0 && aux.coeficiente > 0){
                System.out.print("+" + aux.coeficiente);
            }
            else if(aux.coeficiente > 0){
                System.out.print("+" + aux.coeficiente + "x");
            }
            else{
                System.out.print(aux.coeficiente + "x");
            }
            
            aux = aux.siguiente;
        }
    }

    Monomio crearMonomio(int coef, int exp){
        Monomio nuevo = new Monomio();
        nuevo.coeficiente = coef;
        nuevo.exponente = exp;
        nuevo.siguiente = null;
        return nuevo;
    }
    
}
