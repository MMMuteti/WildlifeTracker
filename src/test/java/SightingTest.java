import com.ke.safaricom.Animal;
import com.ke.safaricom.Sighting;
import org.junit.Rule;
import org.junit.Test;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class SightingTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void SightingInstantiatesCorrectly_True() {
        Sighting testSighting = setUpNewSighting();
        assertTrue(testSighting instanceof Sighting);

    }

    @Test
    public void SightingInstantiatesWithWildlifeId_True() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Sighting testSighting = new Sighting(1, "Zone A", "John Doe", timestamp);
        int testWildlifeId = 1;
        assertEquals(testWildlifeId, testSighting.getWildlifeId());
    }

    @Test
    public void SightingInstantiatesWithRangerName_True() {
        Sighting testSighting = setUpNewSighting();
        String testRangerName = "John Doe";
        assertEquals(testRangerName, testSighting.getRangerName());
    }

    @Test
    public void SightingInstantiatesWithSightingZone_True() {
        Sighting testSighting = setUpNewSighting();
        String testSightingZone = "Zone A";
        assertEquals(testSightingZone, testSighting.getSightingZone());
    }

    @Test
    public void SightingInstantiatesWithTimestamp_True() {
        Sighting testSighting = setUpNewSighting();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        assertEquals(timestamp, testSighting.getSightingTime());
    }

    @Test
    public void equalsReturnsTrueIfAttributesAreEqual() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Sighting testSightingOne = setUpNewSighting();
        Sighting testSightingTwo = new Sighting(1, "Zone A", "John Doe", timestamp);
        assertEquals(testSightingOne, testSightingTwo);
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Sighting testSighting = new Sighting(1, "ZoneA", "JohnDoe", timestamp);
        testSighting.save();
        assertEquals(Sighting.all().get(0), testSighting);
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting sightingOne = setUpNewSighting();
        sightingOne.save();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Sighting sightingTwo = new Sighting(2, "RiverSide", "Jane Doe", timestamp);
        sightingTwo.save();
        assertEquals(Sighting.all().get(0), sightingOne);
        assertEquals(Sighting.all().get(1), sightingTwo);
    }

    @Test
    public void save_assignsIdToSighting() {
        Sighting sightingOne = setUpNewSighting();
        sightingOne.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(sightingOne.getId(), savedSighting.getId());
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Sighting sighting = setUpNewSighting();
        sighting.save();
        Sighting secondSighting = setUpNewSighting();
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }

    //associate one wildlife to many sightings
    @Test
    public void save_savesWildlifeIntoDB_True() {
        Animal animal = new Animal("Lion");
        animal.save();

        Timestamp timestamp = new Timestamp(new Date().getTime());
        Sighting sighting = new Sighting(animal.getId(), "zone A", "Jacob", timestamp);
        sighting.save();

        Sighting sighting1 = Sighting.find(sighting.getId());
        assertEquals(sighting1.getWildlifeId(), animal.getId());

    }

    private Sighting setUpNewSighting() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return new Sighting(1, "Zone A", "John Doe", timestamp);
    }

}