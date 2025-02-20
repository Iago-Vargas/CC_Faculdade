# Linguagens Formais e Autômatos

 ### Pai da computação 1936 – Allan Turing
 ### Maquina de Estados Finitos tem a função de mostrar o comportamento de algo e reconhecer linguagens, a primeira foi criada por Allan que foi chamada de Máquina Universal.
 ### Chomsky: Gramáticas Formais fez protocolos de comunicações e modelos de sistemas com protocolos de comunicação.

# Terminologia
## Léxico 
 Conjunto de palavras e simbulos de linguagens, Dicionário
## Sintaxe
 Forma de escrita, forma de linguagem, Gramática
## Semântica
 Sentido, Significado
 Historicamente, o problema sintático (nas linguagens de programação) foi reconhecido antes do semântico.

# Formalismo
## Operacional ou Reconhecedor
 Neste temos por exemplo os autômatos
## Axiomático ou Gerador
 Gramática = regras associadas ao comportamento da linguagem
## Denotacional
 Expressão Regular (Exemplo filtros nos programas, filtros por nomes, letras)

# Alfabeto
## Definição:
 Um Alfabeto é um conjunto finito de símbolos (alguns exemplos são letras e dígitos). Ele é representado pelo Simbulo E¹ { a, b, c} e o E² {0, 1}

## Palavra, Cadeia de caracteres ou Sentença
 É uma sequência finita de símbolos (do alfabeto) justapostos. Simbulo é o w (minusculo)
 ### e (grego) representa a palavra vazia (palavra sem simbulo)
 ### E* Represent todas as palavras possíveis sobre um E (Alfabeto)
 ### E*² = { e(grego), 01, 00, 10, 11, 0, 1, 000, 001... infinito}
 ### E+ representa todas palavras possíveis sobre o alfabeto excetuando, que é E+² = { 01, 00, 10, 11, 0, 1, 000...}

## Tamanho ou comprimento
 |W| representa tamanho ou comprimento de uma palavra

## Prefixo, Sufixo, Subpalavra
 Quest: w = 101 
 ### Prefixo (o que vem antes da palavra) dessa palavra seriam: 1, 10, 101 
 ### Sufixo (apos): 1, 01, 101 
 ### Subpalavra(qualquer parte): prefixos, sufixos e 0 

## Linguagem Formal
 Um conjunto de palavras sobre um alfabeto 
 ### L¹ = {w e(grego) E+ | E={0,1} e w tem sufixo 0} //Representação por compreensão
 ### L¹ = {0, 10, 000, 010, 110, ...} //Representação por extensão
 ### L² = {w e(grego) {0,1}* | w tem prefixo 11}

 ## Concatenação
  ### w1 = abc
  ### w2 = aa
  ### w1 + w2 = abcaa
 Associatividade: 
  ### w1(w2w3) = (w1w2)w3
  ### abc aab = abc aab
 Elemento Neutro à Esquerda e à Direita:
  ### e(vazio)w1 = eabc
  ### w1 = abc
  ### w1e = abce
  obs: concatenação de duas palavras de uma determinada linguagem não necessariamente resulta em uma palavra desta mesma linguagem

  ## Concatenação Sucessiva
  ### a)
  ### w ≠ e
  ### w0 = e
  ### w¹ = ab
  ### w² = ww = abab
  ### w³ = wwww = ababab
  ###
  ### b) 
  ### w = e
  ### w0 é indefinida
  ### wn = e, para n > 0

  ### Autômato Finito
  ## Fita de entrada
 
  


