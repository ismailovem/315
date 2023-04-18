fetch('http://localhost:8089/api/user/',{
    method: 'GET'
}).then(response => response.json())
    .then(data2 => showPrincipal(data2))
    .catch(error => console.log(error))
function showPrincipal(data2) {
    let principalName =  document.getElementById("principalName");
    let principalRole =  document.getElementById("principalRole");
    principalName.innerHTML = data2.username;
    principalRole.innerHTML = data2.roleToString;
}

fetch('http://localhost:8089/api/admin/',{
    method: 'GET'
}).then(response => response.json())
    .then(data2 => showPrincipal(data2))
    .catch(error => console.log(error))
function showPrincipal(data2) {
    let principalName =  document.getElementById("principalName");
    let principalRole =  document.getElementById("principalRole");
    principalName.innerHTML = data2.username;
    principalRole.innerHTML = data2.roleToString;
}