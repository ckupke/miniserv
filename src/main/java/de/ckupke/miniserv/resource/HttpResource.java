package de.ckupke.miniserv.resource;

import de.ckupke.miniserv.request.RequestParams;
import de.ckupke.miniserv.response.Response;
import de.ckupke.miniserv.response.HttpResponse;

public class HttpResource implements Resource {

    private final String method;
    private final String path;

    public HttpResource(String method, String path) {
        this.method = method;
        this.path = path;
    }

    @Override
    public Response respond(RequestParams requestParams) {
        return new HttpResponse("Hallo Welt!");
    }

    @Override
    public boolean matches(RequestParams requestParams) {
        return requestParams.startsWith(method + " " + path + " ");
    }
}
