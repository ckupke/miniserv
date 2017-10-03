package de.ckupke.miniserv.endpoint;

import de.ckupke.miniserv.input.StreamReaderInput;
import de.ckupke.miniserv.output.PrintWriterOutput;
import de.ckupke.miniserv.request.RequestFactory;
import de.ckupke.miniserv.resource.Resource;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketEndpoint implements Endpoint {

    private ExecutorService executorService;

    private ServerSocket serverSocket;
    private boolean running;
    private List<Resource> resources;
    private RequestFactory requestFactory;

    public ServerSocketEndpoint(int port, List<Resource> resources, RequestFactory requestFactory) {
        this.executorService = Executors.newFixedThreadPool(10);
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {

        }
        this.resources = resources;
        this.requestFactory = requestFactory;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            Socket socket = createSocket();
            executorService.submit(() -> {
                try {
                    new StreamReaderInput(requestFactory, socket.getInputStream())
                            .request()
                            .respond(resources)
                            .write(new PrintWriterOutput(socket.getOutputStream()));
                    close(socket);
                } catch (IOException e) {

                } finally {
                    close(socket);
                }
            });
        }
    }

    @Override
    public void stop() {
        running = false;
        close(serverSocket);
        executorService.shutdown();
    }

    private Socket createSocket() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {

        }
        return socket;
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
