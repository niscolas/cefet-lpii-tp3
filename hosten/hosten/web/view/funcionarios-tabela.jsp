<%@page import="br.cefetmg.inf.hosten.model.domain.Cargo"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Usuario> listaUsuarios = null;
    List<Cargo> listaCargos = null;
    
    if ((request.getAttribute("listaUsuarios")) != null) {
        listaUsuarios = (List<Usuario>)request.getAttribute("listaUsuarios");
    }
    if ((request.getAttribute("listaCargos")) != null) {
        listaCargos = (List<Cargo>)request.getAttribute("listaCargos");
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
                        Email
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th>
                        Cargo
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th><center>Ações</center></th>
                </tr>
            </thead>
            <%
                if (listaUsuarios != null) {
            %>
            <tbody>
                <% 
                    for(Usuario usuario : listaUsuarios) {
                        String codUsuario = usuario.getCodUsuario();
                        String nomUsuario = usuario.getNomUsuario();
                        String desEmailUsuario = usuario.getDesEmail();
                        String codCargoUsuario = usuario.getCodCargo();
                        String nomCargoUsuario = "";
                        if (listaCargos != null) {
                            for(Cargo cargo : listaCargos) {
                                String codCargo = cargo.getCodCargo();
                                if (codCargo.equals(codCargoUsuario)){
                                    nomCargoUsuario = cargo.getNomCargo();
                                }
                            }
                        }
                %>
                <tr>
                    <td><% out.print(codUsuario); %></td>
                    <td><% out.print(nomUsuario); %></td>
                    <td><% out.print(desEmailUsuario); %></td>
                    <td><% out.print(nomCargoUsuario); %></td>
                    <td>
                        <center>
                            <a href="/hosten/servletweb?acao=BuscarUsuario&tipoAcao=Alterar&codUsuario=<%out.print(codUsuario);%>"><i class="material-icons table-icon-edit">edit</i></a>
                            <a href="/hosten/servletweb?acao=BuscarUsuario&tipoAcao=Excluir&codUsuario=<%out.print(codUsuario);%>"><i class="material-icons table-icon-delete">delete</i></a>
                        </center>    
                    </td>
                </tr>
                <% } // for  %>
            </tbody>
            <%} // if %>
        </table>
    </body>
</html>