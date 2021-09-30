function getClick() {
    fetch("api/hello")
    .then(r => r.text())
    .then(t => {
        const p = document
            .getElementById("server-result");
        p.innerText = t;
    });
}

function postClick() {
    fetch("api/hello",{
        method: 'post'
    })
        .then(r => r.text())
        .then(t=>{
            const p = document
                .getElementById("server-result");
            p.innerText = t;
        });
}

function putClick() {
    fetch("api/hello",{
        method: 'put'
    })
        .then(r => r.text())
        .then(t=>{
            const p = document
                .getElementById("server-result");
            p.innerText = t;
        });
}

function deleteClick() {
    fetch("api/hello",{
        method: 'delete'
    })
        .then(r => r.text())
        .then(t=>{
            const p = document
                .getElementById("server-result");
            p.innerText = t;
        });
}