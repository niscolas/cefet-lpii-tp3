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
    if( !(($("#codCargo").val() === "") || ($("#nomCargo").val() === "")) ) {
        caminhourl = "/hosten/servletweb?acao=InserirCargo";
        $("#frmInsertItem").attr("action", caminhourl);
        $("#frmInsertItem").submit();   
    }
}

// ALTERAÇÃO DOS DADOS DE UM ITEM
function saveEditDialog (codCargoEditar) { 
    if( !(($("#codCargo").val() === "") || ($("#nomCargo").val() === "")) ) {
        caminhourl = "/hosten/servletweb?acao=AlterarCargo&codCargoAntigo="+codCargoEditar;
        $("#frmEditItem").attr("action", caminhourl);
        $("#frmEditItem").submit();
    }
}

// EXCLUSÃO DE ITEM
function executeDeleteDialog (codCargoExcluir) {
    caminhourl = "/hosten/servletweb?acao=ExcluirCargo&codCargo="+codCargoExcluir;
    $("#frmDeleteItem").attr("action", caminhourl);
    $("#frmDeleteItem").submit();
}