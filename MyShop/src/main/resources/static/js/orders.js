/* ----------------Order Page---------------- */
function updateOrderQuantity(calType){
    const quantityString = document.getElementById("quantity-box").value;
    const quantityStockStr = document.getElementById("quantity-remain").innerText;
    let quantity = parseInt(quantityString);
    if(calType === 0){
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
    document.getElementById("quantity-box").value = quantity;
}

function confirmDeleteItemInCart(id){
    document.getElementById("confirm-modal-content").innerText = "Xác nhận xoá sản phẩm này?";
    document.getElementById("btn-confirm-modal").href = window.location.origin + "/cart/item/delete/" + id;
    openModal("confirmModal");
}