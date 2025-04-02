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
- 
---


## 👤 Autor

- **Iago Vargas de Oliveira**
- **Bruno Difante**
- **Gabriel Teixeira**
> Curso de Ciência da Computação – Universidade Franciscana (UFN)

📧 E-mail: me@iagovargas.com & [iagovargas.com](https://iagovargas.com)
🔗 GitHub: [@IagoVargas](https://github.com/Iago-Vargas)  

