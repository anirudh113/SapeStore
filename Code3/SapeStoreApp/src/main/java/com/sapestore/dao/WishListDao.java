
package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.WishList;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.WishListVO;

// TODO: Auto-generated Javadoc
/**
 * Accessing from Repository.
 * 
 * @author lborah,pramachandran
 * 
 *
 */
@Repository
public class WishListDao {

	/**
	 * This is to autowire SessionFactory.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * This is to autowire HibernateTemplate.
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * Gets the hibernate template.
	 *
	 * @return HibernateTemplate.
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * Sets the hibernate template.
	 *
	 * @param hibernateTemplate
	 *            - to set HibernateTemplate.
	 */
	public void setHibernateTemplate(final HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * Gets the session factory.
	 *
	 * @return SessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Sets the session factory.
	 *
	 * @param sessionFactory
	 *            - for SessionFactory.
	 */
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Logger for log messages.
	 */
	private static final SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(WishListDao.class.getName());

	/**
	 * Book vo to wish list vo.
	 *
	 * @param bookVO
	 *            - bookVo object.
	 * @param userId
	 *            - user id
	 * @return WishListVO object
	 * @throws SapeStoreException
	 *             exception.
	 */
	public WishListVO bookVOToWishListVO(BookVO bookVO, final String userId) throws SapeStoreException {
		LOGGER.info("Adding from BookVO to WishListVO");
		Session session = sessionFactory.openSession();
		Date date = new Date();
		WishListVO wishListVO = new WishListVO();
		wishListVO.setIsbn(bookVO.getIsbn());
		wishListVO.setCreatedDate(date);
		wishListVO.setUpdatedDate(date);
		wishListVO.setUserId(userId);
		wishListVO.setIsActive(bookVO.getActive());
		wishListVO.setBookPrice(bookVO.getBookPrice());
		wishListVO.setBookTitle(bookVO.getBookTitle());
		Book book = (Book) session.get(Book.class, bookVO.getIsbn());
		wishListVO.setBookThumbImage(book.getBookThumbImage());
		wishListVO.setBookFullImage(book.getBookFullImage());
		return wishListVO;

	}

	/**
	 * Wish list vo to book vo.
	 *
	 * @param wishListVO
	 *            - object of wishListVO
	 * @return BookVo object
	 * @throws SapeStoreException
	 *             - SapeStoreException, in case there is issue with database.
	 */

	public BookVO WishListVOToBookVO(final WishListVO wishListVO) throws SapeStoreException {
		BookVO bookVO = new BookVO();
		Book book = getBookByISBN(wishListVO.getIsbn());
		bookVO.setActive(book.getIsActive());
		bookVO.setBookAuthor(book.getBookAuthor());
		bookVO.setBookDetailDesc(book.getBookDetailDescription());
		bookVO.setBookPrice(book.getBookPrice().toString());
		bookVO.setBookDetailDesc(book.getBookDetailDescription());
		bookVO.setBookTitle(book.getBookTitle());
		bookVO.setCategoryId(book.getCategoryId().toString());
		bookVO.setCategoryName(book.getCategoryName());
		bookVO.setIsbn(book.getIsbn());
		bookVO.setOldIsbn(book.getIsbn());
		bookVO.setPublisherName(book.getPublisherName());
		bookVO.setQuantity(book.getQuantity());
		bookVO.setRentAvailable(book.getRentAvailability());
		bookVO.setRentPrice(book.getRentPrice().toString());
		bookVO.setThumbPath(book.getBookThumbImage());
		return bookVO;
	}

	/**
	 * Adds the to wish list.
	 *
	 * @param wishListVO
	 *            - object
	 * @param userId
	 *            - user Id
	 * @return boolean value
	 * @throws SapeStoreException
	 *             - SapeStoreException, in case there is issue with database.
	 */
	@SuppressWarnings("unchecked")
	public boolean addToWishList(final WishListVO wishListVO, final String userId) throws SapeStoreException {

		String[] namedParams = { "userId", "isbn" };
		Object[] paramValues = { userId, wishListVO.getIsbn() };

		List<WishList> arrList = (List<WishList>) hibernateTemplate
				.findByNamedQueryAndNamedParam("WishList.checkWishList", namedParams, paramValues);

		/*
		 * check if the book exists in the table if not available add to the
		 * wishlist table
		 */

		if (arrList.isEmpty()) {
			WishList wishList = new WishList();
			wishList.setCreatedDate(wishListVO.getCreatedDate());
			wishList.setIsActive(wishListVO.getIsActive());
			wishList.setIsbn(wishListVO.getIsbn());
			wishList.setUpdatedDate(wishListVO.getUpdatedDate());
			wishList.setUserId(userId);
			//wishList.setWishId(new Random().nextInt(100000));
			hibernateTemplate.save(wishList);

			return true;
		}

		return false;
	}

	/**
	 * Gets the book by isbn.
	 *
	 * @param isbn
	 *            - book identifier
	 * @return Book object
	 * @throws SapeStoreException
	 *             - SapeStoreException, in case there is issue with database.
	 */
	public Book getBookByISBN(final String isbn) throws SapeStoreException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Book book = (Book) session.get(Book.class, isbn);

		transaction.commit();
		session.close();
		return book;
	}

