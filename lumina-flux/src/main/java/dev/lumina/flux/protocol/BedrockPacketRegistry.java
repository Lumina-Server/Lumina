package dev.lumina.flux.protocol;

import dev.lumina.flux.protocol.packet.*;
import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class BedrockPacketRegistry {
    private final Map<Integer, Function<ByteBuf, ? extends BedrockPacket>> decoders = new ConcurrentHashMap<>();

    public <T extends BedrockPacket> void register(int id, Function<ByteBuf, T> decoder) {
        decoders.put(id, decoder);
    }

    public Function<ByteBuf, ? extends BedrockPacket> decoder(int id) {
        return decoders.get(id);
    }

    public void registerDefaults() {
        register(LoginPacket.ID, LoginPacket::read);
        register(StartGamePacket.ID, StartGamePacket::read);
        register(MovePlayerPacket.ID, MovePlayerPacket::read);
        register(InventoryTransactionPacket.ID, InventoryTransactionPacket::read);
        register(ChunkPublisherUpdatePacket.ID, ChunkPublisherUpdatePacket::read);
        register(DisconnectPacket.ID, DisconnectPacket::read);
    }
}
