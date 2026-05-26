package dev.lumina.flux.raknet;

import dev.lumina.flux.protocol.packet.BedrockPacket;
import dev.lumina.flux.session.BedrockSession;

import java.net.SocketAddress;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;

public final class RakNetSession {
    private final UUID id = UUID.randomUUID();
    private final SocketAddress address;
    private final Queue<BedrockPacket> outbound = new ArrayDeque<>();

    private BedrockSession bedrockSession;

    public RakNetSession(SocketAddress address) {
        this.address = address;
    }

    public UUID id() {
        return id;
    }

    public SocketAddress address() {
        return address;
    }

    public BedrockSession bedrockSession() {
        return bedrockSession;
    }

    public void bedrockSession(BedrockSession bedrockSession) {
        this.bedrockSession = bedrockSession;
    }

    public void enqueue(BedrockPacket packet) {
        outbound.add(packet);
    }

    public BedrockPacket poll() {
        return outbound.poll();
    }
}
