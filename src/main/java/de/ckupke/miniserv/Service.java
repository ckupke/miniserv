package de.ckupke.miniserv;

import de.ckupke.miniserv.output.PrintWriterOutput;
import de.ckupke.miniserv.request.Request;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private int port;
    private Request request;

    public Service(Request request, int port) {
        this.request = request;
        this.port = port;
    }

    public void start() {
        Socket socket = null;
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                socket = server.accept();
                serve(socket);
                close(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(socket);
        }
    }

    private void serve(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        List<String> params = new ArrayList<>();
        while (true) {
            final String line = reader.readLine();
            if (line.isEmpty()) {
                break;
            }
            params.add(line);
        }
        request.handle(params).write(new PrintWriterOutput(socket.getOutputStream()));
        close(reader);
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
