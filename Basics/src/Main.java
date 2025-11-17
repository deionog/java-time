import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Print "Hello, World!" to the console
        System.out.println("Hello, World!");
        System.out.println("-----");
        System.out.println();
        practicePrimitives();
        System.out.println("-----");
        System.out.println();
        practiceCollections();
    }

    public static void practicePrimitives() {
        int age = 25;
        double height = 5.9;
        char middleInitial = 'A';
        boolean isStudent = false;
        String name = "Alice";

        addBlockOpeningLog("Printing primitive variables");
        System.out.println(name + " is " + age + " years old and " + height + " feet tall.");
        System.out.println("Middle Initial: " + middleInitial);
        System.out.println("Is Student: " + isStudent);
        addBlockClosingLog();
    }

    public static void practiceCollections() {
        ArrayList<String> hobbies = new ArrayList<String>();
        hobbies.add("Studying");
        hobbies.add("Reading");
        hobbies.add("Gaming");

        addBlockOpeningLog("Printing hobbies using forEach and for loop:");
        hobbies.forEach(hobby -> System.out.println(hobby));
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        addBlockClosingLog();

        HashMap<String,String> books = new HashMap<String,String>();
        books.put("1984", "George Orwell");
        books.put("To Kill a Mockingbird", "Harper Lee");

        addBlockOpeningLog("Printing books using for loop:");
        for (String book: books.keySet()){
            System.out.println(book + " by " + books.get(book));
        }
        addBlockClosingLog();

        int[] numbers = new int[5];
        addBlockOpeningLog("Filling and printing numbers array:");
        for( int i = 0; i < numbers.length; i++ ) {
            numbers[i] = i * 2;
        }
        for (int number : numbers) {
            System.out.println(number);
        }
        addBlockClosingLog();
    }

    public static void addBlockOpeningLog(String blockLabel) {
        System.out.println(blockLabel);
        System.out.println(">>>");
    }

    public static void addBlockClosingLog() {
        System.out.println("<<<");
        System.out.println();
    }
}
