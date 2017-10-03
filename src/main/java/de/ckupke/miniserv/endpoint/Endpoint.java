package de.ckupke.miniserv.endpoint;

public interface Endpoint extends Runnable {

    @Override
    void run();

    void stop();
}
