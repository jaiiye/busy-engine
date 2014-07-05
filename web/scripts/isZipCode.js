// Begin client side helper functions
var sZIPCode = "ZIP Code"

var ZIPCodeDelimiters = "-";

var iZIPCode = "Zip Code is incorrect.\nUS Zipcodes are 5 or 9 digits.\nCanadian Postal Codes are 6 or 7 characters (A#A #A#)"
var iZIPCode2 = "This field must not contain special characters. Please reenter it now."

var iSpecial = " must contain a - z, A - Z, 0 - 9, or _."


var pZIPCode = "5 or 9 digit U.S. ZIP Code (like '30303') or a six digit Canadian ZIP Code with a single space (like 'K1A 0B1')."
var digitsInZIPCode1 = 10;
var sTwoDigit = "This number is incorrect.\nIt has to be from 1 to 99 with no decimal places"
var sNumber = "This field has to be numeric with no decimal places!"
var sOneDigit = "This number is incorrect.\nThis field is up to 2 digits long with one decimal point."
var sPers = "This number is incorrect.\nThis field has to be any numerical value from 1 to 100 %"
var sOneDigitAny = "This number is incorrect.\nThis field has to be any numerical value with one decimal point."
var sTwoNumberTwoDigit = "This number is incorrect.\nThis field is up to 2 digits long with up to two decimal points."
var sTwoDigtiAny = "This number is incorrect.\nThis field has to be any numerical value with two decimal points."
var sPrsTwoDigit = "This number is incorrect.\nThis field is numerical value with two decimal places from 0.00 to 100.00 % "
var sFlex = "This number is incorrect.\nThis field is numerical value and can not be more than  $5,000.00 "
var sMaxAge = "This number is incorrect.\nThis field is numerical value and can not be more than 255 "
var sMonth = "This number is incorrect.\nThis field is numerical month value and has to be between 0 to 31 with no decimal points. "
var sMaxValue = "This number is incorrect.\nThis field is numerical and cannot be > $10,500"


function isEmpty(s)
{   return ((s == null) || (s.length == 0));
}

function warnEmpty (theField, s)
{   theField.focus();
    alert(mPrefix + s + mSuffix);
    return false;
}

function warnInvalid (theField, s)
{   theField.focus();
    theField.select();
    alert(s);
    return false;
}


function checkZIPCode (theField)
{
	var normalizedZIP = stripCharsInBag(theField.value, 4);
	if ((theField.value.length == 7) || (theField.value.length == 6))
		{	
		theField.value = theField.value.toUpperCase();
		
		return theField.value; 
		}

 else if (theField.value.length == 0) return true;


	else if (isInteger(theField.value, 1))
	  { 
	  if (theField.value.length == 9) // nine digit american zip codes and add the dash
	  	{
	  	theField.value = reformatZIPCode(theField.value)
	  	return theField.value;
	  	}
	  if ((theField.value.length == 5) || (theField.value.length == 0))  return true;
	  else return warnInvalid (theField, iZIPCode);
	  }
	else if (theField.value.length == 10) // nine digit american zip codes
	  {
	  if (!isDigit(theField.value.charAt(0))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(1))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(2))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(3))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(4))) return warnInvalid (theField, iZIPCode);
	  if (theField.value.charAt(5) != "-") return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(6))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(7))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(8))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(9))) return warnInvalid (theField, iZIPCode);
	  return true;
	  }
	else return warnInvalid (theField, iZIPCode);
}


function isCanadian (ZIPCode)
{	// var CanadianZIPs = new Array(A,B,C,E,G,H,K,L,M,N,P,R,S,T,V,X,Y);
	var CanadianZIPs = makeArray(17);
		CanadianZIPs[1] = "A";
		CanadianZIPs[2] = "B";
		CanadianZIPs[3] = "C";
		CanadianZIPs[4] = "E";
		CanadianZIPs[5] = "G";
		CanadianZIPs[6] = "H";
		CanadianZIPs[7] = "K";
		CanadianZIPs[8] = "L";
		CanadianZIPs[9] = "M";
		CanadianZIPs[10] = "N";
		CanadianZIPs[11] = "P";
		CanadianZIPs[12] = "R";
		CanadianZIPs[13] = "S";
		CanadianZIPs[14] = "T";
		CanadianZIPs[15] = "V";
		CanadianZIPs[16] = "X";
		CanadianZIPs[17] = "Y";
	var firstletter = ZIPCode.charAt(0);
	var i;
	for (i = 1 ; i <= 17 ; i++) {
		if (firstletter == CanadianZIPs[i]) return true; 
		}
	return false	
}

