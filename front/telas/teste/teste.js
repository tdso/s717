(() => {
    var formData = new FormData();
    const getDados = () => {
        var pessoa = {
            nome: document.querySelector('#nome').value,
            idade: document.querySelector('#idade').value
        };
        var foto = document.querySelector('#imagePicker').files[0];
        console.log('foto = ', foto);
        formData.append("pessoaJson", JSON.stringify(pessoa));
        formData.append("foto", foto);
    }

    const sendData = () => {
        console.log("dentro do enviar");
        getDados();
        console.log('form-data = ', formData);

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
    const sendDataNative = () => {
        console.log("dentro do native");
        getDados();
        console.log('form-data = ', formData);

        fetch('http://localhost:8080/native', {
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

    const getData = () => {
        console.log('get data');
        fetch('http://localhost:8080/send/2')
            .then(response => {
                if (!response.ok)
                    throw new Error("não foi possível completar cadastro");

                return response.text();
            })
            .then(data => {
                console.log(data);
                conteudo.textContent = data;
            });

    }
    const getNative = () => {
        console.log('get data native');
        fetch('http://localhost:8080/native/4')
            .then(response => {
                if (!response.ok)
                    throw new Error("não foi possível completar cadastro");

                return response.text();
            })
            .then(data => {
                conteudo.textContent = data;
                console.log(data);
            });
    }

    const btEnviar = document.querySelector("[data-bt-enviar]");
    btEnviar.addEventListener("click", sendData);
    const btEnviarNative = document.querySelector("[data-bt-enviar-native]");
    btEnviarNative.addEventListener("click", sendDataNative);

    const btGet = document.querySelector("[data-bt-get]");
    btGet.addEventListener("click", getData);

    const btGetNative = document.querySelector("[data-bt-get-native]");
    btGetNative.addEventListener("click", getNative);
    const conteudo = document.querySelector("[data-conteudo]");
    conteudo.textContent = "";

})();