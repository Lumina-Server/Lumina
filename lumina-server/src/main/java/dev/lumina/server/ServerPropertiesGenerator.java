package dev.lumina.server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ServerPropertiesGenerator {

    public void generate(Path root) throws IOException {
        Path file = root.resolve("server.properties");

        if (Files.exists(file)) {
            return;
        }

        String content = """
                # =========================================================
                # Lumina Server Properties
                # Generated from Paper/Purpur configuration
                # =========================================================
                
                # ---------------------------------------------------------
                # Network
                # ---------------------------------------------------------
                
                server-ip=
                server-port=25565
                server-name=Unknown Server
                motd=Powered by Lumina
                
                online-mode=true
                prevent-proxy-connections=false
                enable-status=true
                hide-online-players=false
                
                network-compression-threshold=256
                use-native-transport=true
                rate-limit=0
                
                # ---------------------------------------------------------
                # Players
                # ---------------------------------------------------------
                
                max-players=100
                player-idle-timeout=0
                white-list=false
                enforce-whitelist=false
                
                broadcast-console-to-ops=true
                broadcast-rcon-to-ops=true
                
                op-permission-level=4
                function-permission-level=2
                
                # ---------------------------------------------------------
                # Gameplay
                # ---------------------------------------------------------
                
                gamemode=survival
                force-gamemode=false
                difficulty=easy
                hardcore=false
                
                pvp=true
                allow-flight=false
                spawn-protection=16
                
                # ---------------------------------------------------------
                # World
                # ---------------------------------------------------------
                
                level-name=world
                level-seed=
                level-type=minecraft\\:normal
                
                generate-structures=true
                generator-settings={}
                
                max-world-size=29999984
                max-chained-neighbor-updates=1000000
                
                # ---------------------------------------------------------
                # Chunks
                # ---------------------------------------------------------
                
                view-distance=8
                simulation-distance=4
                entity-broadcast-range-percentage=100
                
                sync-chunk-writes=false
                region-file-compression=deflate
                
                # ---------------------------------------------------------
                # Performance
                # ---------------------------------------------------------
                
                max-tick-time=60000
                pause-when-empty-seconds=-1
                
                enable-jmx-monitoring=false
                
                # Lumina Native Engine
                enable-async-chunks=true
                enable-regionized-ticking=true
                enable-parallel-worlds=true
                enable-async-entities=true
                enable-async-pathfinding=true
                
                lumina-worker-threads=4
                lumina-io-threads=2
                lumina-network-threads=2
                
                lumina-cache-chunks=true
                lumina-cache-entities=true
                
                lumina-tick-rate=20
                
                # ---------------------------------------------------------
                # Security
                # ---------------------------------------------------------
                
                enforce-secure-profile=true
                
                enable-query=false
                query.port=25565
                
                enable-rcon=false
                rcon.port=25575
                rcon.password=
                
                bug-report-link=
                
                # ---------------------------------------------------------
                # Resource Pack
                # ---------------------------------------------------------
                
                resource-pack=
                resource-pack-id=
                resource-pack-sha1=
                resource-pack-prompt=
                require-resource-pack=false
                
                initial-enabled-packs=vanilla
                initial-disabled-packs=
                
                # ---------------------------------------------------------
                # Logging
                # ---------------------------------------------------------
                
                debug=false
                log-ips=true
                
                status-heartbeat-interval=0
                
                # ---------------------------------------------------------
                # Text Filtering
                # ---------------------------------------------------------
                
                text-filtering-config=
                text-filtering-version=0
                
                # ---------------------------------------------------------
                # Management Server
                # ---------------------------------------------------------
                
                management-server-enabled=false
                management-server-host=localhost
                management-server-port=0
                
                management-server-allowed-origins=
                
                management-server-secret=
                
                management-server-tls-enabled=true
                management-server-tls-keystore=
                management-server-tls-keystore-password=
                
                # ---------------------------------------------------------
                # Flux (Java ↔ Bedrock)
                # ---------------------------------------------------------
                
                enable-bedrock=true
                bedrock-port=19132
                
                bedrock-motd=Powered by Lumina
                bedrock-max-players=100
                
                bedrock-compression-level=6
                bedrock-clone-java-motd=true
                
                flux-enable-forms=true
                flux-enable-scoreboards=true
                flux-enable-inventory-translator=true
                flux-enable-entity-translator=true
                
                flux-floodgate-support=true
                
                # ---------------------------------------------------------
                # Prism (Paper/Purpur Translator)
                # ---------------------------------------------------------
                
                enable-prism=true
                
                prism-load-bukkit=true
                prism-load-spigot=true
                prism-load-paper=true
                prism-load-purpur=true
                
                prism-strict-plugin-checks=false
                prism-emulate-bukkit-scheduler=true
                prism-emulate-paper-events=true
                
                prism-plugin-load-order=POSTWORLD
                
                # ---------------------------------------------------------
                # ZeroTier Integration
                # ---------------------------------------------------------
                
                enable-zerotier=false
                
                zerotier-network-id=
                zerotier-bind-address=0.0.0.0
                
                zerotier-allow-java=true
                zerotier-allow-bedrock=true
                
                zerotier-auto-detect-ip=true
                
                # ---------------------------------------------------------
                # Lumina Identity
                # ---------------------------------------------------------
                
                lumina-brand=Lumina
                lumina-version=1.0.0
                lumina-distribution=official
                
                # ---------------------------------------------------------
                # Misc
                # ---------------------------------------------------------
                
                accepts-transfers=false
                enable-code-of-conduct=false
                """;

        Files.writeString(file, content, StandardCharsets.UTF_8);
    }
}