function isZIPCode (s)
{  if (isEmpty(s)) 
       if (isZIPCode.arguments.length == 1) return defaultEmptyOK;
       else return (isZIPCode.arguments[1] == true);
   return (s.length <= digitsInZIPCode1);
}

function reformatZIPCode (ZIPString)
{   if (ZIPString.length == 5) return ZIPString;
    else {
		if (ZIPString.length == 9) return (reformat (ZIPString, "", 5, "-", 4));
		else return ZIPString;
	}
}




function stripCharsInBag (s, checkstring)

{   var i;
    var returnString = "";

    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.

    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
		if (checkstring == "1")
			{
			 if (!isLetterOrDigit(c))
				{ returnString = "-1";
				  break;
				  }
			 }
		if (checkstring == "2") 
			{
			 if (!isLetter(c))
				{ returnString = "-1";
				  break;
				  }
			 }
		if (checkstring == "3") 
			{
			 if (!isSpecial(c))
				{ returnString = "-1";
				  break;
				  }
			 }
		if (checkstring == "4") 
			{
			 if (!isSpecial(c) && (c != "-"))
				{ returnString = "-1";
				  break;
				  }
			 else if (c != "-") returnString += c;
			 }
		if (checkstring == "5") 
			{
			 if (isDigit(c) || c == '"')
				{ returnString = "-1";
				  break;
				  }
			}
		if (checkstring == "6") 
			{ // just no quotes
			 if (c == '"')
				{ returnString = "-1";
				  break;
				  }
			 }
	    }

    return returnString;
}

function isInteger (s)

{   var i;

    if (isEmpty(s)) 
       if (isInteger.arguments.length == 1) return defaultEmptyOK;
       else return (isInteger.arguments[1] == true);

    // Search through string's characters one by one
    // until we find a non-numeric character.
    // When we do, return false; if we don't, return true.

    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);

        if (!isDigit(c)) return false;
    }

    // All characters are numbers.
    return true;
}



function isSpecial (c)
{   return (isLetter(c) || isDigit(c) || (c == "_"));
}

function isLetter (c)
{   return ( ((c >= "a") && (c <= "z")) || ((c >= "A") && (c <= "Z")) || (c = " ") || (c = "'") );
}



function reformat (s)
{   var arg;
    var sPos = 0;
    var resultString = "";

    for (var i = 1; i < reformat.arguments.length; i++) {
       arg = reformat.arguments[i];
       if (i % 2 == 1) resultString += arg;
       else {
           resultString += s.substring(sPos, sPos + arg);
           sPos += arg;
       }
    }
    return resultString;
}

function isDigitNumber (c)
{   return ((c >= "0") && (c <= "9"));
}


function isDigit (c)
{   return ((c >= "0") && (c <= "9"));
}


function checkTwoDigitValue (theField)
{
	
if (!isNotZero(theField.value.charAt(0)) && (theField.value.length >= 1) ) return warnInvalid (theField, sTwoDigit);
	else if (isInteger(theField.value, 1)) 
	  { 
	  if ((theField.value.length == 2) || (theField.value.length == 1)) // two digit from 1 to 99
	  	{
	  	return theField.value;
	  	}


	  if ((theField.value.length == 2) || (theField.value.length == 0) || (theField.value.length == 1))  return true;
              
           
	  else return warnInvalid (theField, sTwoDigit);
	  }
	else return warnInvalid (theField, sTwoDigit);
}





function checkNumber (theField)

{
if (!isNotZero(theField.value.charAt(0)) && (theField.value.length >= 2) ) return warnInvalid (theField, sNumber);
	 else if (theField.value.length == 0) return true;


if (isInteger(theField.value, 1)) 
	  	{
	  	return theField.value;
	  	}
else return warnInvalid (theField, sNumber);


}




