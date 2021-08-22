
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        for (int i = 0; i < 10; i++) {
            int min = 0;
            int max = 9;
            int res = min + (int) (Math.random() * ((max - min) + 1));
            int randomNum = ThreadLocalRandom.current().nextInt(0, 9);
            System.out.printf("%d \n", res);
        }
        System.out.printf("%d \n", this.isOne());
    }

    public static int isOne(int num) {
        return (num == 1 ? 1 : 0);
    }
}