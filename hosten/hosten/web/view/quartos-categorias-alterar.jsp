<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.CategoriaQuarto"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.ItemConforto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <!-- Mostra para o browser que o site é otimizado para mobile -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Categorias de Quartos</title>

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
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/quartos-categorias.js"></script>
    </head>
    
    <body>
        <header>
            <%@include file="menu.jsp"%>
        </header>

        <main>            
            <h4 class="title">Edição de Categorias de Quartos</h4>

            <form id="frmEditItem" method="post">  
                  <%
                    CategoriaQuarto categoriaEditar = null;
                    String codCategoriaEditar = "";
                    String nomCategoriaEditar = "";
                    Double vlrDiariaEditar = null;
                    List<ItemConforto> listaItens = null;
                    
                    if (request.getAttribute("categoriaQuarto") != null) {
                        categoriaEditar = (CategoriaQuarto)request.getAttribute("categoriaQuarto");
                        codCategoriaEditar = categoriaEditar.getCodCategoria();
                        nomCategoriaEditar = categoriaEditar.getNomCategoria();
                        vlrDiariaEditar = categoriaEditar.getVlrDiaria();
                        listaItens = (List<ItemConforto>)request.getAttribute("listaItens");
                    }
                %>   
                <div id="container" class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">filter_3</i>
                            <label for="codCategoria">Código</label>
                            <input id="codCategoria" name="codCategoria" type="number" value="<% out.print(codCategoriaEditar); %>" class="validate" required>
                        </div>
                    </div>
                </div>
                <div id="container" class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">description</i>
                            <label for="nomCategoria">Nome</label>
                            <input id="nomCategoria" name="nomCategoria" type="text" value="<% out.print(nomCategoriaEditar); %>" class="validate" required>
                        </div>
                    </div>
                </div>
                <div id="container" class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">attach_money</i>
                            <label for="vlrDiaria">Valor da Diária</label>
                            <input id="vlrDiaria" name="vlrDiaria" type="number" value="<% out.print(vlrDiariaEditar); %>" class="validate" required>
                        </div>
                    </div>
                </div>
                <div id="container" class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">stars</i>
                            <select multiple id="codItem" name="codItem">
                                <%
                                    if(!listaItens.isEmpty()) {
                                        for(ItemConforto item : listaItens) {
                                            String codItem = item.getCodItem();
                                            String desItem = item.getDesItem();
                                %>	
                                            <option value="<% out.print(codItem); %>"><% out.print(desItem);%></option>
                                <%      } // for 
                                    } else {
                                %>
                                        <option value="" disabled>Ainda não há nenhum item de conforto cadastrado no sistema.</option>
                                <%  
                                    } // else
                                %>
                            </select>
                            <label>Itens de Conforto</label>                                        
                        </div>
                    </div>
                </div>
                <div class="card-action right-align button-box">
                    <button id="submit-button" class="btn waves-effect waves-light" onclick="saveEditDialog('<%out.print(codCategoriaEditar);%>')" type="submit"><i class="material-icons left">check_circle_outline</i>Salvar alterações</button>
                    <a href="/hosten/servletweb?acao=ListarCategoriasQuarto"><button id="cancel-button" class="btn waves-effect waves-light" type="button"><i class="material-icons left">highlight_off</i>Cancelar</button></a>
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
