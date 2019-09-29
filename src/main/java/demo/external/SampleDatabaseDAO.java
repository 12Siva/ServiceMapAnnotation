package demo.external;

import demo.external.model.SampleDatabaseObject;

/**
 * Sample DAO that interacts with a database
 */
public class SampleDatabaseDAO {

    public void persist(final SampleDatabaseObject sampleDatabaseObject) {
        System.out.println(String.format("Persisting object with identifier: %s", sampleDatabaseObject.getIdentifer()));
    }
}
