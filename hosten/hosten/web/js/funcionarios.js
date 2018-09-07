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
    if( !(($("#codUsuario").val() === "") || ($("#nomUsuario").val() === "") || 
          ($("#desEmail").val() === "")   ||  $("#desSenha").val() === "") ) {
      
        caminhourl = "/hosten/servletweb?acao=InserirUsuario";
        $("#frmInsertItem").attr("action", caminhourl);
        $("#frmInsertItem").submit();   
        
    }
}

// ALTERAÇÃO DE ITEM
function saveEditDialog (codCargoEditar) { 
    if( !(($("#codUsuario").val() === "") || ($("#nomUsuario").val() === "") || 
          ($("#desEmail").val() === "")   ||  $("#desSenha").val() === "") ) {
      
        caminhourl = "/hosten/servletweb?acao=AlterarUsuario&codUsuarioAntigo="+codCargoEditar;
        $("#frmEditItem").attr("action", caminhourl);
        $("#frmEditItem").submit();
    }
}

// EXCLUSÃO DE ITEM
function executeDeleteDialog (codUsuarioExcluir) {
    caminhourl = "/hosten/servletweb?acao=ExcluirUsuario&codUsuario="+codUsuarioExcluir;
    $("#frmDeleteItem").attr("action", caminhourl);
    $("#frmDeleteItem").submit();
}