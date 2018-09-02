<%@page import="br.cefetmg.inf.hosten.model.domain.Hospede"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    List<Hospede> listaRegistros = null;
    
    if ((request.getAttribute("listaHospedes")) != null) {
        listaRegistros = (List<Hospede>)request.getAttribute("listaHospedes");
    }
%>

<html>
    <body>
        <table class="striped">
            <thead>
                <tr>
                    <th>
                        CPF
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
                        Telefone                        
                    </th>
                    <th><center>Ações</center></th>
                </tr>
            </thead>
            <%
                if (listaRegistros != null) {
            %>
            <tbody>
                <% 
                    for(Hospede registro : listaRegistros) {
                        String codCPF = registro.getCodCPF();
                        String nomHospede = registro.getNomHospede();
                        String desEmail = registro.getDesEmail();
                        String desTelefone = registro.getDesTelefone();
                %>
                <tr>
                    <td><% out.print(codCPF); %></td>
                    <td><% out.print(nomHospede); %></td>
                    <td><% out.print(desEmail); %></td>
                    <td><% out.print(desTelefone); %></td>
                    <td>
                        <center>
                            <input name="radio-group" type="radio" id='<% out.print(codCPF); %>' onclick="salvaCPF('<% out.print(codCPF); %>');"/>
                            <label for='<% out.print(codCPF); %>'>Selecionar hóspede</label>
                        </center>    
                    </td>
                </tr>
                <% } // for  %>
            </tbody>
            <%} // if%>
        </table>
    </body>
</html>