let clickCheckbox = document.getElementsByClassName('checkbox');
for (let i = 0; i < clickCheckbox.length; i++) {
    clickCheckbox[i].addEventListener('click', function() {
        this.form.submit();
        console.log(window.location.href);
    })
}

$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})


let url = new URL(window.location.href);
let page = Number(url.searchParams.get('page'));
if (page <= 0) {
    document.getElementById('first-page-button').classList.remove("btn-outline-primary");
    document.getElementById('first-page-button').classList.add("btn-outline-secondary");
    document.getElementById('first-page-button').disabled = true;
    document.getElementById('previous-page-button').classList.remove("btn-outline-primary");
    document.getElementById('previous-page-button').classList.add("btn-outline-secondary");
    document.getElementById('previous-page-button').disabled = true;
}

if (page >= Number(document.getElementById('number-of-pages').value)) {
    document.getElementById('next-page-button').classList.remove("btn-outline-primary");
    document.getElementById('next-page-button').classList.add("btn-outline-secondary");
    document.getElementById('next-page-button').disabled = true;
    document.getElementById('last-page-button').classList.remove("btn-outline-primary");
    document.getElementById('last-page-button').classList.add("btn-outline-secondary");
    document.getElementById('last-page-button').disabled = true;
}


function previousPage() {
    document.getElementById('previous-page').value = page-1;
}

function nextPage() {
    document.getElementById('next-page').value = page+1;
}
