import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String futasszam = "10";
        int[] b_eredmeny = new int[3];
        long startTime = System.currentTimeMillis();
	    for (int i = 0; i < 10_000; i++){
            switch (jatek()){
                case -1:
                    b_eredmeny[0]++;
                    break;
                case 0:
                    b_eredmeny[1]++;
                    break;
                case 1:
                    b_eredmeny[2]++;
                    break;
            }
	        if (i == Integer.parseInt(futasszam)) {
                long endTime = System.currentTimeMillis();
                System.out.println(futasszam + ". futás " + (endTime - startTime) + " ezredmásodpercnél ért véget.");
                futasszam += "0";
            }
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
            }else{
                b_szamai += bStrategia(sor);
            }
            a_jatekos_kore = !a_jatekos_kore;
        }
        return Integer.compare(b_szamai, a_szamai);
    }

    private static int bStrategia(LinkedList <Integer> sor) {

        //return sor.getFirst();
        return sor.removeLast();
    }
}
