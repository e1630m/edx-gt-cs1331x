public class PrimitiveOperations {
    public static void main(String[] args) {
        byte b = 127, b2 = -128;
        short s = 32767, s2 = -32768;
        int i = 2147483647, i2 = -2147483648;
        long l = 9_223_372_036_854_775_807L, l2 = -9_223_372_036_854_775_808L;
        float f = 1_111_111_111_111_111_111_111_222_333_444_555_666.0F;
        double d = 9_223_372_036_854_775_807.0, d2 = -9_223_372_036_854_775_808.0;
        char c = 'A';
        String str = "ABC";
        String str2 = new String("DEF");
        String str3 = new String(str);
        char[] ccc = {'a', 'b', 'c'};
        char[] ddd = str.toCharArray();
        long casted_s_i = (long) s * (long) i;
        long mul_s_i = s * i;
        float casted_i = (float) i;

        System.out.println("println b: " + b);
        System.out.println("println b + b: " + b + b + ", println (b + b): " + (b + b));
        System.out.printf("b: %d, b2: %d\n", b, b2);
        System.out.printf("s: %d, s2: %d\n", s, s2);
        System.out.printf("i: %d, i2: %d\n", i, i2);
        System.out.printf("printf (float) i: %f, println (float) i: ", (float) i);
        System.out.println((float) i);
        System.out.printf("printf casted_i: %f, println casted_i: ", casted_i);
        System.out.println(casted_i);
        System.out.printf("l: %d, l2: %d, casted_s_i: %d, mul_s_i: %d\n", l, l2, casted_s_i, mul_s_i);
        System.out.printf("f: %f\n", f);
        System.out.printf("d: %f, d2: %f\n", d, d2);
        System.out.printf("s: %s, s2: %s\n", str, str2);
        System.out.printf("c: %c, printf c + 32: %c, println c + 32: ", c, c + 32);
        System.out.println(c + 32);
        System.out.println("println (char) (c + 32): " + (char) (c + 32));
        System.out.printf("ccc: %s, new String(ccc) %s, Arrays.toString(ccc): %s\n",
                                ccc, new String(ccc), java.util.Arrays.toString(ccc));
        System.out.printf("ddd: %s, new String(ddd): %s\nprintln ccc: ", ddd, new String(ddd));
        System.out.println(ccc);
        System.out.println("println ccc: " + ccc);
        System.out.println("println str2: " + str2 + ", str3: " + str3);
    }
}
