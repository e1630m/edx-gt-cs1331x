import java.util.Scanner;

public class Calculator {
    private static int add(int n1, int n2) {
        return (n1 + n2);
    }

    private static int subtract(int n1, int n2) {
        return (n1 - n2);
    }

    private static double multiply(double n1, double n2) {
        return (n1 * n2);
    }

    private static double divide(double n1, double n2) {
        return (n1 / n2);
    }

    private static String alphabetize(String str1, String str2) {
        char[] c1 = str1.toLowerCase().toCharArray();
        char[] c2 = str2.toLowerCase().toCharArray();
        String ans = "Chicken or Egg.";
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (c1[i] < c2[i]) {
                return str1 + " " + str2;
            } else if (c1[i] > c2[i]) {
                return str2 + " " + str1;
            }
        }
        return ans;
    }

    private static boolean isValidInput(String str, int idx) {
        String[] split = str.split(" ");
        if (split.length != 2) {
            return false;
        }
        if (idx < 2) {
            for (int i = 0; i < split[0].length(); i++) {
                char c = split[0].charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            for (int i = 0; i < split[1].length(); i++) {
                char c = split[1].charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
        } else if (idx < 4) {
            try {
                Float.parseFloat(split[0]);
                Float.parseFloat(split[1]);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            if (!(split[0].matches("[a-zA-Z]+")) || !(split[1].matches("[a-zA-Z]+"))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] validOps = {"add", "subtract", "multiply", "divide", "alphabetize"};

        System.out.println("List of operations: add subtract multiply divide alphabetize");
        System.out.println("Enter an operation:");
        String operation = input.nextLine().toLowerCase();

        boolean opIsValid = false;
        int idx = 0;
        for (String x : validOps) {
            if (x.equals(operation)) {
                opIsValid = true;
                break;
            }
            idx++;
        }
        if (opIsValid == true) {
            if (idx < 2) {
                System.out.println("Enter two integers: ");
            } else if (idx < 4) {
                System.out.println("Enter two doubles: ");
            } else {
                System.out.println("Enter two words: ");
            }
            String in = input.nextLine();
            if (isValidInput(in, idx)) {
                String[] vals = in.split(" ");
                switch (idx) {
                    case 0:
                        System.out.printf("Answer: %d", add(Integer.parseInt(vals[0]), Integer.parseInt(vals[1])));
                        break;
                    case 1:
                        System.out.printf("Answer: %d", subtract(Integer.parseInt(vals[0]), Integer.parseInt(vals[1])));
                        break;
                    case 2:
                        System.out.printf("Answer: %.2f", multiply(Double.parseDouble(vals[0]), Double.parseDouble(vals[1])));
                        break;
                    case 3:
                        System.out.printf("Answer: %.2f", divide(Double.parseDouble(vals[0]), Double.parseDouble(vals[1])));
                        break;
                    case 4: 
                        String ans = alphabetize(vals[0], vals[1]);
                        if (ans == "Chicken or Egg.") {
                            System.out.printf("Answer: %s", ans);
                        } else {
                            System.out.printf("%s comes before %s alphabetically.", ans.split(" ")[0], ans.split(" ")[1]);
                        }
                        break;
                }
            } else {
                System.out.println("Invalid input entered. Terminating...");
            }
        } else {
            System.out.println("Invalid input entered. Terminating...");
        }
        input.close();
    }
}
