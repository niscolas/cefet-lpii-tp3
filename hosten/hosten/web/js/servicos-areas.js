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
    if( !(($("#codServicoArea").val() === "") || ($("#nomServicoArea").val() === "")) ) {
        caminhourl = "/hosten/servletweb?acao=InserirServicoArea";
        $("#frmInsertItem").attr("action", caminhourl);
        $("#frmInsertItem").submit();   
    }
}

// ALTERAÇÃO DE ITEM
function saveEditDialog (codServicoAreaEditar) { 
    if( !(($("#codServicoArea").val() === "") || ($("#nomServicoArea").val() === "")) ) {
        caminhourl = "/hosten/servletweb?acao=AlterarServicoArea&codServicoAreaAntigo="+codServicoAreaEditar;
        $("#frmEditItem").attr("action", caminhourl);
        $("#frmEditItem").submit();
    }
}

// EXCLUSÃO DE ITEM
function executeDeleteDialog (codServicoAreaExcluir) {
    caminhourl = "/hosten/servletweb?acao=ExcluirServicoArea&codServicoArea="+codServicoAreaExcluir;
    $("#frmDeleteItem").attr("action", caminhourl);
    $("#frmDeleteItem").submit();
}		