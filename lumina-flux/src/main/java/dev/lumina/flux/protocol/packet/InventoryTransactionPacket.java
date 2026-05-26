package dev.lumina.flux.protocol.packet;

import dev.lumina.flux.protocol.BedrockCodec;
import io.netty.buffer.ByteBuf;

public record InventoryTransactionPacket(int windowId, int slot, String itemId, int count) implements BedrockPacket {
    public static final int ID = 4;

    public static InventoryTransactionPacket read(ByteBuf in) {
        return new InventoryTransactionPacket(
                in.readInt(),
                in.readInt(),
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
        out.writeInt(windowId);
        out.writeInt(slot);
        BedrockCodec.writeString(out, itemId);
        out.writeInt(count);
    }
}
