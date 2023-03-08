import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.*;

public class FileReader {
    RandomAccessFile fileReader;
    Map<String, Short> columnNames;

    public Map<String, Short> readColumns(String filename) throws IOException {
        Map<String, Short> columns = new LinkedHashMap<>();
        try (RandomAccessFile rf = new RandomAccessFile(filename, "r")) {
            rf.seek(8); // Go to column block after id and offset bytes
            // Loop through the amount of columns
            int columnCount = rf.readShort();
            for (int i = 0; i < columnCount; i++) {
                short columnNameLength = rf.readShort();
                byte[] bytesForColumnName = new byte[columnNameLength];
                rf.read(bytesForColumnName);
                String columnName = new String(bytesForColumnName);
                //System.out.println(new String(bytesForColumnName));
                short lenghtOfColumn = rf.readShort(); // Length of column to read hotel information
                //System.out.println("lengthOfColumn: " + lenghtOfColumn);
                columns.put(columnName, lenghtOfColumn);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public long getFilePointer(String filename) {
        long filePointer = 0;
        try (RandomAccessFile rf = new RandomAccessFile(filename, "r")) {
            rf.seek(8); // Go to column block after id and offset bytes
            // Loop through the amount of columns
            int columnCount = rf.readShort();
            for (int i = 0; i < columnCount; i++) {
                short columnNameLength = rf.readShort();
                byte[] bytesForColumnName = new byte[columnNameLength];
                rf.read(bytesForColumnName);
                short lenghtOfColumn = rf.readShort(); // Length of column to read hotel information;
                filePointer = rf.getFilePointer();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePointer;
    }

    public Set<Hotel> readHotels(String filename) throws IOException {
        Set<Hotel> hotels = new HashSet<>();
        try (RandomAccessFile rf = new RandomAccessFile(filename, "r")) {
            Map<String, Short> columns = readColumns(filename);
            long startPointer = getFilePointer(filename);
            rf.seek(startPointer);
            while (true) {
                Map<String,String> output = new LinkedHashMap<>();
                short deleted = rf.readShort();
                //System.out.println("deleted: " + new String(deleted));
                for (Map.Entry<String, Short> column : columns.entrySet()) {
                    byte[] bytesToRead = new byte[column.getValue()];
                    rf.read(bytesToRead);
                    output.put(column.getKey(), new String(bytesToRead));
                    //System.out.println(column.getKey() + ": ");
                    //System.out.println(new String(bytesToRead));
                }
                for (Map.Entry<String,String> entry: output.entrySet()) {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                }

                /*
                long rate = 0;
                String rateString = output.get("rate");
                String rateStringMinus = rateString.substring(1);

                int rateInt = (int) Double.parseDouble(rateStringMinus) * 100;
                System.out.println(rateInt);
                */
                output.clear();

            }
        } catch(IOException e){

        }
        return hotels;

    }


}
