/**
 * 
 */
function dis_able() {
	var r = $("#rentAvailable_id").val().trim();
	if (r == "N") {
		document.getElementById("rentPrice_id").value = "";
		document.getElementById("rentPrice_id").disabled = true;
	} else {
		document.getElementById("rentPrice_id").disabled = false;
	}
}
function HandleBrowseClick() {
	var fileinput = document.getElementById("thumbImage");
	fileinput.click();
}
function Handlechange() {
	var fileinput = document.getElementById("thumbImage");
	if (fileinput) {
		var startIndex = (fileinput.indexOf('\\') >= 0 ? fileinput
				.lastIndexOf('\\') : fileinput.lastIndexOf('/'));
		var filename = fileinput.substring(startIndex);
		if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
			filename = filename.substring(1);
		}
		var textinput = document.getElementById("filename");
		textinput.value = filename.value;
	}

}
function HandleBrowseClickFullImage() {
	var fileinput = document.getElementById("fullImage");
	fileinput.click();
}
function HandlechangeFullImage() {
	var fileinput = document.getElementById("fullImage");
	if (fileinput) {
		var startIndex = (fileinput.indexOf('\\') >= 0 ? fileinput
				.lastIndexOf('\\') : fileinput.lastIndexOf('/'));
		var filename = fileinput.substring(startIndex);
		if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
			filename = filename.substring(1);
		}
		var textinput = document.getElementById("filenameFullImage");
		textinput.value = filename.value;
	}

}
function validateInput() {
	if (document.getElementById("addAddress_bookPrice").value == null) {
		document.getElementById("errorPrice").innerHTML = "Please provide book price.";
	} else if (document.getElementById("addAddress_bookPrice").value == "") {
		document.getElementById("errorPrice").innerHTML = "Please provide book price.";
	} else if (document.getElementById("addAddress_quantity").value == "") {
		document.getElementById("errorQuantity").innerHTML = "Please provide book quantity.";
	} else if (document.getElementById("addAddress_quantity").value == "") {
		document.getElementById("errorQuantity").innerHTML = "Please provide book quantity.";
	} else {
		document.addBooksForm.submit();
	}
}

function submitTheForm() {

	var flag = true;
	//$(".error").remove();
	var characterReg = /^\d+$/;
	
	var nameReg = /^[a-zA-Z0-9 ]{1,100}$/;
	var isbnNameReg = /^[a-zA-Z0-9]{10,10}$/;
	var alphaReg = /^[.a-zA-Z ]{1,100}$/;
	//  var detailsReg=/^[-.,a-zA-Z0-9 ]{1,150}$/;
	$("#addAddress_quantitydiv")
	.html("");
	$("#addAddress_bookPricediv")
	.html("");
	$("#rentPrice_iddiv")
	.html("");
	
	var quantityVal = $("#addAddress_quantity").val();
	var quantityValT = quantityVal.trim();
	if (!characterReg.test(quantityValT)) {
		$("#addAddress_quantitydiv")
				.html(
						'<span class="error"> 		Enter only numbers between 1-9</span>');
		flag = false;
	}

	var quantityVal = $("#addAddress_isbn").val();
	var quantityValT = quantityVal.trim();
	if (!isbnNameReg.test(quantityValT)) {
		$("#addAddress_isbndiv")
				.html(
						'<span class="error"> 		Enter exactly 10 DIGITS</span>');
		flag = false;
	}

	var bookPrice = $("#addAddress_bookPrice").val();
	var bookPriceT = bookPrice.trim();
	if (!characterReg.test(bookPriceT)) {
		$("#addAddress_bookPricediv")
				.html(
						'<span class="error"> 		Enter only numbers between 1-9(max 3 digits) </span>');
		flag = false;

	}

	if ($("#rentAvailable_id").val().trim() == "Y") {
		var rentPrice = $("#rentPrice_id").val();
		var rentPriceT = rentPrice.trim();
		var bookPrice = $("#addAddress_bookPrice").val();
		var bookPriceT = bookPrice.trim();
		if (!characterReg.test(rentPriceT)) {
			$("#rentPrice_iddiv")
					.html(
							'<span class="error"> 		Enter only numbers between 1-9(max 3 digits) </span>');
			flag = false;
		}
		if (parseInt(rentPriceT) >= parseInt(bookPriceT)) {

			$("#rentPrice_iddiv")
					.html(
							'<span class="error"> 		rent price should be lesser than cost price </span>');
			flag = false;
		}

	}

	if (flag == true) {
		document.addBooksForm.submit();
	}

}

function goBack() {
	window.history.back();
}