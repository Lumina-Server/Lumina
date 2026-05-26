package dev.lumina.flux.network;

import dev.lumina.flux.raknet.RakNetCodec;
import io.netty.channel.ChannelPipeline;

import java.util.Objects;

public final class NettyPipeline {
    public static final String RAKNET = "raknet";
    public static final String COMPRESSION = "compression";
    public static final String ENCRYPTION = "encryption";

    private NettyPipeline() {}

    public static void install(
            ChannelPipeline pipeline,
            RakNetCodec codec,
            CompressionCodec compression,
            EncryptionInitializer encryption,
            PacketBatcher batcher
    ) {
        Objects.requireNonNull(pipeline, "pipeline");
        Objects.requireNonNull(codec, "codec");
        Objects.requireNonNull(compression, "compression");
        Objects.requireNonNull(encryption, "encryption");
        Objects.requireNonNull(batcher, "batcher");

        pipeline.addLast(RAKNET, codec);
        pipeline.addLast(COMPRESSION, new PipelineCompressionHandler(compression));
        pipeline.addLast(ENCRYPTION, new PipelineEncryptionHandler(encryption));
        pipeline.addLast(new PipelinePacketBatcher(batcher));
    }
}
