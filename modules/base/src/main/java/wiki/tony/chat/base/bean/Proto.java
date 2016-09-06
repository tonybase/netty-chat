package wiki.tony.chat.base.bean;

import java.io.Serializable;

/**
 * 协议类
 */
public class Proto implements Serializable {

    public static final short HEADER_LENGTH = 16;
    public static final short VERSION = 1;
    private int packetLen;
    private short headerLen;
    private short version;
    private int operation;
    private int seqId;
    private byte[] body;

    public int getPacketLen() {
        return packetLen;
    }

    public void setPacketLen(int packetLen) {
        this.packetLen = packetLen;
    }

    public short getHeaderLen() {
        return headerLen;
    }

    public void setHeaderLen(short headerLen) {
        this.headerLen = headerLen;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public int getSeqId() {
        return seqId;
    }

    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        String text;
        if (body == null) {
            text = "null";
        } else {
            text = new String(body);
        }
        return "Proto{" +
                "packetLen=" + packetLen +
                ", headerLen=" + headerLen +
                ", version=" + version +
                ", operation=" + operation +
                ", seqId=" + seqId +
                ", body=" + text +
                '}';
    }
}
