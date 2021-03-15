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

    /**
     * determine whether two numbers are "approximately equal" by seeing if they
     * are within a certain "tolerance percentage," with `tolerancePercentage` given
     * as a percentage (such as 10.0 meaning "10%").
     *
     * @param tolerancePercentage 1 = 1%, 2.5 = 2.5%, etc.
     */
    public static boolean approximatelyEqual(float desiredValue, float actualValue, float tolerancePercentage) {
        float diff = Math.abs(desiredValue - actualValue);         //  1000 - 950  = 50
        float tolerance = tolerancePercentage/100 * desiredValue;  //  20/100*1000 = 200
        return diff < tolerance;                                   //  50<200      = true
    }
}
