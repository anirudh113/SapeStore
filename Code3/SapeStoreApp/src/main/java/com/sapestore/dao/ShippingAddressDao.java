package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.BookVO;

/**
 * The Class ShippingAddressDao.
 */
@Repository
public class ShippingAddressDao {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(AccountDao.class.getName());

  /** The hibernate template. */
  @Autowired
  private HibernateTemplate hibernateTemplate;

  /**
   * Gets the hibernate template.
   *
   * @return the hibernate template
   */
  public HibernateTemplate getHibernateTemplate() {
    return hibernateTemplate;
  }

  /**
   * Sets the hibernate template.
   *
   * @param hibernateTemplate
   *          the new hibernate template
   */
  public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
    this.hibernateTemplate = hibernateTemplate;
  }

  /**
   * Gets the customer details.
   *
   * @param userId
   *          the user id
   * @return the customer details
   */
  public User getCustomerDetails(String userId) {
    LOGGER.debug("");
    String[] namedParams = { "userId" };
    Object[] paramValues = { userId };
    List<User> listUser = (List<User>) hibernateTemplate
        .findByNamedQueryAndNamedParam("User.findByUserId", namedParams,
            paramValues);
    return listUser.get(0);
  }

  /**
   * Gets the customer address.
   *
   * @param userId
   *          the user id
   * @return the customer address
   */
  public Address getCustomerAddress(String userId) {
    // TODO Auto-generated method stub
    String[] namedParams = { "userId" };
    Object[] paramValues = { userId };
    List<Address> listUser = (List<Address>) hibernateTemplate
        .findByNamedQueryAndNamedParam("Address.findByUserId", namedParams,
            paramValues);
    return listUser.get(0);
  }

  /**
   * Gets the book by isbn.
   *
   * @param isbn
   *          the isbn
   * @return the book by isbn
   */
  public BookVO getBookByIsbn(String isbn) {
    String[] namedParams = { "isbn" };
    Object[] paramValues = { isbn };

    @SuppressWarnings("unchecked")
    List<Book> books = (List<Book>) (hibernateTemplate)
        .findByNamedQueryAndNamedParam("Book.getByIsbn", namedParams,
            paramValues);
    BookVO bookvo = setBookDetailBean(books.get(0));
    return bookvo;
  }

  /**
   * Sets the book detail bean.
   *
   * @param book
   *          the book
   * @return the book vo
   */
  private BookVO setBookDetailBean(Book book) {
    BookVO vo = null;
    if (book != null) {
      vo = new BookVO();
      vo.setIsbn(book.getIsbn());
      vo.setBookTitle(book.getBookTitle());
      vo.setBookAuthor(book.getBookAuthor());
      vo.setBookPrice(book.getBookPrice().toString());
      vo.setThumbPath(book.getBookThumbImage());
      vo.setQuantity(book.getQuantity());
      vo.setRentPrice(book.getRentPrice().toString());
      vo.setBookShortDesc(book.getBookShortDescription());
      vo.setCategoryId(book.getCategoryId().toString());
      vo.setCategoryName(book.getCategoryName());
      vo.setPublisherName(book.getPublisherName());
      vo.setRentAvailable(book.getRentAvailability());
      vo.setType("Purchase");
      // vo.setExpectedReturnDate();
    }
    return vo;
  }

  /**
   * Gets the state.
   *
   * @return the state
   */
  public List<State> getState() {
    // TODO Auto-generated method stub
    List<State> state = (List<State>) hibernateTemplate
        .findByNamedQuery("State.getAllStates");
    return state;
  }

  /**
   * Gets the city by state id.
   *
   * @param stateId
   *          the state id
   * @return the city by state id
   */
  public List<City> getCityByStateId(int stateId) {
    // TODO Auto-generated method stub
    List<City> cityList = (List<City>) hibernateTemplate.findByNamedParam(
        "from City c where c.stateId = :stateId", "stateId", stateId);
    return cityList;
  }

  /**
   * Gets the city by id.
   *
   * @param cityId
   *          the city id
   * @return the city by id
   */
  public City getCityById(Integer cityId) {
    // TODO Auto-generated method stub
    String[] namedParams = { "cityId" };
    Object[] paramValues = { cityId };
    List<City> listUser = (List<City>) hibernateTemplate
        .findByNamedQueryAndNamedParam("City.findByCityId", namedParams,
            paramValues);
    return listUser.get(0);
  }

  /**
   * Gets the state by id.
   *
   * @param stateId
   *          the state id
   * @return the state by id
   */
  public State getStateById(Integer stateId) {
    // TODO Auto-generated method stub
    String[] namedParams = { "stateId" };
    Object[] paramValues = { stateId };
    List<State> listUser = (List<State>) hibernateTemplate
        .findByNamedQueryAndNamedParam("State.findByStateId", namedParams,
            paramValues);
    return listUser.get(0);
  }

  /**
   * Update cutsomer info.
   *
   * @param user
   *          the user
   * @param address
   *          the address
   */
  public void updateCutsomerInfo(User user, Address address) {
    // TODO Auto-generated method stub
    System.out.println("dasdad");
    System.out.println("dasdad");
    hibernateTemplate.saveOrUpdate(user);
    hibernateTemplate.saveOrUpdate(address);

  }

  /**
   * Gets the high ids.
   *
   * @return the high ids
   */
  public List<OrderItemInfo> getHighIds() {
    // TODO Auto-generated method stub
    List<OrderItemInfo> orderItemInfo = (List<OrderItemInfo>) hibernateTemplate
        .findByNamedQuery("OrderItemInfo.getAllIds");
    return orderItemInfo;
  }

  /**
   * Update order info.
   *
   * @param orderinfo
   *          the orderinfo
   * @param orderItem
   *          the order item
   */
  public void updateOrderInfo(OrderInfo orderinfo,
      List<OrderItemInfo> orderItem) {
    // TODO Auto-generated method stub
    hibernateTemplate.save(orderinfo);
    for (OrderItemInfo orderItemInfo : orderItem) {

      hibernateTemplate.save(orderItemInfo);
    }
  }

  /**
   * Update books.
   *
   * @param updateInventoryBean
   *          the update inventory bean
   * @throws SapeStoreException
   *           the sape store exception
   */
  public void updateBooks(BookVO updateInventoryBean)
      throws SapeStoreException {
    LOGGER.debug(" ProductDao.updateBooks method: START ");

    try {
      String[] namedParams = { "isbn" };
      Object[] paramValues = { updateInventoryBean.getIsbn() };
      List<Book> books = (List<Book>) (hibernateTemplate)
          .findByNamedQueryAndNamedParam("Book.getByIsbn", namedParams,
              paramValues);
      Book book = books.get(0);
      if (book != null) {
        System.out.println("--PRODUCT DAO--" + updateInventoryBean);
        book.setIsbn(updateInventoryBean.getIsbn());
        book.setPublisherName(updateInventoryBean.getPublisherName());
        book.setBookTitle(updateInventoryBean.getBookTitle());
        book.setQuantity(updateInventoryBean.getQuantity());
        book.setBookAuthor(updateInventoryBean.getBookAuthor());
        book.setBookPrice(new BigDecimal(updateInventoryBean.getBookPrice()));
        book.setBookShortDescription(updateInventoryBean.getBookShortDesc());
        book.setRentAvailability(updateInventoryBean.getRentAvailable());
        book.setRentPrice(
            new BigDecimal(updateInventoryBean.getRentPrice().trim()));
        book.setBookDetailDescription(updateInventoryBean.getBookDetailDesc());
        book.setCategoryId(
            Integer.parseInt(updateInventoryBean.getCategoryId()));
        book.setBookThumbImage(updateInventoryBean.getThumbPath());
        book.setBookFullImage(updateInventoryBean.getFullPath());
        book.setUpdatedDate(new java.util.Date());
        hibernateTemplate.update(book);
        LOGGER.debug(" Book is updated ");
      }
    } catch (SapeStoreSystemException se) {
      LOGGER.fatal(
          "A DB exception occured while inserting the book's information", se);
    }
    LOGGER.debug(" ProductDao.updateBooks method: END ");
  }

  /*
   * public boolean saveShippingAddress(ShippingAddress shippingAddresssave) {
   * // TODO Auto-generated method stub
   * hibernateTemplate.save(shippingAddresssave); return true; }
   */

}