function reformatOneDigit (S)
{ 
  
if (S.length == 1) return (reformat (S, "", 1, ".0"));
   if (S.length == 2) return (reformat (S, "", 2, ".0")); 

//else if (S.length == 0) return reformat (S);
	

else return (reformat (S, "", 2, ".", 1));
	}



   

function reformatOneDigitZero (S)
{   
if (S.length == 1) return S.value=0;
else return (reformat (S, "", 1, ".", 1));
  	}





function checkNumberDigit (theField)
{
	var nDesPlase = theField.value.lastIndexOf(".")


 if (isInteger(theField.value, 1) && (!isZero(theField.value.charAt(0))) && (theField.value.length >= 1) ) 
	  { 
	  
theField.value = reformatOneDigit(theField.value)
	  	return theField.value;	 
	  }





else if ((theField.value.length == 3)&& (!isZero(theField.value.charAt(0)))) // one number dot decimal
	  {
	  if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sOneDigit);
	   if (theField.value.charAt(1) != ".") return warnInvalid (theField, sOneDigit);
	  if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sOneDigit);
	  return true;
	  }


else if ((theField.value.length == 4)&& (!isZero(theField.value.charAt(0)))) // two number dot decimal
	  {
	  if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sOneDigit);
	  if (!isDigitNumber(theField.value.charAt(1))) return warnInvalid (theField, sOneDigit);
          if (theField.value.charAt(2) != ".") return warnInvalid (theField, sOneDigit);
          if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sOneDigit);
	  return true;
	  }

else if (!isNotZero(theField.value.charAt(0)) && (nDesPlase <=0) && (isInteger(theField.value, 1)) && (theField.value.length >= 1)) // two number dot decimal
	  {
	theField.value = reformatOneDigitZero(theField.value)
	  	return theField.value;	 
	  }

else if (!isNotZero(theField.value.charAt(0)) && (nDesPlase >=1) && (theField.value.length == 3) ) // two number dot decimal
	  {
	if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sOneDigit);
	   if (theField.value.charAt(1) != ".") return warnInvalid (theField, sOneDigit);
	  if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sOneDigit);
	  return true;
	  }


else if (isInteger(theField.value, 1) && (theField.value.length == 0))
	  { 
	  return true;
	  	}

else if (theField.value.length == 0)
	  { 
	 return true;
	  	}

	else return warnInvalid (theField, sOneDigit);
}



function isNotZero (c)
{   return ((c >= "1") && (c <= "9"));
}

function checkPrs (theField){
 if ( (theField.value.length >= 1) ) return warnInvalid (theField, sPers);
         else if (isInteger(theField.value, 1))
	  { 
	  
	  if ((theField.value.length == 1) || (theField.value.length == 2)|| (theField.value.length == 0))  return true;
	 
	 if (theField.value.length == 3) // 100% max
	  {
	  if (!isOne(theField.value.charAt(0))) return warnInvalid (theField, sPers);
	  //if (!isZero(theField.value.charAt(1))) return warnInvalid (theField, sPers);
	  //if (!isZero(theField.value.charAt(2))) return warnInvalid (theField, sPers);
	  return true;
	  }

else if (theField.value.length == 2) // anything that less than 100% and does not start from 0
	  {
	  if (!isDigit(theField.value.charAt(0))) return warnInvalid (theField, sPers);
	  if (!isDigit(theField.value.charAt(1))) return warnInvalid (theField, sPers);
	  return true;
	  }
}
	else return warnInvalid (theField, sPers);
}

function isOne (c)
{   return ((c == "1"));
}

function isZero (c)
{   return ((c == "0"));
}


function reformatOneDigitAnyNumber (S, n)
{   
if (S.length <= 5) return (reformat (S, "", n, ".0"));

 else return (reformat (S, "", 5, ".", 1));
	}














