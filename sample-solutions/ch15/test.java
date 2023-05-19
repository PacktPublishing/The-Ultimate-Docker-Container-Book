public class test {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static int calculateSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // sum = sum + arr[i];
        }
        return sum;
    }

    public double calculateMean(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // sum = sum + arr[i];
        }
        return sum / arr.length;
    }
}
