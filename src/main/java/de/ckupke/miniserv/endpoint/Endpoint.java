package de.ckupke.miniserv.endpoint;

import de.ckupke.miniserv.resource.Resource;

import java.util.List;

public interface Endpoint {

    void open(List<Resource> resources);

    void close();
}
