$(document).ready(function () {
    $('.btnPlus').click(function () {
        var foodId = $(this).attr('data-id');
        var quantityInput = $('#quantity-' + foodId);
        var newQuantity = parseInt(quantityInput.val()) + 1;
        var price = parseFloat($("#price-" + foodId).text())

        $.ajax({
            type: 'POST',
            url: '/Order/updateQuantity/' + foodId + '/' + newQuantity + '?price=' + price,
            success: function (response) {
                // Update the quantity and total on the client side
                quantityInput.val(newQuantity);
                $("#total-amount-" + foodId).text(price * newQuantity)
                console.log(response)
                updateTotalPrice()
            },
            error: function (error) {
                console.error('Error updating quantity: ', error);
            }
        });
    });

    $('.btnMinus').click(function () {
        var foodId = $(this).attr('data-id');
        var quantityInput = $('#quantity-' + foodId);
        var price = parseFloat($("#price-" + foodId).text())
        console.log(price)
        console.log(quantityInput)
        var newQuantity = parseInt(quantityInput.val()) - 1;
        console.log(newQuantity)

        // Make an AJAX request to update the quantity on the server
        $.ajax({
            type: 'POST',
            url: '/Order/updateQuantity/' + foodId + '/' + newQuantity + '?price=' + price,
            success: function (response) {
                // Update the quantity and total on the client side
                quantityInput.val(newQuantity);
                console.log(price * newQuantity)
                $("#total-amount-" + foodId).text(price * newQuantity)
                console.log(response)
                updateTotalPrice()
            },
            error: function (error) {
                console.error('Error updating quantity: ', error);
            }
        });
    });

    $('.btnRemove').click(function () {
        var foodId = $(this).attr('data-id');
        $.ajax({
            type: 'POST',
            url: '/Order/deleteCartItem/' + foodId,
            success: function (response) {
                $("#row-" + foodId).remove()
                updateTotalPrice()
            },
            error: function (err) {
                console.error(err)
            }
        })
    })
});

function updateTotalPrice() {
    var totalPrice = 0;

    // Loop through each row and calculate the total price
    $('[id^="row-"]').each(function () {
        var foodId = $(this).attr('id').split('-')[1];
        var quantity = parseInt($('#quantity-' + foodId).val());
        var price = parseFloat($('#price-' + foodId).text());
        totalPrice += quantity * price;
    });

    // Update the total price for all items
    $(".totalPriceCart").text(totalPrice);
}
