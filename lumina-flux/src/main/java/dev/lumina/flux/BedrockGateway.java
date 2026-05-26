// lumina-flux/src/main/java/dev/lumina/flux/BedrockGateway.java
package dev.lumina.flux;

public final class BedrockGateway {
    private final JavaBedrockMapper mapper = new JavaBedrockMapper();
    private final BedrockPacketTranslator translator = new BedrockPacketTranslator();

    public JavaBedrockMapper mapper() {
        return mapper;
    }

    public BedrockPacketTranslator translator() {
        return translator;
    }
}