function AnyNumericOneDigit (theField)
{
	var nDesPlase = theField.value.lastIndexOf(".")
        var nLength = theField.value.length
                 




 if (isInteger(theField.value, 1) && (theField.value.length >=1) && !isZero(theField.value.charAt(0)) ) 
	  { 
	  
theField.value = reformatOneDigitAnyNumber(theField.value, nLength)
	  	return theField.value;	 
	  }



else if (!isNotZero(theField.value.charAt(0))  && (isInteger(theField.value, 1)) && (theField.value.length >= 2) ) // First Zero dot decimal
	  {
	theField.value = reformat (theField.value, "", 1, ".", 1)
	  	return theField.value;	 
	  }

else if (!isNotZero(theField.value.charAt(0))  && (isInteger(theField.value, 1)) && (theField.value.length == 1) ) // Just 0
	  {
	//theField.value = reformat (theField.value, "", 1, ".", 1)
	  	return theField.value;	 
	  }



else if ((theField.value.length == 3))
	  {
	  if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sOneDigitAny);
	  if (theField.value.charAt(1) != ".") return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sOneDigitAny);
	  return true;
	  }

else if ((theField.value.length == 4))
	  {
	  if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(1))) return warnInvalid (theField, sOneDigitAny);
	  if (theField.value.charAt(2) != ".") return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sOneDigitAny);
	  return true;
	  }

else if ((theField.value.length == 5))
	  {
	  if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(1))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sOneDigitAny);
	  if (theField.value.charAt(3) != ".") return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(4))) return warnInvalid (theField, sOneDigitAny);
	  return true;
	  }

else if ((theField.value.length == 6))
	  {
	  if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(1))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sOneDigitAny);
	  if (theField.value.charAt(4) != ".") return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(5))) return warnInvalid (theField, sOneDigitAny);
	  return true;
	  }

else if ((theField.value.length == 7) )
	  {
	  if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(1))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(4))) return warnInvalid (theField, sOneDigitAny);
	  if (theField.value.charAt(5) != ".") return warnInvalid (theField, sOneDigitAny);
          if (!isDigitNumber(theField.value.charAt(6))) return warnInvalid (theField, sOneDigitAny);
	  return true;
	  }

else if (theField.value.length == 0)  return true;
	 

	else return warnInvalid (theField, sOneDigitAny);
}


function reformatTwoDigit (S)
{ 
  
if (S.length == 1) return (reformat (S, "", 1, ".00"));
   if (S.length == 2) return (reformat (S, "", 2, ".00")); 
   if (S.length == 3) return (reformat (S, "", 2, ".", 1, "0"))

//else if (S.length == 0) return "";
	

else return (reformat (S, "", 2, ".", 2));
	}




function checkNumberDigitTwo (theField)
{
	var nDesPlase = theField.value.lastIndexOf(".")

 if (isInteger(theField.value, 1) && (!isZero(theField.value.charAt(0))) && (theField.value.length >= 1) ) 
	  { 
	  
theField.value = reformatTwoDigit(theField.value)
	  	return theField.value;	 
	  }

else if (!isNotZero(theField.value.charAt(0)) && (nDesPlase <=0) && (isInteger(theField.value, 1)) && (theField.value.length >= 1)) // two number dot decimal
	  {
	theField.value = reformatTwoDigitZero(theField.value)
	  	return theField.value;	 
	  }

else if (!isNotZero(theField.value.charAt(0)) && (nDesPlase >=1) && (theField.value.length == 3) ) // two number dot decimal
	  {
	if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sTwoNumberTwoDigit);
	   if (theField.value.charAt(1) != ".") return warnInvalid (theField, sTwoNumberTwoDigit);
	  if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sTwoNumberTwoDigit);
	  return true;
	  }

else if ((theField.value.length == 4) && (nDesPlase == 1)) // two number dot 2 decimals
	  {
	if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sTwoNumberTwoDigit);
       	   if (theField.value.charAt(1) != ".") return warnInvalid (theField, sTwoNumberTwoDigit);
	  if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sTwoNumberTwoDigit);
          if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sTwoNumberTwoDigit);
	  return true;
	  }
	
else if ( (theField.value.length == 5) ) // two number dot 2 decimals
	  {
	if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sTwoNumberTwoDigit);
        if (!isDigitNumber(theField.value.charAt(1))) return warnInvalid (theField, sTwoNumberTwoDigit); 
	   if (theField.value.charAt(2) != ".") return warnInvalid (theField, sTwoNumberTwoDigit);
	  if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sTwoNumberTwoDigit);
          if (!isDigitNumber(theField.value.charAt(4))) return warnInvalid (theField, sTwoNumberTwoDigit);
	  return true;
	  }

