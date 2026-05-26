package dev.lumina.flux.network;

import dev.lumina.flux.protocol.packet.BedrockPacket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PacketBatcher {
    private final boolean enabled;
    private final List<BedrockPacket> batch = new ArrayList<>();

    public PacketBatcher(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean enabled() {
        return enabled;
    }

    public void add(BedrockPacket packet) {
        if (enabled) {
            batch.add(packet);
        }
    }

    public List<BedrockPacket> drain() {
        List<BedrockPacket> copy = List.copyOf(batch);
        batch.clear();
        return copy;
    }

    public List<BedrockPacket> peek() {
        return Collections.unmodifiableList(batch);
    }
}
