
// let searchName= document.getElementById("search-name");



function loaddataHouse(number) {
    let pageable = document.getElementById("pageable")
    $.ajax({
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token')
        },
        type: "GET",
        url:"http://localhost:8081/api/houses?page="+number,
        success: function (list) {
            loadtable(list.content);
            let str = ""
            let num =list.pageable.pageNumber
            console.log(num)
            if(num > 0 || num+1 === list.totalPages) {
                str += `<button onclick="loaddataHouse(${num-1})">Trước</button>`
            }
            str += `${num+1}/${list.totalPages}`
            if(num <= 0 || num+1 !== list.totalPages) {
                str += `<button onclick="loaddataHouse(${num+1})">Sau</button>`
            }
            pageable.innerHTML = str
        }
    });
}

function loadtable(list){
    let house=document.getElementById("list");
    let str= "";
    for(let i=0;i<list.length; i++){
        console.log(list[i].name)
        str+=`<tr>
                    <th scope="row">${i+1}</th>
                    <td>${list[i].name}</td>
                    <td>${list[i].address}</td>
                    <td>${list[i].numberOfBedrooms}</td>
                    <td>${list[i].numberOfBathrooms}</td>
                    <td>${list[i].price}</td>
                    <td><img src="image/${list[i].img}" width="100px" height="50px"></td>

                 </tr>`
    }
    house.innerHTML=str;
}

function showAddForm(){
    $('#exampleModal').modal('show');
}
function save(){
    let addForm=document.getElementById("addForm");
    let formData = new FormData(addForm);
    $.ajax({
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token')
        },
        enctype: "multipart/form-data",
        type: "POST",
        url: "http://localhost:8081/api/houses",
        data: formData,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function () {
            loaddataHouse()
            $('#exampleModal').modal('hide');
            addForm.reset();
        },
        error: function (error) {
            console.log(error)
        }
    })
}
