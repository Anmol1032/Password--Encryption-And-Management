package encrypt;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Encrypt1 {
    public static byte[] encrypt(byte[] b0, String seed) {
        byte[] b = new byte[b0.length];
        System.arraycopy(b0, 0, b, 0, b.length);
        byte[] s = seed.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < s.length; i++) {
            byte value = s[i];
            Random random = new Random(value);
            for (int o = 0; o < b.length; o++) {
                Random r1 = new Random(random.nextLong());
                Random r2 = new Random(random.nextLong());
                b[o] ^= BigInteger.probablePrime(512, r1).remainder(BigInteger.probablePrime(64, r2))
                        .longValue() * 17 + (o ^ i) * 7L ^ 13;
            }
        }

        return b;
    }

    public static byte[] decrypt(byte[] b, String seed) {
        return encrypt(b, seed);
    }
}
