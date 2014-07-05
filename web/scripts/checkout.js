// All Javascript function related to checkout are here

//Functions to validate the order forms
function PayPal()
{
	//first check to see if a shipping method has been chosen
	//alert(document.shipping.txtShippingText.value);
	if(document.shop.txtShippingText.value == '0') //0 means no shipping selected
	{
		alert("You need to choose a shipping method before you can finalize payment!");
		return;
	}
	
    if (document.shop.ship_fname.value =='')
    {
        alert('Please enter Shipping First Name.');
        document.shop.ship_fname.focus();
        return;
    }

    if (document.shop.ship_lname.value ==''){
        alert('Please enter Shipping Last Name.');
        document.shop.ship_lname.focus();
        return;
    }

    if (document.shop.ship_address1.value ==''){
        alert('Please enter Shipping Address.');
        document.shop.ship_address1.focus();
        return;
    }

    if (document.shop.ship_city.value ==''){
        alert('Please enter Shipping City.');
        document.shop.ship_city.focus();
        return;
    }
    if (document.shop.ship_state.value ==''){
        alert('Please enter Shipping State.');
        document.shop.ship_state.focus();
        return;
    }

    if (document.shop.ship_postalCode.value ==''){
        alert('Please enter Shipping Zip Code.');
        document.shop.ship_postalCode.focus();
        return;
    }

    if (document.shop.ship_email.value ==''){
        alert('Please enter Email Address.');
        document.shop.ship_email.focus();
        return;
    }
	
    if (document.shop.bill_fname.value ==''){
        alert('Please enter Billing First Name.');
        document.shop.bill_fname.focus();
        return;
    }

    if (document.shop.bill_lname.value ==''){
        alert('Please enter Billing Last Name.');
        document.shop.bill_lname.focus();
        return;
    }

    if (document.shop.bill_address1.value ==''){
        alert('Please enter Shipping Address.');
        document.shop.bill_address1.focus();
        return;
    }

    if (document.shop.bill_city.value ==''){
        alert('Please enter Billing City.');
        document.shop.bill_city.focus();
        return;
    }

    if (document.shop.bill_state.value ==''){
        alert('Please enter Billing State.');
        document.shop.bill_state.focus();
        return;
    }


    if (document.shop.bill_postalCode.value ==''){
        alert('Please enter Billing Zip.');
        document.shop.bill_postalCode.focus();
        return;
    }

    if (document.shop.bill_country.options[document.shop.bill_country.selectedIndex].value ==''){
        alert('Please enter Billing Country.');
        document.shop.bill_country.focus();
        return;
    }

    if (document.shop.bill_phone.value ==''){
        alert('Please enter Billing Phone Number.');
        document.shop.bill_phone.focus();
        return;
    }
	
	if (document.shop.bill_card_number.value.length == 0) 
	{
		alert("Please enter a Card Number.");
		document.shop.CardNumber.focus();
		return;
	}
	   
	if (document.shop.CVV2.value =='')
    {
        alert('Please enter CVV2 Verification Code.');
        document.shop.CVV2.focus();
        return;
    }
	
	//alert("Checking Credit Card");
	//alert(document.shop.bill_card_number.value);
	
	if(CheckCardNumber(document.shop))
	{	
		alert("Somehow credit card number is not correct?!!!");	
		return;
	}
	else
	{
		//alert("Credit card number is correct!");
	}

	//alert("Checking Agree Checkbox");
	//alert(document.shop.chkAgree.checked);
	
    if (document.shop.chkAgree.checked) 
	{		
		document.shop.submit();
		document.shop.PayButton.value = "Processing...";
		document.shop.PayButton.disabled = true;    
	}
    else 
	{
        alert('You have to click agree check box before ordering.');
        document.shop.chkAgree.focus();
        return;
    }

    
}

function popUp2(URL) 
{
    day = new Date();
    id = day.getTime();
    eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=800,height=800,left = 0,top = 0');");
}

function popUp(URL) 
{
    day = new Date();
    id = day.getTime();
    eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=600,height=500,left = 0,top = 0');");
}

function PopWindowShipping()
{
    day = new Date();
    id = day.getTime();
    eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=490,height=410,left = 0,top = 0');");
}
		
function samebill() 
{
    document.shop.bill_fname.value = document.shop.ship_fname.value;
    document.shop.bill_lname.value = document.shop.ship_lname.value;
    document.shop.bill_address1.value = document.shop.ship_address1.value;
    document.shop.bill_address2.value = document.shop.ship_address2.value;
    document.shop.bill_city.value = document.shop.ship_city.value;
    document.shop.bill_state.value = document.shop.ship_state.value;
    document.shop.bill_postalCode.value = document.shop.ship_postalCode.value;
    document.shop.bill_country.value = document.shop.ship_country.value;
}

