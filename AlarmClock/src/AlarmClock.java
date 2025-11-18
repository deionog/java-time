import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AlarmClock {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String filePath = "AlarmClock/src/assets/reggae-busy-signal-sound-de-big-ting.wav"; //"AlarmClock/src/assets/TF046.WAV";

        while(alarmTime == null){
            try {
                System.out.print("Set alarm time (HH:mm:ss): ");
                String inputTime = scanner.nextLine();
                // Parse the input time string to LocalTime
                alarmTime = LocalTime.parse(inputTime, formatter);
                System.out.println("Alarm set for: " + alarmTime);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:mm:ss.");
            } catch(Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        Alarm alarm = new Alarm(alarmTime, filePath, scanner);
        Thread alarmThread = new Thread(alarm);
        alarmThread.start();
    }
}
