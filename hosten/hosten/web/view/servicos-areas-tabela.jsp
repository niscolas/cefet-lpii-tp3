<%@page import="br.cefetmg.inf.hosten.model.domain.ServicoArea"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<ServicoArea> listaServicoAreas = null;
    
    if ((request.getAttribute("listaServicoAreas")) != null) {
        listaServicoAreas = (List<ServicoArea>)request.getAttribute("listaServicoAreas");
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
                    <th><center>Ações</center></th>
                </tr>
            </thead>
            <%
                if (listaServicoAreas != null) {
            %>
            <tbody>
                <% 
                    for(ServicoArea servicoArea : listaServicoAreas) {
                        String codServicoArea = servicoArea.getCodServicoArea();
                        String nomServicoArea = servicoArea.getNomServicoArea();
                %>
                <tr>
                    <td><% out.print(codServicoArea); %></td>
                    <td><% out.print(nomServicoArea); %></td>
                    <td>
                        <center>
                            <a href="/hosten/servletweb?acao=BuscarServicoArea&tipoAcao=Alterar&codServicoArea=<%out.print(codServicoArea);%>"><i class="material-icons table-icon-edit">edit</i></a>
                            <a href="/hosten/servletweb?acao=BuscarServicoArea&tipoAcao=Excluir&codServicoArea=<%out.print(codServicoArea);%>"><i class="material-icons table-icon-delete">delete</i></a>
                        </center>      
                    </td>
                </tr>
                <% } // for  %>
            </tbody>
            <%} // if %>
        </table>
    </body>
</html>
