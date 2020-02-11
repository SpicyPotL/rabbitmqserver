package com.shul.rabbitmqserver.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class ConnectionUtils {

    /**
     * 获取rabbitmq连接
     */
    public static Connection getConnection(String host,int port,String username,String password) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setVirtualHost("/");
        factory.setUsername(username);
        factory.setPassword(password);
        Connection connection = factory.newConnection();
        return connection;
    }
}
