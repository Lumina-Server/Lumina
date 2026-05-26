package dev.lumina.flux.inventory;

public final class BedrockInventoryMapper {
    private final ContainerTranslator containerTranslator = new ContainerTranslator();
    private final SlotTranslator slotTranslator = new SlotTranslator();

    public ContainerTranslator containerTranslator() {
        return containerTranslator;
    }

    public SlotTranslator slotTranslator() {
        return slotTranslator;
    }

    public String toBedrockContainer(String javaContainerId) {
        return containerTranslator.toBedrock(javaContainerId);
    }

    public int toBedrockSlot(int javaSlot) {
        return slotTranslator.toBedrock(javaSlot);
    }
}
