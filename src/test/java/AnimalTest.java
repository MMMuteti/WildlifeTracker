import com.ke.safaricom.Animal;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class AnimalTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void AnimalInstantiatesCorrectly_True() {
        Animal testAnimal = setUpNewAnimal();
        assertTrue(testAnimal instanceof Animal);

    }

    private Animal setUpNewAnimal() {
        return new Animal("Lion");
    }

    @Test
    public void testAnimalInstantiatesWithName_True() {
        Animal testAnimal = setUpNewAnimal();
        String testAnimalName = "Lion";
        assertEquals(testAnimalName, testAnimal.getName());
    }

    @Test
    public void equalsReturnsTrueIfAttributesAreEqual() {
        Animal testAnimal = setUpNewAnimal();
        Animal testAnimalTwo = setUpNewAnimal();
        assertEquals(testAnimal, testAnimalTwo);
    }

    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        assertEquals(Animal.all().get(0), testAnimal);
    }


    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal testAnimalTwo = setUpNewAnimal();

        testAnimalTwo.save();
        assertEquals(Animal.all().get(0), testAnimal);
        assertEquals(Animal.all().get(1), testAnimalTwo);
    }

    @Test
    public void save_assignsIdToAnimal() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal testAnimalTwo = setUpNewAnimal();

        testAnimalTwo.save();
        assertEquals(Animal.find(testAnimalTwo.getId()), testAnimalTwo);
    }

}