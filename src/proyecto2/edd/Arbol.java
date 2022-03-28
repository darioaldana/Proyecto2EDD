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
public class Arbol {
    Nodo root; 
    
    public Arbol() {
        this.root = null; 

        }
    
    public Nodo getRoot() {
	return root;
    }
    
    public boolean esVacio(Nodo n){
            return n == null; 
    }
    
    public void insertRoot(String data) {
        this.root = new Nodo(data);
    }
    
    public void insertarIzq(String padre, String valorNuevo){
        /**
         * Con la función buscar se encuentra el nodo padre, 
         * después, se agrega al hijo a ese nodo padre. 
         */
        
        Nodo n = buscar(root, padre);
        Nodo hijoIzq = new Nodo(valorNuevo); 
        n.setLeft(hijoIzq);
        System.out.println("    IZQ  " + hijoIzq.getData() + "\n");
    }
    
    public void insertarDer(String padre, String valorNuevo){
        /**
         * Con la función buscar se encuentra el nodo padre, 
         * después, se agrega al hijo a ese nodo padre. 
         */

        Nodo n = buscar(root, padre);
        Nodo hijoDer = new Nodo(valorNuevo); 
        n.setRight(hijoDer);
        System.out.println("PADRE  " + n.getData());
        System.out.println("    DER  " + hijoDer.getData());
    }
   
    public Nodo buscar(Nodo n, String key){
        Nodo found = null; 
        if (n == null)
            return null;
        if (n.getData().equals(key))
            return n;
        if (n.getLeft() != null)
            found = buscar(n.getLeft(), key);
        if (found == null)
            found = buscar(n.getRight(), key);
        return found; 
    }
    

    /**
     * 
     * @param db [String] mega string con la info
     *          del txt separada por líneas. 
     */
    public void createTree(String db){
        
        String[] dbLines = db.split("\n"); 
        /**
         * dbLines es un array de Strings, cada elemento
         * es una línea de la base de datos. 
         */
        
        for (int i = 0; i < dbLines.length; i++) {
            /**
             * lineInfo será un array de tres elementos: 
             * los tres elementos de cada línea del txt. 
             */

            String[] lineInfo; 
            lineInfo = dbLines[i].split(", ");
            
            //Si el arbol es vacío, se agrega la raíz. 
            if (esVacio(getRoot())){
                insertRoot(lineInfo[0]); 
            } 
            System.out.println(i + "---"+dbLines[i]);
            
            //Se agregan ambos hijos. 
            insertarDer(lineInfo[0], lineInfo[2]);
            insertarIzq(lineInfo[0], lineInfo[1]);
            
        }
    }
    
    public void PreOrder(Nodo n, HashTable s){
        if (n == null)
            return;
        if (n.getLeft() == null && n.getRight() == null){
            System.out.println("\n########Añadimos:   " + n.getData());
            s.insertar(n.getData());
        }
        
        PreOrder(n.getLeft(), s);
        PreOrder(n.getRight(), s);
    }
    
}
