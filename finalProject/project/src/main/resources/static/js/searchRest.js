var searchInput = document.getElementById("SearchResByName");
var searchResElement = document.getElementById("searchRes");

// Thêm event listener cho sự kiện click
searchResElement.addEventListener("click", function() {
    var inputValue = searchInput.value;

    window.location.href = '/Restaurant/show?search=' + inputValue;

});