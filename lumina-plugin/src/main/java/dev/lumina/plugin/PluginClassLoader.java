// lumina-plugin/src/main/java/dev/lumina/plugin/PluginClassLoader.java
package dev.lumina.plugin;

import java.net.URL;
import java.net.URLClassLoader;

public final class PluginClassLoader extends URLClassLoader {
    public PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
