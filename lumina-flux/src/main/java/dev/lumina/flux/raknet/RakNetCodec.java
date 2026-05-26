package dev.lumina.flux.raknet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.MessageToMessageCodec;
import io.netty.channel.socket.DatagramPacket;

import java.util.List;

public final class RakNetCodec extends MessageToMessageCodec<DatagramPacket, DatagramPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) {
        out.add(msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) {
        out.add(msg);
    }
}
