package dev.lumina.server.flux;

import dev.lumina.logging.LuminaLogger;
import dev.lumina.server.RuntimeConfig;

public final class FluxBootstrap {
    private final BedrockListener bedrockListener = new BedrockListener();
    private final RakNetServer rakNetServer = new RakNetServer();
    private final BedrockTranslator translator = new BedrockTranslator();

    public void start(RuntimeConfig config, LuminaLogger logger) {
        if (!config.enableBedrock()) {
            return;
        }
        logger.info("Flux bootstrap ready on port " + config.bedrockPort());
    }

    public BedrockListener bedrockListener() {
        return bedrockListener;
    }

    public RakNetServer rakNetServer() {
        return rakNetServer;
    }

    public BedrockTranslator translator() {
        return translator;
    }
}
