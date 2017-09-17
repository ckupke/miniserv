package de.ckupke.miniserv.input;

import de.ckupke.miniserv.request.HttpRequest;
import de.ckupke.miniserv.request.Request;
import de.ckupke.miniserv.request.RequestParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StreamReaderInput implements Input {

    private InputStream inputStream;

    public StreamReaderInput(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Request request() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> params = new ArrayList<>();
        try {
            while (true) {
                final String line = reader.readLine();
                if (line.isEmpty()) {
                    break;
                }
                params.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpRequest(new RequestParams(params));
    }
}
