var layer = document.getElementById("Layer2");
var back = document.getElementById("backButton4");
var share = document.getElementById("Share");
var share2 = document.getElementById("SHARED");

back.onclick=function(){
    layer.style.display="none";
}
share.onclick=function(){
    layer.style.display="none";
}

share2.onclick=function(){
    layer.style.display="block";
}


var layer2 = document.getElementById("Layer3");
var back2 = document.getElementById("backButton5");
var addNutup = document.getElementById("Add");
var addBuka = document.getElementById("ADD");

addBuka.onclick=function(){
    layer2.style.display="block";
}

back2.onclick=function(){
    layer2.style.display="none";
}

addNutup.onclick=function(){
    layer2.style.display="none";
}

// EDIT

var layer3 = document.getElementById("EDITLAYER1");
var layer4 = document.getElementById("EDITLAYER2");
var editBuka = document.getElementById("EDITBUKA");
var editTutup = document.getElementById("EDITTUTUP");

editBuka.onclick=function(){
    layer4.style.display="block";
    layer3.style.display="none";
}

editTutup.onclick=function(){
    layer4.style.display="none";
    layer3.style.display="block";
}

var downloadButton = document.getElementById("download");

downloadButton.onclick=function(){
    alert("Download Has Started")
}