function GetShippingState()
{
    var details = document.getElementById("shippingrules");
	
    if (document.shop.cboSState.options[document.shop.cboSState.selectedIndex].value =='NA')
    {
        details.innerHTML = "<input type=radio name=chkSMethod value='15' checked  onclick=SelectShp('Ground')>FREE UPS GROUND<br>";
        document.shop.cboSCountry.options.length=0;
        document.shop.cboSCountry.options[0]=new Option("Canada", "can", true, false);
        document.shop.cboSCountry.options[1]=new Option("United Kingdom", "uk", true, false);
        document.shop.cboSCountry.options[2]=new Option("United States", "usa", true, false);	
    }
}


function checkShipping()
{
	//alert(document.shipping.txtShippingText.value);
	if(document.shipping.txtShippingText.value == '0')
	{
		alert("You need to choose a shipping method before you can finalize payment!");
	}
	else
	{
		document.shipping.submit();
	}
}




function GetShipping() 
{
    var details = document.getElementById("shippingrules");
	
    if (document.shop.cboSCountry.options[document.shop.cboSCountry.selectedIndex].value !='usa')
    {
        alert("international (EMS) shipping must be chosen");
        document.shop.txtShippingText.value = "EMS";
        document.shop.chkSMethod[0].disabled = true;
        document.shop.chkSMethod[1].disabled = true;
        document.shop.chkSMethod[2].checked = true;
    }
    else
    {
        document.shop.chkSMethod[0].disabled = false;
        document.shop.chkSMethod[1].disabled = false;
        document.shop.chkSMethod[0].checked = true;
    }
}


function popSize(URL) 
{
    day = new Date();
    id = day.getTime();
    eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=800,height=480,left = 0,top = 0');");		
}

function SelectShp(mystr)
{
	//alert("Shipping: " + document.getElementById("shipprice"));
	document.getElementById("shipprice").innerHTML = mystr;
}

function SelectShp(mystr,rate,additional,items, total)
{
	//alert("called SelectShp with " + rate + " and " + additional);	
	var price = rate+(additional * items);
    document.getElementById('shipprice').innerHTML = "&nbsp;$" + parseFloat( price).toFixed(2);
    document.getElementById('totalprice').innerHTML = "&nbsp;$" + parseFloat(total + price).toFixed(2);
	document.shipping.txtShippingText.value = mystr;
	
}

function SelectShpCheckout(mystr,rate,additional,items, total)
{
	//alert("called SelectShp with " + rate + " and " + additional);	
	var price = rate+(additional * items);
    document.getElementById('shipprice').innerHTML = "&nbsp;$" + parseFloat( price).toFixed(2);
    document.getElementById('totalprice').innerHTML = "&nbsp;$" + parseFloat(total + price).toFixed(2);
	document.shop.txtShippingText.value = mystr;
	
}



/*************************************************************************\
	CREDIT CARD VALIDATION CODE
\*************************************************************************/
var Cards = new makeArray(5);
Cards[0] = new CardType("MasterCard", "51,52,53,54,55", "16");
var MasterCard = Cards[0];
Cards[1] = new CardType("Visa", "4", "13,16");
var Visa = Cards[1];
Cards[2] = new CardType("Amex", "34,37", "15");
var Amex = Cards[2];
Cards[3] = new CardType("Discover", "6011", "16");
var Discover = Cards[3];
var LuhnCheckSum = Cards[4] = new CardType();

/*************************************************************************\
CheckCardNumber(form)
function called when users click the "check" button.
\*************************************************************************/
function CheckCardNumber(form) 
{
	//alert("CheckCardNumber was called");
	var tmpyear;

	//alert(form.ExpYear.options[form.ExpYear.selectedIndex].value)
	if (form.ExpYear.options[form.ExpYear.selectedIndex].value > 2000)
	{
		tmpyear = form.ExpYear.options[form.ExpYear.selectedIndex].value;
	}
	else 
	{
		alert("The Expiration Year is not valid. TempYear: " + tmpyear);
		return true;
	}
	
	tmpmonth = form.ExpMon.options[form.ExpMon.selectedIndex].value;
	
	card = form.CardType.options[form.CardType.selectedIndex].value;
	
	//alert(form.bill_card_number.value + " : " + card + " = " + tmpmonth + "/" +  tmpyear)
	
	//alert(card + ".checkCardNumber(\"" + form.CardNumber.value + "\", " + tmpyear + ", " + tmpmonth + ");");
	var retval = eval(card + ".checkCardNumber(\"" + form.bill_card_number.value + "\", " + tmpyear + ", " + tmpmonth + ");");
	cardname = "";
	if (retval)
	{
		// comment this out if used on an order form
		//alert("This card number appears to be valid.");		
		return false;
	}
	else 
	{
		// The cardnumber has the valid luhn checksum, but we want to know which cardtype it belongs to.
		for (var n = 0; n < Cards.size; n++) 
		{
			if (Cards[n].checkCardNumber(form.bill_card_number.value, tmpyear, tmpmonth)) 
			{
				cardname = Cards[n].getCardType();
				break;
			}
		}
		if (cardname.length > 0) 
		{
			alert("This looks like a " + cardname + " number, not a " + card + " number.");			
			return true;
		}
		else 
		{
			alert("This card number is not valid.");
			return true;
		}
	}
}

