package pt.ulisboa.tecnico.softeng.bank.domain;

import pt.ist.fenixframework.FenixFramework;

public class Account extends Account_Base{

	public Account() {
		super();
	}
	public Account(Bank bank, String IBAN){
		init(bank, IBAN);
	}

	public void init(Bank bank, String IBAN){
		setBank(bank);
		setIBAN(IBAN);
		setBalance(0);
	}

	public void delete() {
		setBank(null);

		deleteDomainObject();
	}

	public void deposit(int amount) {
		if(amount > 0) {
			setBalance(getBalance() + amount);
		}
		else {
			System.out.print("Invalid amount");
		}
	}

	public void withdraw(int amount) {
		if(amount > 0 && amount <= getBalance()) {
			setBalance(getBalance() - amount);
		}
		else {
			System.out.print("Invalid amount");
		}
	}

}
