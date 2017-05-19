package org.iokit.core.read;

import java.util.Optional;

public abstract class TransformReader<R extends Reader, T> extends Reader<T> {

    protected final R reader;

    public TransformReader(R reader) {
        super(reader.getInput());
        this.reader = reader;
    }

    @Override
    public Optional<T> readOptional() throws ReaderException {
        return Optional.of(read());
    }
}
