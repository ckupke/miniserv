package de.ckupke.miniserv.resource;

import de.ckupke.miniserv.content.Content;
import de.ckupke.miniserv.request.RequestParams;
import de.ckupke.miniserv.response.Response;
import de.ckupke.miniserv.response.HttpResponse;

public class HttpResource implements Resource {

    private final String method;
    private final String path;
    private final Content content;

    public HttpResource(String method, String path, Content content) {
        this.method = method;
        this.path = path;
        this.content = content;
    }

    @Override
    public Response respond(RequestParams requestParams) {
        return new HttpResponse(content.generate(requestParams));
    }

    @Override
    public boolean matches(RequestParams requestParams) {
        return requestParams.startsWith(method + " " + path + " ");
    }
}
