package DualSimplexMethod;

import SimplexMethod.Model;
import SimplexMethod.Simplex;

public class DualSimplex extends Simplex{

    private double[] dualRelation; // new double[model.getM()+model.getN()];

    public DualSimplex(Model model) {
        super(model);
    }

    private boolean isSolvedBySimplex() {
        boolean solved = true;
        for (int i = 0; i < model.getM(); i++) {
            solved &= model.getBByIndex(i) >= 0;
        }
        return solved;
    }

    private boolean isSolved(){
        boolean solv = true;
        for (int i = 0; i < model.getM(); i++) {
            for (int j = 0; j < model.getM()+model.getN(); j++) {
                solv &= model.getAByIndex(i, j) >= 0;
            }
            if(solv){
                if(model.getBByIndex(i)  < 0) return false;
            }
            solv = true;
        }
        return true;
    }

    private void calculateDualRelations(){
        double[] dr = new double[model.getM()+model.getN()];
        for (int i = 0; i < model.getM()+model.getN(); i++) {
            if(model.getAByIndex(basicLine, i) < 0)
                dr[i] = Math.abs(model.getAByIndex(model.getM(), i) / model.getAByIndex(basicLine, i));
            else
                dr[i] = -1;
        }
        dualRelation = dr;
    }

    @Override
    protected void searchBasicColumn() {
        double min = 100;
        int minIndex = -1;
        for (int i = 0; i < dualRelation.length; i++) {
            if(dualRelation[i] < min && dualRelation[i] != -1) {
                min = model.getAByIndex(model.getM(), i);
                minIndex = i;
            }
        }
        basicColumn = minIndex;
    }

    @Override
    protected void searchBasicLine() {
        double min = model.getBByIndex(0);
        int minIndex = 0;
        for (int i = 1; i < model.getM(); i++) {
            if(model.getBByIndex(i) < min) {
                min = model.getBByIndex(i);
                minIndex = i;
            }
        }
        basicLine = minIndex;
    }

    @Override
    public boolean equationHaveSolution() {

        while(true){
            System.out.println("--------------------------------------------------");
            model.printModel();
            if(isSolvedBySimplex()){
                Simplex simplex = new Simplex(this.model, this.basicVariables);
                return simplex.equationHaveSolution();
            }
            if(!isSolved()){
                return false;
            }
            searchBasicLine();
            calculateDualRelations();
            searchBasicColumn();
            changeBasic();

            model = calculateNewSolution();
        }
    }

}
