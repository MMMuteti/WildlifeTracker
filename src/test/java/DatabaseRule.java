import com.ke.safaricom.DB;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() throws Throwable {
        try {
            DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "Access");
        }
        catch (Exception e){
            System.out.println("Unable to connect to Database. Check your connection string");
        }
    }

    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()) {
            String deleteSightingsQuery = "DELETE FROM sightings *;";
            con.createQuery(deleteSightingsQuery).executeUpdate();
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
        }
    }
}