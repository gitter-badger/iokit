package org.iokit.warc.read;

import org.iokit.warc.WarcRecord;

import org.iokit.core.read.ConcatenationReader;
import org.iokit.core.read.LineReader;
import org.iokit.core.read.Reader;

import org.iokit.core.input.CrlfLineInputStream;
import org.iokit.core.input.LineInputStream;
import org.iokit.core.input.MagicInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class WarcReader extends ConcatenationReader<WarcRecord> {

    public static final int DEFAULT_MINIMUM_RECORD_COUNT = 1;

    public WarcReader(String warcFilePath) {
        this(new File(warcFilePath));
    }

    public WarcReader(File warcFile) {
        this(newFileInputStream(warcFile));
    }

    public WarcReader(InputStream in) {
        this(new MagicInputStream(in));
    }

    public WarcReader(MagicInputStream in) {
        this(new CrlfLineInputStream(in));
    }

    public WarcReader(LineInputStream in) {
        this(new LineReader(in));
    }

    public WarcReader(LineReader lineReader) {
        this(new WarcRecordReader(lineReader), new WarcConcatenatorReader(lineReader));
    }

    public WarcReader(WarcRecordReader recordReader, Reader<?> concatenatorReader) {
        super(recordReader, concatenatorReader, DEFAULT_MINIMUM_RECORD_COUNT);
    }

    private static FileInputStream newFileInputStream(File warcFile) {
        try {
            return new FileInputStream(warcFile);
        } catch (FileNotFoundException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
