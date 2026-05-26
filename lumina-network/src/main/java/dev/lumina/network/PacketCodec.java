// lumina-network/src/main/java/dev/lumina/network/PacketCodec.java
package dev.lumina.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface PacketCodec<T extends Packet> {
    T decode(DataInput input) throws IOException;
    void encode(T packet, DataOutput output) throws IOException;
}
