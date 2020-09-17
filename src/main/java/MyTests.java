import com.rollbar.notifier.Rollbar;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyTests {

    private static Rollbar rollbar;

    public static double UnhandledExceptionExample()
    {
        double inputedvalue = 0;
        Scanner input;

        input = new Scanner(System.in);
        System.out.println("Enter a number:");
        inputedvalue = input.nextDouble();
        System.out.println("Number input from user was: " +inputedvalue);
        return inputedvalue;

    }
    public static double handledExceptionExample()
    {
        double inputedvalue = 0;
        Scanner input;

        try
        {
            input = new Scanner(System.in);
            System.out.println("Enter a number:");
            inputedvalue = input.nextDouble();
            System.out.println("Number input from user was: " +inputedvalue);

        } catch (InputMismatchException e) {
            rollbar.critical("Major Critical issue here");
            rollbar.error("Invalid input, I was expecting a double but user entered " +inputedvalue);
            rollbar.warning(e, "Warning error user didn't enter a double");
            rollbar.warning(e, "invalid user Input user entered: " +inputedvalue);
            rollbar.handleUncaughtErrors();
            System.out.println("Invalid input " +e.getMessage());
            e.printStackTrace();

        }
        return inputedvalue;

    }
}
