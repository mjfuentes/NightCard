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
        trago.setStock(trago.getStock() + 1);
        if (!getSeleccionados().contains(trago)){
            DrinksController.getSeleccionados().add(trago);
        }
        totalAmount += trago.getPrice();
    }

    public static void lessDrinkSelected(Trago trago){
        if (trago.getStock()>0){
            trago.setStock(trago.getStock()-1);
            if (trago.getStock() == 0){
                getSeleccionados().remove(trago);
            }
        }
        totalAmount -= trago.getPrice();
    }
}
