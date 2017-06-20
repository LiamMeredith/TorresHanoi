/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torreshanoi;

/**
 * Object that stores the current status in a Pile
 * @author Liam
 */
public class Status {
    
    /**
     * Global parameters
     */
    int[][] status;
    int st;

    /**
     * Constructor
     * @param status
     * @param st 
     */
    public Status(int[][] status, int st) {
        this.status = status;
        this.st = st;
    }

    /**
     * Getters and Setters
     * @return 
     */
    
    public int[][] getStatus() {
        return status;
    }

    public void setStatus(int[][] status) {
        this.status = status;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }
    
}
