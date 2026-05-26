package dev.lumina.prism;

import java.net.URL;
import java.net.URLClassLoader;

public final class PrismPluginClassLoader extends URLClassLoader {
    public PrismPluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
