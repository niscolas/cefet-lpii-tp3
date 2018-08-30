package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.model.service.impl.ManterQuarto;
import java.util.ArrayList;
import br.cefetmg.inf.hosten.server.ServerUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ManterQuartoAdapter implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket pacoteRecebido;

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterQuartoAdapter(DatagramSocket socket, DatagramPacket pacoteRecebido) throws IOException, ClassNotFoundException {
        this.socket = socket;
        this.pacoteRecebido = pacoteRecebido;
        listaRecebida = (ArrayList) ServerUtils.toObject(pacoteRecebido.getData());
    }


    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterQuarto manterQuarto = new ManterQuarto();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    Quarto itemInserir = (Quarto) listaRecebida.get(2);
                    objEnviado = manterQuarto.inserir(itemInserir);

                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Quarto>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterQuarto.listar(dadoBusca, coluna);

                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<Quarto>";
                    objEnviado = manterQuarto.listarTodos();

                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    Quarto itemAlterar = (Quarto) listaRecebida.get(3);
                    objEnviado = manterQuarto.alterar(codRegistro, itemAlterar);
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterQuarto.excluir(codRegistro);
                    break;
                }
                case "BuscarSeqHospedagem": {
                    tipoRetorno = "Inteiro";
                    int codRegistro = (int) listaRecebida.get(2);
                    objEnviado = manterQuarto.buscaUltimoRegistroRelacionadoAoQuarto(codRegistro);
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
