import java.util.*;
import java.sql.*;

public class CSE216DBQuery extends CSE216Facade {
    private static Connection con = CSE216DBConnect.connect();

    public void store(CState obj) {
        String query = "INSERT INTO objects 
                (hostname, filename, filetype, message, v, size) VALUES ("
                + obj.getHostname() + ", " + obj.getFilename() + ", "
                + obj.getFiletype() + ", " + obj.getMessage()
                + ", " + obj.getV() + ", " + obj.getFilesize() + ")";
        try (Statement statement = con.createStatement()) {
            ResultSet result = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public CState retrieve(String user) {
        String query = "SELECT * FROM objects";
        try (Statement statement = con.createStatement()) {
            CState res = new CState();
            ResultSet result = statement.executeQuery(query);
            result.next();
            res.setHostname(result.getString("hostname"));
            res.setFilename(result.getString("filename"));
            res.setFiletype(result.getString("filetype"));
            res.setMessage(result.getString("message"));
            res.setV(result.getInt("v"));
            res.setFilesize(result.getLong("size"));
            return res;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
