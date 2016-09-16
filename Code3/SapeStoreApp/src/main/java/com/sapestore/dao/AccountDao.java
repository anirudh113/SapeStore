package com.sapestore.dao;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.CommunicationService;
import com.sapestore.service.impl.CommunicationServicelImpl;
import com.sapestore.service.impl.DbConnect;
import com.sapestore.vo.ProfileVO;

// TODO: Auto-generated Javadoc
/**
 * DAO class to fetch user's login details
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

/**
 * @author Anshul .
 * @author Prakhar
 * @author Akshay
 *
 */
@Repository
public class AccountDao {

	/**
	 * Logger for log messages.
	 */
	private static final SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(AccountDao.class.getName());

	/** The hibernate template. */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * Method to fetch user login details based on the parameters provided .
	 *
	 * @param userId
	 *            the user id
	 * @param password
	 *            .
	 * @return .
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@SuppressWarnings("unchecked")
	public User getUserDetails(String userId, String password) throws SapeStoreException {
		BasicConfigurator.configure();
		LOGGER.debug("");
		String[] namedParams = { "userId", "password" };
		Object[] paramValues = { userId, password };

		List<User> listUser = (List<User>) hibernateTemplate
				.findByNamedQueryAndNamedParam("User.findByUserIdandPassword", namedParams, paramValues);
		if (!listUser.isEmpty()) {
			return listUser.get(0);
		} else {
			return null;
		}
	}

	/**
	 * user details by userid.
	 *
	 * @param userId
	 *            userId.
	 * @return user
	 * @throws SapeStoreException
	 *             exception.
	 */
	public User getUserDetailsById(String userId) throws SapeStoreException {
		LOGGER.debug("");
		BasicConfigurator.configure();
		String namedParams = "userId";
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) hibernateTemplate.findByNamedQueryAndNamedParam("User.findByUserId",
				namedParams, userId);

