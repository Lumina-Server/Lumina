package dev.lumina.server.config;

import dev.lumina.server.ResourceConfigExtractor;
import dev.lumina.server.ServerPropertiesGenerator;

import java.io.IOException;
import java.nio.file.Path;

public final class ConfigBootstrap {
    private final ResourceConfigExtractor extractor = new ResourceConfigExtractor();
    private final ServerPropertiesGenerator propertiesGenerator = new ServerPropertiesGenerator();

    public void bootstrap(Path root) throws IOException {
        propertiesGenerator.generate(root);

        extractor.extract("lumina.yml", root);
        extractor.extract("prism.yml", root);
        extractor.extract("flux.yml", root);
        extractor.extract("zerotier.yml", root);
        extractor.extract("commands.yml", root);
        extractor.extract("permissions.yml", root);
        extractor.extract("logging.yml", root);
        extractor.extract("worlds.yml", root);

        extractor.extractJson("version.json", root.resolve("config"));
        extractor.extractJson("commands.json", root.resolve("config"));
        extractor.extractJson("permissions.json", root.resolve("config"));
        extractor.extractJson("packs.json", root.resolve("config"));
        extractor.extractJson("bedrock.json", root.resolve("config"));

        extractor.extractJson("plugins.json", root.resolve("cache"));
        extractor.extractJson("registries.json", root.resolve("cache"));
    }
}
