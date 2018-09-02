<%@page import="br.cefetmg.inf.hosten.model.domain.rel.Despesa"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        List<Despesa> listaRegistros = null;

        if ((request.getAttribute("listaDespesas")) != null) {
            listaRegistros = (List<Despesa>) request.getAttribute("listaDespesas");
        }
    %>
    <body>
        <table class="striped" id="tabelaCheckOut">
            <table class="striped">
                <thead>
                    <tr>
                        <th>
                            Serviço
                            <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                            <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                        </th>
                        <th>
                            Valor
                            <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                            <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                        </th>
                        <th>
                            Quantidade
                            <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                            <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                        </th>
                    </tr>
                </thead>
                <%
                    if (listaRegistros != null) {
                %>
                <tbody>
                    <%
                        for (Despesa item : listaRegistros) {
                            String desServico = item.getDesServico();
                            Double vlrUnit = item.getVlrUnit();
                            int qtdConsumo = item.getQtdConsumo();
                    %>
                    <tr>
                        <td><% out.print(desServico); %></td>
                        <td><% out.print(vlrUnit); %></td>
                        <td><% out.print(qtdConsumo); %></td>
                    </tr>
                    <% } // for  %>
                </tbody>
                <%} // if%>
            </table>
    </body>
</html>