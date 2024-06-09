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

function addToCart(id) {
    const quantityStr = document.getElementById("quantity-number").innerText;
    console.log(id);
    console.log(quantityStr);
    const apiUrl = window.location.origin + "/api/cart/add?productId=" + id + "&quantity=" + quantityStr;
    fetch(apiUrl)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then((data) => {
            document.getElementById("quantity-item-in-cart").innerText = data.totalProductQuantity;
            console.log(data);
            showListItemInCart(data.cart);
        })
        .catch((error) => {
            console.error("There was a problem with the fetch operation:", error);
        });
}

function showListItemInCart(items){
    let content = "";
    items.forEach(item => {
        content += `<div class="cart-view-scroll">
                                    <table class="table-clone">
                                        <tbody class="table-body-clone">
                                        <tr class="mini-cart-hidden">
                                            <td class="mini-cart-left">
                                                <img src="/image/products/${item.image}" alt="...">
                                            </td>
                                            <td class="mini-cart-right">
                                                <div class="mini-cart-title">
                                                    <p>${item.itemName}</p>
                                                </div>
                                                <div class="mini-cart-detail">
                                                    <span class="mini-cart-quantity">${item.quantity}</span>
                                                    <span>${formatCurrency(item.totalPrice)}</span><sup>đ</sup>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>`;
    });
    document.querySelector("#items").innerHTML = content;
}

function formatCurrency(number) {
    let reversedNumber = number.toString().split('').reverse().join('');
    let reversedFormattedNumber = reversedNumber.replace(/(\d{3})(?=\d)/g, '$1.');
    return reversedFormattedNumber.split('').reverse().join('');
}