// -- Includes the following functions
//
// -- btnZip_onClick
// -- Capitalize
// -- getSelected
// -- CheckEmailAddress
// -- checkPerCent --restricts max of ctrl.value to 100
// -- CheckRoutingNo(ctrl) --returns whether or not ctrl.value is a valid transit routing#
// -- CheckZipOnAddress 
// -- Date.toString() -- in mm/dd/yyyy format
// -- Date in to mm/yyyy format
// -- dateFormatCheck
// -- ddiff -- date difference
// -- errHandler -- generic client side err handler
// -- fillCalendar
// -- focusEmpty 
// -- focusTooShort
// -- GenericValidDate(ctl) -- generic date control validator if don't want to implement it each time
// -- maxLength
// -- setFocus(ctrl) -- sets focus on ctrl  
// -- stod -- Strng to Date  
// -- stripNonNumeric
// -- stripNonNumeric_
// -- stripNonNumeric-

function getSelected(sName,nIndex)
{
	eval('document.forms[0].'+sName+'['+nIndex+'].checked = true');
}

function errHandler(msg,url,line)
{
  alert("The following error has occured on line("+line+") of \""+url+"\".\n\""+msg+"\"");
  return true;
}
//window.onerror = errHandler;

var impl_msg=false;

function DisplayMsg(type, msgto) {
oldDisplayMsg(type, msgto);

}

function oldDisplayMsg(type, msgto) {
	var msg;
	frm = document.forms[0];
	if (!impl_msg) {
		impl_msg = true;
		msg = "The activation of Simpata's " + type + " feature set is complete and approved. ";
		msg = msg + "In this state, you can only change the default group and display order of a " + msgto + ". ";
		msg = msg + "If you have additional changes to make, please contact Simpata at 800.476.0218."
		alert(msg);
		setTimeout('impl_msg=false', 2000);
        }
	setFocus(frm.txtDisplayOrder);
	return;
}
//Date in to mm/yyyy format
function dateFormatmmyyyy(ctrl){
	var len,aYear,strLen;
        form = document.forms[0]; 
	mYear = ctrl.value;
	aYear = mYear;
	len = aYear.length;
	if(((len==6) && (stripNonNumeric(mYear).length==4)) || ((len==7) && (stripNonNumeric(mYear).length==5))) {
		aYear = aYear.split("/");
		mYear = aYear[0]+aYear[2];
	}else
	        mYear = stripNonNumeric(mYear);
		strLen = mYear.toString().length
   	switch (strLen){
		case 3 :
			mYear= mYear.toString().substr(0,1) +"01"+ mYear.toString().substr(1,2)
			break;
		case 4 :
			mYear= mYear.toString().substr(0,2) +"01"+ mYear.toString().substr(2,4)
			break;
		case 5 :
			mYear = mYear.toString().substr(0,2) +"01"+ mYear.toString().substr(2,5)
			break;
		case 6 :
			mYear = mYear.toString().substr(0,2) +"01"+ mYear.toString().substr(2,6)
			break;
		default :
			alert("Please enter a valid date in mm/yyyy format.");
			setFocus(ctrl);
			return "";
		}		
	
	     return mYear;
}
function fillCalendar(frm,name){
	var i,j,bt;
	var before=stod(frm[name+"Before"].value);
	var after=stod(frm[name+"After"].value);
	var mon=frm[name+"Month"].selectedIndex;
	var dt= new Date(frm[name+"Year"].options[frm[name+"Year"].selectedIndex].value,mon,1);
	var dt1=dt;
	dt-=dt.getDay()*daylength;
	dt=new Date(dt);
	for (i=0;i<6;i++) {
		for (j=0;j<7;j++){
			bt=frm[name+"Day"+(i+1)+(j+1)];
			if ((dt.getMonth()==mon)&&(dt>=after)&&(dt<=before)){
				bt.value=dt.getDate();
				}else{
				bt.value="";
				}
			dt=new Date(dt.getTime()+daylength+hourlength);
			dt=new Date(dt.getTime()-hourlength*dt.getHours());
			}
		}
	}

