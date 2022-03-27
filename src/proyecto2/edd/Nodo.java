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
public class Nodo {
    private Nodo left; 
    private Nodo right;
    private Nodo next; 
    private String data; 
    
    public Nodo(String data){
        this.next = null; 
        this.data = data; 
    }

    /**
     * @return the next
     */
    public Nodo getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Nodo next) {
        this.next = next;
    }

    /**
     * @return the name
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the name to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the left
     */
    public Nodo getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(Nodo left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public Nodo getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Nodo right) {
        this.right = right;
    }
    
    public boolean esHoja(){
        if (this.getRight()==null && this.getLeft()==null){
            return true;
        }
        return false;
    }
}

