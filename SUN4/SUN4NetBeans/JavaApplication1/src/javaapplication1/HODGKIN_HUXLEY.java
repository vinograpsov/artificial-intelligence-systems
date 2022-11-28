package javaapplication1;

import javax.swing.*;
import java.util.ArrayList;

public class HODGKIN_HUXLEY {
    private double n,m,h,v_rest,vk,vna,vl,gk,gna,gl,cm,dt;

    public HODGKIN_HUXLEY(double n, double m, double h, double v, double vk, double vna, double vl, double gk, double gna, double gl,double cm, double dt) {
        this.n = n;
        this.m = m;
        this.h = h;
        this.v_rest = v;
        this.vk = vk;
        this.vna = vna;
        this.vl = vl;
        this.gk = gk;
        this.gna = gna;
        this.gl = gl;
        this.cm = cm;
        this.dt = dt;
    }



    private double count_alfa_n(double v_last){
        if (v_last != 10) {
            return (0.01 * (-v_last + 10)) / (Math.exp((-v_last + 10) / 10) - 1);
        }
        else{
            return 0.01;
        }
    }
    private double count_alfa_m(double v_last){
        if (v_last != 25) {
            return (0.1 * (-v_last + 25)) / (Math.exp((-v_last + 25) / 10) - 1);
        }
        else{
            return 1;
        }
    }
    private double count_alfa_h(double v_last){
        return 0.07 * Math.exp(-v_last/20);
    }
    private double count_beta_n(double v_last){
        return 0.125*Math.exp(-v_last/80);
    }
    private double count_beta_m(double v_last){
        return 4*Math.exp(-v_last/18);
    }
    private double count_beta_h(double v_last){
        return 1/(Math.exp((-v_last+30)/10)+1);
    }

    public ArrayList<Double> count_v(ArrayList<Double> v, ArrayList<Double> I)
    {
        double dv;
        v.set(0,this.v_rest);
        for (int i = 1; i < I.size();i++) {
            this.m += (this.count_alfa_m(v.get(i - 1)) * (1 - this.m) - this.count_beta_m(v.get(i - 1)) * this.m) * this.dt;
            this.n += (this.count_alfa_n(v.get(i - 1)) * (1 - this.n) - this.count_beta_n(v.get(i - 1)) * this.n) * this.dt;
            this.h += (this.count_alfa_h(v.get(i - 1)) * (1 - this.h) - this.count_beta_h(v.get(i - 1)) * this.h) * this.dt;
            dv = (1. / this.cm) * (I.get(i - 1) - this.gna * Math.pow(this.m, 3) * this.h * (v.get(i - 1) - this.vna) - this.gk * Math.pow(this.n, 4) * (v.get(i - 1) - this.vk) - this.gl * (v.get(i - 1) - this.vl))* this.dt;
            v.set(i,v.get(i - 1) + dv);
        }
        return v;
    }

    public void show(ArrayList<Double> v_to_show){
        showTry.show(v_to_show,"Huxley");
    }


    public void show_int_borders(ArrayList<Double> v_to_show,int start,int finish){
        ArrayList<Double> to_show = new ArrayList<>();
        for (int i = start; i < finish;i++) {
            to_show.add(v_to_show.get(i));
        }
        showTry.show(to_show,"Huxley");
    }
}
