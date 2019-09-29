package core;

import core.jgrapht.ServiceMapVertexIdComponentNameProvider;
import core.jgrapht.ServiceMapVertexLabelProvider;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.ExportException;
import org.jgrapht.io.GraphExporter;

import java.io.*;
import java.lang.reflect.Field;

public class ServiceCallsGrapher {
    public void graph(final Object object)  {

        final Graph<String, DefaultEdge> fieldsGraph = getFieldsGraph(object.getClass());

        final ServiceMapVertexIdComponentNameProvider serviceMapVertexIdComponentNameProvider =
                new ServiceMapVertexIdComponentNameProvider();
        final ServiceMapVertexLabelProvider serviceMapVertexLabelProvider = new ServiceMapVertexLabelProvider();

        // Generate the dot file
        final GraphExporter<String, DefaultEdge> exporter =
                new DOTExporter<>(serviceMapVertexIdComponentNameProvider, serviceMapVertexLabelProvider, null);
        final Writer writer = new StringWriter();
        try {
            exporter.exportGraph(fieldsGraph, writer);
        } catch (final ExportException exception) {
            //TODO: Log Error
            System.err.println("Caught export exception: " + exception);
        }

        final String dotFormat = writer.toString();
        System.out.println(dotFormat);
        writeDotSourceToFile(dotFormat);
    }

    private static Graph<String, DefaultEdge> getFieldsGraph(final Class klass) {
        final Graph<String, DefaultEdge> fieldsGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        final String sourceName = klass.getSimpleName();
        fieldsGraph.addVertex(sourceName);
        for (final Field field : klass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExternalCall.class)) {
                final String fieldName = field.getAnnotation(ExternalCall.class).value();
                fieldsGraph.addVertex(fieldName);
                fieldsGraph.addEdge(sourceName, fieldName);
            }
        }
        return fieldsGraph;
    }

    /**
     * Writes the source of the graph in a file, and returns the written file
     * as a File object.
     * @param str Source of the graph (in dot language).
     * @return The file (as a File object) that contains the source of the graph.
     */
    private File writeDotSourceToFile(String str)
    {
        final File temp;
        try {
            temp = File.createTempFile("dorrr",".dot", new File("/tmp"));
            final FileWriter fout = new FileWriter(temp);
            fout.write(str);
            final BufferedWriter br=new BufferedWriter(new FileWriter("dotsource.dot"));
            br.write(str);
            br.flush();
            br.close();
            fout.close();
        }
        catch (final Exception e) {
            System.err.println("Error: I/O error while writing the dot source to temp file!");
            return null;
        }
        return temp;
    }
}
