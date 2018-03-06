package lt.swedbank.interestcalculator;
import java.util.Scanner;
import java.math.*;
import java.util.Arrays;

/**
 * Created by p998feq on 2018.03.05.
 */
public class CompoundInterestCalculator {
    //FORMAT YOUR CODE, GADDAMMIT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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

        //Inline your variable declaration with initialization. There is no need to split declaration and initialization into separate statements (lines)
        double[] intermediateInterestAmounts;
        intermediateInterestAmounts = new double[periodLength];
        //You don't really need this temporary array. Just saying...
        double[] tempIntermediateInterestAmounts;
        tempIntermediateInterestAmounts = new double[periodLength];

        //This is not needed, you can use "i" instead of it
        int pos = 0;

        //You should perform "Interest amount after year ..." line printing (for loop way above) and "intermediateInterestAmounts" calculation within the same loop
        for (int i = 1; i <= periodLength; i++) {

            tempIntermediateInterestAmounts[pos] = getInterestAmount(originalAmount, interestRate, i, getCompoundFrequency(compoundFrequency));

            if (pos > 0)
                intermediateInterestAmounts[pos] = tempIntermediateInterestAmounts[pos] - tempIntermediateInterestAmounts[pos - 1];
            else
                intermediateInterestAmounts[pos] = tempIntermediateInterestAmounts[pos];
            pos++;
        }

        //Application outputs incorrect result for "compoundFrequencyString != "Y"". Result (array) should consist of periodLength*compoundFrequency (see explanation in Task 3 description)
        System.out.println(Arrays.toString(intermediateInterestAmounts));
        System.out.printf("Total amount: %.2f", originalAmount + getInterestAmount(originalAmount, interestRate, periodLength, getCompoundFrequency(compoundFrequency)));
    }

    private static double getInterestAmount(double originalAmount, double interestRate, int periodLength, int compoundFrequency) {
        return originalAmount * Math.pow((1 + (interestRate / 100) / compoundFrequency), periodLength * compoundFrequency) - originalAmount;
    }

    //You are calling this 3 times in the same method. Create a separate variable in main method (ex.: int compoundFrequency = getCompoundFrequency(scanner.next()))
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
            //case "Y" is not needed, since "default" returns the same value
            case "Y":
                return 1;
            default:
                return 1;
        }
    }
}
