<%@page import="br.cefetmg.inf.hosten.model.domain.Quarto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    List<Quarto> listaRegistros = null;
    List<String> listaNomCategorias = null;
    
    if ((request.getAttribute("listaQuartos")) != null) {
        listaRegistros = (List<Quarto>)request.getAttribute("listaQuartos");
        listaNomCategorias = (List<String>)request.getAttribute("listaNomCategorias");
    }
%>

<html>
    <body>
        <table class="striped">
            <thead>
                <tr>
                    <th>
                        Número
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th>
                        Categoria
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th><center>Ações</center></th>
                </tr>
            </thead>
            <%
                if (listaRegistros != null) {
            %>
            <tbody>
                <% 
                    for(int i = 0; i < listaRegistros.size(); i++) {
                        int nroQuarto = listaRegistros.get(i).getNroQuarto();
                        String codCategoria = listaRegistros.get(i).getCodCategoria();
                        
                        String nomCategoria = listaNomCategorias.get(i); 
                %>
                <tr>
                    <td><% out.print(nroQuarto); %></td>
                    <td><% out.print(nomCategoria); %></td>
                    <td>
                        <center>
                            <!-- CHAMADA DOS MÉTODOS DE EXIBIÇÃO DO MODAL DE EDIÇÃO E EXCLUSÃO-->
                            <a href="#" class="modal-trigger" onclick="showEditDialog('<% out.print(nroQuarto); %>');"><i class="material-icons table-icon-edit">edit</i></a>
                            <a href="#" class="modal-trigger" onclick="showDeleteDialog('<% out.print(nroQuarto); %>');"><i class="material-icons table-icon-delete">delete</i></a>
                        </center>    
                    </td>
                </tr>
                <% } // for  %>
            </tbody>
            <%} // if%>
        </table>
    </body>
</html>