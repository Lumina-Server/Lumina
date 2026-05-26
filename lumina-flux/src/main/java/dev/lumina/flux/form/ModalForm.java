package dev.lumina.flux.form;

public record ModalForm(String title, String content, String acceptText, String declineText) implements FormAPI {
    @Override
    public String toJson() {
        return "{\"type\":\"modal\",\"title\":" + q(title)
                + ",\"content\":" + q(content)
                + ",\"button1\":" + q(acceptText)
                + ",\"button2\":" + q(declineText)
                + "}";
    }

    private static String q(String s) {
        return "\"" + (s == null ? "" : s.replace("\"", "\\\"")) + "\"";
    }
}
