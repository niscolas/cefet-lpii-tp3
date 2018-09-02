<%@page import="br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    List<QuartoEstado> listaRegistros = null;
    
    if ((request.getAttribute("listaQuartosEstados")) != null) {
        listaRegistros = (List<QuartoEstado>)request.getAttribute("listaQuartosEstados");
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
                        Estado
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th>
                        Data Limite da Estadia (prevista)
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableDESC()">arrow_drop_down</i></a>
                        <a href="#"><i class="material-icons right table-icon-sort" onclick="sortTableASC()">arrow_drop_up</i></a>
                    </th>
                    <th><center>Ações</center></th>
                 </tr>
             </thead>
             <tbody>
            <%
                if (listaRegistros != null) {
            %>
            <tbody>
                <% 
                    for(QuartoEstado registro : listaRegistros) {
                        int nroQuarto = registro.getNroQuarto();
                        String estadoQuarto;
                        if (registro.isIdtOcupado())
                            estadoQuarto = "Ocupado";
                        else
                            estadoQuarto = "Livre";
                        String datCheckOut = "-";
                        Timestamp datCheckOutTS = registro.getDatCheckOut();
                        if (datCheckOutTS != null) {
                            //Passa datCheckOut de Timestamp para String
                            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                            datCheckOut = formato.format(datCheckOutTS);
                        }
                %>
                <tr>
                    <td><% out.print(nroQuarto); %></td>
                    <td><% out.print(estadoQuarto); %></td>
                    <td><% out.print(datCheckOut); %></td>
                    <td>
                        <center>
                            <% if(registro.isIdtOcupado()){  %>
                                <a href="/hosten/servletweb?acao=ListarDetalhesConta&nroQuarto=<% out.print(nroQuarto); %>" id="info-button" class="waves-effect waves-light btn" ><i class="material-icons left">info_outline</i>Detalhes</a>
                                <a href="/hosten/servletweb?acao=CheckOut&nroQuarto=<% out.print(nroQuarto); %>" id="checkout-button" class="waves-effect waves-light btn" ><i class="material-icons left">remove_circle_outline</i>Check-out</a>
                            <% } else {  %>
                                <a href="/hosten/servletweb?acao=CheckIn&nroQuarto=<% out.print(nroQuarto); %>" id="checkin-button" class="waves-effect waves-light btn" ><i class="material-icons left">add_circle_outline</i>Check-in</a>
                            <% } // if  %>        
                        </center>    
                    </td>
                </tr>
                <% } // for  %>
             </tbody>
            <%} // if%>
         </table>
    </body>
</html>