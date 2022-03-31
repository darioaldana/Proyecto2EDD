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
    
    
    /**
     * Descripción: Empezando de la raíz, cuenta cuántas 
     *      relaciones padre-hijo pasan hasta llegar al 
     *      nodo del cual se desea saber el nivel. 
     * 
     * 
     * @param current: nodo examinado, variará su valor
     *          según el ambiente de iteración. 
     * @param key: valor del nodo del cual se desea saber
     *          su nivel.
     * @param counter: contador de cuantas relaciones padre-hijo
     *          se hallan antes de llegar al objetivo. 
     * 
     * @return x: devuelve el nivel del nodo aumentado en una unidad. 
     */
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
   
    /**
     * Description: Pasándole una clave, recorrerá el árbol
     *      recursivamente hasta encontrar el nodo que contiene
     *      a dicha clave. 
     * 
     * @param n: nodo examinado. 
     * @param key: clave buscada, valor dentro del nodo. 
     * @return found ó nodo: el nodo que contiene esa clave,
     *      si no se encuentra el nodo es null. 
     */
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
     * Descripción: Toma el mega string extraído de la 
     *      base de conocimientos y crea un array donde
     *      cada elemento es una línea, cada línea se convierte
     *      en un array también para asignar los valores de 
     *      padres e hijos (derecho o izquierdo) según corresponda. 
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
    
    
    /**
     * Descripción: Recorre el arbol en preorden para ir
     *      chequeando si los valores ya fueron agregados o
     *      no a la HashTable. 
     * 
     * @param n: nodo a evaluar, se examinará si su valor 
     *      se encuentra en la tabla de dispersión.
     * @param s: TDA de la HashTable
     */
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
    
    
    /**
     * Description: Cuando el adivino no logra su objetivo, 
     *      se llama a esta función para ampliar la base de
     *      conocimientos del juego. 
     * 
     * 
     * @param hoja: nodo que contiene el intento de adivinar 
     *          del Akinator (que fue incorrecto). 
     * 
     * @param diferencia: representa la diferencia entre
     *          la respuesta que arrojó el Akinator y la
     *          respuesta que dio el usuario. 
     * 
     * @param animalName: nombre del animal (respuesta correcta
     *          de la ronda dada por el usuario). 
     * 
     * @param rightChild: boolean que determina si el animal que se 
     *          añadirá a la derecha o a la izquierda, es decir,
     *          si cumple o no con la condición. 
     */
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
        
        System.out.println("\n--------------");
        System.out.println("Comprobación del nuevo árbol:  ");
        PreOrder(this.root);
        
    }
    
    /**
     * Description: recorrido de preorden sencillo con impresión
     *          en la terminal, usado para comprobar durante la 
     *          realización del programa. 
     * 
     * @param n: nodo raiz del árbol inicialmente, variará con 
     *          las recursiones
     */
    public void PreOrder(Nodo n){
		if (n == null)
			return;
                System.out.println(n.getData());
		PreOrder(n.getLeft());
		PreOrder(n.getRight());
	}

    
    /**
     * Description: Vacía el arbol. 
     */
    void inicializar() {
        this.root = null; 
    }
    
    
    /**
     * Description: Se genera el recorrido en preorden, donde cada
     *      valor es separado con "/". Además, se especifica quién
     *      es hijo izquierdo e hijo derecho con los prefijos "Si: " 
     *      y "No: " antes de imprimir el valor. 
     * 
     * @param n: nodo evaluado según el ambiente de la recursión. 
     * @param sb: StringBuilder donde se guardará todo el recorrido. 
     * @param answer: prefijo en cuestión, determina si es hijo izquierdo
     *          o derecho. 
     * 
     * @return recorrido entero en preorden del árbol. 
     */
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
    
    /**
     * Description: Se genera una lista multinivel a partir del 
     *      recorrido en preorden. Otra manera para ilustrar cómo
     *      es el árbol creado. 
     * 
     * @param preorden recorrido entero en preorden del árbol. 
     * 
     * @return finalStr: String que será mostrado en pantalla 
     *      tiene el formato de una lista multinivel. 
     */
    public String print(String preorden){
        
        String[] nodos = preorden.split("/");
        int indent;
        String finalStr = "";
        String prefix = ""; 
        String key = "";
       
        for (int i = 0; i < nodos.length; i++) {

            key = nodos[i];
            
            /** El siguiente condicional es porque la raíz no
             * tiene prefijo. Se define el prefijo que puede ser
             * "Si: " o "No: ", ambos de cuatro caracteres. 
             */
            if (i!=0){
                prefix = "";
                for (int j = 0; j < 4; j++) {
                    prefix = prefix + key.charAt(j);
                }
            } else {
                String s = Character.toString(key.charAt(0));
                nodos[i] = key.replace(s, s.toUpperCase());
            }
            
            /**
             * El prefijo sirve a la hora de imprimirlo, pero hay
             * que recordar que en el nodo del árbol como tal, esos
             * valores no llevan ese prefijo. Por ende, para determinar 
             * la sangría, se debe mandar la clave sin el prefijo; pero 
             * para crear el String que será mostrado en pantalla sí se 
             * usa el prefijo. 
             */
            key = key.replace(prefix, "");
            indent = getLevel(root, key, 1)-1;
            finalStr = finalStr + ("│        ".repeat(indent)) + nodos[i] + "\n"; 

        }
        
        return finalStr; 
        
    }
    
    /**
     * Description: Crea el archivo de texto que será guardado
     *      posteriormente. 
     * 
     * @param sb StringBuilder que almacena la información durante
     *          las recursiones para después utilizrla y agregarla
     *          a una base de datos. 
     * 
     * @param current Nodo que se está evaluando en el momento.
     * 
     * @return sb, el StringBuilder pero pasado a String.
     */
    public String createTxt(StringBuilder sb, Nodo current){

        String line = "";
        if (current.getLeft() != null && current.getRight() != null){
            line = line + current.getData() + ", "; 
            line = line + current.getLeft().getData() + ", ";
            line = line + current.getRight().getData() + ", ";
            sb.append("\n" + line); 
        }
 
        if (current.getLeft() != null){
            createTxt(sb, current.getLeft());
        }
        
        if (current.getRight() != null){
            createTxt(sb, current.getRight());
        }

        return sb.toString(); 
    }
  
}
