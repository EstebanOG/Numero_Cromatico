/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import logica.NumeroCromatico;
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
    private ArrayList<ArrayList<Integer>> listPrincipalFinal;
    private int[] permutacion, permutacionFinal;
    private boolean graficarLineas = false;
    private boolean dibujarLineasGrafo = false;
    private ParejasAristas[] parejasLineas = new ParejasAristas[30];
    private ArrayList<ParejasAristas> parejasDeAristas = new ArrayList<>();
    private ArrayList<ParejasAristas> parejasSolucion = new ArrayList<>();
    private ArrayList<ParejasAristas> parejasDeAristasInsertadas = new ArrayList<>();
    private NumeroCromatico numCrom;
    private int numCromatico;
    private Color[] colors;

    public PanelResultados() {
        colors = new Color[9];
        colors[0] = Color.RED;
        colors[1] = Color.blue;
        colors[2] = Color.CYAN;
        colors[3] = Color.green;
        colors[4] = Color.YELLOW;
        colors[5] = Color.MAGENTA;
        colors[6] = Color.pink;
        colors[7] = Color.lightGray;
        for (int h = 0; h < parejasLineas.length; h++) {
            parejasLineas[h] = null;
        }
        setLayout(null);
        setBounds(12, 97, 614, 323);
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
        int j = 0;
        for(Puntos punto: puntos){
            if(punto != null){
                aristas[j] = null;
            }
            j++;
        }
        this.removeAll();
        int x = 0;
        numCrom = new NumeroCromatico(parejasDeAristas, puntos);
        this.listPrincipalFinal = numCrom.getListPrincipalFinal();
        this.permutacion = numCrom.getPermutacion();
        this.permutacionFinal = numCrom.getPermutacionFinal();
        System.out.println(Arrays.toString(permutacionFinal));
        this.numCromatico = numCrom.getListPrincipalFinal().size();
        for (Puntos punto : puntos) {
            if (punto != null) {
                aristas[x] = new JLabel(x + 1 + "");
                aristas[x].setBounds(punto.getPosX(), punto.getPosY(), 20, 35);
                aristas[x].setFont(new Font("Calibri", 3, 19));
                aristas[x].setForeground(Color.white);
                aristas[x].setBackground(colors[colorArista(x + 1)]);
                aristas[x].setOpaque(true);
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

    public int[] getPermutacion() {
        return permutacion;
    }

    public int[] getPermutacionFinal() {
        return permutacionFinal;
    }

    public int getNumCromatico() {
        return numCromatico;
    }

    public int colorArista(int num) {
        int pos = 0, i = 0;
        for (ArrayList<Integer> lista : listPrincipalFinal) {
            if (lista.contains(num)) {
                pos = i;
            } else {
                i++;
            }

        }
        return pos;

    }

}
