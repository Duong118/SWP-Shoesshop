const items = document.querySelectorAll('#item');
const menu = document.getElementById('menu');
const icon = document.getElementById('icon-xoay');
const listMenu = document.getElementById('list-menu');
menu.addEventListener('click', () => {
    listMenu.classList.toggle('show-swp');
    icon.classList.toggle('show-swp-icon');
});

const listCateOpts = document.querySelectorAll('.catenameopt');
const cateType = document.querySelector('#catetype');
let cateTypeSelected = cateType.selectedOptions[0].value;
if (cateTypeSelected === 'Chim' || cateTypeSelected === 'Lồng') {
    listCateOpts.forEach(node => {
        if (!node.value.includes(cateTypeSelected)) {
            node.style.setProperty('display', 'none');
        } else {
            node.style.setProperty('display', 'block');
        }
    });
} else if (cateTypeSelected === 'Thức ăn') {
    listCateOpts.forEach(node => {
        if (node.value.includes('Cám') || node.value.includes('Giun') || node.value.includes('Côn trùng')) {
            node.style.setProperty('display', 'block');
        } else {
            node.style.setProperty('display', 'none');
        }
    });
} else if (cateTypeSelected === 'Phụ kiện khác') {
    listCateOpts.forEach(node => {
        if (node.value.includes('Cám') || node.value.includes('Chim') || node.value.includes('Lồng') || node.value.includes('Giun') || node.value.includes('Côn trùng')) {
            node.style.setProperty('display', 'none');
        } else {
            node.style.setProperty('display', 'block');
        }
    });
}

cateType.addEventListener('change', () => {
    let cateTypeSelected = cateType.selectedOptions[0].value;
    if (cateTypeSelected === 'Chim' || cateTypeSelected === 'Lồng') {
        listCateOpts.forEach(node => {
            if (!node.value.includes(cateTypeSelected)) {
                node.style.setProperty('display', 'none');
            } else {
                node.style.setProperty('display', 'block');
            }
        });
    } else if (cateTypeSelected === 'Thức ăn') {
        listCateOpts.forEach(node => {
            if (node.value.includes('Cám') || node.value.includes('Giun') || node.value.includes('Côn trùng')) {
                node.style.setProperty('display', 'block');
            } else {
                node.style.setProperty('display', 'none');
            }
        });
    } else if (cateTypeSelected === 'Phụ kiện khác') {
        listCateOpts.forEach(node => {
            if (node.value.includes('Cám') || node.value.includes('Chim') || node.value.includes('Lồng') || node.value.includes('Giun') || node.value.includes('Côn trùng')) {
                node.style.setProperty('display', 'none');
            } else {
                node.style.setProperty('display', 'block');
            }
        });
    }
});
function toggleSelect() {
    var catetype = document.getElementById("catetype");
    var categoryname = document.getElementById("categoryname");
    const selectCate = catetype.selectedOptions[0].value;
    document.getElementById("smallCategory").innerHTML = selectCate;
    if (selectCate !== "Chọn") {
        categoryname.style.visibility = "visible";
    } else {
        categoryname.style.visibility = "hidden";
    }
}

var catetype = document.getElementById("catetype");
catetype.addEventListener("change", toggleSelect);


document.getElementById("catetype").addEventListener("change", function () {
    document.getElementById("catename").value = document.getElementById("catename").options[0].value;
});
function toggleInput() {
    var selectElement = document.getElementById("catename");
    var inputElement = document.getElementById("newcatename");
    const selectOpt = selectElement.selectedOptions[0].value;
    if (selectOpt === "Khác") {
        inputElement.style.visibility = "visible";
        inputElement.lastElementChild.setAttribute("required", '');
    } else {
        inputElement.style.visibility = "hidden";
        inputElement.lastElementChild.removeAttribute("required");
    }
}
var selectElement = document.getElementById("catename");
selectElement.addEventListener("change", toggleInput);

function myDiscount() {
    var checkBox = document.getElementById("dis");
    var text = document.getElementById("discountPrice");
    if (checkBox.checked === true) {
        text.style.display = "inline-block";
    } else {
        text.style.display = "none";
    }
}
var checkBox = document.getElementById("dis");
var text = document.getElementById("discountPrice");
if (checkBox.checked === true) {
    text.style.display = "inline-block";
}

function cateAlert() {
    var catetype = document.getElementById("catetype").value;
    var catename = document.getElementById("catename").value;
    if (catetype === "Chọn" || catename === "Chọn") {
        alert("Vui lòng chọn phân loại");
        return false;
    }
    return true;
}

function submitForm(cid2) {
    var form = document.getElementById("cateRecForm");
    form.submit();
            alert("Thành công");
}
