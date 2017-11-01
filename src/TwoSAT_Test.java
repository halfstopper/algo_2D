import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TwoSAT_Test {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Wei Yang\\Dropbox\\SUTD\\Academic\\Term 4\\2D\\Algo_2D\\src\\testfile.cnf");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("") || line.charAt(0) == 'c' || line.charAt(0) == 'p') {
                    continue;
                }
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    if (token.equals("0")) {
                        System.out.println("end");
                        //formula = formula.addClause(clause);
                        //clause = new Clause();
                    } else {

                        if (token.charAt(0) == '-') {
                            System.out.println("-");
                            System.out.println(token);
                            //clause = clause.add(NegLiteral.make(token.substring(1)));
                        } else {
                            System.out.println("+");
                            System.out.println(token);
                            //clause = clause.add(PosLiteral.make(token));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
