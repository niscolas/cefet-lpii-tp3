package br.cefetmg.inf.hosten.server;

import br.cefetmg.inf.hosten.adapter.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class HostenServidor implements Runnable {
    private DatagramSocket serverSocket;

    private byte[] in;
    private byte[] out;

    public HostenServidor() throws SocketException {
        serverSocket = new DatagramSocket(ServerUtils.PORTA);
    }

    public void run() {
        System.err.println("SERVER ON");
        
        while (true) {
            try {
                in = new byte[ServerUtils.TAMANHO];
                out = new byte[ServerUtils.TAMANHO];
                
                /*
                 * Create our inbound datagram packet
                 */
                DatagramPacket receivedPacket = new DatagramPacket(in, in.length);
                serverSocket.receive(receivedPacket);
                
                System.err.println("Pacote recebido do cliente!");
                ArrayList listaRecebida = (ArrayList)ServerUtils.toObject(in);
                
                ArrayList listaEnviada = new ArrayList();

                String tipoObjeto = (String) listaRecebida.get(0);
                
                AdapterInterface adapter = null;
                if (tipoObjeto.equals("ItemConforto")) {
                    adapter = new ManterItemConfortoAdapter(listaRecebida);
                } else if (tipoObjeto.equals("Cargo")) {
                    adapter = new ManterCargoAdapter(listaRecebida);
                } else if (tipoObjeto.equals("CategoriaQuarto")) {
                    adapter = new ManterCategoriaQuartoAdapter(listaRecebida);
                } else if (tipoObjeto.equals("Hospede")) {
                    adapter = new ManterHospedeAdapter(listaRecebida);
                } else if (tipoObjeto.equals("Quarto")) {
                    adapter = new ManterQuartoAdapter(listaRecebida);
                } else if (tipoObjeto.equals("ServicoArea")) {
                    adapter = new ManterServicoAreaAdapter(listaRecebida);
                } else if (tipoObjeto.equals("Servico")) {
                    adapter = new ManterServicoAdapter(listaRecebida);
                } else if (tipoObjeto.equals("Usuario")) {
                    adapter = new ManterUsuarioAdapter(listaRecebida);
                }
                
                listaEnviada.add(adapter.getReturnObjectType());
                listaEnviada.add(adapter.getReturnObject());

                out = ServerUtils.toByteArray(listaEnviada);

                InetAddress IPAddress = receivedPacket.getAddress();
                int port = receivedPacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(out, out.length, IPAddress, port);
                serverSocket.send(sendPacket);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Exceção: " + e.getLocalizedMessage());
            }
        }
    }
}