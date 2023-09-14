package encrypt;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ToImage {
    public static final long transparency = 0x01000000L;
    public static final int x0 = 2;
    public static final int y0 = 2;

    public static void toImage(int[] colors, File file) throws IOException {
        int e = (int) (Math.sqrt(colors.length) + 1);

        BufferedImage bufferedImage = new BufferedImage(e, e, BufferedImage.TYPE_INT_ARGB);
        Random random = new Random();
        for (int x = 0; x < e; x++) {
            for (int y = 0; y < e; y++) {
                try {
                    bufferedImage.setRGB(x, y, (int) (transparency + pattern(x, y, e) + colors[x * e + y]));
                } catch (Exception ex) {
                    bufferedImage.setRGB(x, y, random.nextInt((int) (transparency - 0x1000000), (int) transparency));
                }
            }
        }


        ImageIO.write(bufferedImage, "png", file);
    }

    private static long pattern(int x, int y, int e) {
        if (x == 0 || y == 0 || x == e - 1 || y == e - 1) {
            return 0;
        }
        //return 0xFF000000L - transparency;

        return (x % x0 == y % y0) ? 0xFF000000L - transparency : 0;
    }

    public static int[] fromImage(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        int e = bufferedImage.getHeight();
        int[] colors = new int[e * e];
        for (int x = 0; x < e; x++) {
            for (int y = 0; y < e; y++) {
                colors[x * e + y] = ((bufferedImage.getRGB(x, y) & 0xFF000000L) >= transparency ? bufferedImage.getRGB(x, y) & 0xFFFFFF : 0);
            }
        }

        return colors;
    }
}
