import { ServiceAtivo } from "../services/Service.js";

const pesquisar = () => {
  const inputPesquisa = document.querySelector("[data-cod-ativo]");
  const codigoAtivo = inputPesquisa.value;
  const ativo = document.querySelector("[data-ativo]");
  const tipo = document.querySelector("[data-tipo]");
  const preco = document.querySelector("[data-pm]");
  const qtde = document.querySelector("[data-qtde]");

  ServiceAtivo.getAtivo(codigoAtivo).then((dados) => {
    console.log("dados = ", dados);
    console.log("codigo = ", codigoAtivo);
    ativo.textContent = dados.id;
    tipo.textContent = dados.tipo;
    preco.textContent = dados.precoMedio;
    qtde.textContent = dados.qtde;
  });
};
const btnPesquisa = document.querySelector("[data-bt-pesquisar]");
btnPesquisa.addEventListener("click", pesquisar);
