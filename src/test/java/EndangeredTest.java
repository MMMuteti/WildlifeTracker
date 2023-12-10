import org.junit.Rule;
import org.junit.Test;
import com.ke.safaricom.Endangered;

import static org.junit.Assert.*;

public class EndangeredTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void EndangeredInstantiatesCorrectly_True() {
        Endangered testEndangered = setUpNewEndangered();
        assertTrue(testEndangered instanceof Endangered);
    }

    private Endangered setUpNewEndangered() {
        return new Endangered("young", "healthy","lioness");
    }

    @Test
    public void testAnimalInstantiatesWithAge_True() {
        Endangered testEndangered = setUpNewEndangered();
        String testEndangeredAge = "young";
        assertEquals(testEndangeredAge, testEndangered.getAge());
    }

    @Test
    public void testAnimalInstantiatesWithName_True() {
        Endangered testEndangered = setUpNewEndangered();
        String testEndangeredName = "lioness";
        assertEquals(testEndangeredName, testEndangered.getName());
    }

    @Test
    public void equalsReturnsTrueIfAttributesAreEqual() {
        Endangered testEndangered = setUpNewEndangered();
        Endangered testEndangeredTwo = setUpNewEndangered();
        assertEquals(testEndangered, testEndangeredTwo);
    }

    @Test
    public void save_insertsObjectIntoDatabase_Endangered() {
        Endangered testEndangered = setUpNewEndangered();
        testEndangered.save();
        assertEquals(Endangered.all().get(0), testEndangered);
    }


    @Test
    public void all_returnsAllInstancesOfEndangered_true() {
        Endangered testEndangered = setUpNewEndangered();
        testEndangered.save();
        Endangered testEndangeredTwo = setUpNewEndangered();

        testEndangeredTwo.save();
        System.out.println(Endangered.all().size());
        assertEquals(Endangered.all().get(0), testEndangered);
        assertEquals(Endangered.all().get(1), testEndangeredTwo);
    }

    @Test
    public void save_assignsIdToAnimal() {
        Endangered testEndangered = setUpNewEndangered();
        testEndangered.save();
        Endangered savedAnimal = Endangered.all().get(0);
        assertEquals(testEndangered.getId(), savedAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Endangered testEndangered = setUpNewEndangered();
        testEndangered.save();
        Endangered testEndangeredTwo = setUpNewEndangered();
        testEndangeredTwo.save();
        assertEquals(Endangered.find(testEndangeredTwo.getId()), testEndangeredTwo);
    }


}