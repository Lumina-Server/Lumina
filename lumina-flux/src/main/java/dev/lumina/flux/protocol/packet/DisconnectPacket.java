package dev.lumina.flux.protocol.packet;

import dev.lumina.flux.protocol.BedrockCodec;
import io.netty.buffer.ByteBuf;

public record DisconnectPacket(String reason) implements BedrockPacket {
    public static final int ID = 6;

    public static DisconnectPacket read(ByteBuf in) {
        return new DisconnectPacket(BedrockCodec.readString(in));
    }

    @Override
    public int id() {
        return ID;
    }

    @Override
    public void write(ByteBuf out) {
        BedrockCodec.writeString(out, reason);
    }
}
