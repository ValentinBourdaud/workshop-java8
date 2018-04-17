package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper {
        Account map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<Account> map(List<Person> personList, PersonToAccountMapper mapper) {
    	List<Account> mappedList = new ArrayList<Account>();
    	for(int i = 0; i<personList.size();i++)
    		mappedList.add(mapper.map(personList.get(i)));
		return mappedList; 
    	
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        
        PersonToAccountMapper pa= p->{
        	Account account = new Account();
        	account.setBalance(100);
        	account.setOwner(p);
        	return account;
        };
    

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        List<Account> result = map(personList, pa);

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        List<String> stringList = new ArrayList<>();
        
       for (Person person : personList)
       
    	   stringList.add(person.getFirstname());
       
       List<String> result = stringList;

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    };
    // end::test_map_person_to_firstname[]
}

