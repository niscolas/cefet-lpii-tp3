$( document ).ready(function(){
    $("#button-menu").sideNav();
    $('select').material_select();
});

function sortTableASC() {
    alert("Função para ordenar a coluna de forma crescente");
}

function sortTableDESC() {
    alert("Função para ordenar a coluna de forma decrescente");
}

// INSERÇÃO DE ITEM
function saveInsertDialog () {
    if( !(($("#codCategoria").val() === "") || ($("#nomCategoria").val() === "") || ($("#vlrDiaria").val() === "")) ) {
        caminhourl = "/hosten/servletweb?acao=InserirCategoriaQuarto";
        $("#frmInsertItem").attr("action", caminhourl);
        $("#frmInsertItem").submit();   
    }
}

// ALTERAÇÃO DE UM ITEM
function saveEditDialog (codCategoriaEditar) { 
    if( !(($("#codCategoria").val() === "") || ($("#nomCategoria").val() === "") || ($("#vlrDiaria").val() === "")) ) {
        caminhourl = "/hosten/servletweb?acao=AlterarCategoriaQuarto&codCategoriaAntiga="+codCategoriaEditar;
        $("#frmEditItem").attr("action", caminhourl);
        $("#frmEditItem").submit();
    }
}

// EXCLUSÃO DE ITEM
function executeDeleteDialog (codCategoriaExcluir) {
    caminhourl = "/hosten/servletweb?acao=ExcluirCategoriaQuarto&codCategoria="+codCategoriaExcluir;
    $("#frmDeleteItem").attr("action", caminhourl);
    $("#frmDeleteItem").submit();
}