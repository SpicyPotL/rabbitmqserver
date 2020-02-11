package com.shul.rabbitmqserver.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;

public class MessageSendReceiver {

    public static final String QUEUE_NAME = "apiTest_A";

    private String host;
    private int port;
    private String username;
    private String password;

    public MessageSendReceiver() {
    }

    public MessageSendReceiver(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void testSendMessage(String topic,String context) {
        try {
            //获取一个链接
            Connection connection = ConnectionUtils.getConnection(host,port,username,password);
            //从连接中获取一个通道
            Channel channel = connection.createChannel();
            // 第一个参数，queueName:对列名称。数据类型：String
            // 第二个参数，durable：是否持久化, 队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库。数据类型：boolean
            // 第三个参数，exclusive：是否排外的。数据类型：boolean
            // 第四个参数，autoDelete：是否自动删除。数据类型：boolean
            // 第五个参数，arguments：参数。数据类型：Map<String, Object>
            channel.queueDeclare(topic, true, false, false, null);
            channel.basicPublish("", topic, null, context.getBytes());
Thread.sleep(10*1000);
            //定义队列的消费者
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("接收到一个消息： " + message);
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);

            //RPC从MQ Server端获取队列中的消息
//            GetResponse response = channel.basicGet(QUEUE_NAME, false);
//            byte[] body = response.getBody();
//            String messsage = new String(body);


            //关闭通道和连接
            channel.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


/*    public static void main(String[] args) {
        try {
            //获取连接
            Connection connection = ConnectionUtils.getConnection();
            //创建频道
            Channel channel = connection.createChannel();
            //定义队列的消费者
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("接收到一个消息： " + message);
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
