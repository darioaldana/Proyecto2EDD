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
    
    public int getHeight(Nodo root) {
		if (root == null)
			return 0;
		return Math.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1;
	}
    
    
    
    public int getLevel(Nodo current,String key,int counter){
        
        int x; 
        
        if(current == null){return 0;}
        
        if(current.getData().equals(key)){
            return counter;
        }

        x = getLevel(current.getLeft(),key, counter+1);

        if(x!=0){return x;}

        x = getLevel(current.getRight(),key,counter + 1);
        return x;
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
        System.out.println("        IZQ:  " + hijoIzq.getData() + "\n");
    }
    
    public void insertarDer(String padre, String valorNuevo){
        /**
         * Con la función buscar se encuentra el nodo padre, 
         * después, se agrega al hijo a ese nodo padre. 
         */

        Nodo n = buscar(root, padre);
        Nodo hijoDer = new Nodo(valorNuevo); 
        n.setRight(hijoDer);
        System.out.println("    PADRE:  " + n.getData());
        System.out.println("        DER:  " + hijoDer.getData());
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
        
        System.out.println("\n\n ----------\nComenzamos a crear árbol\n");
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
            
            System.out.println("Línea " + i + ":  "+dbLines[i]);
            
            //Se agregan ambos hijos. 
            insertarDer(lineInfo[0], lineInfo[2]);
            insertarIzq(lineInfo[0], lineInfo[1]);
            
        }
        System.out.println("Terminamos de crear arbol\n----------\n");
    }
    
    public void fillHashTablePreOrder(Nodo n, HashTable s){
        if (n == null)
            return;
        if (n.getLeft() == null && n.getRight() == null){
            System.out.println("\n  Evaluamos para añadir:   " + n.getData());
            s.insertar(n.getData());
        }
        
        fillHashTablePreOrder(n.getLeft(), s);
        fillHashTablePreOrder(n.getRight(), s);
    }
    
    public void agregarAnimal(Nodo hoja, String diferencia, String animalName, boolean rightChild){
        Nodo newAnimal = new Nodo(animalName);
        Nodo moveAnimal = new Nodo(hoja.getData());
        hoja.setData(diferencia);
        
        if (rightChild){
            hoja.setRight(newAnimal);
            hoja.setLeft(moveAnimal);
            System.out.println("\n Añadimos derecha: ");
        }else {
            hoja.setRight(moveAnimal);
            hoja.setLeft(newAnimal);
            System.out.println("\n Añadimos izquierda: ");
        }
        
        PreOrder(this.root);
        
    }
    
    public void PreOrder(Nodo n){
		if (n == null)
			return;
                System.out.println(n.getData());
		PreOrder(n.getLeft());
		PreOrder(n.getRight());
	}

    void inicializar() {
        this.root = null; 
    }
    
    public String printPreOrder(Nodo n, StringBuilder sb, String answer){
        if (n != null){
            sb.append(answer);
            sb.append(n.getData());
            sb.append("/");
           
            printPreOrder(n.getLeft(), sb, "No: ");
            printPreOrder(n.getRight(), sb, "Sí: ");
          
            return sb.toString(); 
        } else {
            return null; 
        }
    }
    
    public String print(String preorden){
        
        String[] nodos = preorden.split("/");
        int indent; 
        Nodo n;
        String finalStr = "";
        String prefix = ""; 
        String key = "";
       
        for (int i = 0; i < nodos.length; i++) {

            key = nodos[i];
            
            if (i!=0){
                prefix = "";
                for (int j = 0; j < 4; j++) {
                    prefix = prefix + key.charAt(j);
                }
            } else {
                String s = Character.toString(key.charAt(0));
                nodos[i] = key.replace(s, s.toUpperCase());
            }
            
            
            key = key.replace(prefix, "");
            indent = getLevel(root, key, 1)-1;
            finalStr = finalStr + ("│        ".repeat(indent)) + nodos[i] + "\n"; 

        }
        
        return finalStr; 
        
    }
  
}
