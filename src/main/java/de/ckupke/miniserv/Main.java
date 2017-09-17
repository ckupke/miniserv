package de.ckupke.miniserv;

import de.ckupke.miniserv.endpoint.ServerSocketEndpoint;
import de.ckupke.miniserv.resource.HttpResource;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new ServerSocketEndpoint( 8181).open(
                Arrays.asList(
                        new HttpResource("GET", "/")
                )
        );
    }
}
