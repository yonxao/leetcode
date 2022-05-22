import java.math.BigInteger;

class Q002 {
    public static void main(String[] args) {
        int x1 = 0;
        int y1 = 0;
        int x2 = 6;
        int y2 = 9;
        int x3 = 0;
        int y3 = 9;
        int x4 = 6;
        int y4 = 0;
        int x5 = -6;
        int y5 = 0;
        int x6 = 0;
        int y6 = -9;
        int x7 = -6;
        int y7 = 12;
        int x8 = -6;
        int y8 = 9;
        int x9 = 6;
        int y9 = -9;
        int x10 = 6;
        int y10 = -12;
        int x11 = -6;
        int y11 = -9;
        int x12 = -6;
        int y12 = -12;

        String show = "x=%d, y=%d%n";
        System.out.printf(show, x1, y1);
        System.out.println("gcd          (x1, y1)   " + gcd(x1, y1));
        System.out.println("bigIntegerGcd(x1, y1)   " + bigIntegerGcd(x1, y1));
        System.out.println();

        System.out.printf(show, x2, y2);
        System.out.println("gcd          (x2, y2)   " + gcd(x2, y2));
        System.out.println("bigIntegerGcd(x2, y2)   " + bigIntegerGcd(x2, y2));
        System.out.println();

        System.out.printf(show, x3, y3);
        System.out.println("gcd          (x3, y3)   " + gcd(x3, y3));
        System.out.println("bigIntegerGcd(x3, y3)   " + bigIntegerGcd(x3, y3));
        System.out.println();

        System.out.printf(show, x4, y4);
        System.out.println("gcd          (x4, y4)   " + gcd(x4, y4));
        System.out.println("bigIntegerGcd(x4, y4)   " + bigIntegerGcd(x4, y4));
        System.out.println();

        System.out.printf(show, x5, y5);
        System.out.println("gcd          (x5, y5)   " + gcd(x5, y5));
        System.out.println("bigIntegerGcd(x5, y5)   " + bigIntegerGcd(x5, y5));
        System.out.println();

        System.out.printf(show, x6, y6);
        System.out.println("gcd          (x6, y6)   " + gcd(x6, y6));
        System.out.println("bigIntegerGcd(x6, y6)   " + bigIntegerGcd(x6, y6));
        System.out.println();

        System.out.printf(show, x7, y7);
        System.out.println("gcd          (x7, y7)   " + gcd(x7, y7));
        System.out.println("bigIntegerGcd(x7, y7)   " + bigIntegerGcd(x7, y7));
        System.out.println();

        System.out.printf(show, x8, y8);
        System.out.println("gcd          (x1, y1)   " + gcd(x8, y8));
        System.out.println("bigIntegerGcd(x1, y1)   " + bigIntegerGcd(x8, y8));
        System.out.println();

        System.out.printf(show, x9, y9);
        System.out.println("gcd          (x9, y9)   " + gcd(x9, y9));
        System.out.println("bigIntegerGcd(x9, y9)   " + bigIntegerGcd(x9, y9));
        System.out.println();

        System.out.printf(show, x10, y10);
        System.out.println("gcd          (x10, y10)   " + gcd(x10, y10));
        System.out.println("bigIntegerGcd(x10, y10)   " + bigIntegerGcd(x10, y10));
        System.out.println();

        System.out.printf(show, x11, y11);
        System.out.println("gcd          (x11, y11)   " + gcd(x11, y11));
        System.out.println("bigIntegerGcd(x11, y11)   " + bigIntegerGcd(x11, y11));
        System.out.println();

        System.out.printf(show, x12, y12);
        System.out.println("gcd          (x12, y12)   " + gcd(x12, y12));
        System.out.println("bigIntegerGcd(x12, y12)   " + bigIntegerGcd(x12, y12));
        System.out.println();
    }

    /**
     * 最大公约数 : gcd（greatest common divisor）
     * <p>
     * 辗转相除法(递归)
     */
    public static int gcd3(int x, int y) {
        if (x == 0) {
            return Math.abs(y);
        } else if (y == 0) {
            return Math.abs(x);
        }
        return x % y == 0 ? y : Math.abs(gcd(y, x % y));
    }

    /**
     * 最大公约数 : gcd（greatest common divisor）
     * <p>
     * 辗转相除法(循环)
     */
    public static int gcd(int x, int y) {
        if (x == 0) {
            return Math.abs(y);
        } else if (y == 0) {
            return Math.abs(x);
        }

        int temp;
        while (true) {
            temp = x % y;
            if (temp == 0) {
                return Math.abs(y);
            }
            x = y;
            y = temp;
        }

    }


    public static int bigIntegerGcd(int a, int b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
    }

    /**
     * 最小公倍数 : lcm（least common multiple）
     * <p>
     * 两数相乘绝对值 = 最小公倍数 * 最大公约数   <br>
     * |x * y| = lcm(x,y) * gcd(x,y)
     * <p>
     * 几个数共有的倍数叫做这几个数的公倍数，其中除0以外最小的一个公倍数，叫做这几个数的最小公倍数。
     * <p>
     * 最小公倍数是数论中的一个概念。若有一个数 X，可以被另外两个数 A、B 整除，且 X 大于（或等于）A 和 B，则 X 为 A、B 的公倍数。 <br>
     * A、B的公倍数有无限个，而所有正的公倍数中，最小的公倍数就叫做最小公倍数。同样地，若干个整数公有的倍数中最小的正整数称为它们的最小公倍数。
     */
    public static int lcm(int x, int y) {
        if (x == 0 || y == 0) {
            // 最小公倍数为正整数, 不能为0, 如果为0要根据业务单独处理
            return 0;
        }
        return Math.abs(x * y) / gcd(x, y);
    }
}
