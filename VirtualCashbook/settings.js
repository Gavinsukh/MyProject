var layer2 = document.getElementById("Layer2")
var okay = document.getElementById("okay2");
var save = document.getElementById("saveds");
localStorage.setItem("mode","WhiteMode");

var blackBut = document.getElementById("BlackButton");
var whiteBut = document.getElementById("WhiteButton");

okay.onclick=function(){
    layer2.style.display="none";
}

save.onclick=function(){
    layer2.style.display="block";
}

function ActivateDarkMode(){
    var Key = localStorage.getItem("mode");
    if(Key == "WhiteMode"){
        // alert("berhasil1");
        localStorage.setItem("mode","DarkMode");
        document.body.style.background= "black";
        document.body.style.color="white";
        blackBut.style.display="none";
        whiteBut.style.display="block";
    }

    if(Key == "DarkMode"){
        // alert("berhasil");
        localStorage.setItem("mode","WhiteMode");
        document.body.style.background="rgb(235, 232, 232)";
        document.body.style.color = "black";
        blackBut.style.display="block";
        whiteBut.style.display="none";
    }
    
}

