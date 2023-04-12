//открыть модальное окно DELETE=====================================================
    function openDelModal(id) {
        let modalDel = document.getElementById("modalDelWindow");
        modalDel.style.display = "block";

        const fromTableBtnDelete = document.createElement("fromTableBtnDelete_" + id);

        fetch("http://localhost:8089/api/users/" + id)
            .then(response => response.json())
            .then(dataDelForm => showMeDelUser(dataDelForm))
            .catch(error => console.log(error))

        function showMeDelUser(dataDelForm) {
            const delModalForm = document.getElementById("delModalForm");
            document.getElementById("delModalId").value = dataDelForm.id;
            document.getElementById("delModalName").value = dataDelForm.username;
            document.getElementById("delModalLastName").value = dataDelForm.lastName;
            document.getElementById("delModalRole").value = dataDelForm.roleToString;
        }
    }

//===========================удаляем user======================================
    const deleteUserButton = document.getElementById('deleteUserButton')

    deleteUserButton.addEventListener('click', () => {
        const userId = document.getElementById("delModalId").value;
        fetch(`http://localhost:8089/api/users/${userId}`, {
            method: 'DELETE'
        }).then(response => {
            if (response.ok) {
                console.log('Пользователь удален');
            } else {
                console.error('Ошибка удаления пользователя');
            }
        }).catch(error => {
            console.error('Ошибка удаления пользователя:', error);
        });
    });

