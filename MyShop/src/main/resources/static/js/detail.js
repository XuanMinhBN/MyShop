/* ----------------Detail Page---------------- */
function updateQuantity(btnType){
    const quantityStr = document.getElementById("quantity-number").innerText;
    const quantityStockStr = document.getElementById("quantity-stock").innerText;
    let quantity = parseInt(quantityStr);
    if(btnType === 0){
        if(quantity > 0){
            quantity--;
        }else {
            document.getElementById("toast-error-content").innerText = "Product quantity cannot less than 0";
            showToast("toast-error")
        }
    }else{
        if(quantity < parseInt(quantityStockStr)){
            quantity++;
        }else {
            document.getElementById("toast-error-content").innerText = "Product quantity cannot more than " + quantityStockStr;
            showToast("toast-error");
        }
    }
    document.getElementById("quantity-number").innerText = quantity;
}

function addToCart() {
    fetch(window.location.origin + "/cart/test")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then((data) => {
            console.log(data);
        })
        .catch((error) => {
            console.error("There was a problem with the fetch operation:", error);
        });
}