else if ((theField.value.length == 4) && (nDesPlase == 2) ) // two number dot decimal
	  {
if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sTwoNumberTwoDigit);
if (!isDigitNumber(theField.value.charAt(1))) return warnInvalid (theField, sTwoNumberTwoDigit);
if (theField.value.charAt(2) != ".") return warnInvalid (theField, sTwoNumberTwoDigit);
if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sTwoNumberTwoDigit);
	theField.value = (reformat (theField.value, "", 4, "0"))
	  	return theField.value;	 
	  }

else if ((theField.value.length == 3) && (!isZero(theField.value.charAt(0))) ) // two number dot decimal
	  {
if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sTwoNumberTwoDigit);
	   if (theField.value.charAt(1) != ".") return warnInvalid (theField, sTwoNumberTwoDigit);
	  if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sTwoNumberTwoDigit);
	theField.value = reformat (theField.value, "", 3, "0")
	  	return theField.value;	 
	  }

else if (isInteger(theField.value, 1) && (theField.value.length == 0))
	  { 
	  return true;
	  	}

	else return warnInvalid (theField, sTwoNumberTwoDigit);
}




function reformatTwoDigitZero (S)
{ 
  
if (S.length == 1) return S.value=0;
   if (S.length == 2) return (reformat (S, "", 1, ".", 1, "0")); 
   if (S.length == 3) return (reformat (S, "", 1, ".", 2))

//else if (S.length == 0) return "";
	

else return (reformat (S, "", 1, ".", 2));
	}


   


function reformatTwoDigitAnyNumber (S, n)
{ 
  
if (S.length <= 5) return (reformat (S, "", n, ".00"));
  if (S.length == 6) return (reformat (S, "", 5, ".",1, "0" ));
   

//else if (S.length == 0) return "";
	

else return (reformat (S, "", 5, ".", 2));
	}



function reformatTwoDigitPrs (S, n)
{ 
  
if (S.length == 3) return (reformat (S, "", 2, ".", 1, "0"));
  

//else if (S.length == 0) return "";
	

else return (reformat (S, "", 2, ".", 2));
	}




function isNotOne (c)
{   return ((c >= "2") && (c <= "9"));
}










function checkPrsTwoDigit (theField)
{
	var nDesPlase = theField.value.lastIndexOf(".")
        var nLenth = theField.value.length
        var sValue = theField.value
            
       






if (isInteger(theField.value, 1) && (!isZero(theField.value.charAt(0))) && ((theField.value.length == 1) || (theField.value.length == 2)) ) 
	  { 
	  
theField.value =  reformat (theField.value, "", nLenth, ".00")
	  	return theField.value;	 
	  }



else if (isInteger(theField.value, 1) && (!isZero(theField.value.charAt(0))) && (theField.value.length >= 4)) 
	  { 
	  
theField.value =  reformat (theField.value, "", 2, ".", 2)
	  	return theField.value;	 
	  }






else if (!isNotZero(theField.value.charAt(0)) && (nDesPlase <=0) && (isInteger(theField.value, 1)) && (theField.value.length >= 1) ) // two number dot decimal
	  {
	theField.value = reformatTwoDigitZero(theField.value)
	  	return theField.value;	 
	  }

else if ((theField.value.length == 3) && ((nDesPlase == 1))) // two number dot 2 decimals
	  {
	theField.value = reformat (theField.value, "", 3, "0")
	  	return theField.value;	
	  }

else if ((theField.value.length == 4) && ((nDesPlase == 2))) // two number dot 2 decimals
	  {
	theField.value = reformat (theField.value, "", 4, "0")
	  	return theField.value;	
	  }


else if ((theField.value.length == 4) && (nDesPlase == 1)) // two number dot 2 decimals
	  {
	if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sPrsTwoDigit);
       	   if (theField.value.charAt(1) != ".") return warnInvalid (theField, sPrsTwoDigit);
	  if (!isDigitNumber(theField.value.charAt(2))) return warnInvalid (theField, sPrsTwoDigit);
          if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sPrsTwoDigit);
	  return true;
	  }
	
