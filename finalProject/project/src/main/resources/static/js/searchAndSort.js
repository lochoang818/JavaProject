const radioButtons = document.querySelectorAll('input[type="radio"]');

radioButtons.forEach(radio => {
    radio.addEventListener('change', function () {
        if (radio.checked) {
            const minPrice = parseFloat(radio.getAttribute('data-min-price'));
            const maxPrice = parseFloat(radio.getAttribute('data-max-price'));
            const cateId = parseInt(radio.getAttribute('data-cate-id'));

            searchFoodByPrice(cateId, minPrice, maxPrice);
        }
    });
});
function searchFoodByPrice(cateId, minPrice, maxPrice) {
    fetch('/Home/searchByPrice', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ cateId: cateId, minPrice:minPrice,maxPrice:maxPrice }) // Truyền dữ liệu trong một đối tượng JSON
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            const productsContainer = document.getElementById('food-container');
            productsContainer.innerHTML = '';

            // Hiển thị danh sách sản phẩm
            data.forEach(food => {
                const productElement = document.createElement('div');
                productElement.className = 'col-lg-4 col-md-6 col-12';
                productElement.innerHTML = `
                <div class="single-product">
								<div class="product-img">
									<a href="product-details.html">
										<img class="default-img" src="${food.imageUrl}" alt="#">
										<img class="hover-img" src="${food.imageUrl}" alt="#">
									</a>
									<div class="button-head">

										<div class="product-action-2">
											<a title="Add to cart" href="#">Add to cart</a>
										</div>
									</div>
								</div>
								<div class="product-content">
									<h3><a href="product-details.html">${food.name}</a></h3>
									<div class="product-price">
										<span>${food.price}$</span>
									</div>
								</div>
							</div>
            `;
                productsContainer.appendChild(productElement);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}