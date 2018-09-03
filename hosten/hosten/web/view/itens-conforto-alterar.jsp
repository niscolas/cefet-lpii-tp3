<%@page import="br.cefetmg.inf.hosten.model.domain.ItemConforto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <!-- Mostra para o browser que o site é otimizado para mobile -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Itens de Conforto</title>

        <!-- CSS -->
        <!-- Google Icon Font -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Materialize CSS -->
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/materialize/materialize.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/padrao-tipo-1.css"/>

        <!--  Script -->
        <!-- Import jQuery before Materialize JS  -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/materialize/materialize.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/itens-conforto.js"></script>
    </head>
    
    <body>
        <header>
            <%@include file="menu.jsp"%>
        </header>

        <main>            
            <h4 class="title">Edição de Itens de Conforto</h4>
            
            <form id="frmEditItem" method="post">
                <%
                    ItemConforto itemEditar = null;
                    String codItemEditar = "";
                    String desItemEditar = "";
                    if (request.getAttribute("itemConforto") != null) {
                        itemEditar = (ItemConforto)request.getAttribute("itemConforto");
                        codItemEditar = itemEditar.getCodItem();
                        desItemEditar = itemEditar.getDesItem();
                    }
                %>
                <div id="container" class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">filter_3</i>
                            <label for="codItem">Código</label>
                            <input id="codItem" name="codItem" type="text" value="<% out.print(codItemEditar); %>" class="validate" required>
                        </div>
                    </div>
                </div>
                <div id="container" class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">description</i>
                            <label for="desItem">Descrição</label>
                            <input id="desItem" name="desItem" type="text" value="<% out.print(desItemEditar); %>" class="validate" required>
                        </div>
                    </div>
                </div>
                <div class="card-action right-align button-box">           
                    <button id="submit-button" class="btn waves-effect waves-light" onclick="saveEditDialog('<%out.print(codItemEditar);%>')" type="submit"><i class="material-icons left">check_circle_outline</i>Salvar alterações</button>                      
                    <a href="/hosten/servletweb?acao=ListarItensConforto&tipoAcao=Padrao"><button id="cancel-button" class="btn waves-effect waves-light" type="button"><i class="material-icons left">highlight_off</i>Cancelar</button></a>
                </div>
            </form>
        </main>

        <footer>
            <div class="footer-copyright">
                <div class="container">
                    Feito com  <i class="tiny material-icons">favorite_border</i> por estudantes do CEFET-MG
                </div>
            </div>
        </footer>
    </body>
</html>
