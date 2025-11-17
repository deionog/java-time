import java.util.Scanner;

public class CompoundInterestCalculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        double principal = 0.0;
        double rate = 0.0;
        int timesCompounded = 0;
        int years = 0;
        double amount = 0.0;

        System.out.print("Enter the principal amount: ");
        principal = scanner.nextDouble();

        System.out.print("Enter the annual interest rate (in %): ");
        rate = scanner.nextDouble() / 100; // Convert percentage to decimal

        System.out.print("Enter the number of times interest is compounded per year: ");
        timesCompounded = scanner.nextInt();

        System.out.print("Enter the number of years the money is invested: ");
        years = scanner.nextInt();
        amount = principal * Math.pow((1 + rate / timesCompounded), timesCompounded * years);
        System.out.printf("After %d years, the investment will be worth $%.2f%n", years, amount);

        scanner.close();
    }
}
