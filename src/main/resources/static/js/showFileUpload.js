function chooseFile(fileInput){
    if(fileInput.files && fileInput.files[0]){
        let reader = new FileReader();
        reader.readAsDataURL(fileInput.files[0]);
        reader.onload = function (e){
            $('#imageShow').attr('src',e.target.result);
        }
    }
}