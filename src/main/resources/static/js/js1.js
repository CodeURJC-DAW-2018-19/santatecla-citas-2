var modal = document.getElementById('quoteSelection');

// Get the button that opens the modal
var btn = document.getElementById("addQuote");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

var addTextBtn = document.getElementById("addText")

var textInput= '<div class="row"> <div class="col-md-6"> <div class="form-group"> <label class="bmd-label-floating">Titulo Tema</label> <input type="text"class="form-control"> </div> </div> </div>'
// When the user clicks on the button, open the modal
var id;
var delUri = "deleteQuote/";
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

addTextBtn.onclick=function () {
  $(".topicForm").append(textInput);
}
 function deleteButton(id) {
  var  estaSeguro = confirm("¿Estas seguro?");
  if (estaSeguro == true) {
    window.location.replace(delUri + id);
  }

}
