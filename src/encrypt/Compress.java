package encrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

public class Compress {
    public static byte[] compress(byte[] in) {

        try {
            Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION, true);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(out, deflater);
            deflaterOutputStream.write(in);
            deflaterOutputStream.flush();
            deflaterOutputStream.close();

            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] decompress(byte[] in) {
        try {
            Inflater inflater = new Inflater(true);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(out, inflater);
            inflaterOutputStream.write(in);
            inflaterOutputStream.flush();
            inflaterOutputStream.close();

            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
