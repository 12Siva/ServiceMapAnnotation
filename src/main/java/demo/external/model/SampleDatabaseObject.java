package demo.external.model;

import demo.external.SampleDatabaseDAO;

/**
 * Sample database object that {@link SampleDatabaseDAO} interacts with
 */
public class SampleDatabaseObject {
    private final String identifer;

    public SampleDatabaseObject(String identifer) {
        this.identifer = identifer;
    }

    public String getIdentifer() {
        return this.identifer;
    }
}
