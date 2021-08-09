import { NegociacaoService } from '../services/NegociacaoService.js'

(() => {

    const exibeTelaIncluir = (evento) => {
        console.log("clicou no botao evento = ", evento);
        console.log(evento.target.attributes["data-menu-inc"]);
        const telaIncluir = document.querySelector("[data-tela-incluir]");
        const telaPesq = document.querySelector("[data-tela-pesq]");
        if (evento.target.attributes["data-menu-inc"]) {
            telaIncluir.style.display = 'block';
            telaPesq.style.display = 'none';
        } else {
            telaIncluir.style.display = 'none';
            telaPesq.style.display = 'block';
        }
    }

    const incluiNegociacao = async () => {
        console.log('botao inclui negociacao');
        const resposta = await NegociacaoService.getAtivo("ITSA4");
        console.log("Resposta = ", resposta);
    }

    const btMenuIncluir = document.querySelector("[data-menu-inc]");
    btMenuIncluir.addEventListener("click", exibeTelaIncluir);
    const btMenuPesq = document.querySelector("[data-menu-pesq]");
    btMenuPesq.addEventListener("click", exibeTelaIncluir);
    const btIncluir = document.querySelector("[data-bt-incluir]");
    btIncluir.addEventListener("click", incluiNegociacao);



})();