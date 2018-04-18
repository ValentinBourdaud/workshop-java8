package java8.ex01;

import java8.data.Account;
import java8.data.Person;
import org.junit.Test;

import java.util.function.Function;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 01 - java.util.function.Function
 */
public class Function_01_Test {

	/******** PART 1 - Integer -> Person *******/

	// tag::intToPerson[]

	private Function<Integer, Person> intToPerson = entier -> {
		Person p = new Person();
		p.setFirstname("first_" + entier);
		p.setLastname("last_" + entier);
		p.setAge(entier);
		p.setPassword("pass_" + entier);
		return p;
	};
	// end::intToPerson[]

	@Test
	public void test_intToPerson() throws Exception {

		Person result = intToPerson.apply(10);

		assertThat(result, hasProperty("firstname", is("first_10")));
		assertThat(result, hasProperty("lastname", is("last_10")));
		assertThat(result, hasProperty("age", is(10)));
		assertThat(result, hasProperty("password", is("pass_10")));
	}

	/******** PART 2 - Person -> Account *******/

	// tag::personToAccount[]

	private Function<Person, Account> personToAccount = person -> {
		Account a = new Account();
		a.setOwner(person);
		a.setBalance(1000);
		return a;
	};
	// end::personToAccount[]

	@Test
	public void test_personToAccount() throws Exception {

		Person person = new Person("Jules", "France", 10, "pass");

		// TODO invoquer la fonction personToAccount
		Account result = personToAccount.apply(person);

		assertThat(result, hasProperty("owner", is(person)));
		assertThat(result, hasProperty("balance", is(1000)));
	}

	/******** PART 3 - Integer -> Account avec compose *******/

	// tag::intToAccountWithCompose[]

	private Function<Integer, Account> intToAccountWithCompose = (personToAccount.compose(intToPerson));
	// end::intToAccountWithCompose[]

	@Test
	public void test_intToAccount_with_Compose() throws Exception {

		Account result = intToAccountWithCompose.apply(10);

		assertThat(result.getOwner(), hasProperty("firstname", is("first_10")));
		assertThat(result, hasProperty("balance", is(1000)));
	}

	/******** PART 4 - Integer -> Account avec andThen *******/

	// tag::intToAccountWithAndThen[]

	private Function<Integer, Account> intToAccountWithAndThen = (intToPerson.andThen(personToAccount));
	// end::intToAccountWithAndThen[]

	@Test
	public void test_intToAccount_with_AndThen() throws Exception {

		Account result = intToAccountWithAndThen.apply(11);

		assertThat(result.getOwner(), hasProperty("firstname", is("first_11")));
		assertThat(result, hasProperty("balance", is(1000)));
	}
}
