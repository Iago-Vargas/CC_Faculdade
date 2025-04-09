import random
# Iago Vargas de Oliveira

class CromossomoRota:
    def __init__(self, rota):
        self.rota = rota
        self.aptidao = self.calcular_aptidao()

    def calcular_aptidao(self):
        penalidade = 0
        # Penalidade por cidade fora de ordem
        for i in range(len(self.rota) - 1):
            if self.rota[i] > self.rota[i+1]:
                penalidade += 10
        
        # Penalidade por repetição de cidades
        vistos = {}
        for cidade in self.rota:
            if cidade in vistos:
                vistos[cidade] += 1
            else:
                vistos[cidade] = 1

        for cidade, qtd in vistos.items():
            if qtd > 1:
                penalidade += 20 * (qtd - 1)

        return penalidade

    def __str__(self):
        return f'{self.rota} - Aptidão (penalidade): {self.aptidao}'

    @staticmethod
    def gerar_populacao(tamanho_populacao, tamanho_rota):
        populacao = []
        for _ in range(tamanho_populacao):
            rota = [random.randint(1, 9) for _ in range(tamanho_rota)]
            populacao.append(CromossomoRota(rota))
        return populacao

    @staticmethod
    def exibir_populacao(populacao, geracao):
        print(f"Geração {geracao}:")
        for individuo in populacao:
            print(individuo)

def selecionar(populacao):
    return sorted(populacao, key=lambda c: c.aptidao)[:2]  # Seleciona os 2 melhores

def crossover(pai1, pai2):
    ponto = random.randint(1, len(pai1.rota) - 2)
    filho_rota = pai1.rota[:ponto] + pai2.rota[ponto:]
    return CromossomoRota(filho_rota)

def mutacao(individuo, taxa=0.1):
    if random.random() < taxa:
        i, j = random.sample(range(len(individuo.rota)), 2)
        individuo.rota[i], individuo.rota[j] = individuo.rota[j], individuo.rota[i]
        individuo.aptidao = individuo.calcular_aptidao()

if __name__ == '__main__':
    tamanho_pop = 10
    tamanho_rota = 9
    max_geracoes = 10000

    populacao = CromossomoRota.gerar_populacao(tamanho_pop, tamanho_rota)

    for geracao in range(1, max_geracoes + 1):
        CromossomoRota.exibir_populacao(populacao, geracao)

        # Verifica se tem solução perfeita (aptidao == 0)
        if any(c.aptidao == 0 for c in populacao):
            print(f"\n Solução perfeita encontrada na geração {geracao}!")
            break

        # Seleção dos pais
        pais = selecionar(populacao)

        # Geração de nova população por cruzamento
        nova_populacao = []
        while len(nova_populacao) < tamanho_pop:
            filho = crossover(pais[0], pais[1])
            mutacao(filho)
            nova_populacao.append(filho)

        populacao = nova_populacao
