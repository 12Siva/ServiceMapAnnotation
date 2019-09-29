package core.jgrapht;

import org.jgrapht.io.ComponentNameProvider;

public class ServiceMapVertexLabelProvider implements ComponentNameProvider<String> {

    @Override
    public String getName(final String component) {
        return component.replace(".", "_");
    }
}
