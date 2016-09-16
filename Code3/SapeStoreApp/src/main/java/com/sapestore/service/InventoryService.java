package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.vo.BookVO;

/**
 * Service interface for Manage Inventory functionality.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
/**
 * @author Amogh .
 * @author Shrihari
 * @author Vishak
 *
 */

public interface InventoryService {

  /**
   * Returns admin inventory list.
   * 
   * @return list of Book objects for admin
   */
  public List<Book> getAdminInventory();

  /**
   * Updates book details
   * @param updateInventory .
   * @throws SapeStoreSystemException .
   */
  void updateBooks(BookVO updateInventory) throws SapeStoreException;

}
