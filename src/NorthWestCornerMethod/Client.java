package NorthWestCornerMethod;

public class Client {
    public static void main(String[] args) {
        Model model = variantInitialization3();
        model.printModel();
        NorthWest northWest = new NorthWest(model);
        northWest.searchSolution();
        System.out.println();
        model.printResult();
    }

    private static Model variantInitialization2(){
        double[] a = {200, 450, 250};
        double[] b = {100, 125, 325, 250, 100};
        double[][] c = {
                {5, 8,  7,  10, 3},
                {4, 2,  2,  5,  6},
                {7, 3,  5,  9,  2}
        };
        return new Model(a, b, c);
    }

    private static Model variantInitialization1(){
        double[] a = {200, 175, 225};
        double[] b = {100, 130, 80, 190, 100};
        double[][] c = {
                {5, 7,  4,  2, 5},
                {7, 1,  3,  1,  10},
                {2, 3,  6,  8,  7}
        };
        return new Model(a, b, c);
    }
    private static Model variantInitialization3(){
        double[] a = {250, 200, 200};
        double[] b = {120, 130, 100, 160, 110};
        double[][] c = {
                {27, 36,  35,  31, 29},
                {22, 23,  26,  32,  35},
                {35, 42,  38,  32,  39}
        };
        return new Model(a, b, c);
    }
    private static Model variantInitialization6(){
        double[] a = {350, 200, 300};
        double[] b = {170, 140, 200, 195, 145};
        double[][] c = {
                {22, 14,  16,  28, 30},
                {19, 17,  26,  36,  36},
                {37, 30,  31,  39,  41}
        };
        return new Model(a, b, c);
    }

    public static Model testInitialization(){
        double[] a = {100, 250, 200, 300};
        double[] b = {200, 200, 100, 100, 250};
        double[][] c = {
                {10, 7,  4,  1, 4},
                {2, 7,  10,  6,  11},
                {8, 5,  3,  2,  2},
                {11,8, 12, 16, 13}
        };
        return new Model(a, b, c);
    }
}
