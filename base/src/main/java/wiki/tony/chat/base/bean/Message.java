package wiki.tony.chat.base.bean;

/**
 * 消息类
 */
public class Message {

    private Long to;
    private Long from;
    private String content;

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "to=" + to +
                ", from=" + from +
                ", content='" + content + '\'' +
                '}';
    }
}
