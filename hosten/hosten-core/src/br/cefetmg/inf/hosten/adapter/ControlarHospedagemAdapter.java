package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.model.service.impl.ControlarHospedagem;
import br.cefetmg.inf.hosten.server.ServerUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ControlarHospedagemAdapter implements Runnable {

    private final DatagramSocket socket;
    private final DatagramPacket pacoteRecebido;


    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ControlarHospedagemAdapter(
            DatagramSocket socket, DatagramPacket pacoteRecebido) 
            throws IOException, ClassNotFoundException {
        this.socket = socket;
        this.pacoteRecebido = pacoteRecebido;
        listaRecebida = (ArrayList) ServerUtils.toObject(pacoteRecebido.getData());
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {
            IControlarHospedagem controlarHospedagem = new ControlarHospedagem();

            switch (operacao) {
                case "CheckIn": {
                    String nroQuarto = (String)listaRecebida.get(2);
                    String codCPF = (String)listaRecebida.get(3);
                    int diasEstadia = (int)listaRecebida.get(4);
                    int nroAdultos = (int)listaRecebida.get(5);
                    int nroCriancas = (int)listaRecebida.get(6);
                    
                    objEnviado 
                            = controlarHospedagem
                                    .efetuarCheckIn(
                                            nroQuarto, 
                                            codCPF, 
                                            diasEstadia, 
                                            nroAdultos, 
                                            nroCriancas);
                    break;
                }
                case "CheckOut": {
                    String nroQuarto = (String) listaRecebida.get(2);
                    
                    objEnviado = controlarHospedagem.efetuarCheckOut(nroQuarto);
                    break;
                }
            }
        } catch (Exception ex) {
            tipoRetorno = "Exception";
            objEnviado = ex;
        }
    }

    @Override
    public void run() {
        operacao();

        try {
            ArrayList listaEnviada = new ArrayList();
            listaEnviada.add(tipoRetorno);
            listaEnviada.add(objEnviado);

            byte[] out = new byte[ServerUtils.TAMANHO];
            out = ServerUtils.toByteArray(listaEnviada);

            System.out.println("Pacote de retorno montado!");

            InetAddress IPAddress = pacoteRecebido.getAddress();
            int port = pacoteRecebido.getPort();
            DatagramPacket sendPacket = new DatagramPacket(out, out.length, IPAddress, port);

            System.out.println("pacote de retorno enviado!!");
            socket.send(sendPacket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

