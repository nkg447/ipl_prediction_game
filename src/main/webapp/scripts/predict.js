function fetchQuestions() {
  axios.post("questions").then(response => {
    if (response.data.status == "SUCCESS") {
      var form = document.getElementsByClassName("form")[0];
      var questions = response.data.data.questions;

      for (var i = 0; i < questions.length; i++) {
        var div = document.createElement("div");
        div.setAttribute("class", "qdiv");

        var select = document.createElement("select");
        select.setAttribute("id", "question_" + questions[i].id);
        select.setAttribute("class", "question");

        var options = questions[i].options;

        for (var j = 0; j < options.length; j++) {
          var option = document.createElement("option");
          option.value = options[j];
          option.innerHTML = options[j];

          select.appendChild(option);
        }

        div.innerHTML = questions[i].question;
        div.appendChild(select);
        form.insertBefore(div, form.children[form.children.length - 1]);
      }
    }
  });
}

function savePrediction() {
  var questions = document.getElementsByClassName("question");
  var jsonData = [];

  for (var i = 0; i < questions.length; i++) {
    var qid = questions[i].id.split("question_")[1];
    jsonData[i] = {
      id: +qid,
      answer: questions[i].value
    };
  }

  axios.post("/prediction", jsonData).then(response => {
    console.log(response);

    if (response.data.status == "SUCCESS") {
      window.location.replace("/success.html");
    }
  });
}
