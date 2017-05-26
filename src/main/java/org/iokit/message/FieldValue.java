package org.iokit.message;

import org.iokit.core.validate.Validator;

import org.iokit.core.parse.ValidatingParser;

import java.util.Objects;
import java.util.regex.Pattern;

import static org.iokit.coding.Ascii.*;
import static org.iokit.line.LineTerminator.CR_LF;

public class FieldValue {

    private final Pattern FOLDED_WHITESPACE_PATTERN = Pattern.compile(String.format("%s[%c%c]+", CR_LF, SPACE, TAB));
    private final String UNFOLDED_WHITESPACE = String.valueOf(SPACE);

    private final String value;

    public FieldValue(String value) {
        this.value = value;
    }

    public String getFoldedValue() { // TODO: rename to getValue? See TODO in Field.Writer
        return value;
    }

    public String getUnfoldedValue() {
        return FOLDED_WHITESPACE_PATTERN.matcher(value).replaceAll(UNFOLDED_WHITESPACE);
    }

    @Override
    public String toString() {
        return getUnfoldedValue();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        FieldValue value = (FieldValue) that;
        return Objects.equals(this.value, value.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    public static class Parser extends ValidatingParser<FieldValue> {

        public Parser() {
            this(new FoldedLine.Validator());
        }

        public Parser(Validator<String> validator) {
            super(validator);
        }

        public FieldValue parseValidated(String input) {
            return new FieldValue(input.trim());
        }
    }
}