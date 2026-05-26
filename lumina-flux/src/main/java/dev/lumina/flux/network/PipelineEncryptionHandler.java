package dev.lumina.flux.network;

import io.netty.channel.ChannelInboundHandlerAdapter;

public final class PipelineEncryptionHandler extends ChannelInboundHandlerAdapter {
    private final EncryptionInitializer initializer;

    public PipelineEncryptionHandler(EncryptionInitializer initializer) {
        this.initializer = initializer;
    }

    public EncryptionInitializer initializer() {
        return initializer;
    }
}
