// lumina-network/src/main/java/dev/lumina/network/PacketRegistry.java
package dev.lumina.network;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PacketRegistry {
    private final Map<Integer, PacketCodec<? extends Packet>> codecs = new ConcurrentHashMap<>();

    public <T extends Packet> void register(int id, PacketCodec<T> codec) {
        codecs.put(id, codec);
    }

    public PacketCodec<? extends Packet> get(int id) {
        return codecs.get(id);
    }
}
