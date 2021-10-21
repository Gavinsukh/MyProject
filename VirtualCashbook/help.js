var layer = document.getElementById("hiddenlayer");
var button = document.getElementById("open");
var upbuttonLayer = document.getElementById("openbut");
var downbutton = document.getElementById("closebut");
var button2 = document.getElementById("close");

button.onclick= function(){
    layer.style.display="block";
    upbuttonLayer.style.display="none";
    downbutton.style.display="block";
}

button2.onclick=function(){
    layer.style.display="none";
    upbuttonLayer.style.display="block";
    downbutton.style.display="none";
}