var daylength=1000*60*60*24;
var hourlength=1000*60*60;

function findForm(frm){
	var i;
	for (i=0; i<document.forms.length;i++) if (document.forms[i]==frm) break;
	return (i);
	}

function findElement(frm,name){
	var i;
	for (i=0;(( i<frm.length)&&(frm.elements[i].name!=name));i++);
	if (i<frm.length) return (frm.elements[i]);
		else alert('Element not found.');
	}

function setDay(ctl,day,name,frm){
	var yr=frm[name+"Year"];
	ctl.value=''+(frm[name+"Month"].selectedIndex+1)+'/'+day+'/'+yr.options[yr.selectedIndex].value;
	}

function maxLength(sIn,iLen){
	if (iLen <=0) iLen=1;
	if (iLen >=4096) iLen = 4096;
	if (sIn.value.length>iLen){
		sIn.value=sIn.value.slice(0,iLen);
		alert('Your entry has been limited to '+iLen+' characters!');
	}
}

 function Capitalize (InString)  {
	var i;
	var OutString=InString
	while ((i=OutString.search(/\b([a-z])/))>=0)
		OutString=OutString.substring(0,i) + OutString.substr(i,1).toUpperCase() + OutString.substr(i+1);
	return(OutString);
}

function PerformCapitalize (InString, Sep)  {
	QualifyArray=new Array();
	
	QualifyArray[1]="a";
	QualifyArray[2]="an";
	QualifyArray[3]="the";
	QualifyArray[4]="of";
	QualifyArray[5]="at";
	QualifyArray[6]="for";
	QualifyArray[7]="by";

	ParsedStringArray=parser(InString, Sep);
	OutString=""
	for(Count=1; Count<=ParsedStringArray[0]; Count++)  {

		Match=false;
		for(CountArray=1; CountArray<QualifyArray.length; CountArray++)  {
			if(ParsedStringArray[Count]==QualifyArray[CountArray]) {
				Match=true;
				break;
			}
		}
		if ((Match==false) || (Count==1)) {
			OutString+=ParsedStringArray[Count].toUpperCase().substring (0, 1)
			OutString+=ParsedStringArray[Count].substring(1, ParsedStringArray[Count].length)
		}	
		else  {
			OutString+=ParsedStringArray[Count]
		}
		if (Count<ParsedStringArray[0])
			OutString+=Sep
	}
	return(OutString);
}

function parser (InString, Sep)  {
	NumSeps=1;
	for (Count=1; Count < InString.length; Count++)  {
		if (InString.charAt(Count)==Sep)
			NumSeps++;
	}
	parse = new Array (NumSeps);
	Start=0; Count=1; ParseMark=0;
	LoopCtrl=1;
	while (LoopCtrl==1)  {
		ParseMark = InString.indexOf(Sep, ParseMark);
		TestMark=ParseMark+0;
		if ((TestMark==0) || (TestMark==-1)){
			parse[Count]= InString.substring (Start, InString.length);
			LoopCtrl=0;
			break;
		}
		parse[Count] = InString.substring (Start, ParseMark);
		Start=ParseMark+Sep.length;
		ParseMark=Start;
		Count++;
	}
	parse[0]=Count;
	return (parse);
}
 
function spaceTrim(InString) {
	return (InString.replace(/^\s+/,'').replace(/\s+$/,''));
	}

function stripSpaces(InString) {
	return (InString.replace(/\s/g,''));
	}

function stripNonNumeric (InString)  {
	return (InString.replace(/\D/g,''));
	}

function stripNonNumeric_ (InString)  {
	return (InString.replace(/[^\d\.]/g,'').replace(/\./,"period").replace(/\./g,'').replace(/period/,'.'));
	}

