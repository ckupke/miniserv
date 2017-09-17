package de.ckupke.miniserv.endpoint;

import de.ckupke.miniserv.input.StreamReaderInput;
import de.ckupke.miniserv.output.PrintWriterOutput;
import de.ckupke.miniserv.resource.Resource;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerSocketEndpoint implements Endpoint {

    private ServerSocket serverSocket;
    private boolean opened;

    public ServerSocketEndpoint(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {

        }
    }

    @Override
    public void open(List<Resource> resources) {
        opened = true;
        Socket socket = null;
        try {
            while (opened) {
                socket = serverSocket.accept();
                new StreamReaderInput(socket.getInputStream()).request().respond(resources).write(new PrintWriterOutput(socket.getOutputStream()));
                close(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(socket);
        }
    }

    @Override
    public void close() {
        opened = false;
        close(serverSocket);
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {

            }
        }
    }
}
