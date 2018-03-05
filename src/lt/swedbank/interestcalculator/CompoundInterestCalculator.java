package lt.swedbank.interestcalculator;
import java.util.Scanner;
import java.math.*;
import java.util.Arrays;

/**
 * Created by p998feq on 2018.03.05.
 */
public class CompoundInterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Amount: ");
        double originalAmount = scanner.nextDouble();
        System.out.print("Interest rate (%): ");
        double interestRate = scanner.nextDouble();
        System.out.print("Period length (years): ");
        int periodLength = scanner.nextInt();
        System.out.print("Compound  frequency: ");
        String compoundFrequency = scanner.next();

        for (int i = 1; i <= periodLength; i++) {
            System.out.printf("Interest amount after year " + i + ": %.2f \n", getInterestAmount(originalAmount, interestRate, i, getCompoundFrequency(compoundFrequency)));
        }

        double[] intermediateInterestAmounts;
        intermediateInterestAmounts = new double[periodLength];
        double[] tempIntermediateInterestAmounts;
        tempIntermediateInterestAmounts = new double[periodLength];

        int pos = 0;
       
        for (int i = 1; i <= periodLength; i++) {
        
            tempIntermediateInterestAmounts[pos] = getInterestAmount(originalAmount, interestRate, i, getCompoundFrequency(compoundFrequency));

            if (pos > 0)
                intermediateInterestAmounts[pos] = tempIntermediateInterestAmounts[pos] - tempIntermediateInterestAmounts[pos - 1];
            else
                intermediateInterestAmounts[pos] = tempIntermediateInterestAmounts[pos];
            pos++;
            }
        System.out.println(Arrays.toString(intermediateInterestAmounts));
        System.out.printf("Total amount: %.2f", originalAmount + getInterestAmount(originalAmount, interestRate, periodLength, getCompoundFrequency(compoundFrequency)));
    }

    private static double getInterestAmount(double originalAmount, double interestRate, int periodLength, int compoundFrequency) {
        return originalAmount * Math.pow((1 + (interestRate / 100) / compoundFrequency), periodLength * compoundFrequency) - originalAmount;
    }

    private static int getCompoundFrequency(String compoundFrequencyString) {
        switch (compoundFrequencyString) {
            case "D":
                return 365;
            case "W":
                return 52;
            case "M":
                return 12;
            case "Q":
                return 4;
            case "H":
                return 2;
            case "Y":
                return 1;
            default:
                return 1;
        }
    }
}