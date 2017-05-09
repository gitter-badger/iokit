package org.iokit.imf;

public class Message<H extends MessageHeader, B extends MessageBody> {

    private final H header;
    private final B body;

    public Message(H header, B body) {
        if (header == null)
            throw new IllegalArgumentException("message header may not be null");
        if (body == null)
            throw new IllegalArgumentException("message body may not be null");

        this.header = header;
        this.body = body;
    }

    public H getHeader() {
        return header;
    }

    public B getBody() {
        return body;
    }
}