package de.ckupke.miniserv.request;

import java.util.List;

public class RequestParams {

    private List<String> params;

    public RequestParams(List<String> params) {
        this.params = params;
    }

    public boolean startsWith(String start) {
        return params.size() > 0 && params.get(0).startsWith(start);
    }
}
