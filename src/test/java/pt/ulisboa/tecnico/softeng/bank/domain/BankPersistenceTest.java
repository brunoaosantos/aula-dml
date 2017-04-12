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
	private SavingsAccount saves;
	@Test
	public void success() {
		atomicProcess();
		atomicAssert();
		atomicProcess1();
		atomicAssert1();
		atomicProcess2();
		atomicAssert2();
	}

	@Atomic(mode = TxMode.WRITE)
	public void atomicProcess() {
		Bank bank1 = new Bank("Money", "BK01");
		account = new Account(bank1, "123");
		account1 = new Account(bank1, "234");
		account.deposit(50);
	}

	@Atomic(mode = TxMode.READ)
	public void atomicAssert() {
		Bank bank = Bank.getBankByCode("BK01");

		assertEquals("Money", bank.getName());
		assertEquals(2, bank.getAccountSet().size());
		assertEquals(50, account.getBalance());

	}

	@Atomic(mode = TxMode.WRITE)
	public void atomicProcess1() {
		account.withdraw(25);
		account1.deposit(50);
	}

	@Atomic(mode = TxMode.READ)
	public void atomicAssert1() {
		Bank bank = Bank.getBankByCode("BK01");

		assertEquals(25, account.getBalance());
		assertEquals(75, bank.totalBalance(bank));
	}

	@Atomic(mode = TxMode.WRITE)
	public void atomicProcess2() {
		Bank bank2 = new Bank("Money", "BK02");
		saves = new SavingsAccount(bank2, "AS");
		saves.deposit(500);
	}

	@Atomic(mode = TxMode.READ)
	public void atomicAssert2() {
		Bank bank = Bank.getBankByCode("BK02");

		assertEquals(500, saves.getBalance());
		assertEquals(500, bank.totalBalance(bank));
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
