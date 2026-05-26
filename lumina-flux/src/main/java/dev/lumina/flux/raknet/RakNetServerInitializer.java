package dev.lumina.flux.raknet;

import dev.lumina.flux.FluxConfig;
import dev.lumina.flux.network.CompressionCodec;
import dev.lumina.flux.network.EncryptionInitializer;
import dev.lumina.flux.network.NettyPipeline;
import dev.lumina.flux.network.PacketBatcher;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.DatagramChannel;

public final class RakNetServerInitializer extends ChannelInitializer<DatagramChannel> {
    private final FluxConfig config;

    public RakNetServerInitializer(FluxConfig config) {
        this.config = config;
    }

    @Override
    protected void initChannel(DatagramChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        NettyPipeline.install(
                pipeline,
                new RakNetCodec(),
                new CompressionCodec(config.compressionLevel()),
                new EncryptionInitializer(config.floodgateAuth()),
                new PacketBatcher(config.packetBatching())
        );
    }
}