	/**
	 * Gets the all wish list vo.
	 *
	 * @param userId
	 *            - user identifier
	 * @return WishListVO List
	 * @throws SapeStoreException
	 *             - SapeStoreException, in case there is issue with database.
	 */
	@SuppressWarnings("unchecked")
	public List<WishListVO> getAllWishListVO(final String userId) throws SapeStoreException {
		BasicConfigurator.configure();
		List<WishListVO> arrListVO = new ArrayList<>();
		String[] namedParams = { "userId" };
		Object[] paramValues = { userId };

		List<WishList> arrList = (List<WishList>) hibernateTemplate
				.findByNamedQueryAndNamedParam("WishList.findByUserId", namedParams, paramValues);

		for (WishList wishList : arrList) {
			if (wishList != null) {

				WishListVO wishListVO = new WishListVO();
				BigDecimal bookPrice;
				Book book = getBookByISBN(wishList.getIsbn());
				bookPrice = book.getBookPrice();

				wishListVO.setBookPrice(bookPrice.toString());
				wishListVO.setCreatedDate(wishList.getCreatedDate());
				wishListVO.setIsActive(wishList.getIsActive());
				wishListVO.setIsbn(wishList.getIsbn());
				wishListVO.setUpdatedDate(wishList.getUpdatedDate());
				wishListVO.setUserId(wishList.getUserId());
				wishListVO.setWishId(wishList.getWishId());
				wishListVO.setBookTitle(book.getBookTitle());
				wishListVO.setBookThumbImage(book.getBookThumbImage());
				wishListVO.setBookFullImage(book.getBookFullImage());
				arrListVO.add(wishListVO);
			}
		}

		return arrListVO;

	}

	/**
	 * Gets the wish list vo.
	 *
	 * @param wishId
	 *            - wishList Identifier
	 * @return WishListVO object
	 * @throws SapeStoreException
	 *             - SapeStoreException, in case there is issue with database.
	 */
	public WishListVO getWishListVO(final Integer wishId) throws SapeStoreException {
		WishListVO wishListVO = new WishListVO();
		WishList wishList = new WishList();
		wishList = getWishListByWishId(wishId);

		BigDecimal bookPrice;
		Book book = getBookByISBN(wishList.getIsbn());
		bookPrice = book.getBookPrice();

		wishListVO.setBookPrice(bookPrice.toString());
		wishListVO.setCreatedDate(wishList.getCreatedDate());
		wishListVO.setIsActive(wishList.getIsActive());
		wishListVO.setIsbn(wishList.getIsbn());
		wishListVO.setUpdatedDate(wishList.getUpdatedDate());
		wishListVO.setUserId(wishList.getUserId());
		wishListVO.setWishId(wishList.getWishId());
		wishListVO.setBookTitle(book.getBookTitle());
		wishListVO.setBookThumbImage(book.getBookThumbImage());
		wishListVO.setBookFullImage(book.getBookFullImage());

		return wishListVO;
	}

	/**
	 * Gets the wish list by wish id.
	 *
	 * @param wishId
	 *            - wishList Identifier
	 * @return WishList object
	 * @throws SapeStoreException
	 *             - SapeStoreException, in case there is issue with database.
	 */
	private WishList getWishListByWishId(final Integer wishId) throws SapeStoreException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		WishList wishList = (WishList) session.get(WishList.class, wishId);

		transaction.commit();
		session.close();
		return wishList;
	}

	/**
	 * Delete from wish list.
	 *
	 * @param wishId
	 *            - wishList Identifier.
	 */
	public void deleteFromWishList(final Integer wishId) {
		WishList wishList = hibernateTemplate.get(WishList.class, wishId);
		hibernateTemplate.delete(wishList);

	}

	/**
	 * Gets the category name by id.
	 *
	 * @param categoryId
	 *            - category identifier
	 * @return String
	 * @throws SapeStoreException
	 *             - SapeStoreException, in case there is issue with database.
	 */
	public String getCategoryNameById(final Integer categoryId) throws SapeStoreException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		BookCategory bookCategory = (BookCategory) session.get(BookCategory.class, categoryId);

		transaction.commit();
		session.close();
		return bookCategory.getCategoryName();
	}

}
