/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
// Функция активации редактирования
function enableEditing() {
    var userInfInputs = document.querySelectorAll('.userInf');
    var editButton = document.getElementById('editBtn');
    var saveButton = document.getElementById('saveBtn');
    var userImgUrlInput = document.getElementById('userImgUrl');

    // Разрешаем редактирование
    userInfInputs.forEach(function (input) {
        input.readOnly = false;
        input.style.border = '1px solid #ccc'; // Показываем рамки
    });
    userImgUrlInput.style.display = 'inline';
    userImgUrlInput.value = "";
    // Переключаем видимость кнопок
    editButton.style.display = 'none';
    saveButton.style.display = 'inline';
}

// Функция сохранения изменений
function saveChanges() {
    var userInfInputs = document.querySelectorAll('.userInf');
    var editButton = document.getElementById('editBtn');
    var saveButton = document.getElementById('saveBtn');
    var userImgUrlInput = document.getElementById('userImgUrl');
    userImgUrlInput.style.display = 'none';
    // Запрещаем редактирование
    userInfInputs.forEach(function (input) {
        input.readOnly = true;
        input.style.border = 'none'; // Скрываем рамки
    });

    // Переключаем видимость кнопок
    editButton.style.display = 'inline';
    saveButton.style.display = 'none';
}

document.addEventListener('DOMContentLoaded', function () {
    var menuListBtn = document.getElementById('menuList');
    var submenuList = document.getElementById('submenuList');

    menuListBtn.addEventListener('click', function () {
        submenuList.classList.toggle('d-none');
    });
});

function profileIsEmpty() {
    alert("Чтобы оставить отзыв, вы должны заполнить данные в профиле!");
    
}

document.addEventListener('DOMContentLoaded', function() {
    // Получаем ссылки на все чекбоксы и кнопку отправки формы
    var checkboxes = document.querySelectorAll('input[name="category"]');
    var submitButton = document.getElementById('submitButton');

    // Функция, которая будет проверять, нужно ли разблокировать кнопку
    function checkIfButtonShouldBeEnabled() {
        var atLeastOneChecked = Array.from(checkboxes).some(function(checkbox) {
            return checkbox.checked;
        });
        // Если хотя бы один чекбокс выбран, разблокируем кнопку отправки формы, иначе блокируем
        submitButton.disabled = !atLeastOneChecked;
    }

    // Добавляем обработчик события изменения состояния каждого чекбокса
    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('change', checkIfButtonShouldBeEnabled);
    });

    // Проверяем состояние чекбоксов при загрузке страницы
    checkIfButtonShouldBeEnabled();
});
