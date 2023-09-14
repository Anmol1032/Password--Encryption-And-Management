package encrypt;

import java.util.Arrays;

public class ColorInt {
    public static int[] byteToColorInt(byte[] b0) {
        byte[] b = new byte[(b0.length + (3 - (b0.length % 3)))];
        System.arraycopy(b0, 0, b, 0, b0.length);

        int[] i = new int[b.length / 3];

        for (int o = 0; o < b.length; o += 3) {
            i[o / 3] = Byte.toUnsignedInt(b[o]) * 256 * 256 + Byte.toUnsignedInt(b[o + 1]) * 256 + Byte.toUnsignedInt(b[o + 2]);
        }

        return i;
    }

    public static byte[] colorIntToByte(int[] i) {
        byte[] b = new byte[i.length * 3];

        for (int o = 0; o < b.length - 1; o += 3) {
            b[o] = (byte) (i[o / 3] / (256 * 256));
            b[o + 1] = (byte) (i[o / 3] / 256 % (256));
            b[o + 2] = (byte) (i[o / 3] % 256);
        }

        int index = b.length;
        while (b[index - 1] == 0) {
            --index;
        }

        return Arrays.copyOf(b, index);
    }
}
