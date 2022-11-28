package com.company;

import java.util.ArrayList;

public class HUX {
    private double n,m,h,v,vk,vna,vl,gk,gna,gl,cm,step,v_rest,dt;
    private ArrayList<Double> n_arr, m_arr, h_arr, V_arr;

    public HUX(double n, double m, double h, double v, double vk, double vna, double vl, double gk, double gna, double gl,double cm, double dt) {
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

    private double count_n(double n,double v){
        return n + (this.count_alfa_m(v) * (1 - m) - this.count_beta_m(v) * m) * this.dt;
    }
    private double count_m(double m,double v){
        return m + (this.count_alfa_n(v) * (1 - n) - this.count_beta_n(v) * n) * this.dt;
    }
    private double count_h(double h,double v){
        return h + (this.count_alfa_h(v) * (1 - h) - this.count_beta_h(v) * h) * this.dt;
    }

    private double count_v(double n,double m,double h,double v,double I){
        return v + (1. / this.cm) * (I - this.gna * Math.pow(m, 3) * h * (v - this.vna) - this.gk * Math.pow(n, 4) * (v - this.vk) - this.gl * (v - this.vl))* this.dt;
    }

    public ArrayList<Double> count_v(ArrayList<Double> V, ArrayList<Double> I) {
        this.n_arr = new ArrayList<>();
        this.m_arr = new ArrayList<>();
        this.h_arr = new ArrayList<>();

        this.n_arr.add(this.n);
        this.m_arr.add(this.m);
        this.h_arr.add(this.h);
        V.set(0,this.count_v(n_arr.get(0),m_arr.get(0),h_arr.get(0),this.v_rest,I.get(0)));

        for(int i = 1; i < I.size(); i++){
            this.n_arr.add(count_n(this.n_arr.get(i-1),V.get(i-1)));
            this.m_arr.add(count_m(this.m_arr.get(i-1),V.get(i-1)));
            this.h_arr.add(count_h(this.h_arr.get(i-1),V.get(i-1)));
            V.add(count_v(this.n_arr.get(i),this.m_arr.get(i),this.h_arr.get(i),V.get(i-1),I.get(i)));
        }
        return V;


    }

    public void show(ArrayList<Double> v_to_show){
        System.out.println(v_to_show);
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
