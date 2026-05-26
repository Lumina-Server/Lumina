// lumina-plugin/src/main/java/dev/lumina/plugin/LoadedPlugin.java
package dev.lumina.plugin;

import dev.lumina.api.LuminaPlugin;
import dev.lumina.api.PluginDescription;

public record LoadedPlugin(PluginDescription description, LuminaPlugin instance) {}
