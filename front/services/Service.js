const getAtivo = (codAtivo) => {
  //return
  return fetch(`http://localhost:3000/overview/${codAtivo}`).then(
    (resposta) => {
      //console.log("dados = ", resposta);

      return resposta.json();
    }
  );
};

export const ServiceAtivo = {
  getAtivo,
};
