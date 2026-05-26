// lumina-network/src/main/java/dev/lumina/network/Connection.java
package dev.lumina.network;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Connection implements Closeable {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final AtomicBoolean open = new AtomicBoolean(true);

    public Connection(Socket socket) throws IOException {
        this.socket = Objects.requireNonNull(socket, "socket");
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    public DataInputStream in() {
        return in;
    }

    public DataOutputStream out() {
        return out;
    }

    public boolean isOpen() {
        return open.get() && !socket.isClosed();
    }

    @Override
    public void close() throws IOException {
        if (open.compareAndSet(true, false)) {
            socket.close();
        }
    }
}
