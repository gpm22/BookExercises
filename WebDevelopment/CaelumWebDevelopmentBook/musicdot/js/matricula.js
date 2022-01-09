var $tdTotalCursos = document.querySelector(".js-total-de-cursos");
var $tdTotalDeHoras = document.querySelector(".js-total-de-horas");
var $buttonConfirmar = document.querySelector(".js-botao-matricula");

var totalHoras = 0;
var totalCursos = 0;

const adicionaCurso = (e) => {
  let checkbox = e.target;
  if (checkbox.checked) {
    totalCursos++;
    totalHoras += parseInt(checkbox.value);
  } else {
    totalCursos--;
    totalHoras -= parseInt(checkbox.value);
  }

  $tdTotalDeHoras.textContent = totalHoras + "h";
  $tdTotalCursos.textContent = totalCursos + " curso(s)";
};

document
  .querySelectorAll("input[name=curso]")
  .forEach((element) => (element.onclick = adicionaCurso));

const confirmaMatriculas = () => {
  if (totalCursos === 0) {
    alert("Nenhum curso selecionado!");
  } else {
    alert("Matricula confirmada!");
    window.location.href = "index.html";
  }
};

$buttonConfirmar.onclick = confirmaMatriculas;
