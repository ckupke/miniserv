package de.ckupke.miniserv;

import de.ckupke.miniserv.endpoint.ServerSocketEndpoint;
import de.ckupke.miniserv.request.HttpRequestFactory;
import de.ckupke.miniserv.resource.HttpResource;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        executorService.submit(new ServerSocketEndpoint(
            8181,
            Arrays.asList(new HttpResource("GET", "/", requestParams -> "TEST!")),
            new HttpRequestFactory()));
    }
}
