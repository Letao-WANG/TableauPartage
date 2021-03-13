package Model.TableSockets;

import java.io.*;

/**
 * Util class for mutual conversion, byte[] and Object.
 */
public final class Util {
    /**
     * convert Object to byte[], which can be sent by UDP.
     * @param obj Shape for this case
     * @return byte data
     * @throws IOException
     */
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    /**
     * convert byte[] to Object, which can be displayed in the view part.
     * @param data byte data
     * @return Shape for this case
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