/*************************************************************************\
Object CardType([String cardtype, String rules, String len, int year,int month])
cardtype    : type of card, eg: MasterCard, Visa, etc.
rules       : rules of the cardnumber, eg: "4", "6011", "34,37".
len         : valid length of cardnumber, eg: "16,19", "13,16".
year        : year of expiry date.
month       : month of expiry date.
eg:
var VisaCard = new CardType("Visa", "4", "16");
var AmExCard = new CardType("Amex", "34,37", "15");
\*************************************************************************/
function CardType() 
{
	var n;
	var argv = CardType.arguments;
	var argc = CardType.arguments.length;
	
	this.objname = "object CardType";
	
	var tmpcardtype = (argc > 0) ? argv[0] : "CardObject";
	var tmprules = (argc > 1) ? argv[1] : "0,1,2,3,4,5,6,7,8,9";
	var tmplen = (argc > 2) ? argv[2] : "13,14,15,16,19";
	
	this.setCardNumber = setCardNumber;  // set CardNumber method.
	this.setCardType = setCardType;  // setCardType method.
	this.setLen = setLen;  // setLen method.
	this.setRules = setRules;  // setRules method.
	this.setExpiryDate = setExpiryDate;  // setExpiryDate method.
	
	this.setCardType(tmpcardtype);
	this.setLen(tmplen);
	this.setRules(tmprules);
	
	if (argc > 4)
	{
		this.setExpiryDate(argv[3], argv[4]);
	}
	
	this.checkCardNumber = checkCardNumber;  // checkCardNumber method.
	this.getExpiryDate = getExpiryDate;  // getExpiryDate method.
	this.getCardType = getCardType;  // getCardType method.
	this.isCardNumber = isCardNumber;  // isCardNumber method.
	this.isExpiryDate = isExpiryDate;  // isExpiryDate method.
	this.luhnCheck = luhnCheck;// luhnCheck method.
	return this;
}

/*************************************************************************\
boolean checkCardNumber([String cardnumber, int year, int month])
return true if cardnumber pass the luhncheck and the expiry date is
valid, else return false.
\*************************************************************************/
function checkCardNumber() 
{
	var argv = checkCardNumber.arguments;
	var argc = checkCardNumber.arguments.length;
	var cardnumber = (argc > 0) ? argv[0] : this.cardnumber;
	var year = (argc > 1) ? argv[1] : this.year;
	var month = (argc > 2) ? argv[2] : this.month;
	
	//alert(cardnumber + ":" + month + ", " +  year)
	this.setCardNumber(cardnumber);	
	this.setExpiryDate(year, month);
	
	if (!this.isCardNumber())   	return false;
	if (!this.isExpiryDate())    	return false;
	
	return true;
}

/*************************************************************************\
String getCardType()
return the cardtype.
\*************************************************************************/
function getCardType() 
{
	return this.cardtype;
}

/*************************************************************************\
String getExpiryDate()
return the expiry date.
\*************************************************************************/
function getExpiryDate()
{
	return this.month + "/" + this.year;
}

/*************************************************************************\
boolean isCardNumber([String cardnumber])
return true if cardnumber pass the luhncheck and the rules, else return false.
\*************************************************************************/
function isCardNumber() 
{
	var argv = isCardNumber.arguments;
	var argc = isCardNumber.arguments.length;
	var cardnumber = (argc > 0) ? argv[0] : this.cardnumber;
	
	if (!this.luhnCheck())
	{
		return false;
	}
	
	for (var n = 0; n < this.len.size; n++)
	{
		if (cardnumber.toString().length == this.len[n]) 
		{
			for (var m = 0; m < this.rules.size; m++) 
			{
				var headdigit = cardnumber.substring(0, this.rules[m].toString().length);
				if (headdigit == this.rules[m])
				{			
					return true;
				}			
				return false;
			}
			return false;
		}
		
	}
}

