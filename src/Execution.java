import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Execution {
    public static void main(String[] args) {
        boolean correct_format = true;
        int variable = 0;
        System.out.println("Please input the CNF file path");
        Scanner scan = new Scanner(System.in);
        String cnf_path = scan.nextLine();

        File file = new File(cnf_path);
        //File file = new File("C:\\Users\\Wei Yang\\Dropbox\\SUTD\\Academic\\Term 4\\2D\\Algo_2D\\src\\testfile.cnf");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            // Skip First Line due to commenting
            br.readLine();
            //Split second line to get no of variables
            String[] problem_line = br.readLine().split("\\s+");
            if(problem_line.length!=4){
                System.out.println("INVALID INPUT");
                System.out.println("INVALID PROBLEM LINE FORMAT");
                correct_format = false;
            }
            else {
                variable = Integer.parseInt(problem_line[2]);
                System.out.println("No of variables: " + variable);
            }
            //Get the variable and put into Graph_TwoSat Class

            Graph_TwoSat SAT = new Graph_TwoSat(variable*2);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                String[] tokens = line.split("\\s+");

                if ((tokens.length==3)){
                    //Check for 0 at the 3rd number of the line
                    if (tokens[2].equals("0")){
                        //Valid Line
                        int v = Integer.parseInt(tokens[0]);
                        int w = Integer.parseInt(tokens[1]);
                        //System.out.println("V "+v );
                        //System.out.println("W "+w );
                        //System.out.println("");
                        SAT.OR(v,w);
                    }
                    else{
                        //Invalid, without zero
                        System.out.println("INVALID INPUT");
                        correct_format = false;
                        break;
                    }
                }
                //Violate 3 items rule
                else{
                    System.out.println(tokens[0]);
                    System.out.println("INVALID INPUT");
                    correct_format = false;
                    break;
                }
            }
            if (correct_format == true){
                //It says SAT not declard
                LinkedList<LinkedList<Integer>> solutions = SAT.getSCCs();
                System.out.println("CONNECTED NODES:");
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
            else{
                System.out.println("PLEASE RECTIFY CNF FILE");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid File Path");
        }
    }
}