/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torreshanoi;

/**
 *
 * @author Liam-Portatil
 */
public class Piece {
    
    /**
     * Global parameters
     */
    private int length;

    /**
     * Piece constructor that sets the size 
     * @param length 
     */
    public Piece(int length) {
        this.length = length;
    }

    /**
     * Construcotr with no parameters in case of empty object needed
     */
    public Piece() {
        this.length = 0;
    }
    
    /**
     * Getter that returns 
     * @return length
     */
    public int Size(){
        return length;
    }
    
    /**
     * Standard setter with will probably not be used
     * @param length 
     */
    public void setSize(int length){
        this.length = length;
    }
    
    /**
     * Method that retorns if one piece is bigger that another one
     * @param p
     * @return 
     */
    public boolean isLower(Piece p){
        if(p.Size() == 0){
            return true;
        }
        return p.Size() > this.length;
    }

    /**
     * toString that prints the size. For debbuging reasons
     * @return 
     */
    @Override
    public String toString() {
        return "Piece{" + "length=" + length + '}';
    }
    
    
}