else if ( (theField.value.length == 5) ) // two number dot 2 decimals
	  {
	if (!isDigitNumber(theField.value.charAt(0))) return warnInvalid (theField, sPrsTwoDigit);
        if (!isDigitNumber(theField.value.charAt(1))) return warnInvalid (theField, sPrsTwoDigit); 
	   if (theField.value.charAt(2) != ".") return warnInvalid (theField, sPrsTwoDigit);
	  if (!isDigitNumber(theField.value.charAt(3))) return warnInvalid (theField, sPrsTwoDigit);
          if (!isDigitNumber(theField.value.charAt(4))) return warnInvalid (theField, sPrsTwoDigit);
	  return true;
	  }

else if ((theField.value.length == 3) && (!isOneHundred(theField.value))  )  // two number dot decimal
	  {
if (!isOne(theField.value.charAt(0))) return warnInvalid (theField, sPrsTwoDigit);
if (!isZero(theField.value.charAt(1))) return warnInvalid (theField, sPrsTwoDigit);
if (!isZero(theField.value.charAt(2))) return warnInvalid (theField, sPrsTwoDigit);
	 theField.value = reformat (theField.value, "", 3, ".00")
	  	return theField.value;	
 
	  }

else if ((theField.value.length == 3) && (isInteger(theField.value, 1))  )  // two number dot decimal
	  {

	 theField.value = reformat (theField.value, "", 2, ".", 1, "0")
	  	return theField.value;	
 
	  }


else if ((theField.value.length == 6))  // two number dot decimal
	  {
if (!isOne(theField.value.charAt(0))) return warnInvalid (theField, sPrsTwoDigit);
if (!isZero(theField.value.charAt(1))) return warnInvalid (theField, sPrsTwoDigit);
if (!isZero(theField.value.charAt(2))) return warnInvalid (theField, sPrsTwoDigit);
if (theField.value.charAt(3) != ".") return warnInvalid (theField, sPrsTwoDigit);
if (!isZero(theField.value.charAt(4))) return warnInvalid (theField, sPrsTwoDigit);
if (!isZero(theField.value.charAt(5))) return warnInvalid (theField, sPrsTwoDigit);
	  	 return true;
	  }


else if (theField.value.length == 0){
 return true;
	  }

	else return warnInvalid (theField, sPrsTwoDigit);
}



function isOneHundred (c)
{   return ((c > "100"));
}

function isNotOneHundred (c)
{   return ((c <= "100"));
}











 

function AnyNumericTwoDigit (theField)
{
	var nDesPlase = theField.value.lastIndexOf(".")
        var nLenth = theField.value.length
        var sValue = theField.value
        var nNum = 0 
var hValue = reformat (theField.value, "", nDesPlase)   
 nNum = nLenth - (nDesPlase + 1)
var nEndValue = theField.value.charAt(nLenth)
var nEndSecond = theField.value.charAt(theField.value.length - 1)
var hDigit = (nLenth - nNum + 2)

//var sTwoDigtiAny = (theField.value.charAt(nLenth)) 



if (isInteger(theField.value, 1) && ((theField.value.length <= 5) && (theField.value.length >= 1)) && (!isZero(theField.value.charAt(0)))  ) 
	  { 
	  
theField.value =  reformat (theField.value, "", nLenth, ".00")
	  	return theField.value;	 
	  }



else if (isInteger(theField.value, 1) && (!isZero(theField.value.charAt(0))) && (theField.value.length >= 6)) 
	  { 
	  
theField.value =  reformat (theField.value, "", 5, ".", 2)
	  	return theField.value;	 
	  }



else if (!isNotZero(theField.value.charAt(0)) && (nDesPlase <=0) && (isInteger(theField.value, 1)) && (theField.value.length >= 1) ) // two number dot decimal
	  {
	theField.value = reformatTwoDigitZero(theField.value)
	  	return theField.value;	 
	  }



else if ((nNum == 1) && (nDesPlase == 1) && (isInteger(hValue, 1)) && (isInteger(nEndSecond)) )  // two number dot decimal
	  {

	 	  	 theField.value = reformat (theField.value, "", nLenth, "0")
	  	return theField.value;	
 }


else if ((isInteger(hValue, 1)) && (nDesPlase >=1) && (nNum >= 3))
	  {
	  theField.value = reformat (theField.value, "", hDigit)
	  	return theField.value;	
	  }



else if ((isInteger(hValue, 1)) && (nDesPlase >= 1))
	  {
	  if (!isDigitNumber(theField.value.charAt(nLenth))) return warnInvalid (theField, sTwoDigitAny);
          if (!isDigitNumber(theField.value.charAt(nLenth - 1))) return warnInvalid (theField, sTwoDigitAny);
          
	  return true;
	  }

else if (theField.value.length == 0){
return true;
	  }

	else return warnInvalid (theField, sTwoDigtiAny);
}



