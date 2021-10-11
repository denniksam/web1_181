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
    // console.log(pid);
    /* В момент нажатия разрешить редактирование описания
       Поменять картинку кнопки на "V", добавить кнопку "Х" */

    const container = e.target.parentNode ;
    const descr = container.querySelector("p");

    if(typeof descr.savedText == 'undefined'){
        // первое нажатие - edit
        // разрешить редактирование описания
        descr.setAttribute( "contenteditable", "true");
        descr.focus();
        // сохранить исходный текст (перед редактированием)
        descr.savedText = descr.innerText;

        // Поменять картинку кнопки на "V"
        e.target.style["background-position"] = "50% 50%" ;

        // добавить кнопку "Х"
        const cancelBtn = document.createElement("div");
        cancelBtn.className = "tool-button";
        cancelBtn.style["background-position"] = "50% 0" ;
        cancelBtn.onclick = () => {
            // восстанавливаем сохраненный текст (отменяем изменения)
            descr.innerText = descr.savedText;
            delete descr.savedText;

            container.removeChild( cancelBtn ) ;
            descr.removeAttribute("contenteditable");
            e.target.style["background-position"] = "0 0" ;
        };
        container.appendChild(cancelBtn);
        container.cancelBtnRef = cancelBtn;
    } else {
        // второе нажатие - save
        descr.removeAttribute("contenteditable");
        e.target.style["background-position"] = "0 0" ;
        delete descr.savedText;
        container.removeChild( container.cancelBtnRef ) ;
        delete container.cancelBtnRef;

        // console.log({id: pid, description: descr.innerText });
        fetch(window.location.href,{
            method: "PUT",
            body: JSON.stringify({id: pid, description: descr.innerText }),
            headers:{
                "Content-Type": "application/json"
            }
        }).then(r => r.text()).then(console.log);
    }


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