<%@page import="br.cefetmg.inf.hosten.model.domain.ItemConforto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<ItemConforto> listaItens = null;
    
    if ((request.getAttribute("listaItens")) != null) {
        listaItens = (List<ItemConforto>)request.getAttribute("listaItens");
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
                        Descrição
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th><center>Ações</center></th>
                </tr>
            </thead>
            <%
                if (listaItens != null) {
            %>
            <tbody>
                <% 
                    for(ItemConforto item : listaItens) {
                        String codItem = item.getCodItem();
                        String desItem = item.getDesItem();
                %>
                <tr>
                    <td><% out.print(codItem); %></td>
                    <td><% out.print(desItem); %></td>
                    <td>
                        <center>
                            <a href="/hosten/servletweb?acao=BuscarItemConforto&tipoAcao=Alterar&codItem=<%out.print(codItem);%>"><i class="material-icons table-icon-edit">edit</i></a>
                            <a href="/hosten/servletweb?acao=BuscarItemConforto&tipoAcao=Excluir&codItem=<%out.print(codItem);%>"><i class="material-icons table-icon-delete">delete</i></a>
                        </center>    
                    </td>
                </tr>
                <% } // for  %>
            </tbody>
            <%} // if%>
        </table>
    </body>
</html>



