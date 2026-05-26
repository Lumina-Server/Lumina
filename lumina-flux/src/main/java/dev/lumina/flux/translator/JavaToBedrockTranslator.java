package dev.lumina.flux.translator;

import dev.lumina.flux.chunk.BlockPaletteTranslator;
import dev.lumina.flux.entity.BedrockEntityMapper;
import dev.lumina.flux.inventory.BedrockInventoryMapper;
import dev.lumina.flux.item.ItemTranslator;
import dev.lumina.flux.protocol.packet.BedrockPacket;
import dev.lumina.flux.translator.biome.BiomeTranslator;
import dev.lumina.flux.translator.block.BlockTranslator;
import dev.lumina.flux.translator.particle.ParticleTranslator;
import dev.lumina.flux.translator.sound.SoundTranslator;

public final class JavaToBedrockTranslator {
    private final BlockTranslator blockTranslator = new BlockTranslator();
    private final ItemTranslator itemTranslator = new ItemTranslator();
    private final BiomeTranslator biomeTranslator = new BiomeTranslator();
    private final SoundTranslator soundTranslator = new SoundTranslator();
    private final ParticleTranslator particleTranslator = new ParticleTranslator();
    private final BedrockEntityMapper entityMapper = new BedrockEntityMapper();
    private final BedrockInventoryMapper inventoryMapper = new BedrockInventoryMapper();
    private final BlockPaletteTranslator blockPaletteTranslator = new BlockPaletteTranslator();

    public Object translate(Object javaThing) {
        if (javaThing == null) {
            return null;
        }

        if (javaThing instanceof BedrockPacket packet) {
            return packet;
        }

        if (javaThing instanceof String s) {
            return blockTranslator.toBedrockBlock(s);
        }

        return javaThing;
    }

    public BlockTranslator blockTranslator() {
        return blockTranslator;
    }

    public ItemTranslator itemTranslator() {
        return itemTranslator;
    }

    public BiomeTranslator biomeTranslator() {
        return biomeTranslator;
    }

    public SoundTranslator soundTranslator() {
        return soundTranslator;
    }

    public ParticleTranslator particleTranslator() {
        return particleTranslator;
    }

    public BedrockEntityMapper entityMapper() {
        return entityMapper;
    }

    public BedrockInventoryMapper inventoryMapper() {
        return inventoryMapper;
    }

    public BlockPaletteTranslator blockPaletteTranslator() {
        return blockPaletteTranslator;
    }
}
