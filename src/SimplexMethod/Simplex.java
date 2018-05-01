package SimplexMethod;

public class Simplex {

    protected Model model;
    
    protected int[] basicVariables;
    protected int basicColumn;
    protected int basicLine;
    private double[] simplexRelation;

    public Simplex(Model model){
        this.model = model;
        fillBasicVariables();
    }
    public Simplex(Model model, int[] bv){
        this.model = model;
        this.basicVariables = bv;
    }

    private boolean optimalTest(){
        boolean result = true;
        for (int i = 0; i < model.getM() + model.getN(); i++) {
            result &= model.getAByIndex(model.getM(), i) >= 0;
        }
        return result;
    }

    private void calculationResult(){
        calculateX(false);
        calculateZ();
    }

    private void calculateX(boolean x){
        int basicIndex = 0;
        int i = 0;
        while(basicIndex < basicVariables.length){
            if(i == basicVariables[basicIndex]){
                model.setXByIndex(i, model.getBByIndex(basicIndex));
                basicIndex++;
            }
            else{
                model.setXByIndex(i, 0);
            }

            i = (i+1)%(model.getM() + model.getN());
        }
    }
    private boolean crutchCheck(int index){
        for(int i : basicVariables)
            if(i == index)
                return true;
        return false;
    }

    private void calculateZ(){
        model.setZ(model.getBByIndex(model.getM()));
    }

    protected void searchBasicColumn(){
        double min = model.getAByIndex(model.getM(), 0);
        int minIndex = 0;
        for (int i = 1; i < model.getM()+model.getN(); i++) {
            if(model.getAByIndex(model.getM(), i) < min) {
                min = model.getAByIndex(model.getM(), i);
                minIndex = i;
            }
        }
        basicColumn = minIndex;
    }
    private boolean indefiniteTargetFunction(){
        boolean result = true;
        for (int i = 0; i < model.getM(); i++) {
            result &= model.getAByIndex(i, basicColumn) <= 0;
        }
        return result;
    }

    private void calculateSimplexRelations(){
        double[] sr = new double[model.getM()];
        for (int i = 0; i < model.getM(); i++) {
            if(model.getAByIndex(i, basicColumn) > 0)
                sr[i] = model.getBByIndex(i) / model.getAByIndex(i, basicColumn);
            else
                sr[i] = -1;
        }
        simplexRelation = sr;
    }
    protected void searchBasicLine(){
        double min = simplexRelation[0];
        int minIndex = 0;
        for (int i = 0; i < simplexRelation.length; i++) {
            if(simplexRelation[i] < min && simplexRelation[i] != -1) {
                min = simplexRelation[i];
                minIndex = i;
            }
        }
        basicLine = minIndex;
    }

    protected void changeBasic(){
        basicVariables[basicLine] = basicColumn;
    }

    protected Model calculateNewSolution(){
        double[][] newA = new double[model.getM()+1][model.getN()+model.getM()];
        double[] newB = new double[model.getM()+1];
        for (int i = 0; i < newA[0].length; i++) {
            newA[basicLine][i] = model.getAByIndex(basicLine, i) / model.getAByIndex(basicLine, basicColumn);
        }
        newB[basicLine] = model.getBByIndex(basicLine) / model.getAByIndex(basicLine, basicColumn);
        for (int i = 0; i < newA.length; i++) {
            if(i != basicLine){
                for (int j = 0; j < newA[0].length; j++) {
                    newA[i][j] = model.getAByIndex(i, j) + newA[basicLine][j] * (-model.getAByIndex(i, basicColumn));
                }
                newB[i] = model.getBByIndex(i) + newB[basicLine] * (-model.getAByIndex(i, basicColumn));
            }
        }
        return new Model(newA, newB);
    }

    public boolean equationHaveSolution(){

        while(true){
            System.out.println("--------------------------------------------------");
            calculationResult();
            model.printModel();
            model.printResult();
            if(optimalTest()){
                return true;
            }
            searchBasicColumn();
            if(indefiniteTargetFunction()){
                return false;
            }
            calculateSimplexRelations();
            searchBasicLine();
            changeBasic();
            calculateNewSolution();

            model = calculateNewSolution();
        }
    }

    protected void fillBasicVariables(){
        int m = model.getM();
        int n = model.getN();
        basicVariables = new int[m];
        for (int i = n, j = 0; i < n+m; i++, j++) {
            basicVariables[j] = i;
        }
    }
}
