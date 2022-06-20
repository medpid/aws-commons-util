package com.me.app;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class App {

    public static void main(String[] args) {

        final String usage = "\n" +
                "Usage:\n" +
                "    <paraName>\n\n" +
                "Where:\n" +
                "    paraName - The name of the parameter.\n";
        getParaValue();
        System.out.println(usage);
    }

    public static void getParaValue() {
        Region region = Region.US_EAST_1;
        SsmClient ssmClient = SsmClient.builder()
                .region(region)
//                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

       try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                .name("my-test-parameter")
                .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
            System.out.println("The parameter value is "+parameterResponse.parameter().value());

        } catch (SsmException e) {
        System.err.println(e.getMessage());
        System.exit(1);
        }
        ssmClient.close();
   }
}
