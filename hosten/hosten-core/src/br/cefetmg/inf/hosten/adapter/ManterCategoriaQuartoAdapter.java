package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.impl.ManterCategoriaQuarto;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.server.ServerUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ManterCategoriaQuartoAdapter implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket pacoteRecebido;

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterCategoriaQuartoAdapter(DatagramSocket socket, DatagramPacket pacoteRecebido) throws IOException, ClassNotFoundException {
        this.socket = socket;
        this.pacoteRecebido = pacoteRecebido;
        listaRecebida = (ArrayList) ServerUtils.toObject(pacoteRecebido.getData());
    }


    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterCategoriaQuarto manterCategoriaQuarto = new ManterCategoriaQuarto();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    CategoriaQuarto itemInserir = (CategoriaQuarto) listaRecebida.get(2);
                    List<ItemConforto> itensCategoria 
                            = (List<ItemConforto>) listaRecebida.get(3);
                    
                    objEnviado = manterCategoriaQuarto.inserir(
                            itemInserir, itensCategoria);

                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<CategoriaQuarto>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterCategoriaQuarto.listar(dadoBusca, coluna);

                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<CategoriaQuarto>";
                    objEnviado = manterCategoriaQuarto.listarTodos();

                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    CategoriaQuarto itemAlterar = (CategoriaQuarto) listaRecebida.get(3);
                    List<ItemConforto> itensCategoria 
                            = (List<ItemConforto>) listaRecebida.get(4);
                    
                    objEnviado = manterCategoriaQuarto
                            .alterar(codRegistro, itemAlterar, itensCategoria);
                    
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterCategoriaQuarto.excluir(codRegistro);
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
