package de.ckupke.miniserv;

import de.ckupke.miniserv.endpoint.ServerSocketEndpoint;
import de.ckupke.miniserv.request.HttpRequestFactory;
import de.ckupke.miniserv.request.RequestParams;
import de.ckupke.miniserv.resource.Resource;
import de.ckupke.miniserv.response.HttpResponse;
import de.ckupke.miniserv.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainIntegrationTest {

    @Test
    public void testStartStop() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ServerSocketEndpoint endpoint = new ServerSocketEndpoint(8181, Arrays.asList(createResource()), new HttpRequestFactory());
        executorService.submit(endpoint);
        URL url = new URL("http://localhost:8181");
        URLConnection connection = url.openConnection();
        StringBuilder content = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input;
        while ((input = br.readLine()) != null) {
            content.append(input);
        }
        br.close();

        Assert.assertTrue(content.toString().startsWith("Test OK"));
        endpoint.stop();
    }

    private Resource createResource() {
        return new Resource() {
            @Override
            public Response respond(RequestParams requestParams) {
                return new HttpResponse("Test OK");
            }

            @Override
            public boolean matches(RequestParams requestParams) {
                return true;
            }
        };
    }
}
