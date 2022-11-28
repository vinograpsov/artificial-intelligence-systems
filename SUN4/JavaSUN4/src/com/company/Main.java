package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> I_arr = new ArrayList<>();
        for (int i = 0; i < 1000;i++){
            I_arr.add((double) 0);
        }
        for (int i = 600; i < 610;i++){
            I_arr.set(i,(double) 20);
        }
        for (int i = 800; i < 801;i++){
            I_arr.set(i,(double) 20);
        }
        for (int i = 400; i < 430;i++){
            I_arr.set(i,(double) 20);
        }

        Izkiewicz izkiewicz = new Izkiewicz(0.02,0.2,-65,8,30,1000,0.1);
        ArrayList<Double> temp = izkiewicz.make_izk(I_arr,-70,-20);

        izkiewicz.show(temp);
        izkiewicz.show_int_borders(temp,200,999);


        HODGKIN_HUXLEY hux = new HODGKIN_HUXLEY(0.316,0,0.607,-10,-12,120,10.6,36,120,0.3,1);

        ArrayList<Double> tempK = new ArrayList<>();
        double test = 0;
        for (int i = 0; i < 1000;i++) {
            tempK.add(test);
            test += 0.001;
        }

        ArrayList<Double> temp1 = hux.count_hux(tempK,I_arr);

        hux.show(temp1);
        hux.show_int_borders(temp1,200,999);
        hux.show(I_arr);


    }
}

