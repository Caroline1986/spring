"use strict";


const itemList = document.getElementById('item-list')
const cartQty = document.getElementById('cart-qty')
const cartTotal = document.getElementById('cart-total')
const addForm = document.getElementById('add-form')
const itemName = document.getElementById('item-name')
const itemPrice = document.getElementById('item-price')

const cart = [];

//add form submit
addForm.onsubmit = function (e) {
    e.preventDefault()
    const name = itemName.value()
    const price = itemPrice.value()
    addItem(name, price)
}

//Add Item
function addItem(name, price) {
    for (let i = 0; i < cart.length; i += 1) {
        if (cart[i].name === name) {
            cart[i].qty += 1
            showItems()
            return
        }
    }
    const item = {name, price, qty: 1}
    cart.push(item)
    showItems()
}

function showItems() {
    const qty = getQty();

    console.log(`You have ${qty} items in your cart.`)
    cartQty.innerHTML = `You have ${qty} items in your cart.`

    let itemStr = ''
    for (let i = 0; i < cart.length; i += 1) {
        // console.log(`${cart[i].name} $${cart[i].price} * ${cart[i].qty}`)
        // const name = cart[i].name
        // const price = cart[i].price
        // const qty = cart[i].qty

        const {name, price, qty} = cart[i]

        itemStr += `<li>
            ${name} 
            $${price} * ${qty} =
            ${qty * price}
            </li>`
    }
    itemList.innerHTML = itemStr

    // console.log(`Total in cart: $${getTotal()}`)
    cartTotal.innerHTML = `Total in cart: $${getTotal()}`
}

function getQty() {
    let qty = 0
    for (let i = 0; i < cart.length; i += 1) {
        qty += cart [i].qty;
    }
    return qty
}

function getTotal() {
    let total = 0 //store running total
    for (let i = 0; i < cart.length; i += 1) {
        total += cart[i].price * cart[i].qty
    }
    return total.toFixed(2)
}

function removeItem(name, qty = 0) {
    for (let i = 0; i < cart.length; i += 1) {
        if (cart[i].name === name) {
            if (qty > 0) {
                cart[i].qty -= qty
            }
            if (cart[i].qty < 1 || qty === 0) {
                cart.splice(i, 1)
            }
            return
        }
    }
}

addItem('apple', 0.99)
addItem('apple', 0.99)
addItem('pizza', 4.99)
// showItems()
removeItem('pizza')
removeItem('apple', 1)
showItems()