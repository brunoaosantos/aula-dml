package pt.ulisboa.tecnico.softeng.bank.domain;


public class Account extends Account_Base{
	private int value;
	public Account(Bank bank){
		setBank(bank);
		this.value=0;
	}

	public void delete() {
		setBank(null);

		deleteDomainObject();
	}

	public void deposit(int amount) {
		if(amount > 0) {
			this.value += amount;
		}
		else {
			System.out.print("Invalid amount");
		}
	}

	public void withdraw(int amount) {
		if(amount > 0 && amount <= this.value) {
			this.value -= amount;
		}
		else {
			System.out.print("Invalid amount");
		}
	}

	public int getAmount() {
		return this.value;
	}
}
