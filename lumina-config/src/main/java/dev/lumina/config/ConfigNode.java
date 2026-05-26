// lumina-config/src/main/java/dev/lumina/config/ConfigNode.java
package dev.lumina.config;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ConfigNode {
    Optional<String> getString(String key);
    Optional<Integer> getInt(String key);
    Optional<Boolean> getBoolean(String key);
    Optional<Double> getDouble(String key);
    ConfigNode getSection(String key);
    List<ConfigNode> getList(String key);
    Map<String, Object> raw();
}
