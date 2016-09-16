package com.sapestore.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.AccountDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.AccountService;
import com.sapestore.service.BookService;
import com.sapestore.service.CommunicationService;
import com.sapestore.vo.ProfileVO;
import com.sapestore.vo.UserVO;
import com.sapestore.vo.ZipCodeValidatorVO;

@Controller
public class CustomErrorController {

 @RequestMapping("error") 
 public String customError(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
  // retrieve some useful information from the request
  Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
  Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
  // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
  String exceptionMessage = getExceptionMessage(throwable, statusCode);

  String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
  if (requestUri == null) {
   requestUri = "Unknown";
  }

  String message = MessageFormat.format("{0} returned for {1} with message {2}", 
   statusCode, requestUri, exceptionMessage
  ); 

  model.addAttribute("errorMessage", message);  
  return "error";
 }

 private String getExceptionMessage(Throwable throwable, Integer statusCode) {
  if (throwable != null) {
   return throwable.getMessage();
  }
  HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
  return httpStatus.getReasonPhrase();
 }
}
