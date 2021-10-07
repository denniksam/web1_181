document.addEventListener("DOMContentLoaded",()=>{
    for( let btn of document.querySelectorAll(".picture button") ) {
        btn.addEventListener("click",deleteClick);
    }
});

function deleteClick(e) {
    const tt = e.target.parentNode.querySelector("tt");
    if( ! tt) throw "tt not found in parent node";
    const pid = tt.innerHTML;
    if(confirm("Таки удалять?")){
        fetch("?id="+pid,{method:"delete"})
            .then(r => r.json())
            .then(j => {
                console.log(j);
            });
    }
}