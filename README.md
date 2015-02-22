# netty-chat

**Protocol:**<br>

| Length | Header  | Actual Content |
| :----: |:-------:| :-------------:|
| 4byte  | 8byte   |   data length  |

**Header:**<br>

> version   协议版本号，当协议有改变，以及entity属性有改变时通过版本号进行序列化操作。<br>
> handlerId 业务分发处理，IMHandlerManager.find(handlerId)，通过handler去处理客户端请求命令。<br>
> commandId 命令动作处理，IMHandler.dispatch(IMConnection, IMRequest)，对IMRequest处理，IMConnection应答或者kill。<br>
> reserved  数据序列处理，对Actual Content进行加解密类型，以及序列化反序列化方式。<br>

##### 客户端请求，通过readEntity读取对象数据，以游标方式读取，可以连续读取多个Entity
**IMRequest:**<br>
> readEntity(IMSerializer.class);<br>
  
##### 服务端应答，通过writeEntity写入对象数据，可以连续写入多个Entity
**IMResponse:**<br>
> writeEntity(IMSerializer);<br>

##### 长连接，发送数据内容，控制客户端连接
**IMConnection:**<br>
> sendResponse(imResponse);<br>
> kill();<br>
  
##### 业务分发处理
**IMHandler:**<br>
> dispatch(IMConnection, IMRequest)<br>

##### 业务处理流程<br>
> UserHandler <- UserService <- UserDao<br>
  
* 水平扩展设计思路，登录后返回token，并分配chat-server，再进行loginChannel，通过token建立连接，以chat-server持有客户连接上的IMConnection。
* 每个chat-server都有一个node cache，保存当前连接用户的在线信息，如果当前node中不存在，在全局nodeAll中获取是否在线信息。
* 并加入chat-route模块，在chat-server启动时注册服到route中，以长连接通信（断开时无限重试连接），做send/notify操作。
* 如果发送消息时from == to服务器，直接做业务处理，再做应答客户端。
* 如果发送消息时from != to服务器，通过chat-route做send操作，自动转发到相应服务器，再做业务处理，然后应答相应客户端。
