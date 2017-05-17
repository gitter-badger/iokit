package org.iokit.warc.read;

import org.iokit.warc.WarcRecord;
import org.iokit.warc.WarcBody;
import org.iokit.warc.WarcHeader;

import org.iokit.imf.read.MessageReader;

import org.iokit.core.read.LineReader;
import org.iokit.core.read.ParameterizedReader;
import org.iokit.core.read.Reader;

import org.iokit.core.input.LineInputStream;

import java.util.function.BiFunction;

public class WarcRecordReader extends MessageReader<WarcHeader, WarcBody, WarcRecord> {

    public WarcRecordReader(LineInputStream in) {
        this(in, new LineReader(in));
    }

    public WarcRecordReader(LineInputStream in, LineReader lineReader) {
        this(new WarcHeaderReader(lineReader), new WarcBodyReader(in));
    }

    public WarcRecordReader(Reader<WarcHeader> headerReader, ParameterizedReader<WarcHeader, WarcBody> bodyReader) {
        this(headerReader, bodyReader, WarcRecord::new);
    }

    public WarcRecordReader(Reader<WarcHeader> headerReader, ParameterizedReader<WarcHeader, WarcBody> bodyReader,
                            BiFunction<WarcHeader, WarcBody, WarcRecord> recordFactory) {
        super(headerReader, bodyReader, recordFactory);
    }
}
