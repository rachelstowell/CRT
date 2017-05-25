/**
 * Created by Rachel Stowell. This method solves a system of linear congruences
 * of the form
 * z = csub1 mod bsub1
 * z = csub2 mod bsub2
 * .
 * .
 * .
 * z = csubi mod bsubi
 * using the Chinese Remainder Theorem we get the equation
 * z =  Bsub1Xsub1Csub1 + Bsub2Xsub2Csub2 + ... + BsubiXsubiCsubi
 * The solution, z, is equivalent to z mod B where B is the product of all the mods.
 *
 */
import java.util.*;
public class CRT {
    public static int s, s1;
    public static double t, t1;

    public static void main(String[] args) {
        ArrayList<Integer> constraints = new ArrayList<Integer>();
        ArrayList<Integer> mods = new ArrayList<Integer>();

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int B = 1;

        System.out.println("Enter values for c in c mod b. When finshed, enter done.");
        while (sc1.hasNextInt()) {
            constraints.add(sc1.nextInt());
        }

        System.out.println("Enter values for b in c mod b. When finished, enter done.");
        while (sc2.hasNextInt()) {
            mods.add(sc2.nextInt());
        }

        for (int i = 0; i < constraints.size(); i++) {
            if(constraints.get(i) == 0){
                 constraints.set(i, mods.get(i));
            }
        }
        //integers read into appropriate arraylists

        for (int i = 0; i < mods.size(); i++) {
            B = B * mods.get(i);
        }

        //B is the product of all of the mods

        int[] xValues = new int[constraints.size()];
        for (int i = 0; i < xValues.length; i++) {
            xValues[i] = getxValues(B/mods.get(i), mods.get(i));
        }

        int z = 0;

        for (int i = 0; i < mods.size(); i++) {
            z = z + (B/mods.get(i))*(xValues[i])*(constraints.get(i));

        }

        if (z == B){
            z = 0;
        }

        while ( z < 0) {
            z = z + B;
        }
        while (z > B){
            z = z - B;
        }

        //these loops above make sure that z is in lowest terms and is positive

        System.out.println("x is equivalent to " + z + " mod " + B);

    }

    public static int getxValues(int a, int b){
        for (s = 0, s1 = -1; s < 100 && s1 > -100; s++, s1--) {
            t = (1 - a * s) / b;
            t1 = (1 - a * s1) / b;
            if ((1 - (a * s)) % b == 0 || (1 - (a * s1)) % b == 0) {
                break;
            }
        }
        if ((1 - (a * s1)) % b == 0) {
            s = s1;
            t = t1;
        } else {
            s = s;
            t = t;
        }
        /**this method above solves for the xsubi values using the euclidean algorithm
         * where gcd(a,b) = 1
         * this finds s and t such that as + bt = 1, then returns the s value which is then used as xsubi
         */
        while (s < 0 ) {
            s = s + b;
        }

        return s;


    }


}
