import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hotel {
    private String name;
    private String location;
    private int size;
    private char smoking;
    private long rate;
    private String date;
    private String owner;

    public Hotel(String name, String location, int size, char smoking, long rate, String date, String owner) {
        this.name = name;
        this.location = location;
        this.size = size;
        this.smoking = smoking;
        this.rate = rate;
        this.date = date;
        this.owner = owner;
    }

    public Map<String,Short> readColumns(String filename) throws IOException {
        HashMap<String, Short> columns = new HashMap<>();
        try (RandomAccessFile rf = new RandomAccessFile(filename, "r")) {
            rf.seek(8); // Go to column block after id and offset bytes
            // Loop through the amount of columns
            int columnCount = rf.readShort();
            for (int i = 0; i < columnCount; i++) {
                short columnNameLength = rf.readShort();
                byte[] bytesForColumnName = new byte[columnNameLength];
                rf.read(bytesForColumnName);
                String columnName = new String(bytesForColumnName);
                System.out.println(new String(bytesForColumnName));
                short lenghtOfColumn = rf.readShort(); // Length of column to read hotel information
                System.out.println("lengthOfColumn: " + lenghtOfColumn);
                columns.put(columnName, lenghtOfColumn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columns;
    }


}
