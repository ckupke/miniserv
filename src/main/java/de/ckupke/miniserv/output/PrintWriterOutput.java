package de.ckupke.miniserv.output;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class PrintWriterOutput implements Output {

    private OutputStream outputStream;

    public PrintWriterOutput(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(String value) {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)),true);
        writer.write(value);
        writer.flush();
        writer.close();
    }
}
