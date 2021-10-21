console.log('connect')

var flag = 0;
var validationFailed = false;
function checkValidation(){
    var nama = document.forms['reservationForm']['nama'].value;
    var angka = document.forms['reservationForm']['nomor'].value;
    var orang = document.forms['reservationForm']['orang'].value;
    var tanggal = document.forms['reservationForm']['tanggal'].value;
    var waktu = document.forms['reservationForm']['waktu'].value;
    var kupon = document.forms['reservationForm']['kupon'].value;
    var eM = document.getElementById('errorMessage');
    
    // alert(nama + angka + orang + tanggal + waktu + kupon)

    if(nama == "" && angka =="" && orang =="" && tanggal == "" && waktu ==""){
        eM.innerText = "-all column must be filled-"
        validationFailed = true;
    }
    else if(nama == ""){
        eM.innerText = "name must be filled"
        validationFailed = true;
    } 
    else if(nama.length < 3 || nama.length >=30){
        eM.innerText = "name must be greater than 3 characters and less than 30 characters"
        validationFailed = true;
    }
    else if(isNaN(angka)){
        eM.innerText = "phone number must be in numbers only"
        validationFailed = true;
    }
    else if(angka ==""){
        eM.innerText = "phone number must be filled"
        validationFailed = true;
    }
    else if(orang ==""){
        eM.innerText = "number of people must be filled"
        validationFailed = true;
    }
    else if(tanggal == ""){
        eM.innerText = "date must be filled"
        validationFailed = true;
    }
    else if(waktu ==""){
        eM.innerText = "time must be filled"
        validationFailed = true;
    }
    
    else{
        eM.innerText = "";
        // flag = 1;
        alert('Reservation Succeed');
        validationFailed = false;
    }
}

$("#Formid").submit(function (e) {
    // do your validation here ...
    if (validationFailed) {
       e.preventDefault();
       return false;
    }
 });