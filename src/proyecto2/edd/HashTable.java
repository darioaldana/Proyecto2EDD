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
    Arbol t = new Arbol(); 
    
    public HashTable(int size){
        this.size = size; 
        this.tabla = new Nodo[size]; 
        for (int i = 0; i < size; i++) {
            this.tabla[i] = null; 
        }
    }
    
    /**
     * Description: Es la función que devuelve el valor de
     *      la posición que tendrá el valor almacenado en 
     *      la tabla en cuestión. 
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
    
    /**
     * Description: Crea la HashTable y ejecuta (con la función
     *      fillHashTablePreOrder) un recorrido en preorden para
     *      determinar si hay nodos que no han sido ingresados. 
     * 
     * @param root, nodo raíz
     * @param s, objeto HashTable
     */
    public void createHashTable(Nodo root, HashTable s){
        System.out.println("\n-----Abrimos HashTable");
        t.fillHashTablePreOrder(root, s);
        System.out.println("\n-----Cerramos HashTable");
    }

    
    /**
     * Description: inserta el nombre de un animal en la tabla, 
     *      utiliza la función de hashing para determinar la 
     *      posición del animal y tiene un boolean para saber si existe. 
     *      Además, con nodos auxiliares para solucionar colisiones. 
     * 
     * @param animal: valor que se agregará. 
     */
    public void insertar(String animal){
        int posicion = hashing(animal); 
        boolean existe = false; 
        if (this.tabla[posicion] != null){ 
            // si la posicion no está vacía, recorro los elementos
            //enlistados en esa misma posición. 
            
            Nodo temp = this.tabla[posicion];
            
            if (temp.getData().equals(animal)){
                existe = true; 
                System.out.println("    Ya existe. No se agrega.");
                
            }
            
            while (temp.getNext() != null){
                temp = temp.getNext(); 
                if (temp.getData().equals(animal)){
                    existe = true;
                    System.out.println("    Ya existe. No se agrega.");
                }
            }
            
            //aquí se agrega el nodo si y sólo si 
            //no se encuentra en la tabla anteriormente
            
            if (!existe){
                Nodo nuevo = new Nodo(animal); 
                temp.setNext(nuevo); 
                System.out.println("    Añadimos: " + animal);
                System.out.println("        En posición: " + posicion);
            }
            
        } else {
            Nodo nuevo = new Nodo(animal); 
            this.tabla[posicion] = nuevo;  
            System.out.println("    Añadimos: " + animal);
            System.out.println("        En posición: " + posicion);
        }
        
    }
    
    /**
     * Description: Función que busca en la HashTable. 
     *      Utiliza la función hashing para determinar el
     *      índice e inmediatamente busca en la posición
     *      en cuestión. 
     * 
     * @param animal: valor buscado
     * 
     * @return: nodo con el valor buscado o null, depende de si
     *      éste existe en la HashTable. 
     */
    public Nodo buscar(String animal){
        boolean existe = false; 
        int posicion = hashing(animal); 
        
        if (this.tabla[posicion] == null){
            return null;
        }
        
        Nodo temp = this.tabla[posicion];
        
        if (temp.getData().equals(animal)){
            existe = true; 
        } else {
            while (temp.getNext() != null && !existe){
                if (temp.getData().equals(animal)){
                    existe = true; 
                } else {
                    temp = temp.getNext(); 
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
