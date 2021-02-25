/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Arrays;
import vista.Puntos;

/**
 *
 * @author Usuario
 */
public class NumeroCromatico {

    private ArrayList<ArrayList<Integer>> listPrincipal;
    private ArrayList<ArrayList<Integer>> listPrincipalFinal;
    private ArrayList<ParejasAristas> parejasAristas;
    private ArrayList<Integer> permutacionList, permutacionEvaluarList;
    
    private Puntos[] puntos;
    private int numPuntos;
    private int[] permutacion, permutacionFinal, permutacionEvaluar;

    public NumeroCromatico(ArrayList<ParejasAristas> parejasAristas, Puntos[] puntos) {
        this.listPrincipal = new ArrayList<>();
        this.listPrincipalFinal = new ArrayList<>();
        this.permutacionList = new ArrayList<>();
        this.permutacionEvaluarList = new ArrayList<>();
        this.numPuntos = 0;
        this.parejasAristas = parejasAristas;
        this.puntos = puntos;
        for (Puntos p : puntos) {
            if (p != null) {
                this.numPuntos++;
            }
        }
        permutacion = new int[numPuntos];
        permutacionEvaluar = new int[numPuntos];
        permutacionFinal = new int[numPuntos];
        for (int i = 0; i < numPuntos; i++) {
            permutacion[i] = i + 1;
            permutacionList.add(i+1);
        }
        System.out.println("numPuntos:" + numPuntos);
        System.out.println(Arrays.toString(permutacion));

        permutacion(permutacionList, "", numPuntos, numPuntos);
    }

    private void calcularNumeroCromatico() {
        listPrincipal.clear();
        boolean seIngreso;
        int i;
        //System.out.println(listPrincipal);
        //System.out.println(Arrays.toString(permutacionEvaluar));
        for (int num : permutacionEvaluar) {

            seIngreso = false;
            i = 0;
            while (!seIngreso) {
                if (listPrincipal.isEmpty()) {
                    listPrincipal.add(i, new ArrayList<>());
                    listPrincipal.get(i).add(num);
                    seIngreso = true;
                } else {
                    for (ArrayList<Integer> listaInterna : listPrincipal) {
                        if (seIngreso == false) {
                            if (verificarLista(num, listaInterna)) {
                                listaInterna.add(num);
                                seIngreso = true;
                            }

                        }
                    }
                    if (seIngreso == false) {
                        listPrincipal.add(new ArrayList<>());
                        listPrincipal.get(listPrincipal.size() - 1).add(num);
                        seIngreso = true;
                    }
                }

            }
        }
        //System.out.println("Tamanios: "+listPrincipal.size()+"-------"+listPrincipalFinal.size());
        if (listPrincipalFinal.isEmpty()) {
            listPrincipalFinal = (ArrayList<ArrayList<Integer>>) listPrincipal.clone();
            System.out.println("Mejora");
            this.permutacionFinal = permutacionEvaluar.clone();
            System.out.println(listPrincipalFinal);
            System.out.println(Arrays.toString(permutacionFinal));
        } else if (listPrincipal.size() < listPrincipalFinal.size()) {
            System.out.println("Mejora");
            listPrincipalFinal = (ArrayList<ArrayList<Integer>>) listPrincipal.clone();
            this.permutacionFinal =  permutacionEvaluar.clone();
            System.out.println(listPrincipalFinal);
            System.out.println(Arrays.toString(permutacionFinal));
        }

    }

    public boolean verificarLista(int num, ArrayList<Integer> lista) {
        boolean sePuedeIngresar = true;
        for (int elemLista : lista) {
            for (ParejasAristas pa : parejasAristas) {
                if (pa.getArista1() == elemLista && pa.getArista2() == num) {
                    sePuedeIngresar = false;
                } else if (pa.getArista1() == num && pa.getArista2() == elemLista) {
                    sePuedeIngresar = false;
                }
            }
        }
        return sePuedeIngresar;
    }

    public void permutacion(ArrayList<Integer> permu, String act, int n, int r) {
        if (n == 0) {
            //System.out.println("Entra");
            //System.out.println(act);
            String[] permuString = act.split(",");
            for(int i = 0; i<permuString.length; i++){
                this.permutacionEvaluar[i] = Integer.parseInt(permuString[i]);
            }
            //System.out.println(Arrays.toString(permutacionEvaluar));
            calcularNumeroCromatico();
            
        } else {
            for (int i = 0; i < r; i++) {
                if (!String.valueOf(act).contains(String.valueOf(permu.get(i)))) { // Controla que no haya repeticiones
                    permutacion(permu, act + String.valueOf(permu.get(i))+",", n - 1, r);
                }
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getListPrincipalFinal() {
        return listPrincipalFinal;
    }

    public int[] getPermutacion() {
        return permutacion;
    }

    public int[] getPermutacionFinal() {
//        int[] permutacionTemp = new int[permutacionFinal.length];
//        System.arraycopy(permutacionFinal, 0, permutacionTemp, 0, permutacionFinal.length);
//        System.out.println("Permutacion a enviar: "+Arrays.toString(permutacionTemp));
        return permutacionFinal;
    }
}
