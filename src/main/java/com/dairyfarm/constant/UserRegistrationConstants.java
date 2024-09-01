package com.dairyfarm.constant;

/**
 * @author Pavankumar - created date : Oct 4, 2021
 */
public interface UserRegistrationConstants {

	// MSG91 Integration Constants
	String SEND_OTP_URL = "https://api.msg91.com/api/v5/otp";

	String VERIFY_OTP_URL = SEND_OTP_URL + "/verify";

	String RESEND_OTP_URL = SEND_OTP_URL + "/retry";

	String AUTH_KEY = "121990AEfB3r8Qegr613d9740P1";

	String TEMPLATE_ID = "613efc3b508e016aa959a507";

}
