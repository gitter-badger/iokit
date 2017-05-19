package org.iokit.core.read;

import java.util.Optional;

public class FoldedLineReader extends Reader<String> {

    private final LineReader reader;

    public FoldedLineReader(LineReader reader) {
        super(reader.getInput());
        this.reader = reader;
    }

    @Override
    public Optional<String> readOptional() throws ReaderException {
        Optional<String> firstLine = reader.readOptional().filter(value -> !value.isEmpty());

        if (!firstLine.isPresent())
            return Optional.empty();

        StringBuilder lines = new StringBuilder(firstLine.get());

        while (isTabOrSpace(reader.peek()))
            lines.append("\r\n").append(reader.read());

        return Optional.of(lines.toString());
    }

    private boolean isTabOrSpace(byte b) {
        return b == ' ' || b == '\t';
    }
}
