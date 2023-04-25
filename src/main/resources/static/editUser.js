//================================= get data from form Edit   =====================================================
    function openEditModal(id) {
        let modalEdit = document.getElementById("modalEditWindow");
        modalEdit.style.display = "block";

        const fromTableBtnEdit = document.createElement("fromTableBtnEdit_" + id);

        fetch("http://localhost:8089/api/users/" + id)
            .then(response => response.json())
            .then(dataEditForm => showMeEditUser(dataEditForm))
            .catch(error => console.log(error))

        function showMeEditUser(dataEditForm) {
            const editModalForm = document.getElementById("editModalForm");
            document.getElementById("editModalId").value = dataEditForm.id;
            document.getElementById("editModalName").value = dataEditForm.username;
            document.getElementById("editModalLastName").value = dataEditForm.lastName;
            document.getElementById("editModalRole").value = dataEditForm.roleToString;
        }

    }

//================================edit user after pushing button ==================================================
    const editUserButton = document.getElementById('editUserButton');

    editUserButton.addEventListener('click', () => {
        let userId = document.getElementById("editModalId").value;
        let userName = document.getElementById("editModalName").value;
        let userLastName = document.getElementById("editModalLastName").value;
        let userPass = document.getElementById("editModalPass").value;
        //let userRole = document.getElementById("editModalRole").value;
        let roles = [];
        let select = document.getElementById("editModalRole");
        for (let option of select.selectedOptions) {
            roles.push(option.value);
        }

        class User {
            constructor(userName, userLastName, userPass) {
                this.userName = userName;
                this.userLastName = userLastName;
                this.userPass = userPass;
            }

            update() {
                fetch(`http://localhost:8089/api/users/?id=${userId}&roles=${roles}`, {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json',
                        'Cache-Control': 'no-cache, no-store, must-revalidate'
                    },
                    body: JSON.stringify({
                        username: this.userName,
                        lastName: this.userLastName,
                        password: this.userPass,
                    })
                }).then(response => {
                    if (response.ok) {
                        console.log('Пользователь изменен');
                    } else {
                        console.error('Ошибка изменения пользователя');
                    }
                }).catch(error => {
                    console.error('Ошибка изменения пользователя: ', error);
                })
                alert("Пользователь изменен")
            }
        }
        const user = new User(userName, userLastName, userPass);
        user.update();

});



