package de.ckupke.miniserv.resource;

import de.ckupke.miniserv.request.RequestParams;
import de.ckupke.miniserv.response.Response;

public interface Resource {

    Response respond(RequestParams requestParams);

    boolean matches(RequestParams requestParams);
}
