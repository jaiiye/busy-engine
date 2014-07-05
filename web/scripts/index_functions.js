function goto(){
	var commSelect = document.frmsearch.searchType;
	var commIndex = commSelect.selectedIndex;
	if (commSelect.options[commIndex].value != '') {
		location = commSelect.options[commIndex].value;
	}
}

function popUp2(URL) {
	day = new Date();
	id = day.getTime();
	eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=600,height=500,left = 0,top = 0');");
}

function Search(){
	var varSearch = document.search.txtSearch.value
	if (varSearch == 0)  {
		alert('Search criteria can not be empty!');
		document.search.txtSearch.focus();
		return;
	}else{
		document.search.submit()
	}
}

function Email(){
	var varSearch = document.email.txtemail.value
	if (varSearch == 0)  {
		alert('Email can not be empty!');
		document.email.txtemail.focus();
		return;
	}else{
		document.email.submit()
	}
}

function emptyBox(){
document.email.txtemail.value = "";
}

function CloseAll() {
	document.getElementById('two').style.visibility = 'hidden';
	document.getElementById('three').style.visibility = 'hidden';
	document.getElementById('four').style.visibility = 'hidden';
}

function CloseTwo() {
	document.getElementById('two').style.visibility = 'hidden';
}

function CloseThree() {
	document.getElementById('three').style.visibility = 'hidden';
}

function CloseFour() {
	document.getElementById('four').style.visibility = 'hidden';
}

function popSize(URL) {
	day = new Date();
	id = day.getTime();
	eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=490,height=410,left = 0,top = 0');");
}

function designerList() {
	CloseAll();
	document.getElementById('two').style.visibility = "";
}

function CategoryList() {
	CloseAll();
	document.getElementById('three').style.visibility = "";
}

function buzz() {
	CloseAll();
	document.getElementById('four').style.visibility = "";
}

function emptyBox(){
document.email.txtemail.value = "";
}

function designerListFP() {
			document.getElementById('five').style.visibility = "";
			}
			
			
function CloseFive() {
				document.getElementById('five').style.visibility = 'hidden';
			}	