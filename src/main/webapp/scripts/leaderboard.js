function fetchLeaderBoard() {
  axios.post("leaderboard").then(response => {
    if (response.data.status == "SUCCESS") {
      var tbody = document.getElementById("leaderboard");
      var leaderboard = response.data.data.leaderboard;

      for (var i = 0; i < leaderboard.length; i++) {
        var tr = document.createElement("tr");

        var serialTd = document.createElement("td");
        serialTd.innerHTML = i + 1;

        var nameTd = document.createElement("td");
        nameTd.innerHTML = leaderboard[i].name;

        var scoreTd = document.createElement("td");
        scoreTd.innerHTML = leaderboard[i].score;

        tr.appendChild(serialTd);
        tr.appendChild(nameTd);
        tr.appendChild(scoreTd);

        tbody.appendChild(tr);
      }
    }
  });
}
