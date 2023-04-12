let usId = document.getElementById('userIdSpan').getAttribute('data-userId');

    fetch('http://localhost:8089/api/users/' + usId,{
        method: 'GET'
    }).then(response => response.json())
      .then(data1 => showMeUser(data1))
      .catch(error => console.log(error))

    let tabl = document.getElementById("userTabl").getElementsByTagName('tbody')[0];
    function showMeUser(data1) {
        let row2 = tabl.insertRow(0);
        let cell22 = row2.insertCell(0);
        let cell23 = row2.insertCell(1);
        let cell24 = row2.insertCell(2);
        let cell25 = row2.insertCell(3);

        cell22.innerHTML = data1.id;
        cell23.innerHTML = data1.username;
        cell24.innerHTML = data1.lastName;
        cell25.innerHTML = data1.roleToString;
    }
