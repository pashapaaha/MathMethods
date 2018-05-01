package NorthWestCornerMethod;

public class Client {
    public static void main(String[] args) {
        Model model = variantInitialization();
        model.printModel();
        NorthWest northWest = new NorthWest(model);
        northWest.searchSolution();
        System.out.println();
        model.printResult();
    }

    private static Model variantInitialization(){
        double[] a = {200, 450, 250};
        double[] b = {100, 125, 325, 250, 100};
        double[][] c = {
                {5, 8,  7,  10, 3},
                {4, 2,  2,  5,  6},
                {7, 3,  5,  9,  2}
        };
        return new Model(a, b, c);
    }

    private static Model testInitialization(){
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
