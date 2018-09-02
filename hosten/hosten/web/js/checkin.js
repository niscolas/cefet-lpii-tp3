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

var codCPF;

function salvaCPF(ACodCPF) {
    codCPF = ACodCPF;
}

function executaCheckIn() {
    var x = document.createElement("INPUT");
    x.setAttribute("type", "text");
    x.setAttribute("name", "codCPF");
    x.setAttribute("value", codCPF);

    document.getElementById("frmCheckIn").appendChild(x);
    document.getElementById("frmCheckIn").submit();
}