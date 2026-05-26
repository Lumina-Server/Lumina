package dev.lumina.flux.network;

import io.netty.channel.ChannelDuplexHandler;

public final class PipelineCompressionHandler extends ChannelDuplexHandler {
    private final CompressionCodec codec;

    public PipelineCompressionHandler(CompressionCodec codec) {
        this.codec = codec;
    }

    public CompressionCodec codec() {
        return codec;
    }
}
