package dev.lumina.server;

import dev.lumina.logging.LuminaLogger;

import java.io.IOException;
import java.nio.file.Path;

public final class LuminaServerBootstrap {
    private final ResourceConfigExtractor extractor = new ResourceConfigExtractor();
    private final RuntimeConfigLoader configLoader = new RuntimeConfigLoader();
    private final ServerPropertiesGenerator propertiesGenerator = new ServerPropertiesGenerator();

    public LuminaServerImpl bootstrap(Path root) throws IOException {
        RuntimeDirectories directories = RuntimeDirectories.of(root);
        directories.ensure();

        propertiesGenerator.generate(root);
        writeEula(root);

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

        RuntimeConfig config = configLoader.load(root);
        LuminaLogger logger = new LuminaLogger("Lumina");
        return new LuminaServerImpl(root, config, logger);
    }

    private static void writeEula(Path root) throws IOException {
        Path eula = root.resolve("eula.txt");
        if (java.nio.file.Files.exists(eula)) {
            return;
        }
        java.nio.file.Files.writeString(
                eula,
                "# By changing the setting below to TRUE you are indicating your agreement to the Minecraft EULA.\n" +
                "# https://aka.ms/MinecraftEULA\n" +
                "eula=true\n"
        );
    }
}
