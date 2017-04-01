import Utils.InitialStatesEnum;
import Utils.OperatorsEnum;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose experiment:");
        System.out.println("1) Operators");
        System.out.println("2) Initial state");
        System.out.println("3) SA parameters");
        System.out.println("4) Incrementation");
        System.out.println("5) Proportions");
        System.out.println("6) Increment Data Centers");
        System.out.println("7) Heuristic");

        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                Experiments.operators();
                break;
            case 2:
                Experiments.initialStates();
                break;
            case 3:
                Experiments.parameters();
                break;
            case 4:
                Experiments.increments();
                break;
            case 5:
                Experiments.proportion();
                break;
            case 6:
                Experiments.dataCenters();
                break;
            case 7:
                Experiments.heuristic();
                break;
            default:
                System.out.println("Manual search will start:");

                System.out.print("Number of centers: ");
                SensorsBoard.NUMBER_CENTERS = scanner.nextInt();
                System.out.print("Number of centers: ");
                SensorsBoard.NUMBER_SENSORS = scanner.nextInt();
                System.out.print("Centers seed: ");
                SensorsBoard.SEED_CENTERS = scanner.nextInt();
                System.out.print("Sensor seed: ");
                SensorsBoard.SEED_SENSORS = scanner.nextInt();
                SensorsSuccessorsHC.CHOSEN_OPERATOR = OperatorsEnum.SWITCH;

                SensorsBoard board = new SensorsBoard(InitialStatesEnum.DISTANCE_GREEDY);

                    Problem p = new Problem(board, new SensorsSuccessorsHC(), new SensorsGoal(), new SensorsHeuristic());
                    Search alg = new HillClimbingSearch();

                    Long time = System.currentTimeMillis();
                    new SearchAgent(p, alg);
                    time = System.currentTimeMillis() - time;
                }

        // Create the Problem object and instantiate the search algorithm
        /*Problem p = null;
        Search alg = null;
        if (args.length == 0) {
            System.out.println("Use args HC or SA to use HillClimbingSearch or SimulatedAnnealingSearch respectively");
            System.exit(1);
        } else {
            /*System.out.print("Choose initial state generator (DS | SG | DG): ");
            switch (args[0]) {
                case "HC":
                    board = new SensorsBoard(new Scanner(System.in).next());
                    p = new Problem(board, new SensorsSuccessorsHC(), new SensorsGoal(), new SensorsHeuristic());
                    alg = new HillClimbingSearch();
                    break;
                case "SA":
                    board = new SensorsBoard(new Scanner(System.in).next());
                    p = new Problem(board, new SensorsSuccessorsSA(), new SensorsGoal(), new SensorsHeuristic());
                    alg = new SimulatedAnnealingSearch(20000, 100, 50, 0.001);
                    break;
                default:
                    System.out.println("Use args HC or SA to use HillClimbingSearch or SimulatedAnnealingSearch respectively");
                    System.exit(1);
            }
        }

        // Instantiate the SearchAgent object
        SearchAgent agent = new SearchAgent(p, alg);

        // We print the results of the search
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());

        // Get total time
        time = System.currentTimeMillis() - time;

        // We print cost and information
        System.out.println();
        System.out.println("Total cost -> " + SensorsBoard.COST);
        System.out.println("Total information -> " + SensorsBoard.INFORMATION);
        System.out.println("Total time -> " + time + "ms");*/
    }

    private static void printInstrumentation(Properties properties) {
        for (Object o : properties.keySet()) {
            String key = (String) o;
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List actions) {
        for (Object action1 : actions) {
            String action = action1.toString();
            System.out.println(action);
        }
    }

}
