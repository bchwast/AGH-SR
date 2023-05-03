package server;

public class Common {

    public static boolean isPrime(long val) {
        if (val == 2 || val == 3) return true;
        if (val < 2 || val % 2 == 0 || val % 3 == 0) return false;

        int num = 5;
        while ((long) num * num <= val) {
            if (val % num == 0 || val % (num + 2) == 0) return false;
            num += 6;
        }

        return true;
    }
}
