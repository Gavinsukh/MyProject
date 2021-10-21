
var layer2 = document.getElementById("Layer2");
var regisbutton = document.getElementById("RegisterButton");
var backbutton = document.getElementById("backButton1");
var accountsignin = document.getElementById("ACCOUNTSIGNIN");


var layer3 = document.getElementById("Layer3");
var backbutton2 = document.getElementById("backButton2");
var signinbutton = document.getElementById("SIGNINBUTTON");
var accountsignup = document.getElementById("ACCOUNTSIGNUP");

var layer4 = document.getElementById("Layer4");
var upgradebutton = document.getElementById("UPGRADEBUTTON");
var backButton3 = document.getElementById("backButton3");


var layer5 = document.getElementById("Layer5");
var backButton4 = document.getElementById("backButton4");
var forgotbutton = document.getElementById("FORGOTPASSWORD");
var resetsuccess = document.getElementById("resetsuccess");

var layer6 = document.getElementById("Layer6");
var okay = document.getElementById("okay");

//dari sign up -> sign in

accountsignin.onclick=function(){
    layer2.style.display="none";
    layer3.style.display="block";
}

//dari sign in -> sign up

accountsignup.onclick=function(){
    layer3.style.display="none";
    layer2.style.display="block";
}

//display block

regisbutton.onclick = function(){
    layer2.style.display = "block";
}

signinbutton.onclick = function(){
    layer3.style.display="block";
}

upgradebutton.onclick=function(){
    layer4.style.display="block";
}

forgotbutton.onclick=function(){
    layer3.style.display="none";
    layer5.style.display="block";
}

resetsuccess.onclick=function(){
    layer5.style.display="none";
    layer6.style.display="block";
}


//display none

backbutton.onclick= function(){
    layer2.style.display="none";
}

backbutton2.onclick = function(){
    layer3.style.display="none";
}

backButton3.onclick = function(){
    layer4.style.display="none";
}

backButton4.onclick=function(){
    layer5.style.display="none";
}

okay.onclick=function () {
    layer6.style.display="none";
}


