package org.iokit.warc;

public final class Separator {

    private Separator() {
    }

    private static final int[] CHARS = new int[]{
        '(', ')', '@', ',', ';', ':', '\\', '/', '[', ']', '?', '=', '{', '}', ' ', '\t'
    };

    private static final CharSequence[] SEQUENCES = new CharSequence[]{
        "&lt;", "&gt;", "&lt;\"&gt"
    };

    public static boolean isSeparatorChar(int c) {
        for (int s : CHARS)
            if (c == s)
                return true;

        return false;
    }
}