var modal = document.getElementById('quoteSelection');

// Get the button that opens the modal
var btn = document.getElementById("addQuote");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

var addTextBtn = document.getElementById("addText");
var textInput = '<div class="row"> <div class="col-md-6"> <div class="form-group"> <label class="bmd-label-floating">Texto</label> <textarea type="text"class="form-control"/> </div> </div> </div>'

var deleteQuote = '<span id="'
var deleteQuoteX = 'Button">Quitar cita<button type="button" onclick="delQuote('
var deleteQuoteY = ')" class="btn btn-white btn-round btn-just-icon"><i class="material-icons">cancel_presentation</i></button></span>'

var buttonAdd = '<span class="pull-right" id="'
var buttonAddX = 'AddButton">Añadir cita<button type="submit" onclick="addQuote('
var buttonAddY = ');" class="btn btn-white btn-round btn-just-icon"><i class="material-icons">library_add</i></button></span>'

var textInput = '<div class="row"> <div class="col-md-6"> <div class="form-group"> <label class="bmd-label-floating">Titulo Tema</label> <input name="texts" type="text"class="form-control"> </div> </div> </div>'
// When the user clicks on the button, open the modal
var id;
var delTopic = "deleteTopic/";
var deleteQuote = "deleteQuote/";

var currentPage;



//Used to load more images
var appendQuote = '<div class="col-lg-6 col-md-6 col-sm-6 "> <div class="card card-stats"> <div class="card-header card-header-warning card-header-icon"> <div class="card-icon"> <i class="material-icons">speaker_notes</i> </div> <p class="card-category">Cita</p> <div class="tim-typo"> <blockquote class="blockquote">{{#logged}} <a href="quote/'
var appendQuote12 = '">{{/logged}} <p style="color: black">';
var appendQuote2 = '</p> <small style="color: grey"> ';
var appendQuote3 = '</small>{{#logged}}</a>{{/logged}} ' +
  '</blockquote>{{#admin}} <div class="td-actions text-left"> <a href="quote/{{id}}"><button type="button" rel="tooltip" title="Editar cita" class="btn btn-primary btn-link btn-sm"> ' +
  '<i class="material-icons">edit</i> </button> </a> <a> <button type="button" onclick="deleteButton({{id}});" rel="tooltip"title="Borrar Cita"class="btn btn-danger btn-link btn-sm btn-delete"> ' +
  '<i class="material-icons">close</i> </button> </a> </div>{{/admin}} </div> </div> </div> </div>';


function addOne(quote) {
  $('#quotes').append(appendQuote);
}

$(document).ready(function () {
  currentPage = 1;
});

var loadBtn = document.getElementById("loadMore");


function displayModal() {
  modal.style.display = "block";
}

function closeOnX() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    closeOnX();
  }
}

function addText() {
  $(".topicForm").append(textInput);
  //formHTML();
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
    window.location.replace(deleteQuote + id);
  }
}

function deleteTopic(id) {
  var estaSeguro = confirm("¿Estas seguro?");
  if (estaSeguro == true) {
    window.location.replace(delTopic + id);
  }
}

function formHTML() {
  var form = '' + getElementById("topicForm");
  $("#htmlSection").replaceWith(form);
}


loadBtn.onclick = function () {


      console.log("loadQuotes");


  $.ajax({
    method: "GET",
    url: "/" + currentPage
  }).done(function (quotes) {
    for (var i = 0; i < quotes.length; i++) {
      console.log("loadNotes");
      console.log(quotes[i]);
      $(".quotes").append(Mustache.render(appendQuote + quotes[i].id +appendQuote12 + quotes[i].text + appendQuote2 + quotes[i].author + ' ,' + quotes[i].book + appendQuote3));
    }
    currentPage++;
  }).fail(function(){
    console.log("Failed to load: "+ currentPage);
  })


}



