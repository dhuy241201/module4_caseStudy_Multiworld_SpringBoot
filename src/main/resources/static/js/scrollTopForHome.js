
let mybutton = document.getElementById("btn-back-to-top");

window.onscroll = function() {
    scrollFunction();
};

function scrollFunction() {
    if (
        document.body.scrollTop > 20 ||
        document.documentElement.scrollTop > 20
    ) {
        mybutton.style.display = "block";
    } else {
        mybutton.style.display = "none";
    }

    if (
        document.body.scrollTop + 20 > document.body.scrollHeight - 1000 ||
        document.documentElement.scrollTop + 20 > document.documentElement.scrollHeight - 1000
    ){
        mybutton.style.position = "absolute";
        mybutton.style.bottom = "0px";
    }else {
        mybutton.style.position = "fixed";
        mybutton.style.bottom = "40px";
    }
    // console.log(document.documentElement.scrollTop)
    // console.log(document.documentElement.scrollHeight)
    // console.log(document.documentElement.scrollHeight - 1000)
    // console.log(document.documentElement.scrollTop + 20 > document.documentElement.scrollHeight - 1000)
}

mybutton.addEventListener("click", backToTop);
function backToTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}