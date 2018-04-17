package java8.ex03;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - Méthode statique
 */
public class Method_03_Test {

    
    interface IDao {
        List<Person> findAll();

        public static IDao getDefaultInstance(){
        	return new DaoA();
        }
        
    }
    

    static class DaoA implements IDao {

        List<Person> people = Data.buildPersonList(20);

        @Override
        public List<Person> findAll() {
            return people;
        }

    }

    @Test
    public void test_getDefaultInstance() throws Exception {
        IDao result = IDao.getDefaultInstance();
        

        assertThat(result.findAll(), hasSize(20));
    }
}
