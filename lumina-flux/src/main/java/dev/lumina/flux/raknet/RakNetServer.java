package dev.lumina.flux.raknet;

import dev.lumina.flux.FluxConfig;
import dev.lumina.flux.network.CompressionCodec;
import dev.lumina.flux.network.EncryptionInitializer;
import dev.lumina.flux.network.NettyPipeline;
import dev.lumina.flux.network.PacketBatcher;
import dev.lumina.flux.protocol.BedrockCodec;
import dev.lumina.flux.session.SessionManager;
import dev.lumina.flux.session.JavaSessionBridge;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.util.Objects;

public final class RakNetServer {
    private final FluxConfig config;
    private final BedrockCodec codec;
    private final SessionManager sessionManager;
    private final JavaSessionBridge bridge;

    private EventLoopGroup group;
    private Channel channel;

    public RakNetServer(FluxConfig config, BedrockCodec codec, SessionManager sessionManager, JavaSessionBridge bridge) {
        this.config = Objects.requireNonNull(config, "config");
        this.codec = Objects.requireNonNull(codec, "codec");
        this.sessionManager = Objects.requireNonNull(sessionManager, "sessionManager");
        this.bridge = Objects.requireNonNull(bridge, "bridge");
    }

    public void start() throws InterruptedException {
        if (channel != null && channel.isActive()) {
            return;
        }

        group = new NioEventLoopGroup(Math.max(1, config.rakNetThreads()));

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new RakNetServerInitializer(config));

        channel = bootstrap.bind(new InetSocketAddress(config.host(), config.port())).sync().channel();
    }

    public void stop() {
        if (channel != null) {
            channel.close();
            channel = null;
        }
        if (group != null) {
            group.shutdownGracefully();
            group = null;
        }
    }

    public BedrockCodec codec() {
        return codec;
    }

    public SessionManager sessionManager() {
        return sessionManager;
    }

    public JavaSessionBridge bridge() {
        return bridge;
    }
}
