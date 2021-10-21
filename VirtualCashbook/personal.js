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

var layer3 = document.getElementById("ds");
var layer4 = document.getElementById("ds2");
var editButton = document.getElementById("EditButton");
var editButtonTutup = document.getElementById("EditButton2")

editButton.onclick = function(){
    layer3.style.display="block";
    layer4.style.display="none";
}

editButtonTutup.onclick=function(){
    layer3.style.display="none";
    layer4.style.display="block";
}

var downloadButton = document.getElementById("download");

downloadButton.onclick=function(){
    alert("Download Has Started")
}