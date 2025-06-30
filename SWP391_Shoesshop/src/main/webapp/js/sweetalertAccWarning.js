/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.getElementById('swa').onclick =
        function () {
            swal({
                title: "Lưu ý!!!",
                text: "Bạn cần có tài khoản để sử dụng chức năng này!",
                icon: "warning",
                buttons: {
                    cancel: "Hủy",
                    login: {
                        text: "Đăng Nhập",
                        value: "login",
                    },
                    signup: {
                        text: "Đăng Ký",
                        value: "signup",
                    },
                },
            })
                    .then((login) => {
                        switch (login) {

                            case "login":
                                window.location = "login.jsp";
                                break;

                            case "signup":
                                window.location = "register.jsp";
                                break;
                        }
                    });
        };
