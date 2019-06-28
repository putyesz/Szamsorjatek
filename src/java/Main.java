import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int futasszam = 10;
        int[] b_eredmeny = new int[3];
        long startTime = System.currentTimeMillis();
	    for (int i = 1; i <= 1_000_000; i++){
            switch (jatek()){
                case 1:
                    b_eredmeny[0]++;
                    break;
                case 0:
                    b_eredmeny[1]++;
                    break;
                case -1:
                    b_eredmeny[2]++;
                    break;
            }
	        if (i == futasszam) {
                long endTime = System.currentTimeMillis();
                System.out.println(futasszam + ". futás " + (endTime - startTime) + " ezredmásodpercnél ért véget.");
                futasszam *= 10;
            }
        }
        System.out.println("\nB statisztikái:\nNyertes: " + b_eredmeny[0] +
                " Döntetlen: " + b_eredmeny[1] + " Vesztes: " + b_eredmeny[2]);
    }

    /**
     * Egy játékmenet lejátszása.
     *
     * Legeneráljuk a random számsort 0 és 10000 között.
     * Kezdetben a kezdő játékos a generált számsor első eleme alapján lesz kiválasztva.
     * Majd minden körben hozzáadjuk a kiválasztott számértéket az aktuális játékos összegéhez.
     *
     * @return -1, 0, 1 értékekkel térünk vissza, hogy B vesztett, nyert vagy döntetlen játszmát játszott
     */
    private static int jatek() {
        LinkedList<Integer> sor = new LinkedList <>();
        int sum = 0;
        for (int i = 0; i < 1_000; i ++){
            int rand = new Random().nextInt(9999) + 1;
            sum += rand;
            sor.addLast(rand);
        }

        boolean a_kezd = false;
        if(new Random().nextInt(2) == 0){
            a_kezd = true;
        }

        if (a_kezd){
            if (sor.getFirst() > sor.getLast()){
                sor.removeFirst();
            }else{
                sor.removeLast();
            }
        }

        return Integer.compare(bStrategia(sor), sum / 2);
    }

    /**
     * B játékos stratégiája az megfelelő elem kiválasztására.
     *
     * @param sor amely tartalmazza a számokat
     * @return a sor utolsó elemével tér vissza
     */
    private static int bStrategia(@NotNull LinkedList <Integer> sor) {
        int[][] table = new int[sor.size()][sor.size()];

        for (int gap = 0; gap < sor.size(); ++gap) {
            for (int i = 0, j = gap; j < sor.size(); ++i, ++j) {
                int x = ((i + 2) <= j) ? table[i + 2][j] : 0;
                int y = ((i + 1) <= (j - 1)) ? table[i + 1][j - 1] : 0;
                int z = (i <= (j - 2)) ? table[i][j - 2] : 0;

                table[i][j] = Math.max(sor.get(i) + Math.min(x, y), sor.get(j) + Math.min(y, z));
            }
        }

        return table[0][sor.size() - 1];
    }
}
