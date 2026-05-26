package dev.lumina.prism;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class PrismPermissionRegistry {
    private final Map<String, PrismPermissionNode> nodes = new ConcurrentHashMap<>();

    public void register(PrismPermissionNode node) {
        registerRecursive(node);
    }

    public Optional<PrismPermissionNode> find(String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(nodes.get(name.toLowerCase()));
    }

    public Map<String, PrismPermissionNode> all() {
        return Collections.unmodifiableMap(nodes);
    }

    public Set<String> expand(String rootPermission) {
        PrismPermissionNode node = find(rootPermission).orElse(null);
        if (node == null) {
            return Set.of();
        }

        Map<String, Boolean> seen = new LinkedHashMap<>();
        Deque<PrismPermissionNode> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            PrismPermissionNode current = stack.pop();
            String key = current.name().toLowerCase();
            if (seen.putIfAbsent(key, Boolean.TRUE) != null) {
                continue;
            }
            for (PrismPermissionNode child : current.children().values()) {
                stack.push(child);
            }
        }

        return seen.keySet();
    }

    private void registerRecursive(PrismPermissionNode node) {
        nodes.put(node.name().toLowerCase(), node);
        for (PrismPermissionNode child : node.children().values()) {
            registerRecursive(child);
        }
    }
}
