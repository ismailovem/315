//==================================  new user add    ========================================
window.onload = function() {
    let addNewUserButton = document.getElementById("addNewUserButton");

    addNewUserButton.addEventListener('click', () => {

        let name = document.getElementById("newUserFormName").value;
        let lastName = document.getElementById("newUserFormLastName").value;
        let password = document.getElementById("newUserFormPassword").value;
        let roles = [];
        let select = document.getElementById("newUserFormRole");
        for (let option of select.selectedOptions) {
            roles.push(option.value);
        }
        class User {
            constructor(name, lastName, password) {
                this.name = name;
                this.lastName = lastName;
                this.password = password;
            }

            create() {
                fetch(`http://localhost:8089/api/users/?roles=${roles}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Cache-Control': 'no-cache, no-store, must-revalidate'
                    },
                    body: JSON.stringify({
                        username: name,
                        lastName: lastName,
                        password: password
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
            }
        }
        const user = new User(name, lastName, password);
        user.create();
    });
}
