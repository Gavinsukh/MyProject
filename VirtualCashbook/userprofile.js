// layer 2

var layer2 = document.getElementById("Layer2");
var okaybutton = document.getElementById("okay2");
var save = document.getElementById("saved");

okaybutton.onclick = function(){
    layer2.style.display="none";
}

save.onclick = function () {
    layer2.style.display="block";
}

// content
// UI
var viewUI = document.getElementById("viewUI");
var userinfobutton = document.getElementById("UIbutton");
var viewUpgrade = document.getElementById("viewUpgrade");
var upgradebutton = document.getElementById("UPbutton");
var subsbutton = document.getElementById("SUBS");
var viewSUBS = document.getElementById("viewSubs");
var backbuton = document.getElementById("BEKBUTON");
var paybutton = document.getElementById("paybuton");
var box5 = document.getElementById("boxkelima");

userinfobutton.onclick = function(){
    viewUI.style.display="block";
    viewUpgrade.style.display="none";
    viewSUBS.style.display="none";
    box5.style.display="none";
}

upgradebutton.onclick=function(){
    viewUpgrade.style.display="block";
    viewUI.style.display="none";
    viewSUBS.style.display="none";
    box5.style.display="none";
}

subsbutton.onclick=function(){
    viewUpgrade.style.display="none";
    viewSUBS.style.display="block";
    box5.style.display="none";
}

backbuton.onclick=function(){
    nutup();
    box5.style.display="none";
}

paybutton.onclick=function(){
    viewUpgrade.style.display="none";
    viewUI.style.display="none";
    viewSUBS.style.display="none";
    box5.style.display="block";
}

function nutup(){
    viewUpgrade.style.display="block";
    viewUI.style.display="none";
    viewSUBS.style.display="none";
}
