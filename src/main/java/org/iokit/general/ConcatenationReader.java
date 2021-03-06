package org.iokit.general;

import org.iokit.core.IOKitReader;

import java.util.Optional;

public class ConcatenationReader<V> extends CountingReader<V> {

    public static final boolean DEFAULT_EXPECT_TRAILING_CONCATENATOR = true;

    private boolean expectTrailingConcatenator = DEFAULT_EXPECT_TRAILING_CONCATENATOR;

    private final IOKitReader<V> valueReader;
    private final IOKitReader<Boolean> concatenatorReader;

    public ConcatenationReader(IOKitReader<V> valueReader, IOKitReader<Boolean> concatenatorReader) {
        super(valueReader.in);
        this.valueReader = valueReader;
        this.concatenatorReader = concatenatorReader;
    }

    @Override
    protected Optional<V> readOptionalBeforeCounting() {
        Optional<V> value = readValue();
        value.ifPresent(v -> readConcatenator());
        return value;
    }

    protected Optional<V> readValue() {
        return in.isAtEOF() ?
            Optional.empty() :
            Optional.of(valueReader.read());
    }

    protected void readConcatenator() {
        if (!concatenatorReader.read() && expectTrailingConcatenator)
            throw new EndOfInputException("" +
                "Expected to read a trailing concatenator but actually encountered end of input. " +
                "Call setExpectTrailingConcatenator(false) to avoid this error.");
    }

    public void setExpectTrailingConcatenator(boolean expectTrailingConcatenator) {
        this.expectTrailingConcatenator = expectTrailingConcatenator;
    }
}
