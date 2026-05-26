package dev.lumina.flux;

public final class FluxBootstrap {

    public BedrockGateway boot(FluxConfig config) {
        BedrockGateway gateway = new BedrockGateway(config);
        gateway.start();
        return gateway;
    }
}
