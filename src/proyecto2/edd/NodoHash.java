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
public class NodoHash {
    private NodoHash next; 
    private String name; 
    
    public NodoHash(String nombre){
        this.next = null; 
        this.name = nombre; 
    }

    /**
     * @return the next
     */
    public NodoHash getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(NodoHash next) {
        this.next = next;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
