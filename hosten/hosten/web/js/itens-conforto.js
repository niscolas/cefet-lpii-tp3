$( document ).ready(function(){
    $("#button-menu").sideNav();
    $('select').material_select();
    $('.modal').modal();
});

function sortTableASC() {
    alert("Função para ordenar a coluna de forma crescente");
}

function sortTableDESC() {
    alert("Função para ordenar a coluna de forma decrescente");
}

// INSERÇÃO DE ITEM
function saveInsertDialog () {
    if( !(($("#codItem").val() === "") || ($("#desItem").val() === "")) ) {
        caminhourl = "/hosten/servletweb?acao=InserirItemConforto";
        $("#frmInsertItem").attr("action", caminhourl);
        $("#frmInsertItem").submit();   
    }
}

// ALTERAÇÃO DE ITEM
function saveEditDialog (codItemEditar) { 
    if( !(($("#codItem").val() === "") || ($("#desItem").val() === "")) ) {
        caminhourl = "/hosten/servletweb?acao=AlterarItemConforto&codItemAntigo="+codItemEditar;
        $("#frmEditItem").attr("action", caminhourl);
        $("#frmEditItem").submit();
    }
}

// EXCLUSÃO DE ITEM
function executeDeleteDialog (codItemExcluir) {
    caminhourl = "/hosten/servletweb?acao=ExcluirItemConforto&codItem="+codItemExcluir;
    $("#frmDeleteItem").attr("action", caminhourl);
    $("#frmDeleteItem").submit();
}
