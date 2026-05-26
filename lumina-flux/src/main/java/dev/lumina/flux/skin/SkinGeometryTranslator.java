package dev.lumina.flux.skin;

public final class SkinGeometryTranslator {
    public String translate(String geometryJson) {
        return geometryJson == null ? "{}" : geometryJson.trim();
    }
}
