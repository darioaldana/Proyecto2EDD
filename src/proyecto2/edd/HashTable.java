/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.edd;

/**
 *
 * @author dario
 */

public class HashTable {
    Nodo tabla[];  //array de nodos
    int size; //número primo (se tomó 10111)
    
    public HashTable(int size){
        this.size = size; 
        this.tabla = new Nodo[size]; 
        for (int i = 0; i < size; i++) {
            this.tabla[i] = null; 
        }
    }
    
    
    /**
     * 
     * @param animal [String]: va a hacer las funciones
     *          de clave del objeto guardado en la tabla. 
     * 
     * @return index [int]: índice del objeto en el arreglo de nodos
     *          para la HashTable; es la posición del elemento en
     *          ese array. 
     */
    public int hashing(String animal){
        
        int valor = 0;  
        //valor de la clave (nombre del animal) en 
        //código ASCII a ser modificado.
        
        int posicion = 1; 
        
        for (int i = 0; i < animal.length(); i++) {
            
            
            if (animal.codePointAt(i) == 32){
                valor += 0; 
                //En este caso no se suma nada, porque el código 
                //ASCII 32 hace referencia a un espacio (" "). 
                
            } else if (animal.codePointAt(i) >= 48 && animal.codePointAt(i) <= 57){
                valor += ((animal.codePointAt(i) - 47) * posicion);
                
            } else if (animal.codePointAt(i) >= 65 && animal.codePointAt(i)<= 90){
                valor += ((animal.codePointAt(i) - 54) * posicion);
                
            } else if (animal.codePointAt(i) >= 97 && animal.codePointAt(i) <= 122){
                valor += ((animal.codePointAt(i) - 60) * posicion);
                
            }
            posicion++; 
        }
        
        
        int index = valor % size;
        /**
         * Index se refiere al índice del array del elemento.
         * De esta manera adaptamos el valor del nombre del animal 
         * según código ASCII al tamaño de la tabla. 
         */
        return index; 
    }
    
    
    
    public void insertar(String animal){
        int posicion = hashing(animal); 
        boolean existe = false; 
        if (this.tabla[posicion] != null){ 
            // si la posicion no está vacía, recorro los elementos
            //enlistados en esa misma posición. 
            
            Nodo temp = this.tabla[posicion];
            
            if (temp.getData().equals(animal)){
                existe = true; 
                
            }
            
            while (temp.getNext() != null){
                temp = temp.getNext(); 
                if (temp.getData().equals(animal)){
                    existe = true; 
                }
            }
            
            //aquí se agrega el nodo si y sólo si 
            //no se encuentra en la tabla anteriormente
            
            if (!existe){
                Nodo nuevo = new Nodo(animal); 
                temp.setNext(nuevo); 
            }
            
        } else {
            Nodo nuevo = new Nodo(animal); 
            this.tabla[posicion] = nuevo;   
        }
    }
    
    public Nodo buscar(String animal){
        int posicion = hashing(animal); 
        Nodo temp = this.tabla[posicion]; 
        boolean existe = false; 
        if (temp.getNext() != null){
            if (temp.getNext() == null){
                existe = true; ////check checl checl 
            } else {
                while (temp.getNext() != null && !existe){
                    if (temp.getData().equals(animal)){
                        existe = true; 
                    } else {
                        temp = temp.getNext(); 
                    }
                }
            }
        }
        if (existe){
            return temp; 
        } else {
            return null; 
        }
    }

    public void vaciar(){
        for (int i = 0; i < size; i++) {
            this.tabla[i] = null; 
        }
    }
}
