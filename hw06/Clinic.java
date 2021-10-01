import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Clinic {
    private File patientFile;
    private int day;

    public Clinic(String fileName) {
        this(new File(fileName));
    }

    public Clinic(File file) {
        this.patientFile = file;
        this.day = 1;
    }

    public String nextDay(String fileName) throws FileNotFoundException {
        return nextDay(new File(fileName));
    }

    public String nextDay(File f) throws FileNotFoundException {
        Scanner appointments = new Scanner(f);
        Scanner takeInput = new Scanner(System.in);
        String updatedInfo = "", msg;
        while (appointments.hasNextLine()) {
            String[] pInfo = appointments.nextLine().split(",");
            String name = pInfo[0], type = pInfo[1], petAttr = pInfo[2], time = pInfo[3];
            if (!(type.equals("Dog") || type.equals("Cat"))) {
                appointments.close();
                throw new InvalidPetException();
            }
            System.out.printf("Consultation for %s the %s at %s.\n", name, type, time);
            msg = String.format("What is the health of %s?", name);
            double health = Math.max(0.0, Math.min(1.0, Double.parseDouble(getUserInput(takeInput, msg, "dbl"))));
            msg = String.format("On a scale of 1 to 10, how much pain is %s in right now?", name);
            int painLevel = Math.max(1, Math.min(10, Integer.parseInt(getUserInput(takeInput, msg, "int"))));
            Pet patient = type.equals("Dog")
                    ? new Dog(name, health, painLevel, Double.parseDouble(petAttr))
                    : new Cat(name, health, painLevel, Integer.parseInt(petAttr));
            patient.speak();
            updatedInfo += String.format("%s,%s,%s,%s,%s,%s,%s,%d\n",
                    patient.getName(), type, petAttr, "Day " + this.day,
                    time, addTime(time, patient.treat()), 
                    String.format("%.2f", health), painLevel);
        }
        appointments.close();
        takeInput.close();
        this.day++;
        return updatedInfo;
    }

    public boolean addToFile(String patientInfo) {
        String[] p = patientInfo.split(",");
        String name = p[0];
        String update = String.format(",%s,%s,%s,%s,%s", p[3], p[4], p[5], p[6], p[7]);
        String patients = "";
        boolean returningPatient = false;
        try (Scanner pReader = new Scanner(patientFile)) {
            while (pReader.hasNextLine()) {
                String pInfo = pReader.nextLine();
                if (name.equals(pInfo.split(",")[0])) {
                    returningPatient = true;
                    patients += pInfo + update + "\n";
                } else {
                    patients += pInfo + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        patients = returningPatient ? patients : patients + patientInfo + "\n";
        try (PrintWriter pWriter = new PrintWriter(patientFile)) {
            pWriter.write(patients);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public String addTime(String timeIn, int treatmentTime) {
        int m = Integer.parseInt(timeIn.substring(2, 4)) + treatmentTime % 60;
        int h = Integer.parseInt(timeIn.substring(0, 2)) + treatmentTime / 60 + m / 60;
        m = m % 60;
        return String.format("%02d%02d", h, m);
    }

    public String getUserInput(Scanner input, String msg, String dataType) {
        String userInput = "";
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(msg);
                userInput = input.nextLine();
                if (dataType.equals("int"))
                    Integer.parseInt(userInput);
                else if (dataType.equals("dbl"))
                    Double.parseDouble(userInput);
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
        return userInput;
    }
}
