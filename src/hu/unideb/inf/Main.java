package hu.unideb.inf;

import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String futasszam = "10";
        int[] b_eredmeny = new int[3];
	    for (int i = 0; i < 1_000_000; i++){
	        if (i == Integer.parseInt(futasszam)) {
                long startTime = System.currentTimeMillis();
                switch (jatek()){
                    case -1:
                        b_eredmeny[0]++;
                    case 0:
                        b_eredmeny[1]++;
                    case 1:
                        b_eredmeny[2]++;
                }
                long endTime = System.currentTimeMillis();
                System.out.println(futasszam + ". futás " + (endTime - startTime) + " ezredmásodpercig tartott.");
                futasszam += "0";
            }
	        else jatek();
        }
        System.out.println("\nB statisztikái:\nNyertes: " + b_eredmeny[0] + " Döntetlen: " + b_eredmeny[1] + " Vesztes: " + b_eredmeny[2]);
    }
    private static int jatek() {
        LinkedList<Integer> sor = new LinkedList <>();
        for (int i = 0; i < 1_000; i ++){
            sor.addLast(new Random().nextInt(10_000));
        }

        int a_szamai = 0, b_szamai = 0;

        boolean a_jatekos_kore = false;
        if(sor.getFirst() % 2 == 0){
            a_jatekos_kore = true;
        }

        while(sor.size() != 0){
            if (a_jatekos_kore){
                if (sor.getFirst() > sor.getLast()){
                    a_szamai += sor.removeFirst();
                }else{
                    a_szamai += sor.removeLast();
                }
                a_jatekos_kore = !a_jatekos_kore;
            }else{
                b_szamai += bStrategia(sor);
                a_jatekos_kore = !a_jatekos_kore;
            }
        }
        return Integer.compare(b_szamai, a_szamai);
    }

    private static int bStrategia(LinkedList <Integer> sor) {

        //return sor.getFirst();
        //return sor.getLast();
    }
}
