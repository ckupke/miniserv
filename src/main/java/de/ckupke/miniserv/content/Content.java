package de.ckupke.miniserv.content;

import de.ckupke.miniserv.request.RequestParams;

public interface Content {

    String generate(RequestParams requestParams);
}
