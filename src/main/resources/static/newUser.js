//==================================  new user add    ========================================
window.onload = function() {
    let addNewUserButton = document.getElementById("addNewUserButton");

    addNewUserButton.addEventListener('click', () => {

        let name = document.getElementById("newUserFormName").value;
        let lastName = document.getElementById("newUserFormLastName").value;
        let password = document.getElementById("newUserFormPassword").value;
        let role = document.getElementById("newUserFormRole").value;

        fetch(`http://localhost:8089/api/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Cache-Control': 'no-cache, no-store, must-revalidate'
            },
            body: JSON.stringify({
                name: name,
                lastName: lastName,
                password: password,
                role: role
            })
        }).then(response => {
            if (response.ok) {
                console.log('Пользователь добавлен');
                location.reload();  // Обновление страницы
            } else {
                console.error('Ошибка добавления пользователя');
            }
        })
        alert("Пользователь добавлен")
    });
}
