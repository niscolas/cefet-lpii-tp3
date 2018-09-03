<%@page import="java.util.ArrayList"%>
<%@page import="br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.ItemConforto"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.CategoriaQuarto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    List<CategoriaQuarto> listaCategorias = null;
    ArrayList arrayItensCategoria = null;
    
    if ((request.getAttribute("listaCategorias")) != null) {
        listaCategorias = (List<CategoriaQuarto>)request.getAttribute("listaCategorias");
        arrayItensCategoria = (ArrayList)request.getAttribute("arrayItensCategoria");
    }
%>

<html>
    <body>
        <table class="striped">
            <thead>
                <tr>
                    <th>
                        Código
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th>
                        Nome
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th>
                        Valor da Diária
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th>Itens de Conforto</th>
                    <th><center>Ações</center></th>
                </tr>
            </thead>
            <%
                if (listaCategorias != null) {
            %>
            <tbody>
                <% 
                    int cont = 0;
                    for(CategoriaQuarto categoria : listaCategorias) {    
                        String codCategoria = categoria.getCodCategoria();
                        String nomCategoria = categoria.getNomCategoria();
                        Double vlrDiaria = categoria.getVlrDiaria();
                        List<ItemConforto> listaItens = (List<ItemConforto>)arrayItensCategoria.get(cont);
                %>
                <tr>
                    <td><% out.print(codCategoria); %></td>
                    <td><% out.print(nomCategoria); %></td>
                    <td>R$ <% out.print(vlrDiaria); %></td>
                    <td>
                        <% 
                            if(!listaItens.isEmpty()){
                                for(ItemConforto item: listaItens){
                                    out.print(item.getDesItem() + "<br/>");
                                }
                            } else {
                                out.print("<center> - </center>");
                            }
                            cont++;
                        %>
                    </td>
                    <td>
                        <center>
                            <a href="/hosten/servletweb?acao=BuscarCategoriaQuarto&tipoAcao=Alterar&codCategoria=<%out.print(codCategoria);%>"><i class="material-icons table-icon-edit">edit</i></a>
                            <a href="/hosten/servletweb?acao=BuscarCategoriaQuarto&tipoAcao=Excluir&codCategoria=<%out.print(codCategoria);%>""><i class="material-icons table-icon-delete">delete</i></a>
                        </center>    
                    </td>
                </tr>
                <% } // for  %>
            </tbody>
            <%} // if %>
        </table>    
    </body>
</html>