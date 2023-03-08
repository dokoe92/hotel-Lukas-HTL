import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        /*Hotel hotel = new Hotel();
        Map<String,Short> columns = hotel.readColumns("C:\\Users\\Dominik\\DevProjects\\Lukas HTL\\Hotel\\hotels.db");
        */

        FileReader fielreader = new FileReader();
        //Map<String,Short> columns = fielreader.readColumns("C:\\Users\\Dominik\\DevProjects\\Lukas HTL\\Hotel\\hotels.db");
        //fielreader.readHotels("C:\\Users\\Dominik\\DevProjects\\Lukas HTL\\Hotel\\hotels.db");
        fielreader.readHotels("C:\\Users\\Dominik\\DevProjects\\Lukas HTL\\Hotel\\hotels.db");
    }
}