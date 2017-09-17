package de.ckupke.miniserv.response;

import de.ckupke.miniserv.output.Output;

public class HttpNotFoundResponse implements Response {

    @Override
    public void write(Output output) {
        output.write(
                "HTTP/1.0 404 OK\r\n"
                        + "Content-Type: text/html\r\n");
    }
}
