package io.daff.springboot.exception;

import java.net.ServerSocket;

/**
 * @author daffupman
 * @since 2020/5/25
 */
public class MySocket {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8080);
        serverSocket.accept();
    }
}
