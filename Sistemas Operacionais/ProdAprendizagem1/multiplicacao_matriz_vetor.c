#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int **A, *V, *R;
int N;

void* multiplicar_linha(void* arg) {
    int linha = *(int*)arg;
    R[linha] = 0;
    for (int j = 0; j < N; j++) {
        R[linha] += A[linha][j] * V[j];
    }
    pthread_exit(NULL);
}

int main() {
    printf("Informe a dimensão da matriz quadrada NxN: ");
    scanf("%d", &N);

    A = malloc(N * sizeof(int*));
    V = malloc(N * sizeof(int));
    R = malloc(N * sizeof(int));

    for (int i = 0; i < N; i++) {
        A[i] = malloc(N * sizeof(int));
    }

    printf("Informe os elementos da matriz A:\n");
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            scanf("%d", &A[i][j]);

    printf("Informe os elementos do vetor V:\n");
    for (int i = 0; i < N; i++)
        scanf("%d", &V[i]);

    pthread_t threads[N];
    int indices[N];

    for (int i = 0; i < N; i++) {
        indices[i] = i;
        pthread_create(&threads[i], NULL, multiplicar_linha, &indices[i]);
    }

    for (int i = 0; i < N; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Resultado da multiplicação:\n");
    for (int i = 0; i < N; i++)
        printf("%d\n", R[i]);

    return 0;
}
