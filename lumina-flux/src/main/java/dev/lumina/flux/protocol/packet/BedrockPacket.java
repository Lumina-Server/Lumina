package dev.lumina.flux.protocol.packet;

import io.netty.buffer.ByteBuf;

public interface BedrockPacket {
    int id();
    void write(ByteBuf out);
}
