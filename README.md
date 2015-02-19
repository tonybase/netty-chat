# netty-chat

Protocol:
  +----------+----------+----------------+
  |  Length  |  Header  | Actual Content |
  +----------+----------+----------------+
Entity:
  +---------+--------------------+
  | Version | Properties Content |
  +---------+--------------------+

  version(); // 当前实体类版本，当属性有改变时可以通过版本号控制序列化
  encode();  // 序列化，以byte[]方式写入到DataBuffer中
  decode(DataBuffer); // 反序列化，通过DataBuffer转换成Entity
  
Header:
  handlerId 业务分发处理，IMHandlerManager.find(handlerId)，通过handler去处理客户端请求命令。
  commandId 命令动作处理，IMHandler.dispatch(IMConnection, IMRequest)，对IMRequest处理，IMConnection应答或者kill。
  reserved  数据序列处理，对Actual Content进行加解密类型，以及序列化反序列化方式。

# 客户端请求，通过readEntity读取对象数据，以游标方式读取，可以连续读取多个Entity
IMRequest:
  readEntity(Entity.class);
  
# 服务端应答，通过writeEntity写入对象数据，可以连续写入多个Entity
IMResponse:
  writeEntity(entity);

# 长连接，发送数据内容，控制客户端连接
IMConnection:
  sendResponse(imResponse);
  kill();
  
# 业务分发处理
IMHandler:
  dispatch(IMConnection, IMRequest)

# 业务处理方式
  UserHandler <- UserService <- UserDao
  
* 水平扩展设计思路，加入chat-route，在chat-server中注册服务器到route中，以长连接通信（断开时无限重试连接），做send/notify操作。
* 当前服务器hold住当前连接，并注册用户ID到当前node cache中，如果当前node中不存在，在全局nodeAll中获取是否在线信息。
* 如果发送消息时from == to服务器，直接做业务处理，再做应答客户端。
* 如果发送消息时from != to服务器，通过chat-route做send操作，自动转发到相应服务器，再做业务处理，然后应答相应客户端。