package encrypt;

import data.MainData;

import java.io.File;
import java.io.IOException;

public class Encrypt {
    public static void encrypt(MainData data, File file, String key1, String key2) throws IOException {
        ToImage.toImage(ColorInt.byteToColorInt(Encrypt1.encrypt(Encrypt2.encrypt(Compress.compress(ToByteArray.toByteArray(data)), key2), key1)), file);
    }

    public static MainData decrypt(File file, String key1, String key2) throws IOException, WrongPasswordError {
        return ToByteArray.fromByteArray(Compress.decompress(Encrypt2.decrypt(Encrypt1.decrypt(ColorInt.colorIntToByte(ToImage.fromImage(file)), key1), key2)));
    }
}
