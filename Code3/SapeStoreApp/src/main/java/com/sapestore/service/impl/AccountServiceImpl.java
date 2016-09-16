package com.sapestore.service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.AccountDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.AccountService;
import com.sapestore.vo.ProfileVO;
import com.sapestore.vo.UserVO;

// TODO: Auto-generated Javadoc
/**
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	/** The Constant LOGGER. */
	private static final SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(AccountServiceImpl.class.getName());

	/** The account dao. */
	@Autowired
	private AccountDao accountDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#getUserDetail(java.lang.String)
	 */
	@Override
	public User getUserDetail(String userId) throws SapeStoreException {

		return accountDao.getUserDetailsById(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sapestore.service.AccountService#getUserAddress(java.lang.Integer)
	 */
	@Override
	public Address getUserAddress(Integer addressId) throws SapeStoreException {
		// TODO Auto-generated method stub
		return accountDao.getUserAddressById(addressId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#getCityList()
	 */
	@Override
	public List<City> getCityList() {
		// TODO Auto-generated method stub
		return accountDao.getCityList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#getStateList()
	 */
	@Override
	public List<State> getStateList() {
		// TODO Auto-generated method stub
		return accountDao.getStateList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sapestore.service.AccountService#getCityByStateId(java.lang.Integer)
	 */
	@Override
	public List<City> getCityByStateId(Integer stateId) {
		// TODO Auto-generated method stub
		return accountDao.getCityByStateId(stateId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#updateProfile(com.sapestore.vo.
	 * ProfileVO)
	 */
	@Override
	public void updateProfile(ProfileVO profilevo) {
		accountDao.updateProfile(profilevo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#getCityAndState(java.util.List,
	 * java.util.List, com.sapestore.vo.ProfileVO)
	 */
	@Override
	public String[] getCityAndState(List<City> cityList, List<State> stateList, ProfileVO profilevo) {

		return accountDao.getCityAndState(cityList, stateList, profilevo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#authenticate(com.sapestore.vo.
	 * UserVO)
	 */
	@Override
	public UserVO authenticate(UserVO userLogin) throws SapeStoreException {
		LOGGER.debug("authenticate method: START");

		// String hashPassword = Base64.getEncoder()
		// .encodeToString(userLogin.getPassword().getBytes());// This is the
		// hashed password
		String hashPassword = userLogin.getPassword();

		userLogin.setPassword(hashPassword);
		User user1 = accountDao.getUserDetails(userLogin.getUserId(), userLogin.getPassword());

		LOGGER.debug("authenticate method: END");
		if (user1 != null) {
			UserVO user = new UserVO();
			user.setAdmin(user1.getIsAdmin());
			user.setUserId(user1.getUserId());
			user.setName(user1.getName());
			user.setEmail(user1.getEmailAddress());
			user.setGenderId(user1.getGenderId());
			user.setMobileNumber(user1.getMobileNumber());
			user.setPhone(user1.getPhone());
			user.setActive(user1.getIsActive());

			return user;
		}
		return null;
	}

	/* prakhar - starks - start */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#addCustomer(com.sapestore.vo.
	 * ProfileVO)
	 */
	@Override
	public void addCustomer(ProfileVO profileVO) {
		// TODO Auto-generated method stub
		accountDao.addCustomer(profileVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#checkUser(java.lang.String)
	 */
	@Override
	public boolean checkUser(String userId) {
		// TODO Auto-generated method stub
		return accountDao.checkUser(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#checkEmail(java.lang.String)
	 */
	@Override
	public boolean checkEmail(String emailAddress) {
		// TODO Auto-generated method stub
		return accountDao.checkEmail(emailAddress);
	}

	/* prakhar - starks - end */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#validateEmail(java.lang.String)
	 */
	@Override
	public User validateEmail(String email) throws SapeStoreException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		LOGGER.debug("authenticate method: START");
		User user = accountDao.ValidateEmail(email);
		LOGGER.debug("authenticate method: END");
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sapestore.service.AccountService#updatePassword(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public User updatePassword(String userid, String password) throws SapeStoreException {
		// TODO Auto-generated method stub
		String hashPassword = Base64.getEncoder().encodeToString(password.getBytes());// This
																						// is
																						// the
																						// hashed
																						// password
		LOGGER.debug("update method: START");
		User update = accountDao.updatePassword(userid, hashPassword);
		LOGGER.debug("update method: START");

		return update;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sapestore.service.AccountService#validateUserId(java.lang.String)
	 */
	@Override
	public User validateUserId(String userid) throws SapeStoreException {
		// TODO Auto-generated method stub
		LOGGER.debug("authenticate method: START");
		User user = accountDao.ValidateUserId(userid);
		LOGGER.debug("authenticate method: END");
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapestore.service.AccountService#getCity(java.lang.String)
	 */
	@Override
	public String getCity(String cityId) {
		// TODO Auto-generated method stub
		return accountDao.getCity(cityId);
	}

}
