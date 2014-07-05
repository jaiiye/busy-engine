var mPrefix = "You did not enter a value into the "
var mSuffix = " field. This is a required field. Please enter it now."
var defaultEmptyOK = false;
var pEntryPrompt = "Please enter a "
var whitespace = " \t\n\r";
var iNumeric = " contains a numeric character."
var pEmail = "valid Email address (like foo@yahoo.com)."
var sEmail = "Email"
var iEmail = "This field is required field and also must be a valid Email address \n(like foo@bar.com). Please reenter it now."
var sUSLastname = "Last name"
var sUSFirstname = "First name"

var sUSInfo = "Requested Information"


function promptEntry (s)
{   
window.status = pEntryPrompt + s;
}

function checkString (theField, s, check, emptyOK)
{
    if (checkString.arguments.length == 2) 
	{ 
		emptyOK = defaultEmptyOK;
		check = 1;
	}
	
	if (checkString.arguments.length == 3) emptyOK = defaultEmptyOK;
    if ((emptyOK == true) && (isEmpty(theField.value))) return true;
    if (isWhitespace(theField.value))
	{ 
       return warnEmpty (theField, s);
	}
	else 
	{
		if (stripCharsInBag(theField.value, 6) == "-1") return warnInvalid(theField,s + iQuotes);
		if (stripCharsInBag(theField.value, check) == "-1")
		{	
			if (check == "5") return warnInvalid(theField,s + iNumeric);
			if (check == "1") return warnInvalid(theField,s + iAlphaNumeric);
			
			if (check == "6") return warnInvalid(theField,s + iQuotes);
			else 
			{ 
				if (check == "2") return warnInvalid(theField,s + iAlphabetic);
				else return warnInvalid(theField,s + iSpecial);
			}
		}
		else 
		{
			return true;
		}
	}
}

function isEmpty(s)
{   
	return ((s == null) || (s.length == 0));
}

function warnEmpty (theField, s)
{   
	theField.focus();
    alert(mPrefix + s + mSuffix);
    return false;
}

function warnInvalid (theField, s)
{   
	theField.focus();
    theField.select();
    alert(s);
    return false;
}
function isWhitespace (s)
{   
	var i;
    // Is s empty?
    if (isEmpty(s)) return true;
    // Search through string's characters one by one
    // until we find a non-whitespace character.
    // When we do, return false; if we don't, return true.
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);

        if (whitespace.indexOf(c) == -1) return false;
    }
    // All characters are whitespace.
    return true;
}


function stripCharsInBag (s, checkstring)
{   
	var i;
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
			{ 
				returnString = "-1";
			  	break;
			}
		}
		if (checkstring == "2") 
		{
			 if (!isLetter(c))
			{ 
				returnString = "-1";
				break;
		    }
		}
		if (checkstring == "3") 
		{
			 if (!isSpecial(c))
			{ 
				returnString = "-1";
				break;
		    }
		}
		if (checkstring == "4") 
		{
			if (!isSpecial(c) && (c != "-"))
			{ 
				returnString = "-1";
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


function checkEmail (theField, emptyOK)
{   if (checkEmail.arguments.length == 1) emptyOK = defaultEmptyOK;

    if ((emptyOK == true) && (isEmpty(theField.value))) 
		{  return true;  }
    else {
		if (!isEmail(theField.value, false)) 
			{  return warnInvalid (theField, iEmail);  }
		else {
			 return true;}
		 }
}


function isEmail (s)
{   if (isEmpty(s)) 
       if (isEmail.arguments.length == 1) return defaultEmptyOK;
       else return (isEmail.arguments[1] == true);
   
    // is s whitespace?
    if (isWhitespace(s)) return false;
    
    var i = 1;
    var sLength = s.length;

    // look for @
    while ((i < sLength) && (s.charAt(i) != "@"))
    { i++
    }

    if ((i >= sLength) || (s.charAt(i) != "@")) return false;
    else i += 2;

    // look for .
    while ((i < sLength) && (s.charAt(i) != "."))
    { i++
    }

    // there must be at least one character after the .
    if ((i >= sLength - 1) || (s.charAt(i) != ".")) return false;
    else return true;
}

function isNonnegativeInteger (s)
{   var secondArg = defaultEmptyOK;

    if (isNonnegativeInteger.arguments.length > 1)
        secondArg = isNonnegativeInteger.arguments[1];

    return (isSignedInteger(s, secondArg)
         && ( (isEmpty(s) && secondArg)  || (parseInt (s) >= 0) ) );
}

function isSignedInteger (s)

{   if (isEmpty(s)) 
       if (isSignedInteger.arguments.length == 1) return defaultEmptyOK;
       else return (isSignedInteger.arguments[1] == true);

    else {
        var startPos = 0;
        var secondArg = defaultEmptyOK;

        if (isSignedInteger.arguments.length > 1)
            secondArg = isSignedInteger.arguments[1];

        // skip leading + or -
        if ( (s.charAt(0) == "-") || (s.charAt(0) == "+") )
           startPos = 1;    
        return (isInteger(s.substring(startPos, s.length), secondArg));
    }
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

function isDigit (c)
{   return ((c >= "0") && (c <= "9"));
}
// Returns true if character c is a letter or digit.
function isLetterOrDigit (c)
{   return (isLetter(c) || isDigit(c));
}

function isSpecial (c)
{   return (isLetter(c) || isDigit(c) || (c == "_"));
}

function isLetter (c)
{   return ( ((c >= "a") && (c <= "z")) || ((c >= "A") && (c <= "Z")) || (c = " ") || (c = "'") );
}

function isMonth (s)
{    if (s == '0' || s == '')
      {
        alert(iEmptyDropPrefix + sMonth + iEmptyDropSuffix);
		return false;
      }
    return true;
}

function isIntegerInRange (s, a, b)
{   var num = s;
	if (isEmpty(s)) 
       if (isIntegerInRange.arguments.length == 1) return defaultEmptyOK;
       else return (isIntegerInRange.arguments[1] == true);
    if (!isInteger(s, false)) return false;
    if (num.indexOf("0") == 0) num = num.charAt(1);
	num = parseInt (num);
    return ((num >= a) && (num <= b));
}

function NotEmpty(f,s){
if ((f.value=="") || (f.length == 0)) {
return alert(mPrefix + s + mSuffix);
}
else{
return true; }
}


