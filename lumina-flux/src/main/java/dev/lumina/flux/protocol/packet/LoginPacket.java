package dev.lumina.flux.protocol.packet;

import dev.lumina.flux.protocol.BedrockCodec;
import io.netty.buffer.ByteBuf;

public record LoginPacket(String xuid, String username, String serverAddress, int protocolVersion) implements BedrockPacket {
    public static final int ID = 1;

    public static LoginPacket read(ByteBuf in) {
        return new LoginPacket(
                BedrockCodec.readString(in),
                BedrockCodec.readString(in),
                BedrockCodec.readString(in),
                in.readInt()
        );
    }

    @Override
    public int id() {
        return ID;
    }

    @Override
    public void write(ByteBuf out) {
        BedrockCodec.writeString(out, xuid);
        BedrockCodec.writeString(out, username);
        BedrockCodec.writeString(out, serverAddress);
        out.writeInt(protocolVersion);
    }
}
