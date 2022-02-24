package io.github.simplexdev.configurations;

public final class Utils {
    Utils() {}

    public boolean checkForInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean checkForFloat(String input) {
        if (!input.endsWith("F")) {
            return false;
        }

        try {
            Float.parseFloat(input.split("F")[0]);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    public boolean checkForBoolean(String input) {
        if (input.equalsIgnoreCase("true")) {
            return true;
        }
        return input.equalsIgnoreCase("false");
    }

    public boolean checkForLong(String input) {
        if (!input.endsWith("L")) return false;

        try {
            Long.parseLong(input.split("L")[0]);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }
}
