const getAtivo = (codAtivo) => {
    const url = "http://localhost:8080/acao";
    //return
    //return fetch(`http://localhost:3000/overview/${codAtivo}`).then(
    return fetch(`http://localhost:8080/acao/${codAtivo}`).then(
        (resposta) => {
            //console.log("dados = ", resposta);

            return resposta.json();
        }
    );
};

export const NegociacaoService = {
    getAtivo,
};
