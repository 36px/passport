package net36px.passport.boot.rest.web.service;

public interface WebSignInService {

	/********************************
	 * step-1 : send a verification code to the email address
	 * 
	 * @param email
	 *            the email of new account
	 * @return ticketId
	 ********************************/

	String signIn1(String email);

	/*****************************
	 * step-2 : verify
	 * 
	 * @param ticketId
	 *            the return value of step-1
	 * @param verification
	 *****************************/

	void signIn2(String ticketId, String verification);

}
