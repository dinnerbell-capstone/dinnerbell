{
    "use strict";
    const logoutLink = document.querySelector('#logout-link');
    const logoutForm = document.querySelector('#logout-form');
    logoutLink.addEventListener('click', e => {
        e.preventDefault();
        logoutForm.submit();
    });
}