package SimplexMethod;

public class Model {

    protected int m, n;
    protected double[][] a; //= new double[m+1][n+m];
    protected double[] x;   //= new double[n+m];
    protected double[] b;   //= new double[m+1];
    protected double z;

    public int getM() {
        return m;
    }
    public int getN() {
        return n;
    }
    public double getAByIndex(int i, int j) {
        return a[i][j];
    }
    public double getBByIndex(int index) {
        return b[index];
    }
    public double getZ() {
        return z;
    }
    public double getXByIndex(int index) {
        return x[index];
    }

    public void setXByIndex(int index, double newValue) {
        this.x[index] = newValue;
    }
    public void setBByIndex(int index, double newValue){
        this.b[index] = newValue;
    }
    public void setZ(double newValue) {
        this.z = newValue;
    }
    public void setAByIndex(int i, int j, double newValue){
        this.a[i][j] = newValue;
    }

    public Model(double[][] a, double[] b){
        this.a = a;
        this.b = b;
        this.m = a.length-1;
        this.n = a[0].length - this.m;
        this.x = new double[n+m];
    }

    public void printModel(){
        System.out.println("A:");
        printMatrix(a);
        System.out.println("B:");
        printArray(b);
    }

    public void printResult(){
        System.out.println("X:");
        printArray(x);
        System.out.println("Z = " + z);
    }

    public static void printMatrix(double[][] matr){
        for (int i = 0; i < matr.length; i++) {
            printArray(matr[i]);
        }
    }

    public static void printArray(double[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
