var modal = document.getElementById('quoteSelection');

// Get the button that opens the modal
var btn = document.getElementById("addQuote");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

var addTextBtn = document.getElementById("addText");
var textInput = '<div class="row"> <div class="col-md-6"> <div class="form-group"> <label class="bmd-label-floating">Texto</label> <textarea type="text"class="form-control"/> </div> </div> </div>'

var deleteQuote = '<span id="'
var deleteQuoteX = 'Button"> Quitar cita<button type="button" onclick="delQuote('
var deleteQuoteY = ');" class="btn btn-white btn-round btn-just-icon"><i class="material-icons">cancel_presentation</i></button></span>'

var buttonAdd = '<span class="pull-right" id="'
var buttonAddX = 'AddButton">Añadir cita<button type="submit" onclick="addQuote('
var buttonAddY = ');" class="btn btn-white btn-round btn-just-icon"><i class="material-icons">library_add</i></button></span>'

var textInput = '<div class="row"> <div class="col-md-6"> <div class="form-group"> <label class="bmd-label-floating">Titulo Tema</label> <input name="texts" type="text"class="form-control"> </div> </div> </div>'
// When the user clicks on the button, open the modal
var id;
var delQuote = "deleteQuote/";
var delTopic = "deleteTopic/";

btn.onclick = function () {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

addTextBtn.onclick = function () {
  $(".topicForm").append(textInput);
  formHTML();
}


function addQuote(id) {
  var quotes = document.getElementById(id);
  quotes.id = id + ".Quote";

  $(".topicForm").append(quotes);
  $(".topicForm").append(deleteQuote + id + deleteQuoteX + id + deleteQuoteY);
  $("#" + id + "AddButton").remove();
  formHTML();
  modal.style.display = "none";
}

function delQuote(id) {

  var quotes = document.getElementById(id + ".Quote");
  quotes.id = id;

  $("#quoteSelectionDiv").append(quotes);
  $("#quoteSelectionDiv").append(buttonAdd + id + buttonAddX + id + buttonAddY);

  $("#" + id + ".Quote").remove();
  $("#" + id + "Button").remove();
}

function deleteButton(id) {
  var estaSeguro = confirm("¿Estas seguro?");
  if (estaSeguro == true) {
    window.location.replace(delQuote + id);
  }
}

function deleteTopic(id) {
  var estaSeguro = confirm("¿Estas seguro?");
  if (estaSeguro == true) {
    window.location.replace(delTopic + id);
  }
}

function formHTML(){
  var form = "" + getElementById("topicForm");
  $("#htmlSection").replaceWith(form);
}
