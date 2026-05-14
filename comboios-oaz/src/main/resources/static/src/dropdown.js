
function setupDropdown(url, nome1, nome2, id="id", nome="nome") {
    const primeiroDropdown = document.getElementById(nome1);
    const segundoDropdown = document.getElementById(nome2);

    primeiroDropdown.addEventListener("change", async function () {
        const selecao = this.value;

        // Clear second dropdown
        segundoDropdown.innerHTML = '<option value="">Aguarde...</option>';

        if (!selecao) {
            segundoDropdown.innerHTML = '<option value="">Selecione um item</option>';
            return;
        }

        try {
            const resposta = await fetch(url);
            const data = await resposta.json();

            segundoDropdown.innerHTML = '<option value="">Selecione um item</option>';

            data.forEach(item => {
                const option = document.createElement("option");
                option.value = item[id];
                option.textContent = item[nome];
                segundoDropdown.appendChild(option);
            });
        } catch (error) {
            console.error("Erro:", error);
            segundoDropdown.innerHTML = '<option value="">Erro carregando</option>';
        }
    });
}