// Strips any additional number after 1 digit after decimal point.
// Adds '.0' for non-decimal input values.
function stripNonNumeric_1 (InString)  {
	return (stripNonNumeric_(InString+'.0').replace(/(\d*\.\d).*/,"$1"));
	}

function stripNonNumeric_dash (InString)  {
	return (InString.replace(/[^\d\-]/g,''));
	}

function stripNonNumeric_dash_ (InString)  {
	return InString.replace(/[^\d\.\-]/g,'');
}

function checkPerCent(ctrl){
	val=stripNonNumeric_(ctrl.value);
	if(val>100){
	  alert("Invalid Percent value");
	  //ctrl.focus();
	  setFocus(ctrl);
	  return false;
	}
	ctrl.value=val;
	return true;
}

function stripNonPhone (InString)  {
	return InString.replace(/[^a-zA-z\d\*#]/g,'');
	}

function Mask(sText, sMask) {
	sOutput = "";
	iMaskPos = 0;
	iTextPos = 0;
	for (i=1; i<32; i++) {
		if (iMaskPos >= sMask.length){
			sMaskChar = "#";
		}
		else
			sMaskChar = sMask.charAt(iMaskPos);

		sTextChar = sText.charAt(iTextPos);
		if (sMaskChar == "#") {
			sOutput += sTextChar;
			iTextPos += 1;
		}
		else
			sOutput += sMaskChar;

		iMaskPos += 1;
		if (iTextPos == sText.length)
			break;
	}
	return(sOutput)
}

function FormatPhone(ctl) {

	sPhone = spaceTrim(ctl.value);

	if (sPhone == "")
		return true;

	sPhone = stripNonNumeric(sPhone);

	if (sPhone.charAt(0) == "1") 
		sPhone = sPhone.substring(1, sPhone.length);

	if (sPhone.length < 10) {
		alert("Please confirm that the phone number you entered is valid.");
		setFocus(ctl);
		return false;
	}
	else {
		sMask = "(###) ###-#### x#####";
		ctl.value = Mask(sPhone, sMask);
		return true;
    }
}

function FormatPhoneAlpha(ctl) {

	sPhone = spaceTrim(ctl.value)

	if (sPhone == "")
		return;

	sPhone = stripNonPhone(sPhone)

	if (sPhone.charAt(0) == "1") 
		sPhone = sPhone.substring(1, sPhone.length);

	if (sPhone.length < 10) {
		alert("Please confirm that the phone number you entered is valid.");
		setFocus(ctl);
		return false;
	}
	else {
		sMask = "###.###.#### ####################";
		ctl.value = Mask(sPhone, sMask);
		return true;
    }
}

function CheckFormatSSN(ctl) {
   if (ctl.value == "") {
	return;
   }
   else {
   	s = stripNonNumeric(ctl.value)

	if (s.length != 9) {
		if (s.length != 0) {	
			alert(ctl.value + " is not a valid Social Security Number!")
			}
		ctl.select();
		ctl.value = s;
		setFocus(ctl);
		return false;
		} else {
		sMask = "###-##-####"
		ctl.value = Mask(s, sMask)
		return true;
		}
	}
}

function countDifferent(str){
	var s,c,i;
	s=c="";
	for(i=0;i<str.length;i++)if (s.indexOf(c=str.charAt(i).toUpperCase())==-1) s+=c;
	return (s.length);
}

function CheckValidPassword(ctl) {return CheckPasswordValid(ctl,6)}

function CheckPasswordValid(ctl,len) {
  ctl.value=spaceTrim(ctl.value);
  if (ctl.value.length < len) {
    alert("Password must be at least "+len+" characters, contain both letters and numbers or symbols, and cannot begin or end with spaces.");
    setFocus(ctl);
    ctl.value="";
    ctl.select();
    return false;
  } else if ((stripNonNumeric(ctl.value)==ctl.value) || (stripNonNumeric(ctl.value).length==0)) {
	alert("Password must contain both letters and numbers or symbols.");
    setFocus(ctl);
    ctl.value="";	
    ctl.select();
    return false;
  } else if (countDifferent(ctl.value)<2) {
	alert("Passwords cannot be a single repeated character.");
	setFocus(ctl);
	ctl.select();
	return false;
  } else if (stripSpaces(top.document.title).toUpperCase().indexOf(ctl.value.toUpperCase())!=-1){
	alert("This password is not allowed. Please pick another one.");
	setFocus(ctl);
	ctl.select();
	return false;
  } else return true;
}

function LoadPage(frame, URL) {
  form = top.Process0.document.forms[0];
  
  if (frame == "")
   form.target = "DisplayFrame"
  else
	if (frame != "Process0" && frame != "KeepAlive")
		form.target = frame
	else
		form.target = "Process1"

  
  form.action = top.Process0.document.forms[1].action;
   if (navigator.appName == "Netscape") 
		form.action = URL
	else
		form.action = "../" + URL
  form.action = form.action.replace(/common\/common/i, "common")
  if (URL.indexOf("common") == -1)
	form.action = form.action.replace(/common\//i, "")
  	
  form.method = "POST";
  form.submit();

}


//Change password without requesting old password
function changePasswd(len) {
	for (var i=0; i<2; i++){
		if(document.forms[i].elements[0].value == ""){
			document.forms[i].elements[0].focus();
			return false;
		}

		if (!CheckPasswordValid(document.forms[i].elements[0],len)){
			document.forms[i].elements[0].focus();
			return false;
		}
	}
	
	if (document.forms[0].elements[0].value != document.forms[1].elements[0].value) {
		alert("The new and confirm passwords do not match.  Please re-type them.");
		document.forms[1].elements[0].focus();
                document.forms[1].elements[0].value ="";
		return false;
	}
	
	document.forms[0].elements[1].value = document.forms[1].elements[0].value;
	return true;
}


//Change password by requesting old password
function changeOldPassword(len){
	var newp,cnewp,oldp;
	oldp=document.forms[0].txtOldPassword;
	newp=document.forms[1].txtNewPassword;
	cnewp=document.forms[2].txtConfirmNP;
	if(oldp.value == ""){
		oldp.focus();
		return false;
	}

	if(oldp.value == newp.value){
	    alert("Your new password must be different from old password.");
            newp.focus();
            newp.value="";
	    cnewp.value="";	
            return false;}   
	if (cnewp.value != newp.value) {
		alert("The new and confirm passwords do not match.  Please re-type them.");
		newp.value="";
		cnewp.value="";
		newp.focus();
		return false;
	}

	if(newp.value == ""){
		newp.focus();
		return false;
	}

	if (!CheckPasswordValid(newp,len)){
		return false;
	}

	if(cnewp.value == ""){
		cnewp.focus();
                cnewp.value="";
		return false;
	}


	document.forms[0].txtNewPassword.value = newp.value;
	document.forms[0].txtConfirmNP.value = cnewp.value;
	
	document.forms[1].txtOldPassword.value = oldp.value;
	document.forms[1].txtConfirmNP.value = cnewp.value;
	
	document.forms[2].txtOldPassword.value = oldp.value;
	document.forms[2].txtNewPassword.value = newp.value;
	//alert("Your password has been changed.");
	return true;
}


//Check the validation of the date
function dateValidCheck(dArray, range){
	mdcy = getCurrentDate();
	m=dArray[0]; d=dArray[1]; c=dArray[2]; y=dArray[3];

	if((m<1) || (m>12) || (d<1) || (d>31) || (y<0) || (y>99))  return err=1;
	if (m==4 || m==6 || m==9 || m==11){
		if (d==31) return err=1;
	}
	if(m==2 && d>29){ return err=1; }
	if(c!=0){
		if(c!=19 && c !=20) return err=1;
		else {
			if(range==">="){
				if(dArray[4]<mdcy[4] || dArray[4]>(mdcy[4]+20000)) return err=1;
			}else if(range=="<=") {
				if(dArray[4]>mdcy[4] || dArray[4]<19200101) return err=1;
			}
		}
	}
	else{
		if(range==">="){
			if(dArray[4]<mdcy[4] || dArray[4]>(mdcy[4]+20000)) {
				if(dArray[5]<mdcy[4] || dArray[5]>(mdcy[4]+20000)) { return err=1; }
				else { c=20;}
			}else { 
				c=19; 
			}
		}else if(range=="<=") {
			if(dArray[4]>mdcy[4] || dArray[4]<19100101) {
				if(dArray[5]>mdcy[4] || dArray[5]<19100101) { return err=1; }
				else { c=20; }
			}else { c=19; }
		}else {	
			if(dArray[4]<=mdcy[4]+200000-1000000)//20 year window
					c=mdcy[2];
				else
					c=19;
		}
	}
	
	if(m==2){
		if(d==29){
			cy = c*100+y*1;
			if((cy/100) == parseInt(cy/100)){
				if((cy/400) != parseInt(cy/400)) return err=1;
			}else {
				if((y/4)!=parseInt(y/4)) return err=1;
			}
		}
	}
	return dStr = m + "/" + d + "/" + c + y;
}

function stod(s) {
	ms=s.indexOf("/",0);
	ds=s.indexOf("/",ms+1);
	month=s.slice(0,ms);
	day=s.slice(ms+1,ds);
	year=s.slice(ds+1);
	return new Date(year,month-1,day);
	}

function dtos(){
	return(new String(this.getMonth()+1+"/"+this.getDate()+"/"+this.getFullYear()));//return 'literal' string
}
Date.prototype.toString=dtos;

function getCurrentDate(){
	var mdcy = new Array(5);     //for current date
	today = new Date();
//	mdcy = [today.getMonth()+1, today.getDate(), 19, today.getYear(), 0];
	today = today.toString()
	dt = today.split('/')
	mdcy = [dt[0], dt[1], 19,dt[2], 0];
	if(mdcy[3].length == 4){
		mdcy[2] = mdcy[3].toString().substr(0,2);
		mdcy[3] = mdcy[3].toString().substr(2,2);
	}
	mdcy[4] = mdcy[2]*1000000+mdcy[3]*10000+mdcy[0]*100+mdcy[1]*1;
//	mdcy[4] = mdcy[2]*1000000+mdcy[3]*10000+mdcy[0]*100+mdcy[1];
	return mdcy;
}


//Check the format of the date inputed by users
function dateFormatCheck(dStr,range){
	dStr = stripSpaces(dStr);
    if(dStr==""){ return err=1; }
	var dArray = new Array(6);  //for the date input by users
	separator = "";
	if (dStr.indexOf("/") != -1 ) { separator = "/"; }
	if (dStr.indexOf(".") != -1 ) { separator = "."; }
	if (dStr.indexOf("-") != -1 ) { separator = "-"; }
	if (dStr.indexOf(",") != -1 ) { separator = ","; }
	if(separator != ""){
		dArray = dStr.split(separator,3);
		if(dArray.length != 3) { return err=1; }
		if ((isNaN(dArray[0])) || (isNaN(dArray[1])) || (isNaN(dArray[2]))) {return err=1;}
		else { 
			if(dArray[2].length==2) {
				dArray[3]=dArray[2];
				dArray[2]=0;
			}
			else if(dArray[2].length==4){
				dArray[3] = dArray[2].toString().substr(2,2);
				dArray[2] = dArray[2].toString().substr(0,2);
			}
			else { 
				return err=1;
			}
		}
	}
	else 
	{
		dStr = stripNonNumeric(dStr);
		switch (dStr.length)
		{
			case 4 :
				dArray = [dStr.substr(0,1),dStr.substr(1,1),0,dStr.substr(2,2),0,0];
				break;
			case 5 :
				dArray = [dStr.substr(0,1),dStr.substr(1,2),0,dStr.substr(3,2),0,0];
				break;
			case 6 :
				dArray = [dStr.substr(0,2),dStr.substr(2,2),0,dStr.substr(4,2),0,0];
				break;
			case 7 :
				dArray = [dStr.substr(0,1),dStr.substr(1,2),dStr.substr(3,2),dStr.substr(5,2),0,0];
				break;
			case 8 :
				dArray = [dStr.substr(0,2),dStr.substr(2,2),dStr.substr(4,2),dStr.substr(6,2),0,0];
				break;
			default :
				return err=1;
		}		
	}
	if(dArray[2]==0){
		dArray[4] = 19000000+dArray[3]*10000+dArray[0]*100+dArray[1]*1;
		dArray[5] = 20000000+dArray[3]*10000+dArray[0]*100+dArray[1]*1;
	}else {
		dArray[4] = dArray[2]*1000000+dArray[3]*10000+dArray[0]*100+dArray[1]*1;
		dArray[5] = dArray[4];
	}
		
 	return dateValidCheck(dArray,range);
}

function ddiff(d1,d2) {
	return ((stod(d2)-stod(d1)) / (1000 * 60 * 60 * 24));
}
function CheckEmailAddress(ctl) {
	if (/^[a-z0-9][\w\.]*@([a-z0-9]([a-z0-9\-]*[a-z0-9])?\.)+[a-z]{2,3}$/i.test(ctl.value)) {
		return true;
		} else {
		alert('Please enter a valid E-mail address.');
		setFocus(ctl);
		return false;
		}
}

function CheckZipOnAddress(ctl)
{
	if (ctl.value != "") return btnZip_onClick(ctl);
	return true;
}

function getCheckDigit(sRNo)
{
	var sOneChar,Wt=0,Sum=0; 
	for(i=0;i<8;i++)
		{
			sOneChar=sRNo.slice(i,i+1);
			switch(i+1)
			{
			case 1:
			case 4:
			case 7:
				Wt = 3
				break;
			case 2:
			case 5:
			case 8:
				Wt = 7
				break;
			case 3:
			case 6:
				Wt = 1
				break;
			} 
			Sum+=parseInt(sOneChar)*Wt;
		}
	return parseInt(10-(Sum%10))%10;/*mod 10 of 10's complement of mod 10 val*/
}

function CheckRoutingNo(ctrl)
{
	var bValid=true,sRNo;
	ctrl.value=stripSpaces(ctrl.value);
	sRNo=ctrl.value;
	
	if(sRNo.length==0)
		return true;
	if(sRNo.length!=9)
		bValid=false;
	else
	{
		chkDigit=parseInt(sRNo.slice(8));
		sRNo=sRNo.slice(0,8);
		if(chkDigit!=getCheckDigit(sRNo))
			bValid=false;
	}
	
	if(!bValid)
	{
		alert("Invalid Transit Number");
		//ctrl.focus();
	  setFocus(ctrl);
	}
	return bValid;	
}

function ValidZIP(ctl){
	var val = spaceTrim(ctl.value.toUpperCase());
	if (val==""){
		alert("Zip Code is Required");
		setFocus(ctl);
		return false;
	} else if (/^[A-Z]\d[A-Z]\s?\d[A-Z]\d$/.test(val)) {
		ctl.value=val.replace(/^([A-Z]\d[A-Z])(\d[A-Z]\d)$/,"$1 $2");
		return true;
	} else if (/^\d{5}[\s\-]?\d{4}$/.test(val)) {
		ctl.value = val.replace(/^(\d{5})[\s\-]?(\d{4})$/,"$1-$2");
		return true;
	} else if (/^\d{5}$/.test(val)) {
		return true;
	} else {
		alert("Zip Code is incorrect.");
		setFocus(ctl);
		return false;
	}
}

var gorilla;

function setFocus(ctrl)
{
  gorilla=ctrl;
  setTimeout('gorilla.focus();',2);
}

Array.prototype.indexOf=arrindexOf;
function arrindexOf(item)
{
	for(i=0;i<this.length;i++)
		if(item==this[i])return i;
}

//local method defs stop working with this here

function GenericValidDate(ctl){
	var dStr;
	if(ctl.value =="" ){
		alert ("Date is a required field");
		setFocus(ctl);
		return false;
		} else {
		dStr = dateFormatCheck(ctl.value,"none");
		if(dStr==1) {
			alert("Please enter a valid date.");
			setFocus(ctl);
			return false;
			}else {
			ctl.value = dStr;
			return true;
			}
		}
	}

//date required

function ValidDateR(ctl){
	var dStr;
	if(ctl.value =="" ){
		alert ("Date is a required field");
		setFocus(ctl);
		return false;
		} else {
		dStr = dateFormatCheck(ctl.value,"none");
		if(dStr==1) {
			alert("Please enter a valid date.");
			setFocus(ctl);
			return false;
			}else {
			ctl.value = dStr;
			return true;
			}
		}
	}

//date not required

function ValidDateNR(ctl){
	var dStr;
	if(ctl.value =="" ){
		return true;
		} else {
		dStr = dateFormatCheck(ctl.value,"none");
		if(dStr==1) {
			alert("Please enter a valid date.");
			setFocus(ctl);
			return false;
			}else {
			ctl.value = dStr;
			return true;
			}
		}
	}

function CompareMMYYYY(Dt1,Dt2,Operator){
//	Compare the dates(dt1,dt2) depending on operator(>,<,=,<=,>=) passed. 
//	Dt1 and Dt2 should be in MM/YYYY format.


var ms1,yr1,ms2,yr2,a,b;
	a =Dt1.indexOf("/",0);
	ms1=Dt1.slice(0,a);
	yr1=Dt1.slice(a+1);
	a =Dt2.indexOf("/",0);
	ms2=Dt2.slice(0,a);
	yr2=Dt2.slice(a+1);
	a= yr1 + ms1
	b = yr2 + ms2
	if (Operator == ">") {
		if (a > b)
		return true;
	}
	if (Operator == "<") {
		if (a < b)
		return true;
	}
	if (Operator == "=") {
		if (a = b)
		return true;
	}
	if (Operator == ">=") {
		if (a >= b)
		return true;
	}
	if (Operator == "<=") {
		if (a <= b)
		return true;
	}
	return false;	
}



function CheckAddressChange(ctl){
if (document.forms[0].hdnflag[0]) {
	if (eval("ctl==document.forms[0]."+ctl.name+"[0]")){
	document.forms[0].hdnflag[0].value=1;
	}else{
	document.forms[0].hdnflag[1].value=1;
	}
}
else {
	document.forms[0].hdnflag.value=1;
}
return true;
}

function checkZip(ctlZip,Index){
	ctlZip.value=ctlZip.value.toUpperCase();
	if (navigator.appName == "Netscape")
		TargetFrame = window.name; // Netscape Specific
  	else
		TargetFrame = document.parentWindow.name; // MSIE Specific

	if (ctlZip.value != ""){
    	var bCanadianZip = false;
		if (/(^\d{5}([\s\-]?\d{4})?$)|(^[A-Z]\d[A-Z]\s?\d[A-Z]\d$)/.test(ctlZip.value)){
			bCanadianZip = /^[A-Z]\d[A-Z]\s?\d[A-Z]\d$/.test(ctlZip.value);
			if (bCanadianZip) ctlZip.value=ctlZip.value.replace(/^([A-Z]\d[A-Z])(\d[A-Z]\d)$/,"$1 $2");
			} else {
			alert("Zip Code is incorrect.\nUS Zipcodes are 5 or 9 digits.\nCanadian Postal Codes are 7 characters (A#A #A#)");
			setFocus(ctlZip);
			return false;
			}
		GetZip(Index, ctlZip.value, TargetFrame,bCanadianZip);
    	}
	return true;
	}

function btnZip_onClick(ctl) {
	var form = document.forms[0];
  	if (form.btnZip == ctl) {
  		Index = "";
    	ctlZip = form.txtZip;
  		}
  		else 
  			if (form.btnZip[0] == ctl) {
    			Index = 0;
    			ctlZip = form.txtZip[0];
  				} else {
				Index = 1;
    			ctlZip = form.txtZip[1];
  				}
	return checkZip(ctlZip,Index);
}

function txtZip_onChange(ctl) {
	var form = document.forms[0];    
	if (form.txtZip != null){
		if (form.txtZip == ctl) {
		Index = ""
		ctlZip = form.txtZip
		ctlCity = form.txtCity
  		} else if (form.txtZip[0] == ctl) {
			Index = 0;
			ctlZip = form.txtZip[0];
			ctlCity = form.txtCity[0];
			} else {
			Index = 1;
			ctlZip = form.txtZip[1];
			ctlCity = form.txtCity[1];
  			}
  		} else {
		Index = -1;
		ctlZip = ctl;
		}
	return checkZip(ctlZip,Index);
}

function GetZip(Index, Zip, TargetFrame, bCanadianZip) {
   LoadPage("Process1", 'common/GetCityState.asp?Index=' + Index + '&Zip=' + escape(Zip) + '&TargetFrame=' + TargetFrame+ '&cz='+bCanadianZip)
}

function CancelGetZip() {   LoadPage("Process1", "common/BlankProcess1.asp") }

function SetFocusToZip(ctl) {
form = document.forms[0]
if (ctl == form.txtAddress2)
  ctlZip = form.txtZip
else
  if (ctl == form.txtAddress2[0])
    ctlZip = form.txtZip[0]
  else
    ctlZip = form.txtZip[1]
setFocus(ctlZip)
}

function focusEmpty(ctl,message) {
	var val;
	switch(ctl.type)
		{
		case "select-one" :
			val = spaceTrim(ctl[ctl.selectedIndex].value);
			break;
		case "password" :
		case "text" :
		case "textarea" :
			val = spaceTrim(ctl.value);
			break;
		case "radio":
			if (!(ctl[0].checked) && !(ctl[1].checked))	val = ""; else val =" ";
			break;
		default :
			val =" ";
			break;
		}
	if (val.length>0) {
		return false;
		} else {
		alert(message);
		setFocus(ctl);
		return true;
		}
	}
function focusTooShort(ctl,len,message) {
	ctl.value = spaceTrim(ctl.value)
	if (ctl.value.length==0 || ctl.value.length>=len) {
		return false;
		} else {
		alert(message+' must be at least '+len+' characters.');
		setFocus(ctl);
		return true;
		}
	}
function imgGiveUp(){
	return;
	}
function imgReload(img, src){
	if ((img.tryCount=(img.tryCount?img.tryCount:0)+1)>=3) { //give up after third attempt
		img.onerror=imgGiveUp;
		// alert('Failed to load '+img.src);
		} else { //reload
		var w=img.width;
		var h=img.height;
		var buf=new Image(w,h);
		buf.src=src;
		img.src=buf.src;
		}
	return;
	}

function GenericCheckForm(frm){
	var i, j, k, nRad;
	nRad=0
	for (i=0;i<frm.length;i++){
		if(frm[i].type=='radio')
			nRad = nRad + 1
	}			
	for (i=0;i<frm.length;i++)	
		switch (frm[i].type){
			case "text":
			case "textarea":
			case "select-one":
				if (frm[i].onchange)
					if (!frm[i].onchange())
						return false;
				break;
			case "radio":
				var cname = frm[i].name;
				var anychecked=false;
				if(nRad>1){
					for (k=0;k<frm[cname].length; k++) if (frm[cname][k].checked) {anychecked=true;break;}
				}else
					if(frm[cname].checked)
						anychecked=true
				if (!anychecked) {
					alert ('Please make a selection.');
					setFocus(frm[i]);
					return false;
					break;
					}											
				break;
			}
	return true;
	}
