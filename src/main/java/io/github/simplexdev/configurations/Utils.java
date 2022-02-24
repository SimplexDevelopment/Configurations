package io.github.simplexdev.configurations;

import org.jetbrains.annotations.Contract;

public final class Utils {
    public Utils() {
    }

    @Contract(pure = true)
    public boolean checkForInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @Contract(pure = true)
    public boolean checkForFloat(String input) {
        if (!input.endsWith("f") || !input.endsWith("F")) {
            return false;
        }

        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    @Contract(pure = true)
    public boolean checkForBoolean(String input) {
        return input.equalsIgnoreCase("true");
    }

    @Contract(pure = true)
    public boolean checkForLong(String input) {
        if (!input.endsWith("L")) return false;

        try {
            Long.parseLong(input);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    @Contract(pure = true)
    public boolean checkForDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }
}
