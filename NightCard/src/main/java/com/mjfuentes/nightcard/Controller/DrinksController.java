package com.mjfuentes.nightcard.Controller;

import com.mjfuentes.nightcard.Model.Trago;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matifuentes on 5/11/14.
 */
public class DrinksController {


    private static List<Trago> cervezas = new ArrayList<Trago>();
    private static List<Trago> tragos = new ArrayList<Trago>();
    private static float totalAmount = 0;
    private static float userAmount = 300;
    private static List<Trago> seleccionados = new ArrayList<Trago>();

    public static List<Trago> getCervezas() {
        return cervezas;
    }

    public static void setCervezas(List<Trago> cervezas) {
        DrinksController.cervezas = cervezas;
    }

    public static List<Trago> getTragos() {
        return tragos;
    }

    public static void setTragos(List<Trago> tragos) {
        DrinksController.tragos = tragos;
    }

    public static float getTotalAmount() {
        return totalAmount;
    }

    public static void setTotalAmount(float totalAmount) {
        DrinksController.totalAmount = totalAmount;
    }

    public static float getUserAmount() {
        return userAmount;
    }

    public static void setUserAmount(float userAmount) {
        DrinksController.userAmount = userAmount;
    }

    public static List<Trago> getSeleccionados() {
        return seleccionados;
    }

    public static void setSeleccionados(List<Trago> seleccionados) {
        DrinksController.seleccionados = seleccionados;
    }

    public static void newDrinkSelected(Trago trago){
        trago.setSelected(trago.getSelected() + 1);
        totalAmount += trago.getPrice();
        if (!getSeleccionados().contains(trago)){
            DrinksController.getSeleccionados().add(trago);
        }
    }

    public static void lessDrinkSelected(Trago trago){
        if (trago.getSelected()>0){
            trago.setSelected(trago.getSelected()-1);
            totalAmount -= trago.getPrice();
            if (trago.getSelected() == 0){
                getSeleccionados().remove(trago);
            }
        }

    }

    public static void fillDrinks(){
        DrinksController.getCervezas().add(new Trago(25, "Heineken", 10,"Heineken Heineken Heineken Heineken"));
        DrinksController.getCervezas().add(new Trago(30, "Stela Artois", 10,"Stela Artois Stela Artois Stela Artois Stela Artois"));
        DrinksController.getCervezas().add(new Trago(25, "Budweiser", 10, "Budweiser Budweiser Budweiser Budweiser"));
        DrinksController.getCervezas().add(new Trago(20, "Quilmes", 10, "Quilmes Quilmes Quilmes Quilmes"));
        DrinksController.getTragos().add(new Trago(20, "Fernet", 10, "30% Fernet Branca, 70% Coca Cola"));
        DrinksController.getTragos().add(new Trago(15, "Tequila", 10,"Shot de tequila, Patron"));
        DrinksController.getTragos().add(new Trago(25, "Vodka con Speed", 10,"Vodka con energizante, Speed"));
        DrinksController.getTragos().add(new Trago(25, "Whiscola", 10,"Whisky con Coca Cola"));
    }
}
