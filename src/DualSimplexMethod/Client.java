package DualSimplexMethod;

import SimplexMethod.Model;

public class Client {
    public static void main(String[] args) {
        Model data = anotherInitialization(); 
        DualSimplex method = new DualSimplex(data);
        if(method.equationHaveSolution())
            System.out.println("*********************** Решение найдено ***********************");
        else
            System.out.println("/////////////////////// Решение не найдено ///////////////////////");
    }

    private static Model variantInitialization(){
        double[][] a = {
                {3, 4, 2, 1, 0, 0},
                {2, 5, 1, 0, 1, 0},
                {1, 2, 4, 0, 0, 1},
                {-3, -5, -4, 0, 0, 0}
        };
        double[] b = {9, 8, 7, 0};
        return new Model(a,b);
    }
    private static Model anotherInitialization(){
        double[][] a = {
                {1, 1, -1, 1, 0},
                {-1, 5, -1, 0, 1},
                {-2, 1, 5, 0, 0}
        };
        double[] b = {4, -5, 0};
        return new Model(a,b);
    }
}
