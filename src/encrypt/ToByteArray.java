package encrypt;

import data.MainData;

import java.io.*;

public class ToByteArray {
    public static byte[] toByteArray(MainData data) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(data);

            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return byteArrayOutputStream.toByteArray();
    }

    public static MainData fromByteArray(byte[] data) {
        MainData mainData;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            mainData = ((MainData) objectInputStream.readObject());

            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return mainData;
    }
}
