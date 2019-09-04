package net36px.passport.app.net.rest.document;

public class CredentialSummary {

    public static class CharRange {

        private char from;
        private char to;

        public char getFrom() {
            return from;
        }

        public void setFrom(char from) {
            this.from = from;
        }

        public char getTo() {
            return to;
        }

        public void setTo(char to) {
            this.to = to;
        }
    }

    private int color;
    private int background;
    private int heading; // 识别码
    private int length;
    private byte[] seed;
    private byte[] salt;
    private CharRange[] charset;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getSeed() {
        return seed;
    }

    public void setSeed(byte[] seed) {
        this.seed = seed;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public CharRange[] getCharset() {
        return charset;
    }

    public void setCharset(CharRange[] charset) {
        this.charset = charset;
    }
}
