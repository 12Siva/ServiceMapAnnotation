package demo.external;

import java.util.Random;

/**
 *
 */
public class SampleExternalServiceDAO {

    /**
     *
     * @return
     */
    public String getAnIdentifier() {
        return new Random().toString();
    }
}
