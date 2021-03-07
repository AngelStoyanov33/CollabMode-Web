// Disable right click
document.addEventListener('contextmenu', event => event.preventDefault());

//Disable F12
document.onkeypress = function(event) {
    event = (event || window.event);
    if (event.keyCode == 123) {
        return false;
    }
}
document.onmousedown = function(event) {
    event = (event || window.event);

    if (event.keyCode == 123) {
        return false;
    }
}
document.onkeydown = function(event) {
    event = (event || window.event);
    if (event.keyCode == 123) {
        return false;
    }
}