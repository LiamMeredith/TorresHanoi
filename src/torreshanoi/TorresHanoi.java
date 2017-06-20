/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torreshanoi;

import java.util.Arrays;
import java.util.Stack;

/**
 * Program the game,the towers of Hanoi. It finds the result using a pile and the posible solutions.
 * Simultaneously searches in the begining and at the end to find quicker the result when they meet at the middle.
 * 
 * @author Liam-Portatil
 */
public class TorresHanoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TorresHanoi();
    }

    /**
     * Global parameters
     */
    private Tower[] towers;
    private Stack<Status> Spila;
    private Stack<Status> vetados;
    private Stack<Status> SpilaINV;

    /**
     * Only constructor that intialazes the game
     */
    public TorresHanoi() {
        initial();
        algorythmInv();
        algorythm();
    }

    /**
     * Initial object instances
     */
    private void initial() {
        towers = new Tower[3];
        for (int con = 0; con < towers.length; con++) {
            towers[con] = new Tower();
        }
        towers[0] = new Tower(3);
        Spila = new Stack();
        SpilaINV = new Stack();
        vetados = new Stack();
    }

    /**
     * Algorythm that start from the result backwards to find a posible solution
     */
    private void algorythmInv() {
        vetados = new Stack();
        SpilaINV.add(new Status(sol, 0));
        for (int con = 0; con < towers.length; con++) {
            towers[con].setArray(sol[con]);
        }
        int st = 0;
        int[][] aux = null;
        Status ax = null;
        int c = 0;
        for (int cont = 0; cont < 10; cont++) {
            st = moveNext(st);
            if (valid(SpilaINV)) {
                SpilaINV.add(new Status(status(), st));
                st = 0;
            } else if (st < 6) {
                ax = SpilaINV.get(SpilaINV.size() - 1);
                aux = ax.getStatus();
                for (int con = 0; con < towers.length; con++) {
                    towers[con].setArray(aux[con]);
                }
            } else {
                vetados.add(new Status(status(), 0));
                ax = Spila.pop();
                aux = ax.getStatus();
                for (int con = 0; con < towers.length; con++) {
                    towers[con].setArray(aux[con]);
                }
                st = ax.getSt();
            }
        }
        System.out.println("Found!");
        printAll(SpilaINV);
    }

    /**
     * Algorythm that finds a way to resolve this game
     */
    private void algorythm() {
        towers = new Tower[3];
        for (int con = 0; con < towers.length; con++) {
            towers[con] = new Tower();
        }
        towers[0] = new Tower(3);
        vetados = new Stack();
        Spila.add(new Status(status(), 0));
        boolean found = false;
        int st = 0;
        int[][] aux = null;
        Status ax = null;
        int c = 0;
        while (!found) {
            st = moveNext(st);
            if (valid(Spila)) {
                Spila.add(new Status(status(), st));
                st = 0;
            } else if (st < 6) {
                ax = Spila.get(Spila.size() - 1);
                aux = ax.getStatus();
                for (int con = 0; con < towers.length; con++) {
                    towers[con].setArray(aux[con]);
                }
            } else {
                vetados.add(new Status(status(), 0));
                ax = Spila.pop();
                aux = ax.getStatus();
                for (int con = 0; con < towers.length; con++) {
                    towers[con].setArray(aux[con]);
                }
                st = ax.getSt();
            }
            if (solution()) {
                found = true;
            }
        }
        System.out.println("ENCONTRADO");
        printAll(Spila);
    }

    private boolean solution() {
        for (int con = SpilaINV.size() - 1; con > -1; con--) {
            if (Arrays.deepEquals(SpilaINV.get(con).getStatus(), Spila.get(Spila.size() - 1).getStatus())) {
                System.out.println("ENCONTRADO");
                printAll(Spila);
                con--;
                while (con > -1) {
                    Spila.add(SpilaINV.get(con));
                    con--;
                }
                return true;
            }
        }
        return false;
    }

    private boolean valid(Stack<Status> e) {
        int[][] statusAux = null;
        for (int con = 0; con < vetados.size() - 1; con++) {
            statusAux = vetados.get(con).getStatus();
            if (Arrays.deepEquals(statusAux, status())) {
                return false;
            }
        }
        if ((find(status(), e) > -1)) {
            return false;
        }
        return true;
    }

    private int moveNext(int st) {
        boolean next = false;
        while (!next) {
            switch (st) {
                case 0:
                    if (!(towers[0].isEmpty()) && (towers[0].view().isLower(towers[1].view()))) {
                        next = move(0, 1);
                    }
                    break;
                case 1:
                    if (!(towers[0].isEmpty()) && (towers[0].view().isLower(towers[2].view()))) {
                        next = move(0, 2);
                    }
                    break;
                case 2:
                    if (!(towers[1].isEmpty()) && (towers[1].view().isLower(towers[2].view()))) {
                        next = move(1, 2);
                    }
                    break;
                case 3:
                    if (!(towers[1].isEmpty()) && (towers[1].view().isLower(towers[0].view()))) {
                        next = move(1, 0);
                    }
                    break;
                case 4:
                    if (!(towers[2].isEmpty()) && (towers[2].view().isLower(towers[0].view()))) {
                        next = move(2, 0);
                    }
                    break;
                case 5:
                    if (!(towers[2].isEmpty()) && (towers[2].view().isLower(towers[1].view()))) {
                        next = move(2, 1);
                    }
                    break;
                case 6:
                    return 6;
            }
            st++;
        }
        return st;
    }

    /**
     * Moves one piece to another tower
     *
     * @param t1
     * @param t2
     */
    private boolean move(int t1, int t2) {
        towers[t2].pushPiece(towers[t1].popPiece());
        return true;
    }

    /**
     * Prints everything
     */
    private void printAll(Stack<Status> e) {
        for (int con = 0; con < e.size(); con++) {
            print(e.get(con).getStatus());
        }
    }

    /**
     * Prints by index
     */
    private void print(int[][] s) {
        Tower aux = new Tower();
        for (int con = 0; con < s.length; con++) {
            aux = new Tower();
            aux.setArray(s[con]);
            System.out.print(aux.toString());
        }
        System.out.println();
    }

    private int[][] status() {
        int[][] aux = new int[towers.length][];
        for (int con = 0; con < towers.length; con++) {
            for (int c = 0; c < towers[con].getSize(); c++) {
                aux[con] = towers[con].getArray();
            }
        }
        return aux;
    }

    /**
     * Looks if the status exists
     *
     * @return
     */
    private int find(int[][] s, Stack<Status> e) {
        int[][] statusAux = null;
        for (int con = e.size() - 1; con > -1; con--) {
            statusAux = e.get(con).getStatus();
            if (Arrays.deepEquals(statusAux, s)) {
                return con;
            }
        }
        return -1;
    }

    private static int[][] sol = {null, null, {3, 2, 1}};
}
