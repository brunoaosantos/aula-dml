package pt.ulisboa.tecnico.softeng.bank.domain;


public class Account extends Account_Base{

	public Account(Bank bank){
		setBank(bank);
	}

	public void delete() {
		setBank(null);

		deleteDomainObject();
	}
}
