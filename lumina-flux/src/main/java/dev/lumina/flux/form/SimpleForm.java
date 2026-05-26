package dev.lumina.flux.form;

import java.util.List;

public record SimpleForm(String title, String content, List<String> buttons) implements FormAPI {
    public SimpleForm {
        buttons = buttons == null ? List.of() : List.copyOf(buttons);
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"form\",\"title\":").append(quote(title))
          .append(",\"content\":").append(quote(content))
          .append(",\"buttons\":[");
        for (int i = 0; i < buttons.size(); i++) {
            if (i > 0) sb.append(',');
            sb.append("{\"text\":").append(quote(buttons.get(i))).append("}");
        }
        sb.append("]}");
        return sb.toString();
    }

    private static String quote(String s) {
        return "\"" + (s == null ? "" : s.replace("\"", "\\\"")) + "\"";
    }
}
