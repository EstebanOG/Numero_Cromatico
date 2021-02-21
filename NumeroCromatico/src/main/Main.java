/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import vista.GUI;

/**
 *
 * @author Esteban Olaya
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Se instancia objeto de la clase GUI.
        GUI gui = new GUI();
        gui.setTitle("Número cromático para un grafo");
        gui.setVisible(true);
    }
    
}
