# Jogo da Memória "Memory Quest"

<img src="https://github.com/user-attachments/assets/bf652d5a-81c9-4c4d-a8d1-8f123fbe1d16" width=50% height=50%><img src="https://github.com/user-attachments/assets/f4d49e89-5e90-43ce-8a12-d80ee6fec2ed" width=50% height=50%>

## Descrição:


O projeto foi desenvolvido para a segunda nota do curso de Modelagem de Jogos para Dispositivos Móveis e foi realizado em colaboração com [Warlison Samuel](https://github.com/warlisonsamuell), outro integrante do grupo. O trabalho conjunto possibilitou a criação de um protótipo funcional de um jogo de memória chamado "Memory Quest", com o objetivo de aplicar e aprofundar o conhecimento adquirido durante o curso, especialmente em desenvolvimento mobile.

## Estrutura do Projeto e Funcionamento:

**Arquitetura do Jogo:**

O jogo foi estruturado de maneira modular, com separação clara entre as classes que gerenciam a lógica do jogo e as interfaces de usuário.
A lógica do jogo, que inclui a geração e aleatoriedade das cartas, o gerenciamento de pontuação e a verificação de pares, está encapsulada na classe Game.
A interação do jogador com o tabuleiro e as cartas é gerenciada pela classe BoardView, que manipula a interface e cuida das ações de clique e virada de cartas.

**Interface de Usuário:**

Foi projetada uma interface intuitiva e amigável para garantir uma boa experiência de usuário. O layout principal exibe um tabuleiro de cartas dispostas de maneira dinâmica e centralizada, e a pontuação e o tempo restante são claramente visíveis na parte superior da tela.
Também foram incluídas telas para feedback ao jogador, como a de "Você Perdeu!" e opções para reiniciar o jogo ou sair, mantendo a experiência do usuário o mais simples e acessível possível.

## Principais Funcionalidades:

**Aleatoriedade e Gerenciamento de Cartas:**

As cartas são geradas e embaralhadas aleatoriamente toda vez que o jogo é iniciado, garantindo uma experiência diferente a cada partida.
O jogo verifica automaticamente se as cartas viradas são um par e mantém a pontuação do jogador, incentivando sequências de acertos consecutivos com um sistema de bônus.

**Sistema de Pontuação e Temporizador:**

A pontuação é acumulada a cada par correto encontrado e é exibida em tempo real na tela principal do jogo.
Um temporizador limita o tempo de jogo a 60 segundos, adicionando um desafio extra para os jogadores tentarem encontrar todos os pares antes do tempo acabar.

**Feedback Visual e Interação:**

Cartas que são viradas para cima permanecem visíveis por um curto período, permitindo ao jogador memorizar e tentar encontrar seu par correspondente.
A interface é dinâmica e foi desenvolvida para responder rapidamente aos toques do usuário, proporcionando uma jogabilidade fluida e intuitiva.
O desenvolvimento do projeto, em colaboração com Warlison Samuel, permitiu uma divisão eficiente das tarefas, onde cada membro pôde aplicar suas habilidades para entregar um protótipo funcional e bem estruturado. Essa experiência reforçou conceitos de arquitetura de software, programação orientada a objetos, e design de interfaces de usuário para dispositivos móveis.
