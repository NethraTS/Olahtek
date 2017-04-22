function numbersonly(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode
	if (unicode != 8) { 
		if (unicode < 48 || unicode > 57)
			return false
	}
}
function onlyAlphabets(e, t) {
	var len=t.value.length;
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }else if (e) {
            var charCode = e.which;
        }else {
        	return true;
        }
        if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) ||charCode==8||charCode==127 ||charCode==32){
            if(len==250){
        		if(charCode==8){
        			return true;	
        		}else{
        			alert('Only 25 characters allowed');
        		return false;
        		}	
        	}else{
            return true;
           }
         }else
            return false;
    }catch (err) {
        alert(err.Description);
    }
}
function onlyAlphabetsUsername(e, t) {
	var len = t.value.length;

	try {
		if (window.event) {
			var charCode = window.event.keyCode;
		} else if (e) {
			var charCode = e.which;
		} else {
			return true;
		}
		if ((charCode > 64 && charCode < 91)
				|| (charCode > 96 && charCode < 123) || charCode == 8
				|| charCode == 9 || charCode == 127 || charCode == 32) {
			return true;
		} else {
			return false;
		}
	}

	catch (err) {
		alert(err.Description);
	}
}
function onlyNumbers(e, t) {
	var len = t.value.length;
	try {
		if (window.event) {
			var charCode = window.event.keyCode;
		} else if (e) {
			var charCode = e.which;
		} else {
			return true;
		}
		if ((charCode >= 48 && charCode <= 57) || charCode == 8
				|| charCode == 127 || charCode == 46) {
			var parts = e.srcElement.value.split('.');
			if (parts.length > 1 && charCode == 46) {
				return false;
			} else {

				if (len == 250) {
					if (charCode == 8) {
						return true;
					} else {
						alert('Only 25 characters allowed');
						return false;
					}
				}

				else {
					return true;
				}
			}
		} else
			return false;

	} catch (err) {
		alert(err.Description);
	}
}

function onlyNumbersWithoutFloating(e, t) {
	var len = t.value.length;
	try {
		if (window.event) {
			var charCode = window.event.keyCode;
		} else if (e) {
			var charCode = e.which;
		} else {
			return true;
		}
		if ((charCode >= 48 && charCode <= 57) || charCode == 8
				|| charCode == 127) {
			{

				if (len == 250) {
					if (charCode == 8) {
						return true;
					} else {
						alert('Only 25 characters allowed');
						return false;
					}
				}

				else {
					return true;
				}
			}
		} else
			return false;

	} catch (err) {
		alert(err.Description);
	}
}

function onlyAlphaNumbers(e, t) {
	var len = t.value.length;
	try {
		if (window.event) {
			var charCode = window.event.keyCode;
		} else if (e) {
			var charCode = e.which;
		} else {
			return true;
		}
		if ((charCode > 64 && charCode < 91)
				|| (charCode > 96 && charCode < 123) || charCode == 8
				|| charCode == 127 || charCode == 32 || charCode == 95
				|| charCode == 45 || (charCode >= 48 && charCode <= 57)
				|| charCode == 40 || charCode == 41) {
			if (len == 250) {
				if (charCode == 8) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else
			return false;
	} catch (err) {
	}
}
function numbersonly(e){
    var unicode=e.charCode? e.charCode : e.keyCode
    if (unicode!=8){ //if the key isn't the backspace key (which we should allow)
        if (unicode<48||unicode>57) //if not a number
            return false //disable key press
    }
}

function isNumberKey(evt, obj) {

	   var charCode = (evt.which) ? evt.which : event.keyCode
	   var value = obj.value;
	   var dotcontains = value.indexOf(".") != -1;
	   if (dotcontains)
	       if (charCode == 46) return false;
	   if (charCode == 46) return true;
	   if (charCode > 31 && (charCode < 48 || charCode > 57))
	       return false;
	   return true;
	}
