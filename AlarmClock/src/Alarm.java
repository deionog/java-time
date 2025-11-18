import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Alarm implements Runnable {
    private final LocalTime alarmTime;
    private final String filePath;
    private final Scanner scanner;

    Alarm(LocalTime alarmTime, String filePath, Scanner scanner){
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.scanner = scanner;
    }

    @Override
    public void run(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        while(LocalTime.now().isBefore(alarmTime)){
            try {
                Thread.sleep(1000); // Sleep for 1 second
                System.out.print("\rCurrent time: " + LocalTime.now().format(formatter));
            } catch (InterruptedException e) {
                System.out.println("Alarm thread interrupted: " + e.getMessage());
            }
        }

        System.out.println("****** Brrrrrrrringg!!!! *******");
        playSound(filePath);
    }

    private void playSound(String filePath){
        // Placeholder for sound playing logic
        // Actual implementation would depend on the libraries available
        File soundFile = new File(filePath);
        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile)){
            // Implement sound playing logic here using AudioSystem
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Press Enter to stop the alarm...");
            scanner.nextLine();
            clip.stop();
            scanner.close();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.out.println("Audio is unavailable: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }
}
