package de.ckupke.miniserv.request;

import de.ckupke.miniserv.response.Response;
import de.ckupke.miniserv.response.SimpleResponse;

import java.util.List;

public class SimpleRequest implements Request {

    @Override
    public Response handle(List<String> params) {
        return new SimpleResponse();
    }
}
