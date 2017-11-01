/*
import java.util.LinkedList;

public class Test_TwoSat {
    public static void main(String[] args) {
        // Create a graph given in the above diagram
        int variable = 3;
        Graph_TwoSat g = new Graph_TwoSat(variable*2); // Times 2 for vertices

        g.OR(1,2);
        g.OR(-2,-3);
        g.OR(-1,3);
        g.OR(3,-2);

        LinkedList<LinkedList<Integer>> solutions = g.getSCCs();
        System.out.println(solutions);
        // check if satisfiable
        boolean satisfiable = false;
        String solutionString = "";
        for (LinkedList<Integer> solution: solutions) {
            if (solution.size() == variable) {
                satisfiable = true;
            } else {
                continue; // this is actually important
            }
            boolean[] sol = new boolean[variable];
            for (Integer var : solution) {
                sol[Math.abs(var) - 1] = var > 0;
            }
            for (int i =0; i < sol.length; i++) {
                solutionString += (sol[i] ? 1 : 0) + " ";
            }
            solutionString += "\n";
        }
        if (satisfiable) {
            System.out.println("FORMULA SATISFIABLE");
            System.out.println(solutionString);
        } else {
            System.out.println("FORMULA UNSATISFIABLE");
        }
    }

}
*/
