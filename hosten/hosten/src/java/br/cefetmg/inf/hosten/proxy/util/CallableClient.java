package br.cefetmg.inf.hosten.proxy.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CallableClient implements Callable {
    private DatagramSocket clientSocket;
    private ArrayList lista;
    private InetAddress ServerIPAddress;
    /*
     * Our byte arrays that we'll use to read in and send out to our UDP server
     */
    private byte[] outData;
    private byte[] inData;

    /*
     * Our Client constructor which instantiates our clientSocket
     * and get's our IPAddress
     */
    public CallableClient() throws SocketException, UnknownHostException {
        clientSocket = new DatagramSocket();
        ServerIPAddress = getServerIP();
        lista = null;
    }

    public CallableClient(ArrayList lista) throws SocketException, UnknownHostException {
        clientSocket = new DatagramSocket();
        ServerIPAddress = getServerIP();
        this.lista = lista;
    }
    
    private InetAddress getServerIP () {
        InetAddress IPAddress = null;
        try {
            // AQUI É SETADO O ENDEREÇO IP DO SERVIDOR
            // CASO SEJA ALTERADO, MUDAR APENAS ESTA LINHA
            IPAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            System.err.println("Servidor não encontrado");
        }
        return IPAddress;
    }

    private void shutdown() {
        clientSocket.close();
    }

    @Override
    public Object call() throws Exception {
        try {
            inData = new byte[ProxyUtils.TAMANHO];
            outData = new byte[ProxyUtils.TAMANHO];

            System.err.println("Preparando o pacote para enviar...");
            outData = ProxyUtils.toByteArray(lista);
            DatagramPacket DpSend = new DatagramPacket(outData, outData.length, ServerIPAddress, ProxyUtils.PORTA);
            System.err.println("Enviando o pacote para o server...");
            clientSocket.send(DpSend);

            DatagramPacket in = new DatagramPacket(inData, inData.length);
            clientSocket.receive(in);

            return ProxyUtils.toObject(inData);
        } catch (IOException e) {
            /*
             * Here we need to capture any exceptions thrown by our application
             */
            System.err.println("Exception Thrown: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }
}
