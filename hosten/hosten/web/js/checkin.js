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

function executaCheckIn(nroQuarto) {
    var x = document.createElement("INPUT");
    x.setAttribute("type", "hidden");
    x.setAttribute("name", "codCPF");
    x.setAttribute("value", codCPF);

    var y = document.createElement("INPUT");
    y.setAttribute("type", "hidden");
    y.setAttribute("name", "nroQuarto");
    y.setAttribute("value", nroQuarto);

    document.getElementById("frmCheckIn").appendChild(x);
    document.getElementById("frmCheckIn").appendChild(y);
    document.getElementById("frmCheckIn").submit();
}