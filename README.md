# netty-chat

### Protocol:

| PacketLen  | Header  | Actual Content |
| :----: |:-------:| :-------------:|
| 4byte  | 12byte   |   data  |

###  Header:

> PacketLen 包长度
> HeaderLen 头长度
> version   版本号
> operation 业务操作号
> seqId     包递增号
> body      业务数据

### Architecture:

![image](https://raw.githubusercontent.com/im-qq/netty-chat/master/docs/architecture.png | width=820)


### Operation:

    AuthOperation -> decode<AuthToken> -> AuthService
    MessageOperation -> decode<Message> -> MessageService