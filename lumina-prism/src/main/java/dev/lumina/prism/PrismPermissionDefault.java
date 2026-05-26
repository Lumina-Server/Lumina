package dev.lumina.prism;

public enum PrismPermissionDefault {
    TRUE,
    FALSE,
    OP,
    NOT_OP;

    public static PrismPermissionDefault fromRaw(Object raw) {
        if (raw == null) {
            return OP;
        }
        if (raw instanceof Boolean b) {
            return b ? TRUE : FALSE;
        }
        String value = String.valueOf(raw).trim().toLowerCase();
        return switch (value) {
            case "true" -> TRUE;
            case "false" -> FALSE;
            case "op" -> OP;
            case "not op", "not_op", "not-op" -> NOT_OP;
            default -> OP;
        };
    }
}
