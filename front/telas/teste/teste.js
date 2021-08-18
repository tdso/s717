(() => {
    const sendData = () => {
        console.log("dentro do enviar");
        var pessoa = {
            nome: document.querySelector('#nome').value,
            idade: document.querySelector('#idade').value
        };
        var foto = document.querySelector('#imagePicker').files[0];
        console.log('foto = ', foto);

        var formData = new FormData();
        formData.append("pessoaJson", JSON.stringify(pessoa));
        formData.append("foto", foto);

        fetch('http://localhost:8080/send', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (!response.ok)
                    throw new Error("não foi possível completar cadastro");

                return response.text();
            })
            .then(data => alert(data));
    }
    const btEnviar = document.querySelector("[data-bt-enviar]");
    btEnviar.addEventListener("click", sendData);
})();