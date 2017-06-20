/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torreshanoi;

import java.util.Stack;

/**
 *
 * @author Liam-Portatil
 */
public class Tower {

    /**
     * Global parameters
     */
    private Stack tower;

    /**
     * Constructor which will create a tower with the number of pieces sent
     * through parameter
     *
     * @param n
     */
    public Tower(int n) {
        tower = new Stack<>();
        for (int con = n; con > 0; con--) {
            tower.add(new Piece(con));
        }
    }

    /**
     * Constructor that creates an empty tower
     */
    public Tower() {
        tower = new Stack<Piece>();
    }

    /**
     * Prints the posisitons of the pieces in the tower. For debbuging reasons
     *
     * @return
     */
    @Override
    public String toString() {
        String result = "";
        if (!tower.isEmpty()) {
            for (int con = 0; con < tower.size(); con++) {
                result = result + tower.get(con).toString();
            }
        } else {
            result = "Empty";
        }

        return result + "\n";
    }

    /**
     * Getter of a piece of the tower
     *
     * @return
     */
    public Piece popPiece() {
        if (tower.isEmpty()) {
            System.out.println("Empty tower, returning null");
            return null;
        }
        return (Piece) tower.pop();
    }

    /**
     *
     * @param p
     */
    public void pushPiece(Piece p) {
        tower.push(p);
    }

    /**
     * Returns size of stack
     *
     * @return
     */
    public int getSize() {
        return tower.size();
    }

    /**
     * Returns if empty
     *
     * @return
     */
    public boolean isEmpty() {
        return tower.isEmpty();
    }

    /**
     * Views top piece
     *
     * @return
     */
    public Piece view() {
        if (tower.isEmpty()) {
            return new Piece();
        }
        return (Piece) tower.get(tower.size() - 1);
    }

    public int[] getArray() {
        int[] aux = new int[tower.size()];
        Piece paux = null;
        for (int con = 0; con < tower.size(); con++) {
            if (!tower.isEmpty()) {
                paux = (Piece) tower.get(con);
                aux[con] = paux.Size();
            }
        }
        return aux;
    }

    public void setArray(int[] aux) {
        tower = new Stack<Piece>();
        if (aux != null) {
            for (int con = 0; con < aux.length; con++) {
                tower.push(new Piece(aux[con]));
            }
        }

    }
}
