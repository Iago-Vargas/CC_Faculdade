#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int **A, **B, **C;
int N, M;

void* somar_linha(void* arg) {
    int linha = *(int*)arg;
    for (int j = 0; j < M; j++) {
        C[linha][j] = A[linha][j] + B[linha][j];
    }
    pthread_exit(NULL);
}

int main() {
    printf("Informe as dimensões da matriz (N linhas, M colunas): ");
    scanf("%d %d", &N, &M);

    // Alocação dinâmica
    A = malloc(N * sizeof(int*));
    B = malloc(N * sizeof(int*));
    C = malloc(N * sizeof(int*));
    for (int i = 0; i < N; i++) {
        A[i] = malloc(M * sizeof(int));
        B[i] = malloc(M * sizeof(int));
        C[i] = malloc(M * sizeof(int));
    }

    // Leitura de A e B
    printf("Informe os elementos da matriz A:\n");
    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
            scanf("%d", &A[i][j]);

    printf("Informe os elementos da matriz B:\n");
    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
            scanf("%d", &B[i][j]);

    pthread_t threads[N];
    int indices[N];

    for (int i = 0; i < N; i++) {
        indices[i] = i;
        pthread_create(&threads[i], NULL, somar_linha, &indices[i]);
    }

    for (int i = 0; i < N; i++) {
        pthread_join(threads[i], NULL);
    }

    // Resultado
    printf("Resultado da soma:\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++)
            printf("%d ", C[i][j]);
        printf("\n");
    }

    return 0;
}
