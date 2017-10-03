package de.ckupke.miniserv.request;

public class HttpRequestFactory implements RequestFactory {

    @Override
    public Request create(RequestParams requestParams) {
        return new HttpRequest(requestParams);
    }
}
