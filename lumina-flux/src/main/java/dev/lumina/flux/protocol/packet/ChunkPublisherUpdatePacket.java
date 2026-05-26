package dev.lumina.flux.protocol.packet;

import io.netty.buffer.ByteBuf;

public record ChunkPublisherUpdatePacket(int chunkX, int chunkZ, int radius) implements BedrockPacket {
    public static final int ID = 5;

    public static ChunkPublisherUpdatePacket read(ByteBuf in) {
        return new ChunkPublisherUpdatePacket(in.readInt(), in.readInt(), in.readInt());
    }

    @Override
    public int id() {
        return ID;
    }

    @Override
    public void write(ByteBuf out) {
        out.writeInt(chunkX);
        out.writeInt(chunkZ);
        out.writeInt(radius);
    }
}
