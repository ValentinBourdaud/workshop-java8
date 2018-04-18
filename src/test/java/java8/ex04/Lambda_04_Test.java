package java8.ex04;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Exercice 04 - FuncCollection
 */
public class Lambda_04_Test {

	// tag::interfaces[]
	interface GenericPredicate<T> {
		boolean test(T predicated);

	}

	interface GenericMapper<T, E> {
		E map(T mapped);
	}

	interface Processor<T> {
		void process(T processed);

	}
	// end::interfaces[]

	// tag::FuncCollection[]
	class FuncCollection<T> {

		private Collection<T> list = new ArrayList<>();

		public void add(T a) {
			list.add(a);
		}

		public void addAll(Collection<T> all) {
			for (T el : all) {
				list.add(el);
			}
		}
		// end::FuncCollection[]

		// tag::methods[]
		private FuncCollection<T> filter(GenericPredicate<T> predicate) {
			FuncCollection<T> result = new FuncCollection<>();
			for (T item : list) {
				if (predicate.test(item)) {
					result.add(item);
				}

			}
			return result;
		}

		private <E> FuncCollection<E> map(GenericMapper<T, E> mapper) {
			FuncCollection<E> result = new FuncCollection<>();
			for (T item : list)
				result.add(mapper.map(item));

			// TODO
			return result;
		}

		private void forEach(Processor<T> processor) {
			for (T item : list)
				processor.process(item);
		}
		// end::methods[]

	}

	// tag::test_filter_map_forEach[]
	@Test
	public void test_filter_map_forEach() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		FuncCollection<Person> personFuncCollection = new FuncCollection<>();
		personFuncCollection.addAll(personList);

		personFuncCollection.filter(t -> t.getAge() > 50).map(t -> {
			Account account = new Account();
			account.setBalance(1000);
			account.setOwner(t);
			return account;
		})

				.forEach(t -> assertTrue(t.getBalance() == 1000 && t.getOwner().getAge() > 50));
	}
	// end::test_filter_map_forEach[]

	// tag::test_filter_map_forEach_with_vars[]
	@Test
	public void test_filter_map_forEach_with_vars() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		FuncCollection<Person> personFuncCollection = new FuncCollection<>();
		personFuncCollection.addAll(personList);

		GenericPredicate<Person> filterByAge = (t -> t.getAge() > 50);

		GenericMapper<Person, Account> mapToAccount = (t -> {
			Account account = new Account();
			account.setBalance(1000);
			account.setOwner(t);
			return account;
		});

		Processor<Account> verifyAccount = (t -> assertTrue(t.getBalance() == 1000 && t.getOwner().getAge() > 50));

		personFuncCollection.filter(filterByAge).map(mapToAccount).forEach(verifyAccount);

	}
	// end::test_filter_map_forEach_with_vars[]

}
