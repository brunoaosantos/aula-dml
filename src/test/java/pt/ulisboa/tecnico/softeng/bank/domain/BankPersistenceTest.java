package pt.ulisboa.tecnico.softeng.bank.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;

public class BankPersistenceTest {
	private Account account;
	private Account account1;
	@Test
	public void success() {
		atomicProcess();
		atomicAssert();
	}

	@Atomic(mode = TxMode.WRITE)
	public void atomicProcess() {
		Bank bank1 = new Bank("Money", "BK01");
		account = new Account(bank1);
		account1 = new Account(bank1);
	}

	@Atomic(mode = TxMode.READ)
	public void atomicAssert() {
		Bank bank = Bank.getBankByCode("BK01");
		account.deposit(50);

		assertEquals("Money", bank.getName());
		assertEquals(2, bank.getAccountSet().size());
		assertEquals(50, account.getAmount());

		account.withdraw(25);
		account1.deposit(50);

		assertEquals(25, account.getAmount());
		assertEquals(75, bank.totalBalance(bank));
	}

	@After
	@Atomic(mode = TxMode.WRITE)
	public void tearDown() {
		for (Bank bank : FenixFramework.getDomainRoot().getBankSet()) {
			for (Account account : bank.getAccountSet()){
				account.delete();
			}
			bank.delete();
		}
	}

}
