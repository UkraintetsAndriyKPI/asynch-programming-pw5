import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class AsyncTasks1 {
    public static void main(String[] args) {
        CompletableFuture<Double> task1 = CompletableFuture.supplyAsync(() -> {
            double result = 0;
            try {
                result = (Math.random() * 100) - 50;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("task1 generate double num = %.2f\n", result);
            return result;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            int[] arr = new int[10];
            int sum = 0;
            try {
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = (int)(Math.random()*10 - 5);
                    sum += arr[i];
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("task2 calculate sum = %d\n", sum);
            return sum;
        });

        CompletableFuture<Double> result = task1.thenCombine(task2,(result1, result2) -> result1 + result2);
        result.thenCompose(result_val -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.printf("result thenCombine+thenCompose result = %f\n",result_val);
                return null;
            });
        });
    }
}