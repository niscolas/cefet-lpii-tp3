package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.impl.ControlarDespesas;
import br.cefetmg.inf.hosten.server.ServerUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ControlarDespesasAdapter implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket pacoteRecebido;


    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ControlarDespesasAdapter(
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
            IControlarDespesas controlarDespesas = new ControlarDespesas();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    QuartoConsumo itemInserir = (QuartoConsumo) listaRecebida.get(2);
                    objEnviado = controlarDespesas.inserir(itemInserir);
                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Despesa>";
                    int seqHospedagem = (int) listaRecebida.get(2);
                    int nroQuarto = (int) listaRecebida.get(3);
                    objEnviado = controlarDespesas.listar(seqHospedagem, nroQuarto);
                    
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    QuartoConsumo qrtConsumo = (QuartoConsumo) listaRecebida.get(2);
                    objEnviado = controlarDespesas.excluir(qrtConsumo);
                    
                    break;
                }
                case "RetornarRelatorioDespesas": {
                    tipoRetorno = "Map<String, Object>";
                    int seqHospedagem = (int) listaRecebida.get(2);
                    int nroQuarto = (int) listaRecebida.get(3);
                    objEnviado 
                            = controlarDespesas
                                    .retornaRelatorioDespesas(
                                            seqHospedagem, nroQuarto);
                    
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