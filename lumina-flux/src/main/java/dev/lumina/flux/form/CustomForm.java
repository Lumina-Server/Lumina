package dev.lumina.flux.form;

import java.util.List;

public record CustomForm(String title, List<Element> elements) implements FormAPI {
    public CustomForm {
        elements = elements == null ? List.of() : List.copyOf(elements);
    }

    public record Element(String type, String text, List<String> options) {
        public Element {
            options = options == null ? List.of() : List.copyOf(options);
        }
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"custom_form\",\"title\":").append(q(title)).append(",\"content\":[");
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(',');
            Element e = elements.get(i);
            sb.append("{\"type\":").append(q(e.type()))
              .append(",\"text\":").append(q(e.text()))
              .append(",\"options\":[");
            for (int j = 0; j < e.options().size(); j++) {
                if (j > 0) sb.append(',');
                sb.append(q(e.options().get(j)));
            }
            sb.append("]}");
        }
        sb.append("]}");
        return sb.toString();
    }

    private static String q(String s) {
        return "\"" + (s == null ? "" : s.replace("\"", "\\\"")) + "\"";
    }
}
