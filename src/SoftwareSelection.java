import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class SoftwareSelection {
    static Random random = new Random();
    public static void main(String[] args) {


        CompletableFuture<int[]> softwareA = getSoftwareData("Software A");
        CompletableFuture<int[]> softwareB = getSoftwareData("Software B");
        CompletableFuture<int[]> softwareC = getSoftwareData("Software C");

        CompletableFuture<Void> allData = CompletableFuture.allOf(softwareA, softwareB, softwareC);

        CompletableFuture<String> bestSoftware = softwareA
                .thenCombine(softwareB, (dataA, dataB) -> {
                    int scoreA = dataA[0] + dataA[1] + dataA[2];
                    int scoreB = dataB[0] + dataB[1] + dataB[2];
                    return new int[]{scoreA, scoreB};
                })
                .thenCombine(softwareC, (scores, dataC) -> {
                    int scoreC = dataC[0] + dataC[1] + dataC[2];

                    if (scores[0] >= scores[1] && scores[0] >= scoreC) {
                        return "Software A - Всього балів: " + scores[0];
                    } else if (scores[1] >= scores[0] && scores[1] >= scoreC) {
                        return "Software B - Всього балів: " + scores[1];
                    } else {
                        return "Software C - Всього балів: " + scoreC;
                    }
                });

        bestSoftware.thenCompose(best_soft ->
            CompletableFuture.supplyAsync(() -> {
                System.out.printf("Chosen BEST_SOFT: %s", best_soft);
                return null;
            }));

    }

    public static CompletableFuture<int[]> getSoftwareData(String softwareName) {
        return CompletableFuture.supplyAsync(() -> {
            // Імітуємо затримку
            try {
                Thread.sleep((long) (Math.random() * 3000)); // Імітація затримки для асинхронної операції
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int[] gen_data = new int[]{random.nextInt(0, 100),
                    random.nextInt(0, 100),
                    random.nextInt(0, 100)};

            System.out.printf("GENERATED %s : %s\n", softwareName, Arrays.toString(gen_data));

            switch (softwareName) { // {ціна, функціональність, підтримка}
                case "Software A":
                    return gen_data;
                case "Software B":
                    return gen_data;
                case "Software C":
                    return gen_data;
                default:
                    return new int[]{0, 0, 0};
            }
        });
    }
}
