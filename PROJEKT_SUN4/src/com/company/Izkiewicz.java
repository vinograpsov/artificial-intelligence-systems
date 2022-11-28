package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Izkiewicz {
    private double a,b,c,d,vpeak,t,tau;
    private ArrayList<Double> v_arr,u_arr;
    public Izkiewicz(double a, double b, double c, double d, double vpeak, double t, double tau) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.vpeak = vpeak;
        this.t = t;
        this.tau = tau;
        this.v_arr = new ArrayList<>();
        this.u_arr = new ArrayList<>();
    }

    private double v_current(double v_last,double u_last,double I){
        double v;
        v = v_last + this.tau * ((0.04 * v_last + 5)* v_last + 140 - u_last + I);
        if(v >= this.vpeak){
            return this.c;
        }
        else{
            return v;
        }
    }

    private double u_current(double v_curr,double u_last){
        double u;
        u = u_last + this.tau * this.a *(this.b * v_curr - u_last);
        if(u >= this.vpeak){
            return this.d + u;
        }
        else{
            return u;
        }
    }

    public ArrayList<Double> make_izk(ArrayList<Double> I_arr,double v_start,double u_start){
        this.v_arr = new ArrayList<>();
        this.u_arr = new ArrayList<>();
        this.v_arr.add(v_start);
        this.u_arr.add(u_start);
        for(int i = 1; i < I_arr.size();i++){
            this.v_arr.add(v_current(this.v_arr.get(i-1),this.u_arr.get(i-1),I_arr.get(i)));
            this.u_arr.add(u_current(this.v_arr.get(i),this.u_arr.get(i-1)));
        }

        return this.v_arr;
    }


    public void show(ArrayList<Double> v_to_show){
        showTry.show(v_to_show,"Izkiewicz");
    }

    public void show_int_borders(ArrayList<Double> v_to_show,int start,int finish){
        ArrayList<Double> to_show = new ArrayList<>();
        for (int i = start; i < finish;i++) {
            to_show.add(v_to_show.get(i));
        }
        showTry.show(to_show,"Izkiewicz");
    }
}
