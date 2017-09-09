package de.ckupke.miniserv;

import de.ckupke.miniserv.request.SimpleRequest;

public class Main {

    public static void main(String[] args) {
        new Service(new SimpleRequest(), 8181).start();
    }
}
