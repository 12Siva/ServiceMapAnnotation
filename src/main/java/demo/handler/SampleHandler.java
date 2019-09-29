package demo.handler;

import core.ExternalCall;
import demo.external.SampleDatabaseDAO;
import demo.external.model.SampleDatabaseObject;
import demo.external.SampleExternalServiceDAO;

/**
 * Handler class to simulate a class that handles business logic
 */
public class SampleHandler {
    @ExternalCall("SampleExternalServiceDAO")
    private final SampleExternalServiceDAO sampleExternalServiceDAO;

    @ExternalCall("SampleDatabaseDAO")
    private final SampleDatabaseDAO sampleDatabaseDAO;


    public SampleHandler(final SampleExternalServiceDAO sampleExternalServiceDAO,
                         final SampleDatabaseDAO sampleDatabaseDao) {
        this.sampleExternalServiceDAO = sampleExternalServiceDAO;
        this.sampleDatabaseDAO = sampleDatabaseDao;
    }

    public void handle() {
        final String identifier = this.sampleExternalServiceDAO.getAnIdentifier();
        final SampleDatabaseObject sampleDatabaseObject = new SampleDatabaseObject(identifier);
        this.sampleDatabaseDAO.persist(sampleDatabaseObject);
    }
}
