package java8.ex03;

import java8.data.Person;
import org.junit.Test;

import java.util.function.BinaryOperator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - java.util.function.BinaryOperator
 */
public class Function_03_Test {

    //  tag::makeAChild[]
  
    BinaryOperator<Person> makeAChild = (pere, mere) ->{
    	Person p = new Person();
    	p.setFirstname(pere.getFirstname()+" "+mere.getFirstname());
    	p.setLastname(pere.getLastname());
    	p.setAge(0);
    	p.setPassword(null);
		return p;
    	
    };
    //  end::makeAChild[]


    @Test
    public void test_makeAChild() throws Exception {

        Person father = new Person("John", "France", 25, "johndoe");
        Person mother = new Person("Aline", "Lebreton", 22, "alino");

        Person child = makeAChild.apply(father, mother);

        assertThat(child, hasProperty("firstname", is("John Aline")));
        assertThat(child, hasProperty("lastname", is("France")));
        assertThat(child, hasProperty("age", is(0)));
        assertThat(child, hasProperty("password", nullValue()));
    }

}
