package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> I_arr = new ArrayList<>();
        for (int i = 0; i < 10000;i++){
            I_arr.add((double) 0);
        }
        for (int i = 6000; i < 6100;i++){
            I_arr.set(i,(double) 20);
        }
        for (int i = 8000; i < 8010;i++){
            I_arr.set(i,(double) 20);
        }
        for (int i = 4000; i < 5000;i++){
            I_arr.set(i,(double) 20);
        }
        ArrayList<Double> v_arr = new ArrayList<>();
        for (int i = 0; i < 10000;i++){
            v_arr.add((double) 0);
        }
        HODGKIN_HUXLEY hux = new HODGKIN_HUXLEY(0.316,0,0.607,-5,-12,120,10.6,36,120,0.3,1,0.025);
        ArrayList<Double> temp1 = hux.count_v(v_arr,I_arr);
        hux.show(temp1);
        hux.show(I_arr);


        ArrayList<Double> I_arr1 = new ArrayList<>();
        for (int i = 0; i < 1000;i++){
            I_arr1.add((double) 0);
        }
        for (int i = 600; i < 610;i++){
            I_arr1.set(i,(double) 20);
        }
        for (int i = 800; i < 801;i++){
            I_arr1.set(i,(double) 20);
        }
        for (int i = 400; i < 430;i++){
            I_arr1.set(i,(double) 20);
        }
        Izkiewicz izkiewicz = new Izkiewicz(0.02,0.2,-65,8,30,1000,0.1);
        ArrayList<Double> temp = izkiewicz.make_izk(I_arr1,-70,-20);

        izkiewicz.show(temp);
    }
}

