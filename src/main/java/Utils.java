import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;

import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;


/*

Ian Flanagan Rollbar 2020
 */

public class Utils {

    private static Rollbar rollbar;

    public static Rollbar createRBInstance(String accessToken, String environment, String version){

        // create a rollbar instance and return a rollbar object
        System.out.println("\nCreate rollbar instance");

        if (accessToken == null) {
            System.out.printf("Please enter a valid rollbar Access Token");
            return null;
        }

        try {

            Config config = withAccessToken(accessToken)
                    .environment(environment)
                    .codeVersion(version)
                    .build();
            rollbar = Rollbar.init(config);

        } catch (Exception e) {
            System.out.printf("Unable to create rollbar instance " +e.getMessage());
            e.printStackTrace();
            return null;
        }
        return rollbar;
    }
    public static Rollbar createRBinstanceConfig(String accessToken, String environment, String version) {

        if (accessToken == null) {
            System.out.printf("Please enter a valid rollbar Access Token");
            return null;
        }
        Config config = withAccessToken(accessToken)
                .environment(environment)
                .codeVersion(version)
                .build();
        rollbar = Rollbar.init(config);
        return rollbar;
    }

}
