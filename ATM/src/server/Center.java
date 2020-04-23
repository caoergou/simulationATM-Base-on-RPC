package server;

import java.io.IOException;

public interface Center {
    void stop();

    void start() throws IOException;

    void register(String className, Object impl);

    boolean isRunning();

    int getPort();
}
