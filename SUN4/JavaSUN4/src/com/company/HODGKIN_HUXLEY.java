package com.company;

import java.util.ArrayList;

public class HODGKIN_HUXLEY {
    private double n,m,h,v,vk,vna,vl,gk,gna,gl,cm;
    private ArrayList<Double> n_arr, m_arr, h_arr, V_arr;

    public HODGKIN_HUXLEY(double n, double m, double h, double v, double vk, double vna, double vl, double gk, double gna, double gl, double cm) {
        this.n = n;
        this.m = m;
        this.h = h;
        this.v = v;
        this.vk = vk;
        this.vna = vna;
        this.vl = vl;
        this.gk = gk;
        this.gna = gna;
        this.gl = gl;
        this.cm = cm;
    }


    private double count_alfa_n(double v_last){
        return (0.01*(v_last+10))/(Math.exp((v_last+10)/10));
    }
    private double count_alfa_m(double v_last){
        return (0.1*(v_last+25))/(Math.exp((v_last+25)/10));
    }
    private double count_alfa_h(double v_last){
        return 0.07 * Math.exp(v_last/20);
    }
    private double count_beta_n(double v_last){
        return 0.125*Math.exp(v_last/80);
    }
    private double count_beta_m(double v_last){
        return 0.125*Math.exp(v_last/18);
    }
    private double count_beta_h(double v_last){
        return 1/(Math.exp((v_last+30)/10)+1);
    }

    private double count_n(double n_last,double v_last){
        return count_alfa_n(v_last)*(1-n_last) - count_beta_n(v_last)*n_last;
    }
    private double count_m(double m_last,double v_last){
        return count_alfa_m(v_last)*(1-m_last) - count_beta_n(v_last)*m_last;
    }
    private double count_h(double h_last,double v_last){
        return count_alfa_h(v_last)*(1-h_last) - count_beta_h(v_last)*h_last;
    }

    private double count_v(double last_n,double last_m,double last_h,double last_v,double step,double I){
        return  last_v + step * (I - (this.gk * Math.pow(last_n, 4) *(last_v-this.vk) +
                this.gna * Math.pow(last_m, 3) * last_h * (last_v-this.vna) +
                this.cm * (last_v-this.vl))) / this.cm;
    }

    public ArrayList<Double> count_hux(ArrayList<Double> arr_steps,ArrayList<Double> arr_I){
        this.n_arr = new ArrayList<>();
        this.m_arr = new ArrayList<>();
        this.h_arr = new ArrayList<>();
        this.V_arr = new ArrayList<>();


        this.n_arr.add(this.n);
        this.m_arr.add(this.m);
        this.h_arr.add(this.h);
        this.V_arr.add(this.v);

        for(int i = 1; i < arr_I.size(); i++){
            this.n_arr.add(count_n(this.n_arr.get(i-1),this.V_arr.get(i-1)));
            this.m_arr.add(count_m(this.m_arr.get(i-1),this.V_arr.get(i-1)));
            this.h_arr.add(count_h(this.h_arr.get(i-1),this.V_arr.get(i-1)));
            this.V_arr.add(count_v(this.n_arr.get(i-1),this.m_arr.get(i-1),this.h_arr.get(i-1),this.V_arr.get(i-1),arr_steps.get(i-1),arr_I.get(i-1)));
        }
        return this.V_arr;
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
