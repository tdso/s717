const getAtivo = (codAtivo) => {
    // `http://localhost:3000/overview/${codAtivo}`
    return fetch(`http://localhost:8080/acao/${codAtivo}`).then(
        (resposta) => {
            console.log("dados = ", resposta);
            //return resposta;
            return resposta.json();
        });
};

export const NegociacaoService = {
    getAtivo,
};
