package NorthWestCornerMethod;

import static SimplexMethod.Model.printArray;
import static SimplexMethod.Model.printMatrix;

public class Model {
    private double[] a; // new double[m]
    private double[] b; // new double[n]
    private double[][] c; // new double[m][n]
    private double[][] x;
    private int n, m;
    private double z;

    public Model(double[] a, double[] b, double[][] c) {
        this.a = a;
        this.b = b;
        this.c = c;
        m = a.length;
        n = b.length;
        x = new double[m][n];
    }

    public void toCloseType(){
        double sumA = arraySum(a);
        double sumB = arraySum(b);
        if(sumA > sumB){
            changeB(sumA - sumB);
            System.out.println("+++");
        }
        else if(sumB > sumA){
            changeA(sumB - sumA);
            System.out.println("+++");
        }
    }

    private void changeA(double newElem){
        double[] newA = new double[m+1];
        for (int i = 0; i < m; i++) {
            newA[i] = a[i];
        }
        newA[m] = newElem;
        double[][] newC = new double[m+1][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newC[i][j] = c[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            newC[m][i] = 0;
        }
        a = newA;
        c = newC;
        m++;
    }
    private void changeB(double newElem) {
        double[] newB = new double[n + 1];
        for (int i = 0; i < n; i++) {
            newB[i] = b[i];
        }
        newB[n] = newElem;
        double[][] newC = new double[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newC[i][j] = c[i][j];
            }
            newC[i][n] = 0;
        }
        b = newB;
        c = newC;
        n++;
    }

    public double arraySum(double[] array){
        double sum = 0;
        for(double item : array)
            sum += item;
        return sum;
    }

    public void printModel(){
        System.out.println("A:");
        printArray(a);
        System.out.println("B:");
        printArray(b);
        System.out.println("C:");
        printMatrix(c);
    }
    public void printResult(){
        System.out.println("X:");
        printMatrix(x);
        System.out.println("Z = " + z);
    }

    public void setX(double[][] x) {
        this.x = x;
    }

    public double getElemA(int index) {
        return a[index];
    }

    public double getElemB(int index) {
        return b[index];
    }

    public double getElemC(int i, int j) {
        return c[i][j];
    }


    public void setElemA(double value, int index) {
        this.a[index] = value;
    }

    public void setElemB(double value, int index) {
        this.b[index] = value;
    }

    public int getN() {
        return n;

    }

    public int getM() {
        return m;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
