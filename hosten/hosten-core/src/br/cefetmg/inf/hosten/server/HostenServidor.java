package br.cefetmg.inf.hosten.server;

import br.cefetmg.inf.hosten.adapter.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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

                DatagramPacket receivedPacket = new DatagramPacket(in, in.length);
                serverSocket.receive(receivedPacket);

                System.err.println("Pacote recebido do cliente!");
                ArrayList listaRecebida = (ArrayList) ServerUtils.toObject(in);

                String tipoObjeto = (String) listaRecebida.get(0);

                Thread t = null;

                switch (tipoObjeto) {
                    case "Despesa": 
                        ControlarDespesasAdapter controlarDespesasAdapter 
                                = new ControlarDespesasAdapter(
                                        serverSocket, receivedPacket);
                        t = new Thread(controlarDespesasAdapter);
                        t.start();
                        break;
                    case "Hospedagem":
                        ControlarHospedagemAdapter controlarHospedagemAdapter 
                                = new ControlarHospedagemAdapter(
                                        serverSocket, receivedPacket);
                        t = new Thread(controlarHospedagemAdapter);
                        t.start();
                        break;
                    case "ItemConforto":
                        ManterItemConfortoAdapter itemConfortoAdapter = new ManterItemConfortoAdapter(serverSocket, receivedPacket);
                        t = new Thread(itemConfortoAdapter);
                        t.start();
                        break;
                    case "Cargo":
                        ManterCargoAdapter cargoAdapter = new ManterCargoAdapter(serverSocket, receivedPacket);
                        t = new Thread(cargoAdapter);
                        t.start();
                        break;
                    case "CategoriaQuarto":
                        ManterCategoriaQuartoAdapter categoriaAdapter = new ManterCategoriaQuartoAdapter(serverSocket, receivedPacket);
                        t = new Thread(categoriaAdapter);
                        t.start();
                        break;
                    case "Hospede":
                        ManterHospedeAdapter hospedeAdapter = new ManterHospedeAdapter(serverSocket, receivedPacket);
                        t = new Thread(hospedeAdapter);
                        t.start();
                        break;
                    case "Quarto":
                        ManterQuartoAdapter quartoAdapter = new ManterQuartoAdapter(serverSocket, receivedPacket);
                        t = new Thread(quartoAdapter);
                        t.start();
                        break;
                    case "ServicoArea":
                        ManterServicoAreaAdapter servicoAreaAdapter = new ManterServicoAreaAdapter(serverSocket, receivedPacket);
                        t = new Thread(servicoAreaAdapter);
                        t.start();
                        break;
                    case "Servico":
                        ManterServicoAdapter servicoAdapter = new ManterServicoAdapter(serverSocket, receivedPacket);
                        t = new Thread(servicoAdapter);
                        t.start();
                        break;
                    case "Usuario":
                        ManterUsuarioAdapter usuarioAdapter = new ManterUsuarioAdapter(serverSocket, receivedPacket);
                        t = new Thread(usuarioAdapter);
                        t.start();
                        break;
                    default:
                        break;
                }

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Exceção: " + e.getLocalizedMessage());
            }
        }
    }
}
