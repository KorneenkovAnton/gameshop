function previewFunc() {
    console.log("test");
    var file = document.getElementById("imgInput").files[0];
    const reader = new FileReader();

    reader.onload =  function (e) {
        document.getElementById("preview").setAttribute('src' , e.target.result);
    };

    reader.readAsDataURL(file);
}
