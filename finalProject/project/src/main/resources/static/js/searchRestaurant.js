var searchInput = document.getElementById("SearchResByName");
var searchResElement = document.getElementById("searchRes");

// Thêm event listener cho sự kiện click
searchResElement.addEventListener("click", function() {
    var inputValue = searchInput.value;

    fetch('/Restaurant/searchRestaurant', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ query:inputValue  }) // Truyền dữ liệu trong một đối tượng JSON
    })
        .then(response => response.json())
        .then(data => {
            const Container = document.getElementById('res-container');
            Container.innerHTML = '';
            data.forEach(Res => {
                const Element = document.createElement('div');
                Element.className = 'card mb-3 mt-3 col-lg-4 col-md-6 col-12';
                Element.innerHTML = `
 
      <img src="${Res.logo_Url}" class="card-img-top" alt="...">
      <div class="card-body">
        <h5  class="card-title">${Res.name}</h5>
        <p  class="card-text">${Res.address}</p>
        <a href="#" class="btn btn-primary">Food Now</a>
      </div>
    
 `;
                Container.appendChild(Element);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
});