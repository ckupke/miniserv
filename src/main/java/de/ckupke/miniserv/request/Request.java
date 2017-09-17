package de.ckupke.miniserv.request;

import de.ckupke.miniserv.resource.Resource;
import de.ckupke.miniserv.response.Response;

import java.util.List;

public interface Request {

    Response respond(List<Resource> resources);
}
