package de.ckupke.miniserv.request;

import de.ckupke.miniserv.response.HttpNotFoundResponse;
import de.ckupke.miniserv.response.Response;
import de.ckupke.miniserv.resource.Resource;

import java.util.List;

public class HttpRequest implements Request {

    private RequestParams requestParams;

    public HttpRequest(RequestParams requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public Response respond(List<Resource> resources) {
        for (Resource resource : resources) {
            if (resource.matches(requestParams)) {
                return resource.respond(requestParams);
            }
        }
        return new HttpNotFoundResponse();
    }
}
