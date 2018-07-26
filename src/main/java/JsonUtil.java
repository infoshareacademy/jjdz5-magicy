import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

class JsonUtil {
    static void writeToJsonFile(String jsonFile, String jsonString) {
        try {
            RandomAccessFile randomAccessFileRoute = new RandomAccessFile(jsonFile, "rw");

            long posR = randomAccessFileRoute.length();
            while (randomAccessFileRoute.length() > 0) {
                posR--;
                randomAccessFileRoute.seek(posR);
                if (randomAccessFileRoute.readByte() == ']') {
                    randomAccessFileRoute.seek(posR);
                    break;
                }
            }

            randomAccessFileRoute.writeBytes("," + jsonString + "\n]");

            randomAccessFileRoute.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
