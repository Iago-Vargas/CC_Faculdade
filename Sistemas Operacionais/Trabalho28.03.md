# Sistemas Operacionais
Trabalho: Material do Livro Sistemas Operacionais, de Deitel.
---

## 1- 🔄 Chaveamento de Contexto

 O chaveamento de contexto ocorre quando o sistema operacional troca a execução de um processo por outro. Isso acontece porque o processador pode executar apenas um processo por vez (em um único núcleo). O sistema precisa salvar o estado atual do processo interrompido e carregar o estado do próximo processo a ser executado.
- Salvar o contexto do processo atual (valores dos registradores, contador de programa).
- Escolher o próximo processo a ser executado.
- Carregar o contexto do novo processo.
- Retomar a execução do processo escolhido.
> Esse processo é essencial para a multiprogramação, garantindo que vários processos compartilhem a CPU de forma eficiente.
---

## 2- 🚨 Interrupções
 As interrupções são eventos que desviam momentaneamente a execução normal do processador para tratar alguma situação específica, como entrada de dados, erros ou ações do sistema operacional.
- Interrupções de Hardware: Ocorridas devido a dispositivos externos, como teclado e disco rígido.
- Interrupções de Software: Causadas por instruções específicas, como chamadas de sistema
- Interrupções de Relógio: Geradas pelo temporizador do sistema para controlar a execução dos processos.
- Interrupções de Erro: Causadas por operações inválidas, como divisão por zero.
 Como o SO trata uma interrupção?
- O processador pausa a execução atual e salva seu contexto.
- O controle é transferido para o tratador de interrupções correspondente.
- Após tratar a interrupção, o sistema restaura o contexto salvo e retoma a execução normal.
---

## 3- 🔄 Comunicação Interprocessos
Os processos precisam se comunicar para compartilhar informações ou coordenar execuções. Para isso, os sistemas operacionais oferecem diferentes mecanismos:
- Sinais: Notificações enviadas entre processos para eventos específicos.
- Troca de Mensagens : Comunicação entre processos por envio e recebimento de mensagens.
- Pipes: Canal de comunicação unidirecional entre processos.
- Mailboxes: Filas de mensagens usadas para comunicação indireta.
---

## 4- 🖥️ Estudo de Processos no UNIX
No UNIX, um processo é uma instância de um programa em execução. Cada processo tem um PID (Process ID) e pode ser criado por outro processo usando fork().
- ps aux: Lista todos os processos em execução.
- kill PID: Envia um sinal para finalizar um processo.
- Pipes: Canal de comunicação unidirecional entre processos.
- top: Exibe os processos em tempo real.
---

## Mapa mental:
![Green and Blue Playful Illustrative Mind Map](https://github.com/user-attachments/assets/ede30b5e-48d2-422d-9b3a-0e07f2c656eb)

---

## Questão: 

Ao longo dos anos, o chaveamento de contexto foi otimizado para reduzir sua latência e impacto no desempenho dos sistemas operacionais. Inicialmente, esse processo era bastante custoso, pois envolvia a cópia de todos os registradores e estados do processador para a memória principal. Com o avanço das arquiteturas de hardware, foram introduzidos registradores dedicados para troca de contexto e buffers de cache que armazenam temporariamente os estados dos processos, reduzindo a necessidade de acesso à RAM e tornando o chaveamento mais rápido.
---
Além disso, melhorias no escalonamento de processos ajudaram a minimizar trocas desnecessárias de contexto, como o uso de process scheduling heuristics e CPU affinity, que mantém processos frequentemente executados no mesmo núcleo do processador para reduzir a sobrecarga. Tecnologias como Hyper-Threading (Intel) e SMT (Simultaneous Multithreading) também permitem que um núcleo execute múltiplos threads de forma mais eficiente, reduzindo a necessidade de um chaveamento completo de contexto. Essas otimizações tornaram os sistemas operacionais mais responsivos e eficientes, especialmente em ambientes multitarefa e de alto desempenho.
---
## 👤 Autor

- **Iago Vargas de Oliveira**
- Curso de Ciência da Computação – Universidade Franciscana (UFN)

📧 E-mail: me@iagovargas.com & [iagovargas.com](https://iagovargas.com)
🔗 GitHub: [@IagoVargas](https://github.com/Iago-Vargas)  

