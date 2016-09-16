package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.WishListVO;

public interface WishListService {

  // boolean saveBook(Book book) throws SapeStoreException;
  WishListVO bookVOToWishListVO(BookVO bookVO, String userId)
      throws SapeStoreException;

  BookVO WishListVOToBookVO(WishListVO wishListVO) throws SapeStoreException;

  boolean addToWishList(WishListVO wishListVO, String userId)
      throws SapeStoreException;

  Book getBookByISBN(String isbn) throws SapeStoreException;

  List<WishListVO> getAllWishListVO(String userId) throws SapeStoreException;

  WishListVO getWishListVO(Integer wishId) throws SapeStoreException;

  void deleteFromWishList(Integer wishId) throws SapeStoreException;

  String getCategoryNameById(Integer categoryId) throws SapeStoreException;
}
