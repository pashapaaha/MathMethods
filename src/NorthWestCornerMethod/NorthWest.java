package NorthWestCornerMethod;

import static SimplexMethod.Model.printMatrix;

public class NorthWest {
    Model model;
    double[][] x;

    NorthWest(Model model){
        this.model = model;
    }

    public void searchSolution(){
        model.toCloseType();
        x = new double[model.getM()][model.getN()];
        int i = 0, j = 0;
        while(i < model.getM() && j < model.getN()){
            if(model.getElemA(i) < model.getElemB(j)){
                excludeVendor(i, j);
                i++;
            }
            else if(model.getElemA(i) > model.getElemB(j)){
                excludeConsumer(i, j);
                j++;
            }
            else{excludeVendor(i, j); i++;}
        }
        model.setX(x);
        double sumproizv = 0;
        for (int k = 0; k < model.getM(); k++) {
            for (int l = 0; l < model.getN(); l++) {
                sumproizv += x[k][l]*model.getElemC(k,l);
            }
        }
        model.setZ(sumproizv);
    }

    private void excludeVendor(int i, int j){
        x[i][j] = model.getElemA(i);
        model.setElemA(0, i);
        model.setElemB(model.getElemB(j) - x[i][j], j);
    }
    private void excludeConsumer(int i, int j){
        x[i][j] = model.getElemB(j);
        model.setElemB(0, j);
        model.setElemA(model.getElemA(i) - x[i][j], i);
    }
}
