//Загружаем всех===============================================================================
fetch("http://localhost:8089/api/users")
    .then( response => response.json() )
    .then( data => showMeUsers(data) )
    .catch( error => console.log(error))

let table = document.getElementById("myTable").getElementsByTagName('tbody')[0];
const showMeUsers = (data) => {
    for (let i = 0; i < data.length; i++) {
        let row = table.insertRow(i);
        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        let cell4 = row.insertCell(3);
        let cell5 = row.insertCell(4);
        let cell6 = row.insertCell(5);
        cell1.innerHTML = data[i].id;
        cell2.innerHTML = data[i].username;
        cell3.innerHTML = data[i].lastName;
        cell4.innerHTML = data[i].roleToString;
        cell5.innerHTML = `<button type="button" id="fromTableBtnEdit_${data[i].id}" onclick="openEditModal(${data[i].id})" value="${data[i].id}" data-bs-toggle="modal" class="btn btn-primary">Edit</button>`
        cell6.innerHTML = `<button type="button" id="fromTableBtnDelete_${data[i].id}" onclick="openDelModal(${data[i].id})" value="${data[i].id}" data-bs-toggle="modal" class="btn btn-primary btn-danger">Delete</button>`
    }
}
//закрыть модальное окно DELETE==================================================
function closeDelModal() {
    let modal = document.getElementById("modalDelWindow");
    modal.style.display = "none";
}
//закрыть модальное edit============================================================
function closeEditModal() {
    let modal = document.getElementById("modalEditWindow");
    modal.style.display = "none";
}


