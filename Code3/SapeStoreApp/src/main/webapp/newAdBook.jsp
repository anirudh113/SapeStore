<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
	<div id="mainContent" style="width:700px; min-height:100px; border-right:none; padding:0 0 0 0;">
		<div class="bookAd">
			<h2>Advertisement</h2>
			<div class="clearfix"></div>
			<div>
				<form name="advertisementForm" action="addToShoppingCart"
					method="GET">
					<ul>
						<li><a id="clickBook"
							href="/SapeStore/bookDetailsController?isbn=${AdBook.isbn}"
							title="${AdBook.bookTitle}"
							onclick="dataLayer.push({'bookprice' : '${AdBook.bookPrice}',
										    'bookcategory' : 'Advertisement',
										    'booktitle' : '${AdBook.bookTitle}'});"><span
								id="product_name">${AdBook.bookTitle}</span>
								<p>${AdBook.bookAuthor}</p> </a>
							<figure>
								<c:choose>
									<c:when test="${AdBook.averageRating == null}">
										<img src="img/ratings-0.jpg" width="98" height="14"
											alt="ratings" />
									</c:when>
									<c:otherwise>
										<img src="img/ratings-${AdBook.averageRating}.jpg" width="98"
											height="14" alt="ratings" />
									</c:otherwise>
								</c:choose>
							</figure>
							<p class="price">$${AdBook.bookPrice}</p> <c:choose>
								<c:when test="${not empty userId}">
									<a id="addToCart"
										href="/SapeStore/addToShoppingCart?categoryId=${AdBook.categoryId}&categoryName=${categoryName}&checkMe=${checkMe}&isbn=${AdBook.isbn}&type=Purchase"
										title="Add To Cart" class="addCart"
										onclick="dataLayer.push({'bookprice' : '${AdBook.bookPrice}',
										    'bookcategory' : 'Advertisement',
										    'booktitle' : '${AdBook.bookTitle}'});">
										<img src="img/add_cart.jpg" width="15" height="13"
										alt="add to cart" />
									</a>
								</c:when>
								<c:otherwise>

									<script type="text/javascript">
										function advcartlogin() {
											$("#loginPop").click();
										}
										
									</script>
									<a href="#" title="Add To Cart" class="addCart inline"
										id="addToCart"
										onclick="dataLayer.push({'bookprice' : '${AdBook.bookPrice}',
										    'bookcategory' : 'Advertisement',
										    'booktitle' : '${AdBook.bookTitle}'}); advcartlogin();"><img
										src="img/add_cart.jpg" width="15" height="13"
										alt="add to cart" /></a>
								</c:otherwise>
							</c:choose></li>
						<li><a id="clickBook"
							href="/SapeStore/bookDetailsController?isbn=${AdBook.isbn}"
							title="${AdBook.bookTitle}"
							onclick="dataLayer.push({'bookprice' : '${AdBook.bookPrice}',
										    'bookcategory' : 'Advertisement',
										    'booktitle' : '${AdBook.bookTitle}'});">
								<img src="${AdBook.bookFullImage}" width="131" height="180" />
						</a></li>

					</ul>
				</form>
			</div>
		</div>
	</div>
</body>
</html>