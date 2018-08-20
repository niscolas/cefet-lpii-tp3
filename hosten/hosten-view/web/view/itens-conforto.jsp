<%--<jsp:include page="/WEB-INF/controleAcesso.jsp" flush="false">
    <jsp:param name="nomePagina" value="Tela de Itens de Conforto"/>
</jsp:include>--%>
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
            <h4 class="title">Visualização de Itens de Conforto</h4>
            
            <div id="container" class="row">
                <div class="col s9 search-box">
                    <div class="input-field">
                        <i class="material-icons prefix">search</i>
                        <label for="search">Buscar item de conforto por... (selecione um campo ao lado)</label>
                        <input id="search" type="search">
                    </div>
                </div>

                <div class="col s3 select-box">
                    <div class="input-field">
                        <select>
                            <option value="1">Código</option>
                            <option value="2">Descrição</option>
                        </select>
                        <label>Filtro</label>
                    </div>
                </div>
            </div>

            <div>
                <jsp:include page="itens-conforto-tabela.jsp"></jsp:include>
            </div>
                
            <div class="card-action right-align button-box">
                <button data-target="modal-add-item" id="add-button" class="btn waves-effect waves-light modal-trigger"><i class="material-icons left">add_circle_outline</i>Novo Item de Conforto</button>
            </div>
            
            <!-- Modals -->  
            <!-- Adicionar -->
            <div id="modal-add-item" class="modal">
                <div class="modal-content">
                    <h4 class="title">Cadastro de Itens de Conforto</h4>
                    <form id="frmInsertItem" method="post">
                        <div id="modal-container">
                            <div class="row">
                                <div class="col s12 form-input">
                                    <div class="input-field">
                                        <i class="material-icons prefix">filter_3</i>
                                        <label for="codItem">Código</label>
                                        <input id="codItem" name="codItem" type="number" class="validate" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 form-input">
                                    <div class="input-field">
                                        <i class="material-icons prefix">description</i>
                                        <label for="desItem">Descrição</label>
                                        <input id="desItem" name="desItem" type="text" class="validate" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-action right-align button-box">
							<!-- CHAMADA DE MÉTODO PARA REGISTRAR A OPERAÇÃO -->
                            <button id="submit-button" class="btn waves-effect waves-light" onclick="saveInsertDialog()"><i class="material-icons left">check_circle_outline</i>Salvar item de conforto</button>
							<!-- CHAMADA DE MÉTODO PARA FECHAR O MODAL -->
                            <button id="cancel-button" class="btn waves-effect waves-light" onclick="cancelInsertDialog()"><i class="material-icons left">highlight_off</i>Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <!-- Editar -->
            <div id="modal-edit-item" class="modal">
                <div class="modal-content">
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
                        <input type="hidden" name="codItemAntigo" id="codItemAntigo" value="<%out.println(codItemEditar);%>">
                        <div id="modal-container">
                            <div class="row">
                                <div class="col s12 form-input">
                                    <div class="input-field">
                                        <i class="material-icons prefix">filter_3</i>
                                        <label for="codItem">Código</label>
                                        <input id="codItem" name="codItem" type="text" value="<% out.print(codItemEditar); %>" class="validate" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 form-input">
                                    <div class="input-field">
                                        <i class="material-icons prefix">description</i>
                                        <label for="desItem">Descrição</label>
                                        <input id="desItem" name="desItem" type="text" value="<% out.print(desItemEditar); %>" class="validate" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-action right-align button-box">
							<!-- CHAMADA DE MÉTODO PARA REGISTRAR A OPERAÇÃO -->
                            <button id="submit-button" class="btn waves-effect waves-light" onclick="saveEditDialog(document.frmEditItem)"><i class="material-icons left">check_circle_outline</i>Salvar alterações</button>
							<!-- CHAMADA DE MÉTODO PARA FECHAR O MODAL -->
                            <button id="cancel-button" class="btn waves-effect waves-light" onclick="cancelEditDialog()"><i class="material-icons left">highlight_off</i>Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <!-- Excluir -->
            <div id="modal-delete-item" class="modal">
                <div class="modal-content">
                    <h4 class="title">Exclusão de Itens de Conforto</h4>
                    <form id="frmDeleteItem" method="post">
                        <%
                            ItemConforto itemExcluir = null;
                            String codItemExcluir = "";
                            String desItemExcluir = "";
                            if (request.getAttribute("itemConforto") != null) {
                                itemExcluir = (ItemConforto)request.getAttribute("itemConforto");
                                codItemExcluir = itemExcluir.getCodItem();
                                desItemExcluir = itemExcluir.getDesItem();
                            }
                        %>
                        <input type="hidden" name="codItem" id="codItem" value="<%out.println(codItemExcluir);%>">
                        <div id="modal-container">
                            <p>Tem certeza que deseja excluir <%out.println(desItemExcluir);%> Se sim, confirme sua senha no campo abaixo:</p>
                            <div class="row">
                                <div class="col s12 form-input">
                                    <div class="input-field">
                                        <i class="material-icons prefix">lock</i>
                                        <label for="senhaFuncionario">Senha</label>
                                        <input id="senhaFuncionario" name="senhaFuncionario" type="password" class="validate" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-action right-align button-box">
							<!-- CHAMADA DE MÉTODO PARA REGISTRAR A OPERAÇÃO -->
                            <button id="submit-button" class="btn waves-effect waves-light" onclick="executeDeleteDialog()"><i class="material-icons left">check_circle_outline</i>Excluir</button>
							<!-- CHAMADA DE MÉTODO PARA FECHAR O MODAL -->
                            <button id="cancel-button" class="btn waves-effect waves-light" onclick="cancelDeleteDialog()"><i class="material-icons left">highlight_off</i>Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
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