function isNotFiveTh (c)
{   return ((c <= "5000"));
}







function checkNumberFlex (theField)

{
if (!isNotFiveTh(theField.value) && (theField.value.length >= 4) ) return warnInvalid (theField, sFlex);
	 else if (theField.value.length == 0) return true;
else if (!isNotZero(theField.value.charAt(0)) && (theField.value.length >= 2)) return warnInvalid (theField, sFlex);

else if (theField.value.length >= 5) return warnInvalid (theField, sFlex);

if (isInteger(theField.value, 1) && (theField.value.length >= 1) ) 
	  	{
	  	return theField.value;
	  	}
else return warnInvalid (theField, sFlex);


}


function isNotMaxAge (c)
{   return ((c <= "255"));
}

function checkNumberMaxAge (theField)

{
if (!isNotMaxAge(theField.value) && (theField.value.length >= 3) ) return warnInvalid (theField, sMaxAge);
	 else if (theField.value.length == 0) return true;
else if (!isNotZero(theField.value.charAt(0)) && (theField.value.length >= 2)) return warnInvalid (theField, sMaxAge);

if (isInteger(theField.value, 1) && (theField.value.length >= 1) ) 
	  	{
	  	return theField.value;
	  	}
else return warnInvalid (theField, sMaxAge);


}















function FiveNumerickTwodigit (theField)
{
	var nDesPlase = theField.value.lastIndexOf(".")
        var nLenth = theField.value.length
        var sValue = theField.value
        var nNum = 0 
var hValue = reformat (theField.value, "", nDesPlase)   
 nNum = nLenth - (nDesPlase + 1)
var nEndValue = theField.value.charAt(nLenth)
var nEndSecond = theField.value.charAt(theField.value.length - 1)
var hDigit = (nLenth - nNum + 2)





if (isInteger(theField.value, 1) && (theField.value.length <= 5) )return true;


else if (isInteger(theField.value, 1) && (theField.value.length > 5) )  // more than five numbers
	  {

	 	  	 theField.value = reformat (theField.value, "", 5, ".", 2)
	  	return theField.value;	
 }



else if (theField.value.length == 7) // 5 numbers one digit
	  {
	  if (!isDigit(theField.value.charAt(0))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(1))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(2))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(3))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(4))) return warnInvalid (theField, iZIPCode);
	  if (theField.value.charAt(5) != ".") return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(6))) return warnInvalid (theField, iZIPCode);
	  
	  return true;
	  }

else if (theField.value.length == 8) // 5 numbers two digit
	  {
	  if (!isDigit(theField.value.charAt(0))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(1))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(2))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(3))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(4))) return warnInvalid (theField, iZIPCode);
	  if (theField.value.charAt(5) != ".") return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(6))) return warnInvalid (theField, iZIPCode);
	  if (!isDigit(theField.value.charAt(7))) return warnInvalid (theField, iZIPCode);
	  
	  return true;
	  }

else return warnInvalid (theField, sMaxAge);	
 
}



function isNotMonth (c)
{   return ((c <= "31"));
}






function checkMonthValue (theField)

{
if (!isNotMonth(theField.value) && (theField.value.length >= 2) ) return warnInvalid (theField, sMonth);
	 else if (theField.value.length == 0) return true;
if (isInteger(theField.value, 1) && (theField.value.length <= 2) ) 
	  	{
	  	return theField.value;
	  	}
else return warnInvalid (theField, sMonth);


}

function checkMaxBenAmt(theField)
{
	var num = parseFloat(theField.value);
	if (num > 10500)
	    {
		return warnInvalid (theField, sMaxValue);
	    }
	else
		return true;

}


















// -->	