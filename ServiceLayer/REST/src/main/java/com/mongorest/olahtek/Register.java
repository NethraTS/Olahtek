package com.mongorest.olahtek;

public class Register {

	
		int user_id;
		String email;
		String _id;
		String city;
		String beforetaxincome;
		int age;
		String Taxable_investment;
		String Monthly_expenses;
		public String getMonthly_expenses() {
			return Monthly_expenses;
		}

		public void setMonthly_expenses(String monthly_expenses) {
			Monthly_expenses = monthly_expenses;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTaxable_investment() {
			return Taxable_investment;
		}

		public void setTaxable_investment(String taxable_investment) {
			Taxable_investment = taxable_investment;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getBeforetaxincome() {
			return beforetaxincome;
		}

		public void setBeforetaxincome(String beforetaxincome) {
			this.beforetaxincome = beforetaxincome;
		}

		public String get_id() {
			return _id;
		}

		public void set_id(String _id) {
			this._id = _id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getUser_id() {
			return user_id;
		}

		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
		
}