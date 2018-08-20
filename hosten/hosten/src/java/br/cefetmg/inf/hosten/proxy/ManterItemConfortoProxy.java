package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.proxy.util.ProxyUtils;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ManterItemConfortoProxy implements IManterItemConforto {

    @Override
    public boolean inserir(ItemConforto itemConforto) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ItemConforto");
        lista.add("Inserir");
        lista.add(itemConforto);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public List<ItemConforto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        return null;
    }

    @Override
    public List<ItemConforto> listarTodos() throws NegocioException, SQLException {
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, ItemConforto itemConforto) throws NegocioException, SQLException {
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        return false;
    }
    
    public Object operacaoRegistro (ArrayList lista) {
        DatagramSocket ds;
        DatagramPacket DpReceive;
        
        byte[] vetorByteRecebido = new byte[ProxyUtils.TAMANHO];
        
        try {
            ds = new DatagramSocket();
            InetAddress ip = InetAddress.getLocalHost();
            byte buf[] = null;

            buf = ProxyUtils.toByteArray(lista);
            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);

            ds.send(DpSend);

            DpReceive = new DatagramPacket(vetorByteRecebido, vetorByteRecebido.length);
            ds.receive(DpReceive);

            return ProxyUtils.toObject(vetorByteRecebido);

        }   catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }

}
