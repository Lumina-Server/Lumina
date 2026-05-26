// lumina-api/src/main/java/dev/lumina/api/LuminaPlugin.java
package dev.lumina.api;

public interface LuminaPlugin {
    default void onLoad(PluginContext context) {}
    default void onEnable(PluginContext context) {}
    default void onDisable(PluginContext context) {}
}