/*************************************************************************\
boolean isExpiryDate([int year, int month])
return true if the date is a valid expiry date,else return false.
\*************************************************************************/
function isExpiryDate() 
{
	var argv = isExpiryDate.arguments;
	var argc = isExpiryDate.arguments.length;
	
	year = argc > 0 ? argv[0] : this.year;
	month = argc > 1 ? argv[1] : this.month;
	
	if (!isNum(year+"")) 	return false;
	if (!isNum(month+"")) 	return false;
	
	today = new Date();
	expiry = new Date(year, month);
	
	if (today.getTime() > expiry.getTime()) 	return false;
	else                                     	return true;
}

/*************************************************************************\
boolean isNum(String argvalue)
return true if argvalue contains only numeric characters, else return false.
\*************************************************************************/
function isNum(argvalue) 
{
	argvalue = argvalue.toString();
	
	if (argvalue.length == 0) 	return false;
	
	for (var n = 0; n < argvalue.length; n++)
	{
		if (argvalue.substring(n, n+1) < "0" || argvalue.substring(n, n+1) > "9")
		{
			return false;
		}	
		return true;
	}
}

/*************************************************************************\
boolean luhnCheck([String CardNumber])
return true if CardNumber pass the luhn check else return false.
Reference: http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl
\*************************************************************************/
function luhnCheck() 
{
	var argv = luhnCheck.arguments;
	var argc = luhnCheck.arguments.length;
	
	var CardNumber = argc > 0 ? argv[0] : this.cardnumber;
	
	if (! isNum(CardNumber)) 
	{
		return false;
	}
	
	var no_digit = CardNumber.length;
	var oddoeven = no_digit & 1;
	var sum = 0;
	
	for (var count = 0; count < no_digit; count++) 
	{
		var digit = parseInt(CardNumber.charAt(count));
		if (!((count & 1) ^ oddoeven)) 
		{
			digit *= 2;
		}
		if (digit > 9) 
		{
			digit -= 9;
		}
		sum += digit;
	}
	
	if (sum % 10 == 0) 	return true;
	else             	return false;
}

/*************************************************************************\
ArrayObject makeArray(int size)
return the array object in the size specified.
\*************************************************************************/
function makeArray(size) 
{
	this.size = size;
	return this;
}

/*************************************************************************\
CardType setCardNumber(cardnumber)
return the CardType object.
\*************************************************************************/
function setCardNumber(cardnumber) 
{
	this.cardnumber = cardnumber;
	return this;
}

/*************************************************************************\
CardType setCardType(cardtype)
return the CardType object.
\*************************************************************************/
function setCardType(cardtype) 
{
	this.cardtype = cardtype;
	return this;
}

/*************************************************************************\
CardType setExpiryDate(year, month)
return the CardType object.
\*************************************************************************/
function setExpiryDate(year, month) 
{
	this.year = year;
	this.month = month;
	return this;
}

/*************************************************************************\
CardType setLen(len)
return the CardType object.
\*************************************************************************/
function setLen(len) 
{
	// Create the len array.
	if (len.length == 0 || len == null) 	len = "13,14,15,16,19";
	
	var tmplen = len;
	n = 1;
	
	while (tmplen.indexOf(",") != -1) 
	{
		tmplen = tmplen.substring(tmplen.indexOf(",") + 1, tmplen.length);
		n++;
	}
	
	this.len = new makeArray(n);
	n = 0;
	
	while (len.indexOf(",") != -1) 
	{
		var tmpstr = len.substring(0, len.indexOf(","));
		this.len[n] = tmpstr;
		len = len.substring(len.indexOf(",") + 1, len.length);
		n++;
	}
	
	this.len[n] = len;
	return this;
}

/*************************************************************************\
CardType setRules()
return the CardType object.
\*************************************************************************/
function setRules(rules) 
{
	// Create the rules array.
	if (rules.length == 0 || rules == null) 	rules = "0,1,2,3,4,5,6,7,8,9";
	  
	var tmprules = rules;
	n = 1;
	
	while (tmprules.indexOf(",") != -1) 
	{
		tmprules = tmprules.substring(tmprules.indexOf(",") + 1, tmprules.length);
		n++;
	}
	
	this.rules = new makeArray(n);
	n = 0;
	
	while (rules.indexOf(",") != -1) 
	{
		var tmpstr = rules.substring(0, rules.indexOf(","));
		this.rules[n] = tmpstr;
		rules = rules.substring(rules.indexOf(",") + 1, rules.length);
		n++;
	}
	this.rules[n] = rules;
	return this;
}
//  End Credit Card Validation Code






