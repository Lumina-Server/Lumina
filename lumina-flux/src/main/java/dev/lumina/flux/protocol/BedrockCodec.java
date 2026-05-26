package dev.lumina.flux.protocol;

import dev.lumina.flux.protocol.packet.BedrockPacket;
import dev.lumina.flux.protocol.packet.DisconnectPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class BedrockCodec {
    private final BedrockPacketRegistry registry;

    public BedrockCodec(BedrockPacketRegistry registry) {
        this.registry = Objects.requireNonNull(registry, "registry");
    }

    public BedrockPacket decode(ByteBuf input) {
        int id = input.readInt();
        var decoder = registry.decoder(id);
        if (decoder == null) {
            return new DisconnectPacket("Unknown packet id: " + id);
        }
        return decoder.apply(input);
    }

    public void encode(BedrockPacket packet, ByteBuf output) {
        output.writeInt(packet.id());
        packet.write(output);
    }

    public static void writeString(ByteBuf out, String value) {
        byte[] bytes = (value == null ? "" : value).getBytes(StandardCharsets.UTF_8);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }

    public static String readString(ByteBuf in) {
        int length = in.readInt();
        byte[] bytes = new byte[Math.max(0, length)];
        in.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
