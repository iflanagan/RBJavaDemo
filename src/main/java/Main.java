import com.rollbar.notifier.Rollbar;

import java.io.*;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;

/*

Ian Flanagan Rollbar 2020
 */

public class Main {

private static Rollbar rollbar;
private static String mycount;
public static final String myAccessToken = System.getenv("ROLLBAR_ACCESS_TOKEN");

    public static void main(String[] args) throws Exception {


    //   rollbar = Utils.createRBInstance(MyConfiguration.myAccessToken,MyConfiguration.environment, MyConfiguration.version);
        rollbar = Utils.createRBInstance(myAccessToken,MyConfiguration.environment, MyConfiguration.version);
       rollbar.handleUncaughtErrors();

      //  UnhandledExceptionExample();
     //  handledExceptionExample();
        AnotherException();

       /* mycount = readFile("/Users/ianflanagan/Workspace/Demos/JavaDemo/count");
        System.out.println("Count is: " +mycount);
        delay(5000);

        int mycurrentcount = Integer.parseInt(mycount);
        mycurrentcount++;
        System.out.println("Mycurrentcount value is: " +mycurrentcount);
        mycount = Integer.toString(mycurrentcount);

        try
        {
            System.out.println("\nEntering try portion");
            rollbar.error("I have an isue now");
           rollbar.critical("I had a critical error");
         //   rollbar.log(Throwable error, Level.CRITICAL);
            rollbar.log("\nHello, Rollbar this is number:" +mycount+ " datetime is: " +getDateTime()+  " this is IMF");

        } catch (Exception e) {
            System.out.println("\nError thrown " +e.getMessage());
        } finally {

            System.out.println("\nIn finally portion, close out the rollbar instance");
            writeFile("/Users/ianflanagan/Workspace/Demos/JavaDemo/count",mycount);
            rollbar.close(true);
        }*/

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

        } catch (Exception e) {
         //   rollbar.critical("Major Critical issue here");
            rollbar.error("Invalid input: " +inputedvalue);
            rollbar.warning(e, "Warning error");
            rollbar.warning(e, "invalid Input");
            rollbar.critical(e, "We threw an error");
            rollbar.error(e);
            rollbar.info(e);
            System.out.println("Invalid input " +e.getMessage());
            e.printStackTrace();

        }

        return inputedvalue;

    }

    public static void AnotherException() {

      //  throw mult exceptions to show up in Rollbar

        for (int i=0; i<= 50; i++){

            rollbar.info(new RuntimeException(), "We threw an error" +i+ " in the code");
            rollbar.warning(new RuntimeException());
            rollbar.log(new IllegalThreadStateException());
            rollbar.critical(new InterruptedException()," Critical Error" +i+ " in the code");
          //  throw new RuntimeException();

        }
    }

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

    public static String readFile(String fileName)
    {
        String dataInFile = "";
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){

            dataInFile = br.readLine();
            System.out.println("Found this value in the file: " +dataInFile);

        } catch (IOException e) {
            System.out.println("Can't open file: " +fileName+ " for reading" +e.getMessage());
        }

        return dataInFile;
    }

    public static void writeFile(String fileName, String value)
    {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(value);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void delay(int milliseconds) {

        try
        {
            Thread.sleep(milliseconds);

        } catch (InterruptedException e ) {
            System.out.println("Can't call thread.sleep() " +e.getMessage());
        }
    }

    private  static String getDateTime() {

        System.out.println("Calling getDateTime() method");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dtf.format(now);
        System.out.println("Time is" +currentDateTime);
        System.out.println(dtf.format(now));
        return currentDateTime;
    }
}