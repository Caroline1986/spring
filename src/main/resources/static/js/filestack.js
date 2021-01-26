
console.log("hello");
const client = filestack.init(filestackAPIKey);
console.log(client);
const options = {
    'onFileUploadFinished': callback =>{
        const imgURL = callback.url;
        $('#image').val(imgURL);
        $('#imagePreview').attr('src',imgURL);
    }
}

$('#addPicture').click(function (event){
    event.preventDefault();
    client.picker(options).open();
})
