package PotentialsMethod;

import NorthWestCornerMethod.Coords;
import NorthWestCornerMethod.Model;
import NorthWestCornerMethod.NorthWest;

import java.util.ArrayList;
import java.util.List;

import static SimplexMethod.Model.printArray;

public class Potentials {

    public Potentials(Model model){
        this.model = model;
    }

    Model model;
    NorthWest northWest;

    double[] u; // new double[m]
    double[] v; // new double[n]
    Coords newBasisItem;
    List<Coords> optimalityConditionViolated;

    private void buildPotentialsSystem(){
        u = new double[model.getM()];
        v = new double[model.getN()];
        u[0] = 0;
        for(Coords coord : northWest.Basis){
            if(u[coord.i] != 0 || coord.i == 0){
                v[coord.j] = model.getElemC(coord.i, coord.j) - u[coord.i];
                continue;
            }
            if(v[coord.j] != 0){
                u[coord.i] = model.getElemC(coord.i, coord.j) - v[coord.j];
            }
        }
    }
    private boolean optimalTest(){
        boolean isOptimal = true;
        optimalityConditionViolated = new ArrayList<>();
        for (int i = 0; i < model.getM(); i++) {
            for (int j = 0; j < model.getN(); j++) {
                if(!containedInList(northWest.Basis, i, j)){
                    boolean b = u[i] + v[j] <= model.getElemC(i, j);
                    isOptimal &= b;
                    if(!b)
                        optimalityConditionViolated.add(new Coords(i, j));
                }
            }
        }
        return isOptimal;
    }
    private void searchNewBasis(){
        double max = -1;
        Coords item = new Coords(0, 0);
        for (Coords coords: optimalityConditionViolated){
            double m = u[coords.i] + v[coords.j] - model.getElemC(coords.i, coords.j);
            if(max < m){
                max = m;
                item = new Coords(coords.i, coords.j);
            }
        }
        newBasisItem = item;
    }
    private void buildNewBasicSolution(){

    }

    public void searchSolution(){
        northWest = new NorthWest(model);
        northWest.searchSolution();
        buildPotentialsSystem();
        if(optimalTest()){
            return;
        }
        searchNewBasis();
    }

    public static void main(String[] args) {
        Model model = NorthWestCornerMethod.Client.testInitialization();
        Potentials potentials = new Potentials(model);
        potentials.searchSolution();
    }

    private boolean containedInList(List<Coords> list, int i, int j){
        for(Coords coord: list){
            if(i == coord.i && j == coord.j)
                return true;
        }
        return false;
    }
}
