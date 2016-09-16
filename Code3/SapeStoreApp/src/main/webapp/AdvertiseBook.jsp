<div class="bookAd">
	<h2>Advertisement</h2>
	<div class="clearfix"></div>
	<div>
		<form name="advertisementForm" action="addToShoppingCart" method="GET">
			<ul>
				<li><a id="clickBook"
					href="/SapeStore/bookDetailsController?isbn=${adbook.isbn}"
					title="${adbook.bookTitle}"
					onclick="dataLayer.push({'bookprice' : '${adbook.bookPrice}',
										    'bookcategory' : 'Advertisement',
										    'booktitle' : '${adbook.bookTitle}'});"><span
						id="product_name">${adbook.bookTitle}</span>
						<p>${adbook.bookAuthor}</p> </a>
					<figure>
						<c:choose>
							<c:when test="${adbook.averageRating == null}">
								<img src="img/ratings-0.jpg" width="98" height="14"
									alt="ratings" />
							</c:when>
							<c:otherwise>
								<img src="img/ratings-${adbook.averageRating}.jpg" width="98"
									height="14" alt="ratings" />
							</c:otherwise>
						</c:choose>
					</figure>
					<p class="price">$${adbook.bookPrice}</p> <c:choose>
						<c:when test="${not empty userId}">
							<a id="addToCart"
								href="/SapeStore/addToShoppingCart?categoryId=${adbook.categoryId}&categoryName=${categoryName}&checkMe=${checkMe}&isbn=${adbook.isbn}&type=Purchase"
								title="Add To Cart" class="addCart"
								onclick="dataLayer.push({'bookprice' : '${adbook.bookPrice}',
										    'bookcategory' : 'Advertisement',
										    'booktitle' : '${adbook.bookTitle}'});">
								<img src="img/add_cart.jpg" width="15" height="13"
								alt="add to cart" />
							</a>
						</c:when>
						<c:otherwise>
							<script type="text/javascript">
								function alertIt() {
									alert("Please login to be able to use the cart.");
								}
							</script>
							<a href="#login" title="Add To Cart" class="addCart inline"
								id="addToCart"
								onclick="dataLayer.push({'bookprice' : '${adbook.bookPrice}',
										    'bookcategory' : 'Advertisement',
										    'booktitle' : '${adbook.bookTitle}'});"><img
								src="img/add_cart.jpg" width="15" height="13" alt="add to cart" /></a>
						</c:otherwise>
					</c:choose></li>
				<li><a id="clickBook"
					href="/SapeStore/bookDetailsController?isbn=${adbook.isbn}"
					title="${adbook.bookTitle}"
					onclick="dataLayer.push({'bookprice' : '${adbook.bookPrice}',
										    'bookcategory' : 'Advertisement',
										    'booktitle' : '${adbook.bookTitle}'});">
						<img src="${adbook.bookFullImage}" width="131" height="180" />
				</a></li>

			</ul>
		</form>
	</div>
</div>