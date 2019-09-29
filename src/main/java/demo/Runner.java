package demo;

import core.ServiceCallsGrapher;
import demo.external.SampleDatabaseDAO;
import demo.external.SampleExternalServiceDAO;
import demo.handler.SampleHandler;

/**
 * Entry point to demo the {@link core.ExternalCall} annotation's functionality
 */
public class Runner {

    public static void main(final String[] args) {
        final SampleExternalServiceDAO sampleExternalServiceDAO = new SampleExternalServiceDAO();
        final SampleDatabaseDAO sampleDatabaseDAO = new SampleDatabaseDAO();
        final SampleHandler sampleHandler = new SampleHandler(sampleExternalServiceDAO, sampleDatabaseDAO);
        sampleHandler.handle();

        // Generate the service map of the sample handler
        final ServiceCallsGrapher serviceCallsGrapher = new ServiceCallsGrapher();
        serviceCallsGrapher.graph(sampleHandler);
    }
}
