

    // Thu thập dữ liệu từ các trường
  
var checkoutButton = document.getElementById('checkoutButton');

// Thêm sự kiện click vào nút
checkoutButton.addEventListener('click', function (event) {
    // Ngăn chặn hành vi mặc định của nút (tránh chuyển hướng hoặc gửi yêu cầu form)
    event.preventDefault();
    const name = document.querySelector('input[name="name"]').value;
    const address = document.querySelector('input[name="address"]').value;
    const email = document.querySelector('input[name="email"]').value;
    const phone = document.querySelector('input[name="phone"]').value;

    // Để lấy total price từ view, bạn có thể sử dụng JavaScript để lấy nó
    const totalPriceElement = document.querySelector('.order-details .last span');
    const totalPrice = totalPriceElement.textContent;

    // Tạo đối tượng chứa dữ liệu để gửi lên máy chủ
    const data = {
        Name: name,
        Email: email,
        Phone: phone,

        Address: address,
        totalPrice: totalPrice
    };
    fetch('/Order/completeCheckout', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data) 
    }).then(response => response.json())
        .then(data => {
            console.log(data)
        })
        .catch(error => {
            console.error('Lỗi: ', error);
        });
});

