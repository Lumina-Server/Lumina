package dev.lumina.flux.inventory;

public final class SlotTranslator {
    public int toBedrock(int javaSlot) {
        return Math.max(0, javaSlot);
    }

    public int toJava(int bedrockSlot) {
        return Math.max(0, bedrockSlot);
    }
}
