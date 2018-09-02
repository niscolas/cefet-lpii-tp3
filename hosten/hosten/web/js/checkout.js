$( document ).ready(function(){
    $("#button-menu").sideNav();
});

function sortTableASC() {
    alert("Função para ordenar a coluna de forma crescente");
}

function sortTableDESC() {
    alert("Função para ordenar a coluna de forma decrescente");
}

function checkout(nroQuarto) {
    var y = document.createElement("INPUT");
    y.setAttribute("type", "hidden");
    y.setAttribute("name", "nroQuarto");
    y.setAttribute("value", nroQuarto);

    document.getElementById("frmCheckOut").appendChild(y);
    document.getElementById("frmCheckOut").submit();
}