package SimplexMethod;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Model data = variantInitialization();
        Simplex method = new Simplex(data);
        if(method.equationHaveSolution())
            System.out.println("*********************** Решение найдено ***********************");
        else
            System.out.println("/////////////////////// Решение не найдено ///////////////////////");


    }

    public static Model variantInitialization(){
        double[][] a = {
                {3, 4, 2, 1, 0, 0},
                {2, 5, 1, 0, 1, 0},
                {1, 2, 4, 0, 0, 1},
                {-3, -5, -4, 0, 0, 0}
        };
        double[] b = {9, 8, 7, 0};
        return new Model(a,b);
    }
    public static Model anotherInitialization(){
        double[][] a = {
                {20,    9,      6,      1,      1,  0},
                {10,    4,      2,      1,      0,  1},
                {-60,   -26,    -15,    -4.75,  0,  0}
        };
        double[] b = {20, 10, 0};
        return new Model(a,b);
    }

    public static Model selfInitialization(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество неравенств в системе: ");
        int m = sc.nextInt();

        System.out.print("Введите количество неизвестных: ");
        int n = sc.nextInt();

        System.out.println("Введите матрицу А ("+m+"x"+(n+m-1)+"): ");
        double[][] a = enterMatrix(m, n+m-1);

        System.out.println("Введите массив В (количество элементов: " + m + "): ");
        double[] b = enterArray(m);
        return new Model(a, b);
    }

    public static double[][] enterMatrix(int m, int n){
        Scanner sc = new Scanner(System.in);
        double[][] result = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("matrix["+i+"]["+j+"]");
                result[i][j] = sc.nextDouble();
            }
        }
        return result;
    }

    public static double[] enterArray(int n){
        Scanner sc = new Scanner(System.in);
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = sc.nextDouble();
        }
        return result;
    }
}
