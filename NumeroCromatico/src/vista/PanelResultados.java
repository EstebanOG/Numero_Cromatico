/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import logica.ParejasAristas;


/**
 *
 * @author Usuario
 */
public class PanelResultados extends JPanel {

    private ArrayList<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
    private Puntos[] puntos;
    private JLabel[] pesoCamino = new JLabel[30];
    private JLabel[] pesoCamino2 = new JLabel[30];
    private JLabel[] aristas = new JLabel[12];
    private JLabel[] aristas2 = new JLabel[12];

    private boolean graficarLineas = false;
    private boolean dibujarLineasGrafo = false;
    private ParejasAristas[] parejasLineas = new ParejasAristas[30];
    private ArrayList<ParejasAristas> parejasDeAristas = new ArrayList<>();
    private ArrayList<ParejasAristas> parejasSolucion = new ArrayList<>();
    private ArrayList<ParejasAristas> parejasDeAristasInsertadas = new ArrayList<>();

    public PanelResultados() {
        for (int h = 0; h < parejasLineas.length; h++) {
            parejasLineas[h] = null;
        }
        setLayout(null);
        setBounds(12,97,614,323);
        setBorder(new LineBorder(Color.BLACK));
        setVisible(true);
    }

    public void setParejasDeAristas(ArrayList<ParejasAristas> parejasDeAristas) {
        this.parejasDeAristas = parejasDeAristas;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (parejasLineas[0] == null) {
        } else {
            if (graficarLineas) {
                for (int i = 0; i < parejasLineas.length; i++) {
                    if (parejasLineas[i] != null && parejasLineas[i + 1] != null) {
                        g.drawLine(parejasLineas[i].getX(), parejasLineas[i].getY(), parejasLineas[i + 1].getX(), parejasLineas[i + 1].getY());
                    }
                    i++;
                }
            }
        }

        if (dibujarLineasGrafo) {
            parejasDeAristas.forEach((pda) -> {
                g.drawLine(aristas[pda.getArista1() - 1].getX(), aristas[pda.getArista1() - 1].getY(), aristas[pda.getArista2() - 1].getX(), aristas[pda.getArista2() - 1].getY());
            });

        }

    }

    public void dibujarGrafos() {
        int x = 0;
        for (Puntos punto : puntos) {
            if (punto != null) {
                aristas[x] = new JLabel(x + 1 + "");
                aristas[x].setBounds(punto.getPosX(), punto.getPosY(), 20, 45);
                add(aristas[x]);
                this.repaint();
            }
            x++;
        }
        // Dibujar líneas del grafo
        this.dibujarLineasGrafo = true;
    }

    public void setPuntos(Puntos[] puntos) {
        this.puntos = puntos;
    }

}
