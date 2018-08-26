package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.model.service.impl.ManterHospede;
import java.util.ArrayList;
import br.cefetmg.inf.hosten.server.ServerUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ManterHospedeAdapter implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket pacoteRecebido;

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterHospedeAdapter(DatagramSocket socket, DatagramPacket pacoteRecebido) throws IOException, ClassNotFoundException {
        this.socket = socket;
        this.pacoteRecebido = pacoteRecebido;
        listaRecebida = (ArrayList) ServerUtils.toObject(pacoteRecebido.getData());
    }


    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterHospede manterHospede = new ManterHospede();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    Hospede itemInserir = (Hospede) listaRecebida.get(2);
                    objEnviado = manterHospede.inserir(itemInserir);

                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Hospede>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterHospede.listar(dadoBusca, coluna);

                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<Hospede>";
                    objEnviado = manterHospede.listarTodos();

                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    Hospede itemAlterar = (Hospede) listaRecebida.get(3);
                    objEnviado = manterHospede.alterar(codRegistro, itemAlterar);
                    break;
                }
                /*
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterHospede.excluir(codRegistro);
                    break;
                }
                */
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