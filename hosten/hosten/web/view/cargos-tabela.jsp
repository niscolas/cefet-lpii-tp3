<%@page import="br.cefetmg.inf.hosten.model.domain.Cargo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Cargo> listaCargos = null;
    
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
                    <th>Telas com acesso permitido</th>
                    <th><center>Ações</center></th>
                </tr>
            </thead>
            <%
                if (listaCargos != null) {
            %>
            <tbody>
                 <% 
                    for(Cargo cargo : listaCargos) {
                        String codCargo = cargo.getCodCargo();
                        String nomCargo = cargo.getNomCargo();
                        
                        //CargoProgramaDAOImpl cargoProgramaDAO = CargoProgramaDAOImpl.getInstance();
                        //CargoPrograma [] codTelas = cargoProgramaDAO.busca(codCargo, "codCargo"); 
                %>
                <tr>
                    <td><% out.print(codCargo); %></td>
                    <td><% out.print(nomCargo); %></td>
                    <td>
                        <% 
                            //for(int j = 0; j < codTelas.length; ++j) {
                                //String codTela = codTelas[j].getCodPrograma();

                                //ProgramaDAO programaDAO = ProgramaDAO.getInstance(); 
                                //Programa [] telas = programaDAO.busca("codprograma", codTela); 
                                //String desPrograma = telas[0].getDesPrograma(); 
                                //out.print(desPrograma + "<br>");
                            //}
                        %>
                    </td>
                    <td>
                        <center>
                            <a href="/hosten/servletweb?acao=BuscarCargo&tipoAcao=Alterar&codCargo=<%out.print(codCargo);%>"><i class="material-icons table-icon-edit">edit</i></a>
                            <a href="/hosten/servletweb?acao=BuscarCargo&tipoAcao=Excluir&codCargo=<%out.print(codCargo);%>"><i class="material-icons table-icon-delete">delete</i></a>
                        </center>    
                    </td>
                </tr>
                <% } // for  %>
            </tbody>
            <%} // if %>
        </table>
    </body>
</html> 