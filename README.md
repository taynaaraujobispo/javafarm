# JavaFarm

Este projeto é um **simulador de fazenda** desenvolvido como atividade da disciplina de **POO** do SENAC, sob orientação do prof. @joaocarloslima.

O sistema explora conceitos de **Programação Orientada a Objetos** (POO) como **classes, objetos, herança, abstração e composição**, além de **estruturas de dados** e **lógica de programação** (listas, condicionais, loops).

O projeto foi implementado em **Java com JavaFX**, e apresenta:

* Interface gráfica interativa para **plantar, colher e gerenciar culturas**;
* Controle de estoque no **Celeiro**;
* Crescimento das plantas baseado em **tempo de vida e tempo de crescimento**;
* Exibição de imagens dinâmicas das plantas conforme seu tamanho;
* ProgressBar para acompanhar a **ocupação do celeiro**;
* Opção de **aceleração do crescimento** das plantas.

### Estrutura do projeto

```
javafarm-main/
│
├─ src/
│   └─ br/com/joaocarloslima/
│       ├─ App.java
│       ├─ Controller.java
│       ├─ Fazenda.java
│       ├─ Celeiro.java
│       ├─ Terreno.java
│       ├─ Planta.java
│       ├─ Batata.java
│       ├─ Cenoura.java
│       └─ Morango.java
│
├─ resources/
│   └─ br/com/joaocarloslima/images/  (ícones e imagens das plantas)
```

### Funcionalidades

* Plantar Batata, Cenoura e Morango, respeitando o estoque do celeiro;
* Colher plantas maduras, recebendo **2 unidades por colheita**;
* Atualização automática da **tela com imagens e estoque**;
* Controle do crescimento das plantas com ciclos de tempo;
* Indicador visual da ocupação do celeiro;
* Aceleração opcional do crescimento via checkbox.

### Como rodar

1. Certifique-se de ter o **JDK 21** instalado.
2. Baixe o **JavaFX SDK** (ex.: `javafx-sdk-21`) e configure no VS Code.
3. Abra o projeto no VS Code com a extensão **Java Extension Pack**.
4. Configure o `launch.json` com:

```json
"vmArgs": "--module-path \"C:/javafx-sdk-21/lib\" --add-modules javafx.controls,javafx.fxml"
```

5. Compile e rode a classe `App.java`.
