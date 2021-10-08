document.addEventListener("DOMContentLoaded",()=>{
    for( let btn of document.querySelectorAll(".picture .tool-delete") ) {
        btn.addEventListener("click",deleteClick);
    }
    for( let btn of document.querySelectorAll(".picture .tool-download") ) {
        btn.addEventListener("click",downloadClick);
    }
    for( let btn of document.querySelectorAll(".picture .tool-edit") ) {
        btn.addEventListener("click",editClick);
    }
});

function editClick(e) {
    const pid = findPictureId(e);
    console.log(pid);
    /* В момент нажатия разрешить редактирование описания
       Поменять картинку кнопки на "V", добавить кнопку "Х" */

    // разрешить редактирование описания
    const container = e.target.parentNode ;
    const descr = container.querySelector("p");
    descr.setAttribute( "contenteditable", "true");
    descr.focus();

    // Поменять картинку кнопки на "V"
    e.target.style["background-position"] = "50% 50%" ;

    // добавить кнопку "Х"
    const cancelBtn = document.createElement("div");
    cancelBtn.className = "tool-button";
    cancelBtn.style["background-position"] = "50% 0" ;
    cancelBtn.onclick = () => {
        container.removeChild( cancelBtn ) ;
        descr.removeAttribute("contenteditable");
        e.target.style["background-position"] = "0 0" ;
    };
    container.appendChild(cancelBtn);
}

function deleteClick(e) {
    const pid = findPictureId(e);
    if(confirm("Таки удалять?")){
        fetch("?id="+pid,{method:"delete"})
            .then(r => r.json())
            .then(j => {
                console.log(j);
            });
    }
}

function downloadClick(e) {
    const pid = findPictureId(e);
    // console.log(pid);
    window.location = "download/" + pid;
}

function findPictureId(e) {
    const tt = e.target.parentNode.querySelector("tt");
    if( ! tt) throw "tt not found in parent node";
    return tt.innerHTML;
}