		if (!listUser.isEmpty()) {
			return listUser.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Method for citylist .
	 * 
	 * @return citylist .
	 */
	public List<City> getCityList() {
		BasicConfigurator.configure();
		String query = "from City city";
		@SuppressWarnings("unchecked")
		List<City> cityList = (List<City>) hibernateTemplate.find(query);

		return cityList;

	}

	/**
	 * Retrives address by id.
	 *
	 * @param addressId
	 *            .
	 * @return address .
	 * @throws SapeStoreException
	 *             .
	 */
	public Address getUserAddressById(Integer addressId) throws SapeStoreException {
		BasicConfigurator.configure();
		LOGGER.debug("");
		String[] namedParams = { "addressId" };
		Object[] paramValues = { addressId };
		@SuppressWarnings("unchecked")
		List<Address> address = (List<Address>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Address.findByAddressId", namedParams, paramValues);

		return address.get(0);

	}

	/**
	 * updates database with user info.
	 *
	 * @param profilevo
	 *            .
	 */
	public void updateProfile(ProfileVO profilevo) {

		CommunicationService service = new CommunicationServicelImpl();

		BasicConfigurator.configure();

		User user = new User();

		String namedParams = "userId";
		String paramValues = profilevo.getUserId();
		user = hibernateTemplate.get(User.class, profilevo.getUserId());

		String paramname = "addressId";
		Integer paramValue = user.getAddressId();

		Address address;
		address = hibernateTemplate.get(Address.class, paramValue);

		address.setAddressLine1(profilevo.getAddressLine1());
		address.setAddressLine2(profilevo.getAddressLine2()); // auto generate
																// add id
		address.setUserId(user.getUserId());
		address.setPostalCode(profilevo.getPostalCode());
		address.setIsActive("Y");
		address.setCityId(Integer.parseInt(profilevo.getCityId()));
		address.setStateId(Integer.parseInt(profilevo.getStateId()));

		String hashPassword = Base64.getEncoder().encodeToString(profilevo.getPassword().getBytes());

		user.setPassword(hashPassword);
		user.setMobileNumber(profilevo.getMobileNumber());
		user.setPhone(profilevo.getPhone());

		user.setAddressId(address.getAddressId());
		hibernateTemplate.update(address);
		hibernateTemplate.update(user);
		try {
			service.communicateUpdate(user);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * retrives city and state of user.
	 *
	 * @param cityList
	 *            .
	 * @param stateList
	 *            .
	 * @param profilevo
	 *            .
	 * @return string array with city and state
	 */
	public String[] getCityAndState(List<City> cityList, List<State> stateList, ProfileVO profilevo) {
		String[] output = new String[2];

		for (City city : cityList) {
			int city1 = (int) city.getCityId();
			int city2 = (int) Integer.parseInt(profilevo.getCityId());

			if (city1 == city2) {
				output[0] = city.getCityName();
			}
		}

		for (State state : stateList) {
			if (state.getStateId().toString().equalsIgnoreCase(profilevo.getStateId())) {
				output[1] = state.getStateName();
			}
		}

		return output;
	}

	/**
	 * Gets the state list.
	 *
	 * @return the state list
	 */
	public List<State> getStateList() {
		String query = "from State state";
		@SuppressWarnings("unchecked")
		List<State> stateList = (List<State>) hibernateTemplate.find(query);
		return stateList;
	}

	/**
	 * retrives citylist .
	 * 
	 * @param stateId
	 *            .
	 * @return citylist .
	 */
	public List<City> getCityByStateId(Integer stateId) {
		@SuppressWarnings("unchecked")
		List<City> cityList = (List<City>) hibernateTemplate.findByNamedParam("from City c where c.stateId = :stateId",
				"stateId", stateId);
		return cityList;
	}

	/**
	 * Adds the customer.
	 *
	 * @param profileVO
	 *            the profile vo
	 */
	public void addCustomer(ProfileVO profileVO) {
		User user = new User();
		Address address = new Address();
		State state = new State();
		address.setUserId(profileVO.getUserId());
		address.setAddressLine1(profileVO.getAddressLine1());
		address.setAddressLine2(profileVO.getAddressLine2()); // auto generate
																// add id
		address.setCityId(Integer.parseInt(profileVO.getCityId()));
		address.setStateId(Integer.parseInt(profileVO.getStateId()));
		address.setPostalCode(profileVO.getPostalCode());
		address.setIsActive("Y");
		address.setCreatedDate(new java.util.Date());
		address.setUpdatedDate(new java.util.Date());
		state = (State) hibernateTemplate.get(State.class, Integer.parseInt(profileVO.getStateId()));
		address.setCountryId(state.getCountryId());
		hibernateTemplate.save(address);
		// String hashPassword = Base64.getEncoder()
		// .encodeToString(profileVO.getPassword().getBytes());
		String hashPassword = profileVO.getPassword();
		user.setAddressId(address.getAddressId());
		user.setGenderId(Integer.parseInt(profileVO.getGenderId()));
		user.setEmailAddress(profileVO.getEmailAddress());
		user.setName(profileVO.getName());
		user.setPassword(hashPassword);
		user.setMobileNumber(profileVO.getMobileNumber());
		user.setPhone(profileVO.getPhone());
		user.setUserId(profileVO.getUserId());
		user.setIsActive("Y");
		user.setUserStatus("Y");
		user.setCreatedDate(new java.util.Date());
		user.setUpdatedDate(new java.util.Date());
		user.setIsAdmin("N");
		hibernateTemplate.save(user);
		
		String userId = user.getUserId();
		String cookieId = UUID.randomUUID().toString();
		new DbConnect().insertCookieId(userId, cookieId);
		
		
		CommunicationService service = new CommunicationServicelImpl();
		try {
			service.communicateRegister(user);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Check user.
	 *
	 * @param userId
	 *            the user id
	 * @return true, if successful
	 */
	public boolean checkUser(String userId) {
		// TODO Auto-generated method stub
		if (hibernateTemplate.get(User.class, userId) == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Check email.
	 *
	 * @param emailAddress
	 *            the email address
	 * @return true, if successful
	 */
	public boolean checkEmail(String emailAddress) {
		// TODO Auto-generated method stub
		String query = "from User u where u.emailAddress = :emailAddress";
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) hibernateTemplate.findByNamedParam(query, "emailAddress", emailAddress);
		if (userList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Validate user id.
	 * 
	 * @author Mukesh
	 *
	 * @param userid
	 *            the userid
	 * @return the user
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@SuppressWarnings("unchecked")
	public User ValidateUserId(String userid) throws SapeStoreException {
		LOGGER.debug("");
		String[] namedParams = { "userId" };
		Object[] paramValues = { userid };

		List<User> listUser = (List<User>) hibernateTemplate.findByNamedQueryAndNamedParam("User.findByUserId",
				namedParams, paramValues);
		if (!listUser.isEmpty()) {
			return listUser.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Update password.
	 * 
	 * @author Mukesh
	 *
	 * @param userid
	 *            the userid
	 * @param newpassword
	 *            the newpassword
	 * @return the user
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@SuppressWarnings("unchecked")
	public User updatePassword(String userid, String newpassword) throws SapeStoreException {
		LOGGER.debug("");

		User user = hibernateTemplate.get(User.class, userid);
		user.setPassword(newpassword);
		hibernateTemplate.saveOrUpdate(user);
		return user;
	}

	/**
	 * Validate email.
	 * 
	 * @author Mukesh
	 *
	 * @param email
	 *            the email
	 * @return the user
	 */
	public User ValidateEmail(String email) {
		LOGGER.debug("");
		String[] namedParams = { "emailAddress" };
		Object[] paramValues = { email };

		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) hibernateTemplate.findByNamedQueryAndNamedParam("User.findByEmail",
				namedParams, paramValues);
		if (!listUser.isEmpty()) {
			return listUser.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Gets the city.
	 *
	 * @param cityId
	 *            the city id
	 * @return the city
	 */
	public String getCity(String cityId) {
		// TODO Auto-generated method stub
		City city = (City) hibernateTemplate.get(City.class, Integer.parseInt(cityId));
		return city.getCityName();
	}

	public String getCookieId(String userId) {
		// TODO Auto-generated method stub
		DbConnect dbConnect = new DbConnect();
		
		return dbConnect.getCookieId(userId);
	}

}
