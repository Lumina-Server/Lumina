package dev.lumina.flux.protocol.packet;

import dev.lumina.flux.protocol.BedrockCodec;
import io.netty.buffer.ByteBuf;

public record StartGamePacket(long entityId, String worldName, float spawnX, float spawnY, float spawnZ) implements BedrockPacket {
    public static final int ID = 2;

    public static StartGamePacket read(ByteBuf in) {
        return new StartGamePacket(
                in.readLong(),
                BedrockCodec.readString(in),
                in.readFloat(),
                in.readFloat(),
                in.readFloat()
        );
    }

    @Override
    public int id() {
        return ID;
    }

    @Override
    public void write(ByteBuf out) {
        out.writeLong(entityId);
        BedrockCodec.writeString(out, worldName);
        out.writeFloat(spawnX);
        out.writeFloat(spawnY);
        out.writeFloat(spawnZ);
    }
}
