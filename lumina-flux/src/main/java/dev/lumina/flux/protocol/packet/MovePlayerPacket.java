package dev.lumina.flux.protocol.packet;

import io.netty.buffer.ByteBuf;

public record MovePlayerPacket(long entityId, double x, double y, double z, float yaw, float pitch, boolean onGround) implements BedrockPacket {
    public static final int ID = 3;

    public static MovePlayerPacket read(ByteBuf in) {
        return new MovePlayerPacket(
                in.readLong(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readFloat(),
                in.readFloat(),
                in.readBoolean()
        );
    }

    @Override
    public int id() {
        return ID;
    }

    @Override
    public void write(ByteBuf out) {
        out.writeLong(entityId);
        out.writeDouble(x);
        out.writeDouble(y);
        out.writeDouble(z);
        out.writeFloat(yaw);
        out.writeFloat(pitch);
        out.writeBoolean(onGround);
    }
}
