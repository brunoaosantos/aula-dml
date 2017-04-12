package pt.ulisboa.tecnico.softeng.bank.domain;

public class SavingsAccount extends SavingsAccount_Base {

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(Bank bank, String IBAN) {
		super();
		init(bank,IBAN);
	}

	public void init(Bank bank, String IBAN) {
		super.init(bank, IBAN);
	}

	@Override
	public void deposit(int amount) {
		if(amount > 0 &&(amount % 100) == 0) {
			setBalance(getBalance() + amount);
		}
		else {
			System.out.print("Invalid amount");
		}
	}

	@Override
	public void withdraw(int amount) {
		if(amount > 0 && amount == getBalance()) {
			setBalance(getBalance() - amount);
		}
		else {
			System.out.print("Invalid amount");
		}
	}
}
